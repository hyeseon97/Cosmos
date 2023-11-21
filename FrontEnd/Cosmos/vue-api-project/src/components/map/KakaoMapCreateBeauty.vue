<template class="template-map">
  <div>
    <div id="map"></div>
    <div class="buttons">
      <button class="course-draw-button" @click="selectOverlay('POLYLINE')">경로 그리기</button>
      <button class="course-remove-button" @click="remove">경로 삭제</button>
      <button class="course-create-button" @click="regist">등록</button>
      <button class="bicycleroad-button" @click="road">자전거도로</button>
      <input type="text" v-model="keyword" @keyup.enter="searchkeyword">
    </div>
    <p id="result"></p>
  </div>
</template>

<script setup>
import { onMounted, ref, toRaw } from 'vue';
import { useCourseStore } from '@/stores/course'

const courseStore = useCourseStore();

let map = null;
let manager = null;

let positions = null;
let markers = [];
let markerPosition = null;
let marker = null;
let point = [];

const initMap = function () {
  let myCenter = new kakao.maps.LatLng(36.370841, 127.387943); //카카오본사
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }
  const container = document.getElementById('map');
  const options = {
    center: myCenter,
    level: 6,
  }; // 지도 객체를 등록합니다.
  map = new kakao.maps.Map(container, options);
  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


  // ########################################################################
  // DB에 등록되어있는 코스 지도에 표시하기

  courseStore.getCourseList()
    .then(() => {
      const courseList = courseStore.courseList;
      const lineList = [];
      courseList.forEach(course => {
        const line = []
        console.log(course.courseMap.length)
        for (var i = 0; i < course.courseMap.length / 2; i++) {
          console.log("i포문")
          line.push(new kakao.maps.LatLng(course.courseMap[i * 2], course.courseMap[i * 2 + 1]));
        }

        // 마커 이미지
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
          markerPosition = new kakao.maps.LatLng(course.courseMap[0], course.courseMap[1]); // 마커가 표시될 위치입니다

        // 마커를 생성합니다
        var dbmarker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage // 마커이미지 설정 
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        dbmarker.setMap(map);

        // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var content = '<div class="customoverlay">' +
          '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
          '    <span class="title">' + course.course_name + '</span>' +
          '  </a>' +
          '</div>';

        // 커스텀 오버레이가 표시될 위치입니다 
        var position = new kakao.maps.LatLng(course.courseMap[0], course.courseMap[1]);

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          map: map,
          position: position,
          content: content,
          yAnchor: 1
        });

        // customOverlay.setMap(map);

        // // 시작위치 마커 생성
        // const dbmarker = new kakao.maps.Marker({
        //   map: map,
        //   position: new kakao.maps.LatLng(course.courseMap[0], course.courseMap[1]),
        //   title: "위치"
        // });
        // const dbmarkers = [];
        // dbmarkers.push(dbmarker);
        // dbmarker.setMap(map);

        // const infowindow = new kakao.maps.InfoWindow({
        //   content: course.course_name
        // })

        // kakao.maps.event.addListener(dbmarker, 'mouseover', makeOverListener(map, dbmarker, infowindow));
        // kakao.maps.event.addListener(dbmarker, 'mouseout', makeOutListener(infowindow));

        // // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
        // function makeOverListener(map, marker, infowindow) {
        //   return function () {
        //     infowindow.open(map, marker);
        //   };
        // }

        // // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
        // function makeOutListener(infowindow) {
        //   return function () {
        //     infowindow.close();
        //   };
        // }

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(dbmarker, 'click', function () {
          // // 마커 위에 인포윈도우를 표시합니다
          // infowindow.open(map, dbmarker);
          console.log("클릭이벤트")
        });

        // 코스 표시
        lineList.push(line);
      });

      lineList.forEach(line => {
        var polyline = new kakao.maps.Polyline({
          path: line, // 선을 구성하는 좌표배열 입니다
          strokeWeight: 10, // 선의 두께 입니다
          strokeColor: '#FF8E9E', // 선의 색깔입니다
          strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid' // 선의 스타일입니다
        });

        // 지도에 선을 표시합니다 
        polyline.setMap(map);
      });


    })


  // ########################################################################



  // ########################################################################
  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places();
  const keyword = ref('')
  // 키워드로 장소를 검색합니다
  ps.keywordSearch(keyword.value, placesSearchCB);

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < data.length; i++) {
        displayMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    }
  }


  // ########################################################################


  kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;

    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';

    // 코스 좌표 배열에 클릭할때마다 하나씩 푸쉬
    point.push({ lat: latlng.getLat(), lng: latlng.getLng() });

    console.log("클릭할 때 마다 : ");
    console.log(manager);

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;

  });


  var drawOptions = { // Drawing Manager를 생성할 때 사용할 옵션입니다
    map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
    drawingMode: [ // drawing manager로 제공할 그리기 요소 모드입니다
      kakao.maps.drawing.OverlayType.POLYLINE,
    ],
    // 사용자에게 제공할 그리기 가이드 툴팁입니다
    // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
    guideTooltip: ['draw', 'drag', 'edit'],
    polylineOptions: { // 선 옵션입니다
      draggable: false, // 그린 후 드래그가 가능하도록 설정합니다
      removable: false, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
      editable: false, // 그린 후 수정할 수 있도록 설정합니다 
      strokeColor: '#FF8E9E', // 선 색
      strokeStyle: 'solid', // 선 스타일
      strokeWeight: 10, // 선 두께
      strokeOpacity: 0.8, // 선 투명도
      hintStrokeStyle: 'dash', // 그리중 마우스를 따라다니는 보조선의 선 스타일
      hintStrokeOpacity: 0.7  // 그리중 마우스를 따라다니는 보조선의 투명도
    }
  };

  // 위에 작성한 옵션으로 Drawing Manager를 생성합니다
  manager = new kakao.maps.drawing.DrawingManager(drawOptions);

  // ============ 다 그린 후에 시작지점 끝지점 마커 표시하기 ============
  manager.addListener('drawend', function (data) {


    marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(point[0].lat, point[0].lng),
      title: "시작"
    });
    markers.push(marker);
    marker.setMap(map);

    marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(point[point.length - 1].lat, point[point.length - 1].lng),
      title: "끝"
    });
    markers.push(marker);
    marker.setMap(map);


  });


};



// ============ 삭제 버튼 누르면 마커랑 선 지우고 배열데이터도 지우기 ============
const remove = function () {
  // 모든 오버레이 가져와서 그중 선만 가져오고 0번째꺼 삭제
  manager.remove(manager.getOverlays().polyline[0]);
  console.log("새버튼delete");
  // 삭제할때
  // 여태까지 저장했던 포인트들의 위도경도 값 삭제하고 다시 저장해야함
  // 그니까 클릭할때 위도경도 임시저장을 하는거고
  // 등록버튼을 눌러야 db에 저장
  point = [];
  // markerPosition = null;
  // positions = null;
  markers[0].setMap(null);
  markers[1].setMap(null);
  markers = [];
  console.log(marker)
};


// ============ 경로 그리기 버튼 누르면 그리기 모드 ============

// 버튼 클릭 시 호출되는 핸들러 입니다
const selectOverlay = function (type) {

  // 이미 경로가 하나 등록되어있을 때 그리기 제한
  if (markers.length != 0) {
    alert("코스는 하나의 길만 등록 가능합니다")
  } else {

    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(kakao.maps.drawing.OverlayType[type]);
  }
}


// ============ 등록버튼 누르면 서버와 연결해서 DB에 저장 ============
const regist = function () {
  // const courseMap = [
  //   0, 0, 0, 0
  // ];
  const courseMap = [];

  point.forEach(element => {
    courseMap.push(element.lat);
    courseMap.push(element.lng)
  });

  const course = {
    courseMap: courseMap,
    course_address: "대전광역시",
    course_content: "test content'",
    course_keyword: "봄&여름",
    course_name: "test name",
    course_rcm: 0,
    course_regDate: "",
    course_userId: "aaa",
    course_viewCnt: 0
  }

  courseStore.createCourse(course);

  // console.log(point);
};

let check = false;
const road = function () {
  console.log(check);
  check = !check;
  if (check) {
    map.addOverlayMapTypeId(kakao.maps.MapTypeId.BICYCLE);
  } else {
    map.removeOverlayMapTypeId(kakao.maps.MapTypeId.BICYCLE);
  }
};




onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script'); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY
      }&libraries=services,drawing`;
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }
});

</script>

<style>
.template-map {
  display: inline;
}

.template-map>div {
  display: flex;
  flex-direction: column;
}

#map {
  /* display: inline; */
  width: 600px;
  height: 450px;
  border-radius: 50px;
  border-color: #24613b;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.buttons {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 5px;
  /* 버튼 사이의 간격을 조절할 수 있습니다. */
}


/* 커스텀오버레이 */
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}

.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;
}

.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}

.customoverlay:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}
</style>

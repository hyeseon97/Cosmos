<template>
  <div class="course-create-container">

    <div class="course-create-container-left">
      <div class="course-form">
        <div>
          <input type="text" v-model="course.course_name" placeholder="코스 이름">
        </div>
        <div class="course-form-address">
          {{ course.course_address }}
          <!-- 주소 -->
        </div>
        <div>
          <textarea cols="30" rows="10" v-model="course.course_content" placeholder="코스 내용"></textarea>
        </div>

        <!-- 키워드 리스트 -->
        <div>코스와 어울리는 키워드를 골라주세요 (최대 5개)</div>
        <div class="course-keyword-list">
          <label v-for="(keyword, index) in keywords" :key="index" :class="{ 'checked': keyword.checked }">
            <input type="checkbox" :name="'coursekeyword' + index" :value="keyword.value" v-model="keyword.checked">
            {{ keyword.label }}
          </label>
        </div>
        <!-- 키워드 리스트 -->

      </div>
      
      

    </div>

    <div class="course-create-container-right">
      <div id="map"></div>
      <div class="buttons">
        <button class="course-draw-button" @click="selectOverlay('POLYLINE')">경로 그리기</button>
        <button class="course-remove-button" @click="remove">경로 삭제</button>
        <button class="course-create-button" @click="regist">등록</button>
        <button class="bicycleroad-button" @click="road">자전거도로</button>
        <input type="text" v-model="keyword" @keyup.enter="searchkeyword">
      </div>

      <!-- 최종 등록 버튼 -->
      <div class="course-final-buttons"></div>
      <button @click="create">등록</button>
      <button @click="cancel">취소</button>
      <!-- 최종 등록 버튼 -->
    </div>



  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useCourseStore } from '@/stores/course'

const courseStore = useCourseStore();

let map = null;
let manager = null;

let positions = null;
let markers = [];
let markerPosition = null;
let marker = null;
let point = [];

const courseMap = [];

const keywords = ref([
  { value: '봄', label: '봄', checked: false },
  { value: '여름', label: '여름', checked: false },
  { value: '가을', label: '가을', checked: false },
  { value: '겨울', label: '겨울', checked: false },
  { value: '맑음', label: '맑음', checked: false },
  { value: '흐림', label: '흐림', checked: false },
  { value: '비', label: '비', checked: false },
  { value: '눈', label: '눈', checked: false },
  { value: '산', label: '산', checked: false },
  { value: '바다', label: '바다', checked: false },
  { value: '도시', label: '도시', checked: false },
  { value: '시골', label: '시골', checked: false },
  { value: '혼자', label: '혼자', checked: false },
  { value: '연인', label: '연인', checked: false },
  { value: '친구', label: '친구', checked: false },
  { value: '가족', label: '가족', checked: false },
  { value: '초급', label: '초급', checked: false },
  { value: '중급', label: '중급', checked: false },
  { value: '고급', label: '고급', checked: false },
  { value: '프로', label: '프로', checked: false },
]);


const course = ref({
  courseMap: courseMap,
  course_address: '',
  course_content: '',
  course_keyword: '',
  course_name: '',
  course_rcm: 0,
  course_regDate: '',
  course_userId: '',
  course_viewCnt: 0
})



// ============ 등록버튼 누르면 서버와 연결해서 DB에 저장 ============
const regist = function () {

  point.forEach(element => {
    courseMap.push(element.lat);
    courseMap.push(element.lng)
  });

  courseStore.createCourse(course);
};

// ========================================================================================================================
// ========================================================================================================================
// ========================================================================================================================
// 여기부터 지도



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
        // var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
        var imageSrc = '../../assets/bicycle_pink.png', // 마커이미지의 주소입니다    
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
          strokeWeight: 5, // 선의 두께 입니다
          strokeColor: '#24613b', // 선의 색깔입니다
          strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
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


  // ============ 지도 레벨 변경을 감지하는 이벤트 리스너 추가 ============
  

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

<style scoped>
#map {
  width: 100%;
  /* 부모 컨테이너의 가로 길이에 맞게 지도 크기 조정 */
  height: 450px;
  border-radius: 10px;
  border-color: #24613b;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

/* 전체 컨테이너 스타일 */
.course-create-container {
  display: flex;
}

/* 왼쪽 섹션 스타일 */
.course-create-container-left {
  flex: 1;
  padding: 20px;
  background-color: #f4f4f4;
  /* 연한 배경색 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  /* 부드러운 박스 그림자 */
}

/* 오른쪽 섹션 스타일 */
.course-create-container-right {
  flex: 1;
  padding: 20px;
  background-color: #ffffff;
  /* 흰 배경색 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  /* 부드러운 박스 그림자 */
  display: flex;
  flex-direction: column;
  /* 자식 엘리먼트들을 세로로 정렬하기 위해 */
  align-items: center;
  /* 자식 엘리먼트들을 가운데로 정렬하기 위해 */
}


.course-form {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.course-form div {
  margin-bottom: 15px;
}

.course-form input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.course-form-address {
  width: 100%;
  height: 32px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: white;
}

.course-form textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* 체크박스 */
.course-keyword-list {
  display: flex;
  flex-wrap: wrap;
  padding: 0 10%;
}

.course-keyword-list label {
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center;
  margin-right: 15px;
  margin-bottom: 10px;
  padding-left: 5px;
  font-size: 0.9rem;
  width: 20%;
  /* 4개씩 나열하므로 100%를 4로 나눈 값 */
  border: 2px solid #ddd;
  /* 테두리 추가 */
  padding: 10px;
  /* 테두리 내부 여백 추가 */
  border-radius: 5px;
  transition: background-color 0.3s;
}

.course-keyword-list label:hover {
  background-color: #ffe0e4;
}

/* 클릭한 상태의 라벨 스타일 */
.course-keyword-list label.checked {
  background-color: #FF8E9E;
  /* 클릭 시 배경색 변경 */
  border-color: #ff7e90;
}

.course-keyword-list input[type="checkbox"] {
  display: none;
}





</style>
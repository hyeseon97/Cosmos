<template class="template-map">
  <div>
    <div id="map"></div>
    <div class="buttons">
      <button class="course-draw-button" @click="selectOverlay('POLYLINE')">경로 그리기</button>
      <button class="course-remove-button" @click="remove">경로 삭제</button>
      <button class="course-create-button" @click="regist">등록</button>
      <button class="bicycleroad-button" @click="road">자전거도로</button>
    </div>
    <p id="result"></p>
  </div>
</template>

<script setup>
import { onMounted, ref, toRaw } from 'vue';
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
      strokeColor: '#384C41', // 선 색
      strokeStyle: 'solid', // 선 스타일
      strokeWeight: 10, // 선 두께
      strokeOpacity: 0.8, // 선 투명도
      hintStrokeStyle: 'dash', // 그리중 마우스를 따라다니는 보조선의 선 스타일
      hintStrokeOpacity: 0.7  // 그리중 마우스를 따라다니는 보조선의 투명도
    }
  };

  // 위에 작성한 옵션으로 Drawing Manager를 생성합니다
  manager = new kakao.maps.drawing.DrawingManager(drawOptions);

  // manager.addListener('remove', function (e) {
  //   console.log(e.overlayType);
  //   console.log("delete");
  //   // 삭제할때
  //   // 여태까지 저장했던 포인트들의 위도경도 값 삭제하고 다시 저장해야함
  //   // 그니까 클릭할때 위도경도 임시저장을 하는거고
  //   // 등록버튼을 눌러야 db에 저장
  //   point = [];
  //   markerPosition = null;
  //   positions = null;
  //   markers[0].setMap(null);
  //   markers[1].setMap(null);
  // });



  manager.addListener('drawend', function (data) {

    // markerPosition = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng());

    positions = [
      {
        title: '시작',
        latlng: new kakao.maps.LatLng(point[0].lat, point[0].lng)
      },
      {
        title: '끝',
        latlng: new kakao.maps.LatLng(point[point.length - 1].lat, point[point.length - 1].lng)
      }
    ];

    marker = new kakao.maps.Marker({
      map: map,
      position: positions[0].latlng,
      title: positions[0].title
    });
    markers.push(marker);
    marker.setMap(map);

    marker = new kakao.maps.Marker({
      map: map,
      position: positions[1].latlng,
      title: positions[1].title
    });
    markers.push(marker);
    marker.setMap(map);


  });


};

const remove = function () {
  // 모든 오버레이 가져와서 그중 선만 가져오고 0번째꺼 삭제
  manager.remove(manager.getOverlays().polyline[0]);
  console.log("새버튼delete");
  // 삭제할때
  // 여태까지 저장했던 포인트들의 위도경도 값 삭제하고 다시 저장해야함
  // 그니까 클릭할때 위도경도 임시저장을 하는거고
  // 등록버튼을 눌러야 db에 저장
  point = [];
  markerPosition = null;
  positions = null;
  markers[0].setMap(null);
  markers[1].setMap(null);

  console.log(marker)
};

// 버튼 클릭 시 호출되는 핸들러 입니다
const selectOverlay = function (type) {
  // 그리기 중이면 그리기를 취소합니다
  manager.cancel();

  // 클릭한 그리기 요소 타입을 선택합니다
  manager.select(kakao.maps.drawing.OverlayType[type]);
}



const regist = function () {
  console.log(point);
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

<style scoped>
.template-map {
  display: inline;
}

.template-map > div {
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
  gap: 5px; /* 버튼 사이의 간격을 조절할 수 있습니다. */
}

</style>

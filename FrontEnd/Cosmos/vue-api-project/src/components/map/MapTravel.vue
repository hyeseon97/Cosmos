<template class="template-map">
  <!-- Map-Travel -->
  <!-- 검색기능 : 마커 클릭하고 추가버튼 누르면 장소리스트에 추가 -->
  <!-- 코스조회 : 코스 시작 마커 클릭하고 추가버튼 누르면 장소리스트에 추가 -->
  <!-- 자전거도로버튼도 남겨두기 -->

  <div>
    <div id="map"></div>
    <div class="buttons">
      <button class="bicycleroad-button" @click="road">자전거도로</button>
      <input type="text" @keyup.enter="search" v-model="keyword">
      <button @click="search">검색</button>
    </div>
    <p id="result"></p>
  </div>
</template>

<script setup>
import { onMounted, ref, } from 'vue';
let map = null;

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

};


// ==================== 자전거 도로 ====================
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
// ==================== 자전거 도로 끝 ====================



// ==================== 검색 기능 ====================

// 검색 키워드
const keyword = ref('')

const search = function () {
  // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
      center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
    };

  // 지도를 생성합니다    
  var map = new kakao.maps.Map(mapContainer, mapOption);

  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places();

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

  // 지도에 마커를 표시하는 함수입니다
  function displayMarker(place) {

    // 마커를 생성하고 지도에 표시합니다
    var smarker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(place.y, place.x)
    });

    // console.log(place.address_name)

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(smarker, 'click', function () {
      // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
      infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
      infowindow.open(map, smarker);


      // 장소 이름과 주소
      console.log(place.place_name)
      console.log(place.address_name)
    });
  }

}

// ==================== 검색 끝 ====================




// ========================================================================================================================

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
</style>

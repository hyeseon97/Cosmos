<template>
  <div class="container">
    <div class="container-left">
      <!-- 여행 폼 -->
      <div class="travel-form">
        <div>
          <input type="text" v-model="travel.travel_title" placeholder="여행 계획 이름">
        </div>
        <div>
          <input type="date" v-model="travel.travel_travelDate">
        </div>
        <div>
          <textarea cols="30" rows="10" v-model="travel.travel_memo" placeholder="메모"></textarea>
        </div>
      </div>
      <!-- 여행 폼 -->

      <!-- 장소리스트 -->
      <div class="placeList">
        <table>
          <tr>
            <th>장소 이름</th>
            <th>장소 주소</th>
            <th></th>
            <th></th>
          </tr>
          <tr v-for="(p, index) in place" :key="p.num">
            <td>{{ p.place_name }}</td>
            <td>{{ p.place_address }}</td>
            <td v-if="index != 4"><button @click="addPlace">+</button></td>
            <td v-if="index == 4"></td>
            <td v-if="index != 0"><button @click="removePlace(index)">-</button></td>
            <td v-if="index == 0"></td>
          </tr>
        </table>
      </div>
      <!-- 장소리스트 -->

      <!-- 최종 등록 버튼 -->
      <div class="final-buttons"></div>
      <button @click="create">등록</button>
      <button @click="cancel">취소</button>
      <!-- 최종 등록 버튼 -->
    </div>

    <!-- 지도공간 -->
    <div class="container-right">
      <div id="map"></div>
      <div class="map-controls">
        <div class="bicycleroad-button" @click="road" :class="{ 'active': check }">자전거도로</div>
        <input type="text" @keyup.enter="search" v-model="keyword" class="search-input">
        <div @click="search" class="search-button">검색</div>
        <div @click="save" class="save-button">장소저장</div>
      </div>
    </div>
    <!-- 지도공간 -->
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useTravelStore } from '../../stores/travel';
import { useCourseStore } from '@/stores/course'
import { useUserStore } from '../../stores/user';


const router = useRouter();
const travelStore = useTravelStore();
const courseStore = useCourseStore();
const userStore = useUserStore();

let map = null;
let idx = 0;

// 장소 리스트
const place = ref([
  {
    place_name: '',
    place_address: '',
  }
])

const name = ref('')
const address = ref('')
// 장소 저장
const save = function () {
  console.log(place.value.length + " " + idx)
  place.value[idx].place_name = name.value;
  place.value[idx].place_address = address.value;
}

// 여행 정보
const travel = ref({
  travel_title: '',
  travel_memo: '',
  travel_travelDate: '',
  travel_userId: userStore.loginUserId,
  places: place.value,
})

// 장소 추가
const addPlace = function () {

  if (place.value[idx].place_name == '') {
    alert("장소를 먼저 등록해주세요")
  } else {
    place.value.push({
      place_name: '',
      place_address: '',
    })
    idx++;
  }
}

// 장소 삭제
const removePlace = function (index) {
  place.value.splice(index, 1)
  idx--;
}

// 여행 등록
const create = function () {
  console.log(place)
  travelStore.createTravel(travel)
  // router.push({ name: "travelDetail", params: { num: 1 }})
}

// 취소
const cancel = function () {
  router.push({ name: "travelList" })
}

// ========================================================================================================================
// ========================================================================================================================
// ========================================================================================================================
// 여기부터 지도

let saveOverlay = null;

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
  // map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


  // ==================== DB 데이터 표시 ====================

  const condition = {
    key: 'none',
    word: '',
    orderBy: 'none',
    orderByDir: ''
  }

  courseStore.getCourseList(condition)
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
        var imageSrc = 'https://cdn-icons-png.flaticon.com/512/5693/5693811.png', // 마커이미지의 주소입니다    
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

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(dbmarker, 'click', function () {
          if (saveOverlay != null) {
            saveOverlay.setMap(null);
          }

          console.log("클릭이벤트")
          // 장소 이름과 주소
          name.value = course.course_name;
          address.value = course.course_address;
          // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
          var content = '<div class="customoverlay">' +
            '  <a>' +
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

          saveOverlay = customOverlay;


        });
        // 코스 표시
        lineList.push(line);
      });

      lineList.forEach(line => {
        var polyline = new kakao.maps.Polyline({
          path: line, // 선을 구성하는 좌표배열 입니다
          strokeWeight: 5, // 선의 두께 입니다
          strokeColor: '#C75869', // 선의 색깔입니다
          strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid' // 선의 스타일입니다
        });

        // 지도에 선을 표시합니다 
        polyline.setMap(map);
      });


    })
  // ==================== DB 데이터 표시 끝 ====================


  // 현재 맵 레벨에 따라 마커 크기 조정
  const currentLevel = map.getLevel();
  setMapLevelBasedMarkerSize(currentLevel);

  // 맵 레벨 변경 이벤트 리스너 등록
  kakao.maps.event.addListener(map, 'zoom_changed', function () {
    // 맵 레벨이 변경될 때마다 호출되는 함수
    const newLevel = map.getLevel();
    setMapLevelBasedMarkerSize(newLevel);
  });

  // 이미지 크기를 조정하는 함수
  function setMapLevelBasedMarkerSize(level) {
    // 이미지 크기를 조정할 수 있는 값
    const imageSize = level * 10;

    // 마커 이미지의 URL
    const imageSrc = 'https://cdn-icons-png.flaticon.com/512/5693/5693811.png';

    // 마커 이미지의 크기 설정
    const markerImage = new kakao.maps.MarkerImage(imageSrc, new kakao.maps.Size(imageSize, imageSize));

    // 마커 생성
    const marker = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(37.5665, 126.9780),
      image: markerImage,
      map: map
    });
  }
};




// ==================== 자전거 도로 ====================
let check = ref(false);
const road = function () {
  check.value = !check.value;
  if (check.value) {
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

      if (saveOverlay != null) {
        saveOverlay.setMap(null);
      }



      // 장소 이름과 주소
      name.value = place.place_name;
      address.value = place.address_name;


      // 아래 부분을 추가하여 커스텀 오버레이로 표시하도록 변경
      var content = '<div class="customoverlay">' +
        '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
        '    <span class="title">' + place.place_name + '</span>' +
        '  </a>' +
        '</div>';

      // 커스텀 오버레이가 표시될 위치입니다 
      var position = new kakao.maps.LatLng(place.y, place.x);

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: position,
        content: content,
        yAnchor: 1
      });

      saveOverlay = customOverlay;
      // console.log(place.place_name)
      // console.log(place.address_name)
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
  userStore.getUser(userStore.loginUserId);
});


</script>

<style>
#map {
  width: 100%;
  /* 부모 컨테이너의 가로 길이에 맞게 지도 크기 조정 */
  height: 450px;
  border-radius: 10px;
  border-color: #24613b;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

/* 전체 컨테이너 스타일 */
.container {
  display: flex;
}

/* 왼쪽 섹션 스타일 */
.container-left {
  flex: 1;
  padding: 20px;
  background-color: #f4f4f4;
  /* 연한 배경색 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  /* 부드러운 박스 그림자 */
}

/* 오른쪽 섹션 스타일 */
.container-right {
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

/* 버튼 추가 스타일 */
.container button {
  background-color: #f7f2e4;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* 초록 버튼 배경색 */
  /* color: #ffffff; */
  /* 흰 텍스트 색상 */
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
}

.container button:hover {
  background-color: #ebe4d1;
  /* 호버 시 더 어두운 초록 */
}

/* 입력 필드에 대한 스타일 */
.container input[type="text"],
.container input[type="date"],
.container textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.travel-form>div>input,
textarea {
  outline: none;
}

/* placeList 테이블 스타일링 */
.container table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 5px;
}

.container th,
.container td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
  line-height: 1.2;
  /* 이 부분을 조절하여 행 높이를 변경하세요. */
}

.container th {
  background-color: #ddd;
  /* color: #ffffff; */
  text-align: center;
}

.container th:first-child,
.container td:first-child {
  width: 30%;
  /* 첫 번째 열의 넓이 설정 */
}

.container th:nth-child(2),
.container td:nth-child(2) {
  width: 54%;
  /* 두 번째 열의 넓이 설정 */
}

.container th:nth-child(3),
.container td:nth-child(3) {
  width: 8%;
  /* 세 번째 열의 넓이 설정 */
}

.container th:nth-child(4),
.container td:nth-child(4) {
  width: 8%;
  /* 네 번째 열의 넓이 설정 */
}

/* 최종 버튼에 대한 스타일 */
.final-buttons {
  text-align: right;
  margin-top: 20px;
}

/* 하단 버튼 및 입력창 스타일 */
.map-controls {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  align-items: center;
  width: 100%;
  height: 50px;
  flex: 1 0 auto;
}

.map-controls>.search-input {
  flex: 1;
  padding: 8px;
  border-radius: 4px;
  font-size: 14px;
  outline: none;

}

.map-controls>input {
  margin-top: 11px;
  transition: border-color 0.3s ease-in-out;
  transition: border-width 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.map-controls>input:focus {
  border-color: #f3ead3;
  border-width: 3px;
}

.search-button,
.save-button,
.bicycleroad-button {
  padding: 8px 16px;
  font-size: 14px;
  background-color: #f7f2e4;
  /* color: #fff; */
  border: none;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.search-button:hover,
.save-button:hover {
  background-color: #f3ead3;
}


.bicycleroad-button:hover {
  background-color: #fff27c;
  /* 클릭하거나 호버 시 더 어두운 초록 */
}


.bicycleroad-button.active {
  background-color: #fff27c;
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
  background: #4982C8;
  background: #4982C8 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;
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
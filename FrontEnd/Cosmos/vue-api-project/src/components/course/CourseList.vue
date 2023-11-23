<template>
  <div class="course-list-container">

    <div class="course-list-container-left">
      <div class="weather-div">
        <Weather />
      </div>
      <!-- 키워드 리스트 -->
      <div style="font-size: 20px;">키워드로 코스 검색하기 (최대 5개)</div><br>
      <div class="course-list-keyword-list">
        <label v-for="(keyword, index) in keywords" :key="index"
          :class="{ 'checked': keyword.checked, 'disabled': isKeywordDisabled(keyword) }">
          <input type="checkbox" :name="'coursekeyword' + index" :value="keyword.value" v-model="keyword.checked"
            @change="handleKeywordChange">
          {{ keyword.label }}
        </label>
      </div>
      <!-- 키워드 리스트 -->
    </div>

    <div class="course-list-container-right">
      <div v-if="checkedKeywordsCount == 0" id="map"></div>
      <div v-if="checkedKeywordsCount == 0" class="course-list-buttons">
        <div class="course-bicycle-button" @click="road" :class="{ 'active': check }">자전거도로</div>
        <div class="course-list-create-button" @click="create">새 코스 등록</div>
      </div>
      <div v-if="checkedKeywordsCount != 0">
        <CourseSearchList />
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch, watchEffect } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useTravelStore } from '../../stores/travel';
import { useCourseStore } from '@/stores/course'
import Weather from '../map/weather.vue';
import MapCourse from '../map/MapCourse.vue';
import CourseSearchList from '../course/CourseSearchList.vue';

const router = useRouter();
const courseStore = useCourseStore();

let map = null;
let manager = null;

let positions = null;
let markers = [];
let markerPosition = null;
let marker = null;
let point = [];
let drawmode = false;

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

// 최대 5개의 키워드만 선택 가능하도록 처리
const handleKeywordChange = () => {
  const selectedKeywords = keywords.value.filter(keyword => keyword.checked);
  courseStore.keywords.value = selectedKeywords;

  // 아래 두 줄을 추가하여 변경된 키워드 데이터를 출력해봅니다.
  console.log('Selected Keywords:', selectedKeywords);
  console.log('Course Store Keywords:', courseStore.keywords.value);


  if (selectedKeywords.length > 5) {
    // 선택한 키워드가 5개를 초과하면 알림창 띄우기
    alert("최대 5개까지 선택 가능합니다.");
    // 변경된 체크된 상태를 다시 원래대로 돌리기
    keywords.value.forEach(keyword => {
      if (!selectedKeywords.some(selectedKeyword => selectedKeyword.value === keyword.value)) {
        keyword.checked = false;
      }
    });
  }

  let senddata = '';
  // 아래 두 줄을 추가하여 변경된 키워드 데이터를 출력해봅니다.
  console.log('Keywords after if statement:', keywords.value);
  keywords.value.forEach(element => {
    if (element.checked) {
      console.log('Checked Keyword:', element.value);
      senddata += ("_" + element.value);
    }
  });

  courseStore.keywords.value = keywords.value;

  courseStore.getCourseListByKeyword(senddata);

  initMap();
};

// 선택 불가능한 상태인지 여부를 확인하는 함수
const isKeywordDisabled = (keyword) => {
  const selectedKeywords = keywords.value.filter(k => k.checked);
  return selectedKeywords.length >= 5 && !keyword.checked;
};

const checkedKeywordsCount = computed(() => {
  return keywords.value.filter(keyword => keyword.checked).length;
});


const create = function () {
  router.push({ name: "courseCreate" })
}

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
        for (var i = 0; i < course.courseMap.length / 2; i++) {
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

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(dbmarker, 'click', function () {
          // // 마커 위에 인포윈도우를 표시합니다
          // infowindow.open(map, dbmarker);
          console.log("클릭이벤트")
          router.push({ name: "recommandDetail", params: { num: course.course_num } })
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



  // ==================== 클릭했을 때 ====================

  kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;

    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';

    if (drawmode) {
      // 코스 좌표 배열에 클릭할때마다 하나씩 푸쉬
      point.push({ lat: latlng.getLat(), lng: latlng.getLng() });
    }

    if (point.length == 1) {
      // 주소-좌표 변환 객체를 생성합니다
      getAddr(point[0].lat, point[0].lng);
      function getAddr(lat, lng) {
        let geocoder = new kakao.maps.services.Geocoder();

        let coord = new kakao.maps.LatLng(lat, lng);
        let callback = function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            console.log(result);
            course.value.course_address = result[0].address.address_name;
          }
        }
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
      }
    }

  });
  // ==================== 클릭했을 때 끝 ====================

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

.course-list-buttons {
  width: 100%;
  margin-top: 30px;
}

.course-list-container {
  display: flex;
  width: 100%;
  height: 100%;
}

/* 왼쪽 섹션 스타일 */
.course-list-container-left {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 40%;
  flex: 1;
  padding: 20px;
  background-color: #f4f4f4;
  /* 연한 배경색 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  /* 부드러운 박스 그림자 */
}

/* 오른쪽 섹션 스타일 */
.course-list-container-right {
  width: 60%;
  flex: 1;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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

.weather-div {
  display: flex;
  flex-direction: column;
  /* 자식 엘리먼트들을 세로로 정렬하기 위해 */
  align-items: center;
  /* 자식 엘리먼트들을 가운데로 정렬하기 위해 */
  width: 100%;
  /* padding-left: 10%; */
  margin-bottom: 50px;
}

/* 체크박스 */
.course-list-keyword-list {
  display: flex;
  flex-wrap: wrap;
  padding: 0 7%;
}

.course-list-keyword-list label {
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center;
  /* margin-bottom: 10px; */
  margin: 5px;
  font-size: 0.9rem;
  width: 23%;
  /* 4개씩 나열하므로 100%를 4로 나눈 값 */
  border: 2px solid #ddd;
  /* 테두리 추가 */
  padding: 10px;
  /* 테두리 내부 여백 추가 */
  border-radius: 5px;
  transition: background-color 0.3s;
}

.course-list-keyword-list label:hover {
  background-color: #ffe0e4;
}

/* 클릭한 상태의 라벨 스타일 */
.course-list-keyword-list label.checked {
  background-color: #FF8E9E;
  /* 클릭 시 배경색 변경 */
  border-color: #ff7e90;
  color: white;
}

.course-list-keyword-list input[type="checkbox"] {
  display: none;
}

/* 선택 불가능한 상태일 때 스타일 추가 */
.course-list-keyword-list label.disabled {
  opacity: 0.5;
  cursor: not-allowed;
  pointer-events: none;
  /* 클릭 이벤트 비활성화 */
}

.course-list-buttons {
  width: 100%;
  margin-top: 30px;
  display: flex;
  justify-content: end;
  /* 가로로 균등하게 정렬하려면 추가 */
}

.course-bicycle-button,
.course-list-create-button {
  margin-left: 5px;
  margin: 0 5px;
  padding: 10px;
  background-color: #f7f2e4;
  /* 배경색은 원하는 색상으로 변경 */
  border: none;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.course-bicycle-button:hover {
  background-color: #fff27c;
  /* 마우스 호버 시 배경색 변경 */
}

.course-list-create-button:hover {
  background-color: #ffe0e4;
}

.course-bicycle-button.active {
  background-color: #fff27c;
  /* 활성 상태일 때 배경색 변경 */
}</style>
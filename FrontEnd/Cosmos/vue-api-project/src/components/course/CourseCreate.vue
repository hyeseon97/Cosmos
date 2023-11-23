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
          <label v-for="(keyword, index) in keywords" :key="index"
            :class="{ 'checked': keyword.checked, 'disabled': isKeywordDisabled(keyword) }">
            <input type="checkbox" :name="'coursekeyword' + index" :value="keyword.value" v-model="keyword.checked"
              @change="handleKeywordChange">
            {{ keyword.label }}
          </label>
        </div>
        <!-- 키워드 리스트 -->
      </div>
    </div>

    <input type="file" @change="imageUpload" ref="boardImage" id="photo" accept="image/*">이미지 등록
                <img :src="imageUploaded" alt="사용자가 업로드한 이미지">

    <div class="course-create-container-right">
      <div id="map"></div>
      <div class="course-create-buttons">
        <div class="course-bicycle-button" @click="road" :class="{ 'active': check }">자전거도로</div>
        <div class="course-draw-button" @click="selectOverlay('POLYLINE')">경로 그리기</div>
        <div class="course-remove-button" @click="remove">경로 삭제</div>
        <div class="course-create-button" @click="regist">코스 등록</div>
      </div>
    </div>



  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useCourseStore } from '@/stores/course'
import { useUserStore } from '../../stores/user';
import { storeToRefs } from 'pinia';

const courseStore = useCourseStore();
const userStore = useUserStore();

let map = null;
let manager = null;

let positions = null;
let markers = [];
let markerPosition = null;
let marker = null;
let point = [];
let drawmode = false;

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

// 최대 5개의 키워드만 선택 가능하도록 처리
const handleKeywordChange = () => {
  const selectedKeywords = keywords.value.filter(keyword => keyword.checked);
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
};

// 선택 불가능한 상태인지 여부를 확인하는 함수
const isKeywordDisabled = (keyword) => {
  const selectedKeywords = keywords.value.filter(k => k.checked);
  return selectedKeywords.length >= 5 && !keyword.checked;
};


const course = ref({
  courseMap: courseMap,
  course_address: '',
  course_content: '',
  course_keyword: '',
  course_name: '',
  course_rcm: 0,
  course_regDate: '',
  course_userId: userStore.loginUserId,
  course_viewCnt: 0
})

// ============ 등록버튼 누르면 서버와 연결해서 DB에 저장 ============
const regist = function () {
  let keyword = '';

  keywords.value.forEach(k => {
    if (k.checked) {
      keyword += ('/' + k.value)
    }
  });

  point.forEach(element => {
    courseMap.push(element.lat);
    courseMap.push(element.lng)
  });

  course.value.course_keyword = keyword;
  console.log("코스");
  console.log(course.value);
  console.log("유저아이디")
  console.log(userStore.loginUserId);
  console.log(userStore.isAuthenticated);
  // courseStore.createCourse(course);

  const frm = new FormData()
  console.log(course.value)
  frm.append("courseMap",course.value.courseMap)
  frm.append("course_userId",userStore.loginUserId)
  frm.append("course_name",course.value.course_name)
  frm.append("course_content",course.value.course_content)
  frm.append("course_address",course.value.course_address)
  frm.append("course_keyword",course.value.course_keyword)
  frm.append("course_viewCnt",course.value.course_viewCnt)
  frm.append("course_rcm",course.value.course_rcm)
  frm.append("course_regDate",course.value.course_regDate)
  frm.append("file",file.value)
  courseStore.createCourse(frm)
};

// 파일업로드
const file = ref({})
const imageUploaded = ref({})

const imageUpload = function () {
    const photo = document.getElementById("photo")
    file.value = photo.files[0];
    const image = file.value
    console.log(file.value)
    imageUploaded.value=URL.createObjectURL(image)
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

  courseStore.getCourseList()
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

    console.log("클릭할 때 마다 : ");
    console.log(manager);

  });
  // ==================== 클릭했을 때 끝 ====================




  // ==================== 그리기 모드 설정 ====================

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
      strokeColor: '#2B9D82', // 선 색
      strokeStyle: 'solid', // 선 스타일
      strokeWeight: 10, // 선 두께
      strokeOpacity: 0, // 선 투명도
      hintStrokeStyle: 'solid', // 그리중 마우스를 따라다니는 보조선의 선 스타일
      hintStrokeOpacity: 0  // 그리중 마우스를 따라다니는 보조선의 투명도
    }
  };

  // 위에 작성한 옵션으로 Drawing Manager를 생성합니다
  manager = new kakao.maps.drawing.DrawingManager(drawOptions);

  // ==================== 그리기 설정 끝 ====================





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

    drawmode = false;

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
  course.value.course_address = '';
  console.log(marker)
};


// ============ 경로 그리기 버튼 누르면 그리기 모드 ============

// 버튼 클릭 시 호출되는 핸들러 입니다
const selectOverlay = function (type) {

  // 이미 경로가 하나 등록되어있을 때 그리기 제한
  if (markers.length != 0) {
    alert("코스는 하나의 길만 등록 가능합니다")
  } else {
    drawmode = true;
    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(kakao.maps.drawing.OverlayType[type]);
  }
}


// ==================== 자전거 도로 ====================
let check = ref(false);
const road = function () {
  console.log(check.value);
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
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: white;
  font-size: 15px;
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
  width: 100%;
  flex-wrap: wrap;
  padding: 0 7%;
  justify-content: center;
  align-items: center;
}

.course-keyword-list label {
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center;
  margin-bottom: 10px;
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

.course-keyword-list label:hover {
  background-color: #ffe0e4;
}

/* 클릭한 상태의 라벨 스타일 */
.course-keyword-list label.checked {
  background-color: #FF8E9E;
  /* 클릭 시 배경색 변경 */
  border-color: #ff7e90;
  color: white;
}

.course-keyword-list input[type="checkbox"] {
  display: none;
}

.course-create-buttons {
  width: 100%;
  margin-top: 30px;
}

.course-create-container {
  display: flex;
  width: 100%;
}

.course-create-buttons>div {
  display: inline-flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 3px;
  padding: 10px;
  background-color: #f7f2e4;
  border-radius: 5px;
  width: 24%;
}

.course-create-buttons>.course-bicycle-button:hover {
  background-color: #fff27c;
}
.course-create-buttons>.course-bicycle-button.active {
  background-color: #fff27c;
}

.course-create-buttons>.course-create-button:hover {
  background-color: #ffe0e4;
}


.course-create-buttons>div:hover {
  background-color: #F5ECD7;
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

/* 선택 불가능한 상태일 때 스타일 추가 */
.course-keyword-list label.disabled {
  opacity: 0.5;
  cursor: not-allowed;
  pointer-events: none;
  /* 클릭 이벤트 비활성화 */
}

</style>
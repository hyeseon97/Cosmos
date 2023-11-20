<template>
  <div class="course-list">
    <div class="course" v-for="c in course" @click="goDetail(c.num)">
      <img :src="c.src" alt="사진" class="course-image">
      <div class="view-good">
        <div class="viewCnt icon-center" style="margin-right: 10px;"><img src="../../assets/view.png" style="width: 20px; margin-right: 10px;">{{ c.viewCnt }}</div>
        <div class="good icon-center"><img src="../../assets/good.png" style="width: 20px; margin-right: 10px;">{{ c.good }}</div>
      </div>
      <div class="name">{{ c.name }}</div>
      <div class="address">{{ c.address }}</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

const course = ref([
  {
    num: 1,
    name: "이름1",
    address: "주소1",
    viewCnt: 3495735,
    good: 5921,
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhKzRz%2FbtradSs09rD%2FgDcrgDfYvrlXY9RPxEH1kK%2Fimg.png'
  },
  {
    num: 2,
    name: "이름1",
    address: "주소1",
    viewCnt: 2563524,
    good: 2643,
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcgVNR1%2FbtracZlbhFp%2FLWdO69OUTBkZKE2RZI2sOk%2Fimg.png'
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 134314,
    good: 932,
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FWxf89%2FbtrafMeM3pE%2FN2SiTnFSs0SB1kAIEOieO0%2Fimg.png'
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: "1k",
    good: 1,
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FwWwlg%2FbtrafLAcSgK%2FecIZaDEa4UwQxD8Mu3x7b1%2Fimg.jpg'
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
    src: ''
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
    src: ''
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
    src: ''
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
    src: ''
  },
])


const goDetail = function (num) {
  router.push({ name: "recommandDetail", params: { num: num } })
}

</script>

<style scoped>
.course-list {
  display: flex;
  flex-wrap: wrap;
  height: 100%;
  gap: 20px;
  /* 각 카드 사이의 간격 조절 */
}

.course {
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center;
  /* 세로 중앙 정렬을 위한 스타일 */
  width: calc(25% - 20px);
  /* 한 행에 4개씩 위치하도록 너비 조절 (20px 간격 고려) */
  height: 50%;
  margin-bottom: 20px;
  padding: 15px;
  background: linear-gradient(to bottom, #8FBF9F, #68a67d);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  /* 마우스 오버시 그림자 강화 */
  /* background-color: #F18F01; */
  /* border: 5px solid #F18F01; */
  border-radius: 40px;
  cursor: pointer;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  /* 두 가지 트랜지션을 한 줄에 나열 */
}

.course-image {
  width: 280px;
  height: 320px;
  margin-bottom: 1%;
  object-fit: cover;
  /* 이미지가 영역을 가득 채우도록 설정 */
  border-radius: 10px;
  /* 원하는 모양으로 이미지 모서리를 둥글게 만들 수 있음 */
  transition: transform 0.3s ease-in-out; /* 이미지에 호버 효과를 부드럽게 만들기 위한 트랜지션 설정 */
}

.course:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  /* 마우스 오버시 그림자 강화 */
  transform: scale(1.1);
  /* 마우스 오버시 크기를 1.1배로 확대 */
}

.course-image {
  max-width: 100%;
}


.name {
  color: #24613b;
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 3%;
}

.address {
  color: #353535;
  font-size: 1.1rem;
  margin-top: 5px;
  font-weight: bold;
  margin-bottom: 5%;
}

.view-good {
  display: inline-flex;
  justify-content: end;
  width: 100%;
  padding-right: 10px;
  margin-bottom: 20px;
}

.icon-center {
  display: flex;
  align-items: center; /* 수직 중앙 정렬을 위한 속성 */
}


</style>
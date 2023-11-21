<template>
  <div class="template">
    <div class="images" style="width: 55%;">
      <div v-for="i in image">
        <img :src="i.src" alt="사진" style="max-width: 100%;">
      </div>
    </div>
    <div class="course">
      <div class="course-board">
        <div class="course-name">{{ course.name }}</div>
        <div class="course-address">{{ course.address }}</div>
        <div class="course-content">{{ course.content }}</div>
      </div>
      <RecommandComment/>
    </div>
  </div>
</template>

<script setup>
import RecommandComment from './RecommandComment.vue';

import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const image = ref([
  {
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhKzRz%2FbtradSs09rD%2FgDcrgDfYvrlXY9RPxEH1kK%2Fimg.png'
  },
  {
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhKzRz%2FbtradSs09rD%2FgDcrgDfYvrlXY9RPxEH1kK%2Fimg.png'
  },
  {
    src: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhKzRz%2FbtradSs09rD%2FgDcrgDfYvrlXY9RPxEH1kK%2Fimg.png'
  }
])

const course = {
  num: 1,
  name: "한강에서 자전거타고 치킨",
  address: "대한민국 서울 한강 00동",
  content: "한강에서 자전거타고 노을보고 치킨먹고 맥주먹고 맛있겠다한강에서 자전거타고 노을보고 치킨먹고 맥주먹고 맛있겠다",
}

</script>

<style scoped>
.template {
  display: inline-flex;
}
.course {
  margin-left: 5%;
  width: 40%;
}

div {
  font-weight: bold;
}

.course-board {
  height: 30%;
}


.course-name {
  color: #24613b;
  font-size: 1.5rem;
  padding: 5% 0;
}

.course-address {
  padding: 5% 0;
  max-width: 100%;
}

.course-content {
  padding: 5% 0;
}

</style>
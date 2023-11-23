<template>
  <div>
    <h4>CourseDetail</h4>
    <h4>{{ route.params.num }}</h4>
    <div v-for="i in image">
      <img src="" alt="사진">
    </div>

    <div>
      <div>{{ course.name }}</div>
      <div>{{ course.address }}</div>
      <div>{{ course.content }}</div>
    </div>

    <CourseComment/>
  </div>
</template>

<script setup>
import CourseComment from './CourseComment.vue';

import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const route = useRoute();

const image = ref([
  {
    src: ''
  },
  {
    src: ''
  },
  {
    src: ''
  }
])

const course = {
  num: 1,
  name: "이름",
  address: "주소",
  content: "내용",
}

</script>

<style scoped>

</style>
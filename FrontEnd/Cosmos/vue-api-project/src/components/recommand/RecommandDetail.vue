<template>
  <div class="template">
    <div class="images-container">
      <img v-if="imgName" :src="`http://localhost:8080/upload/${imgName}`" alt="사진" class="course-image">
    </div>
    <div class="course">
      <div class="course-board">
        <div class="course-name">{{ course.course_name }}</div>
        <div class="course-address">{{ course.course_address }}</div>
        <div class="course-content">{{ course.course_content }}</div>
      </div>
      <RecommandComment/>
    </div>
  </div>
</template>

<script setup>
import RecommandComment from './RecommandComment.vue';

import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useCourseStore } from '../../stores/course';

const route = useRoute();
const courseStore = useCourseStore();

const imgName = computed(()=>courseStore.course.course_imgName)
const course = computed(()=>courseStore.course)

onMounted(() => {
  courseStore.getCourse(route.params.num)
  // console.log(route.params.num)
  console.log("detail에서" + courseStore.course.course_imgName)
})

</script>

<style scoped>
.template {
  display: flex;
  justify-content: space-between;
  margin: 20px;
}

.images-container {
  width: 55%;
  overflow: hidden;
}

.course-image {
  max-width: 100%;
  border-radius: 8px; /* 이미지에 둥근 테두리 적용 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
}

.course {
  width: 40%;
}

.course-board {
  height: 30%;
}

div {
  font-weight: bold;
}

.course-name {
  color: #24613b;
  font-size: 1.5rem;
  padding: 10px 0;
}

.course-address,
.course-content {
  padding: 10px 0;
  max-width: 100%;
  color: #555; /* 텍스트 색상 변경 */
}
</style>
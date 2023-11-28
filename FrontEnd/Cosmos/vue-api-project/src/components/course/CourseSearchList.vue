<template class="coursesearchlist-container">
  <div class="scrollable-container">
    <div class="row">
      <div v-if="list.length==0" style="font-size: large; ">키워드를 포함하는 코스가 없습니다</div>
      <div class="search-course" v-for="c in list" @click="goDetail(c.num)">
        <img :src="`http://localhost:8080/upload/${c.course_imgName}`" alt="사진" class="course-image" style="margin-left: 7px; width: 100%; height: 80%;">
        <div class="info-container">
          <div class="view-good">
            <div class="viewCnt icon-center">
              <img src="../../assets/view.png" class="icon" />
              {{ c.course_viewCnt }}
            </div>
            <div class="good icon-center">
              <img src="../../assets/good.png" class="icon" />
              {{ c.course_rcm }}
            </div>
          </div>
          <div class="name">{{ c.course_name }}</div>
          <div class="address">{{ c.course_address }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch, watchEffect } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useCourseStore } from '@/stores/course'

const courseStore = useCourseStore();

const list = computed(() => courseStore.courseList)


</script>

<style scoped>
.coursesearchlist-container {
  width: 100%;
}

.testcolor {
  background-color: black;
}


.scrollable-container {
  max-height: 550px;
  min-width: 700px;
  padding: 10px;
  width: 100%;
  overflow-y: auto;

  /* 스크롤바 디자인 */
  scrollbar-color: #888 #f4f4f4; /* Firefox */
  scrollbar-width: thin; /* Firefox */

}

/* Webkit (Chrome, Safari) 스크롤바 디자인 */
.scrollable-container::-webkit-scrollbar {
  width: 12px;
}

.scrollable-container::-webkit-scrollbar-thumb {
  background-color: #888;
  border-radius: 6px;
}

.scrollable-container::-webkit-scrollbar-track {
  background-color: #f4f4f4;
}

.row {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.search-course {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 0 0 48%;
  /* 48%로 설정하여 2개씩 나란히 보이도록 함 */
  height: 400px;
  margin: 1%;
  /* 각 카드 간의 간격 설정 */
  display: flex;
  align-items: center;
  padding: 10px;
  border: 1px solid #ddd;
  cursor: pointer;
  transition: background-color 0.3s;
}

.course:hover {
  background-color: #f4f4f4;
}

.course-image {
  width: 100px;
  height: 100px;
  margin-right: 10px;
  object-fit: cover;
  border-radius: 5px;
}

.info-container {
  flex: 1;
  text-align: center;
}

.view-good {
  display: flex;
  justify-content: end;
}

.icon-center {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.icon {
  width: 20px;
  margin-right: 5px;
}

.name {
  font-size: 1.2rem;
  margin-top: 5px;
}

.address {
  font-size: 0.9rem;
  color: #777;
}</style>
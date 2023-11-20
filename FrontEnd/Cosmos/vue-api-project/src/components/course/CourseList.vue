<template>
  <div>
    <h4>CourseList</h4>
    <KakaoMap/>
    <Weather/>

    <div class="course-list" v-for="c in course">
      <div class="course" @click="goDetail(c.num)">
        <img src="" alt="사진">
        <div>{{ c.name }}</div>
        <div>{{ c.address }}</div>
        <div>{{ c.viewCnt }}</div>
        <div>{{ c.good }}</div>
      </div> 
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import KakaoMap from '../map/KakaoMap.vue';
import Weather from '../map/weather.vue';

const router = useRouter();

const course = ref([
  {
    num: 1,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
  },
  {
    num: 2,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
  },
  {
    num: 3,
    name: "이름1",
    address: "주소1",
    viewCnt: 3,
    good: 1,
  }
])


const goDetail = function(num){
  router.push({ name: "courseDetail", params: { num: num } })
}

</script>

<style scoped>

</style>
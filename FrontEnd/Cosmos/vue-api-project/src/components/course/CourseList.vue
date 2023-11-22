<template>
  <div>
    <div class="header">
      <!-- <Weather/> -->
      <KakaoMap/>
    </div>
    <button @click="create">새 코스 등록</button>
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

const create = function(){
  router.push({ name: "courseCreate" })
}

const goDetail = function(num){
  router.push({ name: "courseDetail", params: { num: num } })
}

</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-around;
}
</style>
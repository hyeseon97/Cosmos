<template>
  <div>
    <h4>TravelDetail</h4>
    <div v-for="t in travel" :key="t.num">
      <div>{{ t.name }}</div>
      <div>{{ t.address }}</div>
    </div>
    <div class="plan-button">
      <div @click.stop.prevent="update">수정</div>
      <div @click.stop.prevent="remove">삭제</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute();
const router = useRouter();

const travel = ref([
  {
    num: 1,
    name: "한밭수목원",
    address: "대전 서구"
  },
  {
    num: 2,
    name: "엑스포 다리",
    address: "대전 서구"
  },
  {
    num: 3,
    name: "성심당",
    address: "대전 대덕구"
  },
])

const update = function(){
  router.push({ name: "travelUpdate", params: { num: route.params.num } })
}

const remove = function(){
  console.log("삭제")
}

</script>

<style scoped></style>
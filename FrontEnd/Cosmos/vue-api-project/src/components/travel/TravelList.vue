<template>
    <div class="plans">
      <div v-for="plan in plans" :key="plan.num" class="plan" @click="goDetail(plan.num)">
        <div class="plan-icon">아이콘</div>
        <div class="plan-name">{{ plan.name }}</div>
        <div class="plan-date">{{ plan.date }}</div>
      </div>

      <div v-for="no in noplans" class="add" @click="addPlan">
        <div class="add-name">새 여행 등록</div>
      </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const router = useRouter()

const plans = ref([
  {
    num: 1,
    icon: "111",
    name: "한밭수목원",
    date: "2023-11-03"
  },
  {
    num: 2,
    icon: "111",
    name: "제주도",
    date: "2023-12-25"
  }
])

const noplans = ref([
  {},
])

const goDetail = function (num) {
  router.push({ name: "travelDetail", params: { num: num } })
}

const addPlan = function(){
  router.push({ name: "travelCreate" })
}


</script>

<style scoped>
.plans {
  display: flex;
  height: 100%;
  justify-content: center; /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center; /* 세로 중앙 정렬을 위한 스타일 */
}

.plan,
.add {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center; /* 세로 중앙 정렬을 위한 스타일 */
  width: 30vh;
  height: 60vh;
  margin: 0 3vh;
  border-radius: 50px;
  font-size: 1.2rem;
  font-weight: bold;

  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out; /* 두 가지 트랜지션을 한 줄에 나열 */
}

.plan {
  /* background-color: #F18F01; */
  background: linear-gradient(to bottom, #68a67d, #24613b);
  color: white;
}

.plan:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* 마우스 오버시 그림자 강화 */
  transform: scale(1.1); /* 마우스 오버시 크기를 1.1배로 확대 */
}

.add {
  background: linear-gradient(to bottom, #F5ECD7, #c2baa6);
  color: #24613b;
}

.add:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* 마우스 오버시 그림자 강화 */
  transform: scale(1.1); /* 마우스 오버시 크기를 1.1배로 확대 */
}

.plan-icon{
  margin: 10% 0 50% 0;
}

.plan-name{
  margin: 0 0 10% 0;
}




</style>
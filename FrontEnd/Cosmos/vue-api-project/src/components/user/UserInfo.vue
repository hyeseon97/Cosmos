<template>
  <div>
    <h4>UserInfo</h4>
    <button @click="update">내정보수정2</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

const update = function(){
  router.push({ name: "userUpdate" })
}
</script>

<style scoped>

</style>
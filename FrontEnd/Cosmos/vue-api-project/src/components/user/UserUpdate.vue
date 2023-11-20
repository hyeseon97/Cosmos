<template>
  <div>
    <h4>UserUpdate</h4>
    <div>
      <input type="text" v-model="user.id" placeholder="{{ user.id }}" readonly>
    </div>
    <div>
      <input type="password" v-model="user.pw" placeholder="PASSWORD">
    </div>
    <div>
      <input type="text" v-model="user.name" placeholder="NAME">
    </div>
    <div>
      <input type="date" v-model="user.birth">
    </div>
    <button @click="update">수정</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

const user = ref({
  id: "hyeseon",
  pw: '',
  name: '',
  birth: '',
})

const update = function(){
  console.log(user.value)
  router.push({ name: "info" })
}

</script>

<style scoped>

</style>
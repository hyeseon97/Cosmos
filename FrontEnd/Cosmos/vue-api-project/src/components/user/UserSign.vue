<template>
  <div>
    <h4>UserSign</h4>
    <div>
      <input type="text" v-model="user.id" placeholder="ID">
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
    <button @click="sign">가입</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

const user = ref({
  id: '',
  pw: '',
  name: '',
  birth: '',
})

const sign = function(){
  console.log(user.value)
  router.push({ name: "login" })
}

</script>

<style scoped>

</style>
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
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '../../stores/user';
import { useRoute, useRouter} from 'vue-router';

const userStore = useUserStore();
const router = useRouter();

const user = ref({
  id: '',
  pw: '',
  name: '',
  birth: '',
})

const sign = function(){
  console.log(user.value)
  router.push({ name: "sign" })
}

</script>

<style scoped>

</style>
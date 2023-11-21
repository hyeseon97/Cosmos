<template>
  <div>
    <h4>UserUpdate</h4>
    <div>
      <input type="text" v-model="userStore.user.user_id" readonly>
    </div>
    <div>
      <input type="password" v-model="userStore.user.user_pw" placeholder="PASSWORD">
    </div>
    <div>
      <input type="text" v-model="userStore.user.user_name" placeholder="NAME">
    </div>
    <div>
      <input type="date" v-model="userStore.user.user_birth">
    </div>
    <button @click="update">수정</button>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '../../stores/user';
import { useRoute, useRouter} from 'vue-router';

const userStore = useUserStore();

const update = function(){
  console.log(userStore.user.value)
  userStore.updateUser()
}

onMounted(() => {
  userStore.getUser(userStore.loginUserId)
})

</script>

<style scoped>

</style>
<template>
  <div>
    <h4>UserUpdate</h4>
    <div>
      <div>아이디</div>
      <input type="text" v-model="userStore.user.user_id" readonly>
    </div>
    <div>
      <div>패스워드</div>
      <input type="password" v-model="userStore.user.user_pw" placeholder="PASSWORD">
    </div>
    <div>
      <div>이름</div>
      <input type="text" v-model="userStore.user.user_name" placeholder="NAME">
    </div>
    <div>
      <div>생년월일</div>
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
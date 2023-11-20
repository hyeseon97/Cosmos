<template>
  <div class="template">
    <input type="text" v-model="user.user_id" placeholder="ID">
    <input type="password" v-model="user.user_pw" placeholder="PASSWORD">
    <div class="hide-button">
      <input type="text" class="input-name" v-model="user.name" placeholder="NAME">
      <input type="date" class="input-birth" v-model="user.birth">
    </div>

    <div class="buttons">
      <div>
        <button @click="login" class="login-button">로그인</button>
      </div>
      <div>
        <button @click="sign" class="sign-button">회원가입</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useUserStore();

// const id = ref('')
// const pw = ref('')

const user = ref(
  {
    user_id: '',
    user_pw: ''
  }
)

const login = function () {
  // console.log(user.value)
  store.userLogin(user)
}

const sign = function () {
  // router.push({ name: "sign" })
}
</script>

<style scoped>
.template {
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  height: 100%;
  margin-top: 20vh;
}

input {
  width: 30%;
  /* height: 50px; */
  margin: 5px;
  padding: 15px;
  outline: none;
  border: 4px solid white;
  border-radius: 50px;
  transition: border-color 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

input:focus {
  border-color: #FF8E9E;
  /* 클릭 시 테두리 색상 변경 */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  /* 마우스 오버시 그림자 강화 */
  transform: scale(1.1);
  /* 마우스 오버시 크기를 1.1배로 확대 */
}

.buttons {
  width: 30%;
}

button {
  width: 100%;
  /* height: 50px; */
  margin-top: 5px;
  padding: 15px;
  /* margin-right: 8px; */
  outline: none;
  background-color: #FF8E9E;
  border: 4px solid white;
  border-radius: 50px;
  transition: border-color 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

button:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  /* 마우스 오버시 그림자 강화 */
  transform: scale(1.1);
  /* 마우스 오버시 크기를 1.1배로 확대 */
}



</style>

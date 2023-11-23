<template>
  <div class="login-wrap">
    <div class="login-html">
      <img src="../../assets/free-icon-pink-cosmos-8116980.png" style="width: 80px; margin-left: 150px;">
      <div class="login-container">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">로그인</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">회원가입</label>
        <div class="login-form">
          <div class="sign-in-htm">
            <div class="group">
              <label for="user" class="label">아이디</label>
              <input v-model="user.user_id" id="user" type="text" class="input" placeholder="아이디를 입력하세요">
            </div>
            <div class="group">
              <label for="pass" class="label">비밀번호</label>
              <input v-model="user.user_pw" id="pass" type="password" class="input" data-type="password" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="group">
              <input id="check" type="checkbox" class="check" checked>
              <label for="check"><span class="icon"></span> 아이디 기억하기</label>
            </div>
            <div class="group">
              <button @click="login" class="button">로그인</button>
            </div>
            <div class="hr"></div>
            <div class="foot-lnk">
              <a href="#forgot">Forgot Password?</a>
            </div>
          </div>
          <div class="sign-up-htm">
            <div class="group">
              <label for="user" class="label">아이디</label>
              <input v-model="signUp.user_id" type="text" class="input">
            </div>
            <div class="group">
              <label for="pass" class="label">비밀번호</label>
              <input v-model="signUp.user_pw" type="password" class="input" data-type="password">
            </div>
            <div class="group">
              <label for="pass" class="label">이름</label>
              <input v-model="signUp.user_name" id="pass" type="text" class="input">
            </div>
            <div class="group">
              <label for="pass" class="label">생년월일</label>
              <input v-model="signUp.user_birth" id="pass" type="date" class="input">
            </div>
            <div class="group">
              <button @click="signBtn" class="button">가입하기</button>
            </div>
            <div class="hr"></div>
            <div class="foot-lnk">
              <label for="tab-1">Already Member?</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter();
const useStore = useUserStore();

const user = ref({
  user_id: '',
  user_pw: ''
});

const login = () => {
  useStore.userLogin(user)
};

const sign = () => {
  router.push({ name: sign });
}

const signUp = ref({
  user_id: '',
  user_pw: '',
  user_name: '',
  user_birth: '',
});

const signBtn = function () {
  console.log(signUp.value)
  useStore.signup(signUp)
  router.push({ name: "home" })
}


</script>

<style scoped>
body {
  margin: 0;
  color: #6a6f8c;
  background: #171717;
  font: 600 16px/18px 'Open Sans', sans-serif;
}

*,
:after,
:before {
  box-sizing: border-box
}

.clearfix:after,
.clearfix:before {
  content: '';
  display: table
}

.clearfix:after {
  clear: both;
  display: block
}

a {
  color: inherit;
  text-decoration: none
}

.login-wrap {
  width: 100%;
  margin: auto;
  max-width: 525px;
  min-height: 670px;
  position: relative;

}

.login-html {
  width: 100%;
  height: 104%;
  position: absolute;
  margin-top: 3em;
  padding: 90px 70px 50px 70px;
  background: #8FBF9F;
}

.login-html .sign-in-htm,
.login-html .sign-up-htm {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  transform: rotateY(180deg);
  backface-visibility: hidden;
  transition: all .4s linear;
}

.login-container {
  margin-top: 3em;
}

.login-html .sign-in,
.login-html .sign-up,
.login-form .group .check {
  display: none;
}

.login-html .tab,
.login-form .group .label,
.login-form .group .button {
  text-transform: uppercase;
}

.login-html .tab {
  font-size: 22px;
  margin-right: 15px;
  padding-bottom: 5px;
  margin: 0 15px 10px 0;
  display: inline-block;
  border-bottom: 2px solid transparent;
}

.login-html .sign-in:checked+.tab,
.login-html .sign-up:checked+.tab {
  color: #000000;
  border-color: #FF8E9E;
}

.login-form {
  min-height: 345px;
  position: relative;
  perspective: 1000px;
  transform-style: preserve-3d;
}

.login-form .group {
  margin-bottom: 15px;
}

.login-form .group .label,
.login-form .group .input,
.login-form .group .button {
  width: 100%;
  color: #fff;
  display: block;
}

.login-form .group .input,
.login-form .group .button {
  border: none;
  padding: 15px 20px;
  border-radius: 25px;
  background: rgba(255, 255, 255, .1);
}

/* .login-form .group input[data-type="password"]{
	text-security:circle;
	-webkit-text-security:circle;
} */
.login-form .group .label {
  color: #171717;
  font-size: 12px;
}

.login-form .group .button {
  background: #FF8E9E;
}

.login-form .group label .icon {
  width: 15px;
  height: 15px;
  border-radius: 2px;
  position: relative;
  display: inline-block;
  background: rgba(255, 255, 255, .1);
}

.login-form .group label .icon:before,
.login-form .group label .icon:after {
  content: '';
  width: 10px;
  height: 2px;
  background: #fff;
  position: absolute;
  transition: all .2s ease-in-out 0s;
}

.login-form .group label .icon:before {
  left: 3px;
  width: 5px;
  bottom: 6px;
  transform: scale(0) rotate(0);
}

.login-form .group label .icon:after {
  top: 6px;
  right: 0;
  transform: scale(0) rotate(0);
}

.login-form .group .check:checked+label {
  color: #fff;
}

.login-form .group .check:checked+label .icon {
  background: #FF8E9E;
}

.login-form .group .check:checked+label .icon:before {
  transform: scale(1) rotate(45deg);
}

.login-form .group .check:checked+label .icon:after {
  transform: scale(1) rotate(-45deg);
}

.login-html .sign-in:checked+.tab+.sign-up+.tab+.login-form .sign-in-htm {
  transform: rotate(0);
}

.login-html .sign-up:checked+.tab+.login-form .sign-up-htm {
  transform: rotate(0);
}

input {
  outline: none;
}

.hr {
  height: 2px;
  margin: 60px 0 50px 0;
  background: #FF8E9E;
}

.foot-lnk {
  text-align: center;
}</style>
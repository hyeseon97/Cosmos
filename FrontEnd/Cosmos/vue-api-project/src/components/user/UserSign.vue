<template>
  <div>
    <h4>UserSign</h4>
    <div>
      <input type="text" v-model="user.user_id" placeholder="ID">
    </div>
    <div>
      <input type="password" v-model="user.user_pw" placeholder="PASSWORD">
    </div>
    <div>
      <input type="text" v-model="user.user_name" placeholder="NAME">
    </div>
    <div>
      <input type="date" v-model="user.user_birth">
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

//user.user_id 는 변수명 user에 아래 dto와 맞춘 이름을 이용한다.
const user = ref({
  user_id: '',
  user_pw: '',
  user_name: '',
  user_birth: '',
})

const sign = function(){
  console.log(user.value)
  userStore.signup(user)
  router.push({ name: "login" })
}

</script>

<style scoped>

</style>



<!-- <template>
  <div>
    <h4>UserSign</h4>
    <div :class="{ 'invalid-field': !isIdValid }">
      <input type="text" v-model="user.user_id" placeholder="ID">
      <span v-if="!isIdValid" class="check-mark">&#10007;</span>
    </div>
    <div :class="{ 'invalid-field': !isPasswordValid }">
      <input type="password" v-model="user.user_pw" placeholder="PASSWORD">
      <span v-if="!isPasswordValid" class="check-mark">&#10007;</span>
    </div>
    <div>
      <input type="text" v-model="user.user_name" placeholder="NAME">
    </div>
    <div>
      <input type="date" v-model="user.user_birth">
    </div>
    <button @click="sign" :disabled="!isIdValid || !isPasswordValid">가입</button>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '../../stores/user';
import { useRoute, useRouter } from 'vue-router';

const userStore = useUserStore();
const router = useRouter();

const user = ref({
  user_id: '',
  user_pw: '',
  user_name: '',
  user_birth: '',
});

const isIdValid = computed(() => /^[a-zA-Z0-9]{4,}$/.test(user.value.user_id));
const isPasswordValid = computed(() => /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(user.value.user_pw));

const sign = function () {
  if (isIdValid.value && isPasswordValid.value) {
    console.log(user.value);
    userStore.signup(user);
    router.push({ name: "login" });
  }
};
</script>

<style scoped>
.invalid-field {
  border: 1px solid red;
}

.check-mark {
  color: red;
  margin-left: 5px;
}
</style> -->
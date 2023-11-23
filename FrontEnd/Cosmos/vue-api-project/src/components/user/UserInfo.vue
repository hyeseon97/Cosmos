<template>
  <h1>마이페이지</h1>
  <div class="mypage-container">
    <div class="mypage-image-container">
      <input type="hidden" v-model="userStore.user.user_grade">
      <img v-if="userStore.user.user_grade === 1" class="mypage-image" src="../../assets/grade_1.png" alt="Grade 1 Image">
      <img v-else-if="userStore.user.user_grade === 2" class="mypage-image" src="../../assets/grade_2.png"
        alt="Grade 2 Image">
      <img v-else-if="userStore.user.user_grade === 3" class="mypage-image" src="../../assets/grade_3.png"
        alt="Grade 3 Image">
      <img v-else class="mypage-image" src="../../assets/grade_4.png" alt="Grade 4 Image">
    </div>

    <div class="mypage-input-container">
      <div>아이디</div>
      <input type="text" class="mypage-input" v-model="userStore.user.user_id" :readonly="true">

      <div>패스워드</div>
      <input type="password" class="mypage-input" v-model="userStore.user.user_pw" :readonly="!editing" @focus="highlight"
        @blur="removeHighlight">

      <div>이름</div>
      <input type="text" class="mypage-input" v-model="userStore.user.user_name" :readonly="!editing" @focus="highlight"
        @blur="removeHighlight">

      <div>생년월일</div>
      <input type="date" class="mypage-input" v-model="userStore.user.user_birth" :readonly="!editing" @focus="highlight"
        @blur="removeHighlight">

      <!-- 수정 및 취소 버튼 -->
      <div class="buttons">
        <button class="mypage-updateBtn" type="button" @click="toggleEditing">{{ editing ? '수정 완료' : '수정' }}</button>
        <button v-if="editing" class="mypage-cancelBtn" type="button" @click="cancelEditing">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useUserStore } from '../../stores/user';
import { useRouter } from 'vue-router';

const userStore = useUserStore();
const router = useRouter();
const editing = ref(false);

const update = function () {
  console.log(userStore.user.value);
  console.log("잘나오나?" + userStore.user.user_grade)
  router.push({ name: "userUpdate" });
}

const toggleEditing = function () {
  if (editing.value) {
    userStore.updateUser();
  }
  editing.value = !editing.value;
}

const highlight = function (event) {
  event.target.style.border = "3px solid #24613b";
}

const removeHighlight = function (event) {
  event.target.style.border = "1px solid #24613b";
}

const cancelEditing = function () {
  editing.value = false;
  userStore.getUser(userStore.loginUserId);
}

onMounted(() => {
  userStore.getUser(userStore.loginUserId);
})
</script>

<style scoped>
.mypage-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.mypage-image {
  max-width: 80%;
  /* 이미지의 최대 너비를 부모 요소에 맞게 조절합니다. */
  max-height: 80%;
  /* 이미지의 최대 높이를 부모 요소에 맞게 조절합니다. */
}

.mypage-input {
  width: 50%px;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
</style>
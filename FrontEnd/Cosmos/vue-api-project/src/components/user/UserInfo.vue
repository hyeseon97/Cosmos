<template>
  <div class="mypage-container">
    <div class="mypage-container2">
      <div class="mypage-image-container" style="align-items: center;">
        <img v-if="userStore.user.user_grade == 1" class="mypage-image" src="../../assets/grade_1.png" alt="Grade 1 Image"
          >
        <img v-else-if="userStore.user.user_grade == 2" class="mypage-image" src="../../assets/grade_2.png"
          >
        <img v-else-if="userStore.user.user_grade == 3" class="mypage-image" src="../../assets/grade_3.png"
          >
        <img v-else class="mypage-image" src="../../assets/grade_4.png" alt="Grade 4 Image"
          >
      </div>

      <input type="hidden" v-model="userStore.user.user_grade">
      <div class="mypage-grade" v-if="userStore.user.user_grade == 1">등급: 어린새싹</div>
      <div class="mypage-grade" v-else-if="userStore.user.user_grade == 2">등급: 청소년새싹</div>
      <div class="mypage-grade" v-else-if="userStore.user.user_grade == 3">등급: 어른새싹</div>
      <div class="mypage-grade" v-else>등급: 꽃</div>

      <div class="mypage-input-container">
        <div>아이디</div>
        <input type="text" class="mypage-input" v-model="userStore.user.user_id" :readonly="true">

        <div>패스워드</div>
        <input type="password" class="mypage-input" v-model="userStore.user.user_pw" :readonly="!editing"
          @focus="highlight" @blur="removeHighlight">

        <div>이름</div>
        <input type="text" class="mypage-input" v-model="userStore.user.user_name" :readonly="!editing" @focus="highlight"
          @blur="removeHighlight">

        <div>생년월일</div>
        <input type="date" class="mypage-input" v-model="userStore.user.user_birth" :readonly="!editing"
          @focus="highlight" @blur="removeHighlight">
      </div>
        <button class="mypage-updateBtn" id="mypage-update-btn" type="button" @click="toggleEditing">{{ editing ? '수정 완료'
          : '수정' }}</button>
        <button v-if="editing" class="mypage-cancelBtn" id="mypage-cancel-btn" type="button"
          @click="cancelEditing">취소</button>
      </div>
  </div>
</template>


<!-- <template>
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

      수정 및 취소 버튼
      <div class="buttons">
        <button class="mypage-updateBtn" id="mypage-update-btn" type="button" @click="toggleEditing">{{ editing ? '수정 완료' : '수정' }}</button>
        <button v-if="editing" class="mypage-cancelBtn" id="mypage-cancel-btn" type="button" @click="cancelEditing">취소</button>
      </div>
    </div>
  </div>
</template> -->

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
  event.target.style.border = "3px solid #FF8E9E";
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
  width: 100%;
  margin: auto;
  max-width: 525px;
  min-height: 670px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.mypage-container2 {
  border-radius: 7%;
  width: 100%;
  height: 100%;
  position: absolute;
  /* margin-top: 3em; */
  padding: 50px 70px 50px 70px;
  background: rgba(255, 255, 255, 0.7);

}

.mypage-image-container{
  border: 5px solid #FF8E9E;
  border-radius: 7%;
  width: 100%;
  height: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 5px;
 
}

.mypage-input-container{
  font-weight: bold;
}

.mypage-image {
  max-width: 40%;
  /* 이미지의 최대 너비를 부모 요소에 맞게 조절합니다. */
  max-height: 40%;
  /* 이미지의 최대 높이를 부모 요소에 맞게 조절합니다. */
}

.mypage-input {
  width: 100%;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
#mypage-cancel-btn,
#mypage-update-btn {
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  bottom: 0px;
  right: 0px;
  padding: 10px 20px;
  background-color: #8FBF9F;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
#mypage-cancel-btn:hover,
#mypage-update-btn:hover {
  background-color: #68a67d;
}

.mypage-grade{
  text-align: center;
}

@media (max-width: 600px) {
  .create-button {
    bottom: 20px;
    right: 20px;
  }
}


</style>
<template>
  <div class="container">
    <div class="mypage">
      <!-- 이미지를 v-if로 분기하여 사용 -->
      <div class="image">
        <input type="hidden" v-model="userStore.user.user_grade">
        <img v-if="userStore.user.user_grade === 1" class="png-image" src="../../assets/grade_1.png" alt="Grade 1 Image">
        <img v-else-if="userStore.user.user_grade === 2" class="png-image" src="../../assets/grade_2.png" alt="Grade 2 Image">
        <img v-else-if="userStore.user.user_grade === 3" class="png-image" src="../../assets/grade_3.png" alt="Grade 3 Image">
        <img v-else class="png-image" src="../../assets/grade_4.png" alt="Grade 4 Image">
      </div>

      <!-- 사용자 정보 입력 -->
      <div class="input-container">
        <div>
          <div>아이디</div>
          <input type="text" class="loginbg" v-model="userStore.user.user_id" :readonly="true">
        </div>

        <div>
          <div>패스워드</div>
          <input type="password" class="loginbg" v-model="userStore.user.user_pw" :readonly="!editing" @focus="highlight" @blur="removeHighlight">
        </div>

        <div>
          <div>이름</div>
          <input type="text" class="loginbg" v-model="userStore.user.user_name" :readonly="!editing" @focus="highlight" @blur="removeHighlight">
        </div>

        <div>
          <div>생년월일</div>
          <input type="date" class="loginbg" v-model="userStore.user.user_birth" :readonly="!editing" @focus="highlight" @blur="removeHighlight">
        </div>
      </div>

      <!-- 수정 및 취소 버튼 -->
      <div class="buttons">
        <button class="modifybtn" type="button" @click="toggleEditing">{{ editing ? '수정 완료' : '수정' }}</button>
        <button v-if="editing" class="modifybtn" type="button" @click="cancelEditing">취소</button>
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
  event.target.style.border = "5px solid #F18F01";
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
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 화면 전체 높이만큼 설정 */
}

.mypage {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  flex-direction: column;
  align-items: center;
}

.loginbg,
.modifybtn {
  width: 100%;
  max-width: 400px;
  font-size: 25px;
  border-top: 1px solid #24613b;
  border-right: 1px solid #24613b;
  border-left: 3px solid #8FBF9F;
  border-bottom: 3px solid #8FBF9F;
  border-radius: 8px;
  outline: none;
  padding-left: 10px;
  margin-bottom: 10px;
}

.modifybtn {
  background: #24613b;
  color: white;
}

.loginbg:focus {
  border: 2px solid black;
}

.png-image {
  width: 100%; /* 이미지 너비를 부모 요소에 맞게 조절 */
  height: auto; /* 이미지 높이를 자동으로 조정하여 비율 유지 */
}

.mypage {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  flex-direction: column;
  align-items: center;
}

.input-container {
  width: 100%;
  max-width: 400px;
}
</style>
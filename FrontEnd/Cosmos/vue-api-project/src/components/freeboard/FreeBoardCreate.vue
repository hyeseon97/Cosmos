<template>
  <div class="free-board-write-container">
    <div>
      <h1>글쓰기</h1>
    </div>
    <div class="free-board-write-input-container">
      <div class="free-board-write-title">
        제목<input type="text" v-model="freeBoard.fb_title" placeholder="제목읍 입력하세요">
      </div>
      <div class="free-board-write-content">
        내용<textarea v-model="freeBoard.fb_content" placeholder="내용을 입력하세요"></textarea>
        <input type="file" @change="imageUpload" ref="boardImage" id="photo" accept="image/*">
      </div>
      <div class="free-board-btn-container">
        <button class="free-board-btn-regist" @click="regist">등록</button>
        <button class="free-board-btn-cancel" @click="cancel">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '../../stores/freeBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

// 파일업로드
const file = ref({})
const imageUploaded = ref({})

const freeBoard = ref({
  fb_num: 0,
  fb_title: "",
  fb_writer: userStore.user.user_name,
  fb_content: "",
  fb_userId: userStore.loginUserId,
  fb_viewCnt: 0,
  fb_rcm: 0,
  fb_regDate: "",
})

const cancel = function () {
  router.push({ name: "freeBoardList" })
}

const regist = function () {
  const frm = new FormData()
  frm.append("fb_num", freeBoard.value.fb_num)
  frm.append("fb_title", freeBoard.value.fb_title)
  frm.append("fb_writer", userStore.loginUserName)
  frm.append("fb_content", freeBoard.value.fb_content)
  frm.append("fb_userId", userStore.loginUserId)
  frm.append("fb_viewCnt", freeBoard.value.fb_viewCnt)
  frm.append("fb_rcm", freeBoard.value.fb_rcm)
  frm.append("fb_regDate", freeBoard.value.fb_regDate)
  frm.append("file", file.value)
  store.createFreeBoard(frm)
}

const imageUpload = function () {
  const photo = document.getElementById("photo")
  file.value = photo.files[0];
  const image = file.value
  console.log(file.value)
  imageUploaded.value = URL.createObjectURL(image)
}

onMounted(() => {
  userStore.getUser(userStore.loginUserId);
})

</script>

<style scoped>
h1 {
  font-size: 30px;
  font-weight: 600;
}

.free-board-write-input-container {
  padding: 20px;
}

.free-board-write-input-container input,
textarea {
  margin-left: 10px;
  padding: 10px;
  border: 1px solid #999;
  border-radius: 10px;
  box-shadow: 3px 3px 10px #e6e6e6;
}

.free-board-write-input-container input[type="text"] {
  width: 25%;
  margin-right: 50px;
}

.free-board-write-input-container .free-board-write-title input[type="text"] {
  margin-top: 20px;
  margin-bottom: 20px;
  width: 85.5%;
}

.free-board-write-input-container .free-board-write-content textarea {
  min-width: 85.5%;
  max-width: 85.5%;
  min-height: 200px;
  max-height: 200px;
  box-shadow: inset 3px 3px 10px #e6e6e6;

  vertical-align: top;
}

.free-board-write-input-container input[type="file"] {
  border: none;
  box-shadow: none;
  ;
  padding: 10px;
  margin-left: 40px;
}

.free-board-btn-container {
  width: 100%;
  margin-top: 30px;
  display: flex;
  justify-content: flex-end;
  padding-right: 11%;
  /* 가로로 균등하게 정렬하려면 추가 */
}

.free-board-btn-regist,
.free-board-btn-cancel {
  margin-left: 5px;
  padding: 10px;
  background-color: #f7f2e4;
  /* 배경색은 원하는 색상으로 변경 */
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.free-board-btn-regist:hover {
  background-color: #ffe0e4;
  /* 마우스 호버 시 배경색 변경 */
}

.free-board-btn-cancel:hover {
  background-color: #ffe0e4;
}

.free-board-btn-regist.active {
  background-color: #fff27c;
  /* 활성 상태일 때 배경색 변경 */
}</style>
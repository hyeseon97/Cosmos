<template>
  
    <div class="free-board-update">
      <h1>게시물 수정</h1>
      <div>
        <div class="free-board-update-title">
          <h4>제목</h4>
          <h2><input class="free-board-update-title-input" type="text" v-model="store.freeBoard.fb_title"></h2>
        </div>
      </div>
      <div>
        <div class="free-board-update-content">
          <h4>내용</h4>
          <input class="free-board-update-content-input" type="text" v-model="store.freeBoard.fb_content">
        </div>
      </div>

      <div class="free-board-update-btn">
        <button class="free-board-update-updateBtn" @click="update">수정</button>
        <button class="free-board-update-cancelBtn" @click="cancel">취소</button>
      </div>

    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useFreeBoardStore } from '@/stores/freeBoard'

const route = useRoute();
const router = useRouter();
const store = useFreeBoardStore();

const update = function () {
  store.updateFreeBoard()
}

const cancel = function () {
  router.push({ name: "freeBoardDetail", params: { num: route.params.num } })
}

</script>

<style scoped>
.free-board-update {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid #ddd;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.free-board-update-title,
.free-board-update-content {
  margin-top: 20px;
  margin-bottom: 20px;
}

.free-board-update-btn{
  float: right;
}

.free-board-update-updateBtn,
.free-board-update-cancelBtn
 {
  background-color: #68a67d;
  color: white;
  padding: 10px 15px;
  margin-right: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}

.free-board-update-title-input,
.free-board-update-content-input{
  width: 100%;
  height: 40px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

.free-board-update-content-input{
  height: 100px;
}
</style>
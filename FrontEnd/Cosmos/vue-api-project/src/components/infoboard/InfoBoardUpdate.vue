<template>
  
  <div class="info-board-update">
    <h1>게시물 수정</h1>
    <div>
      <div class="info-board-update-title">
        <h4>제목</h4>
        <h2><input class="info-board-update-title-input" type="text" v-model="store.infoBoard.fb_title"></h2>
      </div>
    </div>
    <div>
      <div class="info-board-update-content">
        <h4>내용</h4>
        <input class="info-board-update-content-input" type="text" v-model="store.infoBoard.fb_content">
      </div>
    </div>

    <div class="info-board-update-btn">
      <button class="info-board-update-updateBtn" @click="update">수정</button>
      <button class="info-board-update-cancelBtn" @click="cancel">취소</button>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useInfoBoardStore } from '../../stores/infoBoard';

const route = useRoute();
const router = useRouter();

const store = useInfoBoardStore()

const update = function(){
  store.updateInfoBoard()
}

const cancel = function(){
  router.push({ name: "infoBoardDetail", params: { num: route.params.num } })
}

</script>

<style scoped>
.info-board-update {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid #ddd;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.info-board-update-title,
.info-board-update-content {
  margin-top: 20px;
  margin-bottom: 20px;
}

.info-board-update-btn{
  float: right;
}

.info-board-update-updateBtn,
.info-board-update-cancelBtn
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

.info-board-update-title-input,
.info-board-update-content-input{
  width: 100%;
  height: 40px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

.info-board-update-content-input{
  height: 100px;
}
</style>
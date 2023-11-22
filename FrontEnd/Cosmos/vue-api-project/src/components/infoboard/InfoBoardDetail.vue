<template>
  <div class="info-board-detail">
    <h4>InfoBoardDetail</h4>
    <div class="board-detail-item">{{ infoBoard.ib_title }}</div>
    <div class="board-detail-item">{{ infoBoard.ib_content }}</div>
    <div class="board-detail-item">Views: {{ infoBoard.ib_viewCnt }}</div>
    <div class="board-detail-item">Likes: {{ infoBoard.ib_good }}</div>
    <div class="board-detail-item">Posted on: {{ infoBoard.ib_regDate }}</div>
    <button @click="update(infoBoard.ib_num)" v-if="user !== null">수정</button>
    <button @click="remove" v-if="user !== null">삭제</button>
    <InfoBoardCommentVue/>
  </div>
</template>

<script setup>
import InfoBoardCommentVue from './InfoBoardComment.vue';

import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'

import { useInfoBoardStore } from '../../stores/infoBoard';

const route = useRoute();
const router = useRouter();

const store = useInfoBoardStore();

const user = ref("11");

onMounted(() => {
  store.getInfoBoard(route.params.num)
})

const infoBoard = computed(()=>store.infoBoard)  

const update = function(num){
  router.push({ name: "infoBoardUpdate", params: { num: num } })
}

const remove = function(){
  store.deleteInfoBoard(infoBoard.value.ib_num)
}

</script>

<style scoped>
.info-board-detail {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.board-detail-item {
  margin-bottom: 10px;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}
</style>
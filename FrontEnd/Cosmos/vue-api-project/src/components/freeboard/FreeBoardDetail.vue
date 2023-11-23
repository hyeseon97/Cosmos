<template>
  <div class="free-board-detail">
    <div>
      <div class="board-detail-item">
        <h2>제목입니다.{{ freeBoard.fb_title }}</h2>
      </div>
      <div>작성자: {{ freeBoard.fb_wrtier }}</div>
      <span>조회수: {{ freeBoard.fb_viewCnt }}</span>
      <span>작성일: {{ freeBoard.fb_regDate }}</span>
    </div>
    <div>
      <div class="board-detail-item">{{ freeBoard.fb_content }}</div>
    </div>

    <div>
      <button @click="update(freeBoard.ib_num)" v-if="userStore.loginUserId==freeBoard.fb_wrtier">수정</button>
      <button @click="remove" v-if="userStore.loginUserId==freeBoard.fb_wrtier">삭제</button>
    </div>
  </div>
  <div class="free-board-comment">
    <FreeBoardCommentVue />
  </div>
</template>

<script setup>
import FreeBoardCommentVue from './FreeBoardComment.vue';

import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'

import { useFreeBoardStore } from '@/stores/freeBoard'
import { useUserStore } from '@/stores/user'

const store = useFreeBoardStore();
const userStore = useUserStore();

const route = useRoute();
const router = useRouter();

// 로그인유저 저장
// const user = ref(null);
const user = ref("");

// console.log("현재 게시글은 " + route.params.num + "번 게시글");


onMounted(() => {
  store.getFreeBoard(route.params.num)
})

const freeBoard = computed(()=>store.freeBoard)  

// 수정 버튼
const update = function(num){
  router.push({ name: "freeBoardUpdate", params: { num: num } })
}

// 삭제 버튼
const remove = function(){
  store.deleteFreeBoard(freeBoard.value.fb_num)
}



</script>

<style scoped>
.free-board-detail,
.free-board-comment {
  max-width: 600px;
  margin:  auto;
  margin-bottom: 10px;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid #ddd;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.board-detail-item {
  margin-bottom: 10px;
}

button {
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
</style>
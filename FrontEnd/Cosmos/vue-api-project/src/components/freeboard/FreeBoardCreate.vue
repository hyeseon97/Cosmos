<template>
  <div>
    <h4>FreeBoardCreate</h4>
    <div>
      <input type="text" v-model="freeBoard.fb_title" placeholder="제목">
    </div>
    <div>
      <textarea cols="30" rows="10" v-model="freeBoard.fb_content" placeholder="내용"></textarea>
    </div>
    <button @click="cancel">취소</button>
    <button @click="regist">등록</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '../../stores/freeBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

const freeBoard = ref({
  fb_num: 0,
  fb_title: "",
  fb_writer: "",
  fb_content: "",
  fb_userId: userStore.loginUserId,
  fb_viewCnt: 0,
  fb_rcm: 0,
  fb_regDate: "",
})

const cancel = function(){
  router.push({ name: "freeBoardList" })
}

const regist = function () {
  store.createFreeBoard(freeBoard)
}

</script>

<style scoped></style>
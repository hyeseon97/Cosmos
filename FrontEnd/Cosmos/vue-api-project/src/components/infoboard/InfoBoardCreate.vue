<template>
  <div>
    <h4>InfoBoardCreate</h4>
    <div>
      <input type="text" v-model="infoBoard.ib_title" placeholder="제목">
    </div>
    <div>
      <textarea cols="30" rows="10" v-model="infoBoard.ib_content" placeholder="내용"></textarea>
    </div>
    <button @click="cancel">취소</button>
    <button @click="regist">등록</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useInfoBoardStore } from '../../stores/infoBoard';

const router = useRouter();
const store = useInfoBoardStore();


const infoBoard = ref({
  ib_num: 0,
  ib_title: "",
  ib_content: "",
  ib_viewCnt: 0,
  ib_rcm: 0,
  ib_regDate: "",
})

const cancel = function(){
  router.push({ name: "infoBoardList" })
}

const regist = function () {
  store.createInfoBoard(infoBoard)
}

</script>

<style scoped></style>
<template>
  <div>
    <h4>InfoBoardDetail</h4>
    <div>{{ infoBoard.ib_title }}</div>
    <div>{{ infoBoard.ib_content }}</div>
    <div>{{ infoBoard.ib_viewCnt }}</div>
    <div>{{ infoBoard.ib_good }}</div>
    <div>{{ infoBoard.ib_regDate }}</div>
    <button @click="update(infoBoard.ib_num)" v-if="user!=null">수정</button>
    <button @click="remove" v-if="user!=null">삭제</button>
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

// 로그인유저 저장
// const user = ref(null);
const user = ref("11");

console.log("현재 게시글은 " + route.params.num + "번 게시글");

onMounted(() => {
  store.getInfoBoard(route.params.num)
})

const infoBoard = computed(()=>store.infoBoard)  



// 수정 버튼
const update = function(num){
  router.push({ name: "infoBoardUpdate", params: { num: num } })
}

// 삭제 버튼
const remove = function(){
  store.deleteInfoBoard(infoBoard.value.ib_num)
}

</script>

<style scoped>

</style>
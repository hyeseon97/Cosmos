<template>
  <div>
    <h4>FreeBoardDetail</h4>
    <div>{{ freeBoard.fb_title }}</div>
    <div>{{ freeBoard.fb_wrtier }}</div>
    <div>{{ freeBoard.fb_content }}</div>
    <div>{{ freeBoard.fb_viewCnt }}</div>
    <div>{{ freeBoard.fb_good }}</div>
    <div>{{ freeBoard.fb_regDate }}</div>
    <button @click="update(freeBoard.fb_num)" v-if="user!=null">수정</button>
    <button @click="remove" v-if="user!=null">삭제</button>
    <FreeBoardCommentVue/>
  </div>
</template>

<script setup>
import FreeBoardCommentVue from './FreeBoardComment.vue';

import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'

import { useFreeBoardStore } from '@/stores/freeBoard'

const store = useFreeBoardStore();

const route = useRoute();
const router = useRouter();

// 로그인유저 저장
// const user = ref(null);
const user = ref("11");

console.log("현재 게시글은 " + route.params.num + "번 게시글");


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

</style>
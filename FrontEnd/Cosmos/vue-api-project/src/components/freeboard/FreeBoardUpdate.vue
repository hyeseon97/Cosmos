<template>
  <div>
    <h4>FreeBoardUpdate</h4>
    <div>
      <input type="text" v-model="store.freeBoard.fb_title">
    </div>
    <div>
      <textarea cols="30" rows="10" v-model="store.freeBoard.fb_content"></textarea>
    </div>
    <button @click="update">수정</button>
    <button @click="cancel">취소</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useFreeBoardStore } from '@/stores/freeBoard'

const route = useRoute();
const router = useRouter();
const store = useFreeBoardStore();

const update = function(){
  store.updateFreeBoard()
}

const cancel = function(){
  router.push({ name: "freeBoardDetail", params: { num: route.params.num } })
}

</script>

<style scoped>

</style>
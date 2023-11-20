<template>
  <div>
    <h4>InfoBoardUpdate</h4>
    <div>
      <input type="text" v-model="store.infoBoard.ib_title">
    </div>
    <div>
      <textarea cols="30" rows="10" v-model="store.infoBoard.ib_content"></textarea>
    </div>
    <button @click="update">수정</button>
    <button @click="cancel">취소</button>
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

</style>
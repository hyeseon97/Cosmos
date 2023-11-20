<template>
  <div>
    <h4>InfoBoardComment</h4>
    <input type="text" @keyup.enter="create" v-model="comment.ic_content" placeholder="댓글을 입력하세요">
    <button @click="create">등록</button>

    <table>
      <tr v-for="(item, index) in infoCommentList" :key="index">
        <td>{{ item.ic_content }}</td>
        <td>{{ item.ic_userName }}</td>
        <td>{{ item.ic_regdate }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useInfoCommentStore } from '../../stores/infoBoardComment';
import { useUserStore } from '../../stores/user';

const route = useRoute();
const store = useInfoCommentStore();
const userStore = useUserStore();

const infoCommentList = computed(()=> store.infoCommentList)

onMounted(()=>{
  store.getInfoCommentList(route.params.num)
})

const comment = ref({
  ic_boardNum: route.params.num,
  ic_userId: userStore.loginUserId,
	ic_userName: 'aaa',
	ic_content: ''
})

const create = function(){
  console.log("comment " + route.params.num)
  store.createComment(comment)
  .then(()=>{
    store.getInfoCommentList(route.params.num)
    comment.value.ic_content = ''
  })
}

</script>

<style scoped>

</style>
<template>
  <div>
    <h4>FreeBoardComment</h4>
    <input type="text" @keyup.enter="create" v-model="comment.fc_content" placeholder="댓글을 입력하세요">
    <button @click="create">등록</button>

    <table>
      <tr v-for="(item, index) in freeCommentList" :key="index">
        <td>{{ item.fc_content }}</td>
        <td>{{ item.fc_userName }}</td>
        <td>{{ item.fc_regdate }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useFreeCommentStore } from '../../stores/freeBoardComment';
import { useUserStore } from '../../stores/user';

const route = useRoute();
const store = useFreeCommentStore();
const userStore = useUserStore();

const freeCommentList = computed(()=> store.freeCommentList)

onMounted(()=>{
  store.getFreeCommentList(route.params.num)
})

const comment = ref({
  fc_boardNum: route.params.num,
  fc_userId: userStore.loginUserId,
	fc_userName: 'aaa',
	fc_content: ''
})

const create = function(){
  console.log("comment " + route.params.num)
  store.createComment(comment)
  .then(()=>{
    store.getFreeCommentList(route.params.num)
    comment.value.fc_content = ''
  })
}

</script>

<style scoped>

</style>
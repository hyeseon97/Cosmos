<template>
  <div>
    <h4>댓글목록</h4>
    <div class="info-board-comment-input-container">
      <input class="info-comment-input" type="text" @keyup.enter="create" v-model="comment.ic_content" placeholder="댓글을 입력하세요">
      <button @click="create">등록</button>
    </div>

    <table>
      <tr v-for="(item, index) in infoCommentList" :key="index">
        <td>
          <h4>{{ item.ic_userName }}</h4>
          <span class="comment-date">{{ item.ic_regdate }}</span>
        </td>
        <td>{{ item.ic_content }}</td>
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
const commentStore = useInfoCommentStore();
const userStore = useUserStore();

const infoCommentList = computed(()=> store.infoCommentList)

const comment = ref({
  ic_boardNum: route.params.num,
  ic_userId: userStore.loginUserId,
	ic_userName: userStore.user.user_name,
	ic_content: ''
})

const create = function(){
  commentStore.createComment(comment)
  .then(()=>{
    commentStore.getInfoCommentList(route.params.num)
    comment.value.ic_content = ''
  })
}

onMounted(()=>{
  userStore.getUser(userStore.loginUserId);
  commentStore.getInfoCommentList(route.params.num)
})

</script>

<style scoped>
.info-board-comment-input-container {
  display: flex;
  gap: 10px;
}

.info-comment-input {
  flex: 1;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

button {
  height: 32px;
  background-color: #68a67d;
  color: white;
  padding: 0 15px;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  font-size: 15px;
}


button:hover {
  background-color: #45a049;
}

table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
}

tr {
  border-bottom: 1px solid #ddd;
}

td {
  padding: 10px;
}

h4, .comment-date {
  margin-bottom: 5px;
}

.comment-date {
  color: #888;
  font-size: 12px;
}

</style>
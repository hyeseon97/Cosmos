<template>
  <div>
    <h4>댓글목록</h4>
    <div class="free-board-comment-input-container">
      <input class="free-comment-input" type="text" @keyup.enter="create" v-model="comment.fc_content" placeholder="댓글을 입력하세요">
      <button @click="create">등록</button>
    </div>

    <table>
      <tr v-for="(item, index) in freeCommentList" :key="index">
        <td>
          <h4>{{ item.fc_userName }}</h4>
          <span class="comment-date">{{ item.fc_regdate }}</span>
        </td>
        <td>{{ item.fc_content }}</td>
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
.free-board-comment-input-container {
  display: flex;
  gap: 10px;
}

.free-comment-input {
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
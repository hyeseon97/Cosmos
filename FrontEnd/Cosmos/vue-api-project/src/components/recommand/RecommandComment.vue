<template>
  <div class="comment-container">
    <input type="text" @keyup.enter="create" v-model="comment" placeholder="댓글을 입력하세요">
    <button @click="create">등록</button>

    <table>
      <tr v-for="c in comments" :key="c.comment">
        <td class="comment">{{ c.comment }}</td>
        <td class="name">{{ c.name }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const comments = ref([
  {
    comment: "댓글이당",
    name: "혜선",
  },
]);

const comment = ref('');

const create = function () {
  const c = {
    comment: comment.value,
    name: "혜선",
  };
  comments.value.unshift(c);
  comment.value = '';
};
</script>

<style scoped>
.comment-container {
  max-width: 100%;
  margin: auto;
}

input {
  width: 90%;
  padding: 8px;
  margin-right: 8px;
  outline: none;
  border: 4px solid white;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
}

input:focus {
  border-color: #68a67d; /* 클릭 시 테두리 색상 변경 */
}

button {
  padding: 8px;
  color: white;
  background-color: #68a67d;
  border: 1px solid #68a67d;
  border-radius: 4px;
  cursor: pointer;
}

table {
  width: 100%;
  margin-top: 16px;
  border-collapse: collapse;
}

td {
  padding: 8px;
  border: 1px solid #ddd;
}

.comment {
  max-width: 60%;
  word-break: break-all;
}

.name {
  max-width: 40%;
}
</style>

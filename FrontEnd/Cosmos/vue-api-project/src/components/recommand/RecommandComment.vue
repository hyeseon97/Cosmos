<template>
  <div class="comment-container">
    <div class="input-form">
      <input type="text" @keyup.enter="create" v-model="comment" placeholder="댓글을 입력하세요">
      <button @click="create">등록</button>
    </div>
    <div class="comments-cover">
      <div v-for="c in comments" :key="c.comment" class="comments">
        <div class="comment">{{ c.cc_content }}</div>
        <div>
          <div class="name">{{ c.cc_userName }}</div>
          <div class="date">{{ c.cc_regDate }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const comments = ref([
  {
    cc_content: "댓글이당",
    cc_userName: "혜선",
    cc_regDate: "2023-04-01"
  },
]);

const comment = ref('');

const create = function () {
  const c = {
    cc_content: comment.value,
    cc_userName: "혜선",
    cc_regDate: "2024-03-05"
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

.input-form {
  margin-bottom: 10px;
}

input {
  width: 85%;
  padding: 8px;
  margin-right: 8px;
  outline: none;
  border: 4px solid white;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
}

input:focus {
  border-color: #68a67d;
  /* 클릭 시 테두리 색상 변경 */
}

button {
  padding: 8px;
  width: 13%;
  color: white;
  background-color: #68a67d;
  border: 1px solid #68a67d;
  border-radius: 4px;
  cursor: pointer;
}

.comments-cover {
  height: fit-content;
}

.comments {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
  padding: 0 2%;
  color: #24613b;
  font-weight: 600;
}

.comment {
  display: inline-flex;
}

.name {
  display: inline-flex;
}

.date {
  display: inline-flex;
  margin-left: 15px;
}
</style>

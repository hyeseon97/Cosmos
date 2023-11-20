<template>
  <div>
    <h4>InfoBoardComment</h4>
    <input type="text" @keyup.enter="create" v-model="comment" placeholder="댓글을 입력하세요">
    <button @click="create">등록</button>

    <table>
      <tr v-for="c in comments">
        <td>{{ c.comment }}</td>
        <td>{{ c.name }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const comments = ref([
  {
    comment: "댓글이당",
    name: "혜선",
  }
])

const comment = ref('')

const create = function(){
  const c = {
    comment: comment.value,
    name: "혜선",
  }
  comments.value.unshift(c);
  comment.value=''
}

</script>

<style scoped>

</style>
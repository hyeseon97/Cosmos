<template>
  <div>
    <h4>AdminUserList</h4>
    <table>
      <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>생년월일</th>
        <th>등급</th>
      </tr>
      <tr v-for="u in userList">
        <td>{{ u.id }}</td>
        <td>{{ u.name }}</td>
        <td>{{ u.birth }}</td>
        <td>{{ u.grade }}</td>
        <button @click="update">등급수정</button>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

// 로그인유저 저장 -> template 수정해야함
const user = ref(null);
// const user = ref("11");

const userList = ref([
  {
    num: 1,
    id: "id",
    name: "글쓴이1",
    birth: "생일",
    grade: "골드"
  },
  {
    num: 2,
    id: "id",
    name: "글쓴이1",
    birth: "생일",
    grade: "골드"
  },
]);

</script>

<style scoped></style>
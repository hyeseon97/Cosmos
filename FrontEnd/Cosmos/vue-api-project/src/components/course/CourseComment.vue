<template>
  <div>
    <h4>CourseComment</h4>
    <input type="text" @keyup.enter="create" v-model="cc_content" placeholder="댓글을 입력하세요">
    <button @click="create">등록</button>

    <table>
      <tr v-for="c in commentList">
        <td>{{ c.cc_content }}</td>
        <td>{{ c.cc_userName }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useCourseCommentStore } from '../../stores/courseComment';
import { useUserStore } from '../../stores/user';

const route = useRoute();
const commentStore = useCourseCommentStore();
const userStore = useUserStore();

const commentList = computed(()=>commentStore.courseCommentList)

const comment = ref({
    cc_courseNum: route.params.num,
    cc_userId: userStore.user.user_id,
    cc_userName: userStore.user.user_name,
    cc_content: ''
})

const create = function(){
  commentStore.createComment(comment)
  .then(()=>{
    commentStore.getCourseCommentList(route.params.num)
  })
}

onMounted(() => {
  userStore.getUser(userStore.loginUserId);
  commentStore.getCourseCommentList(route.params.num);
})

</script>

<style scoped>

</style>
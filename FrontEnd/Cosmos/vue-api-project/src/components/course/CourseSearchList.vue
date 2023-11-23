<template>
  <div>
    <h4>CourseSearchList</h4>
    <div class="testcolor"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch, watchEffect } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useCourseStore } from '@/stores/course'

const courseStore = useCourseStore();

onMounted(()=>{
  courseStore.getCourseList();
})

</script>

<style scoped>
.testcolor{
  background-color: black;
}
</style>
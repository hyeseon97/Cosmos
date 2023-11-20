<template>
  <div class="container-center">
    <div class="back-color">
      <RouterView />
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.container-center {
  padding: 5vh 15vh;
}

.back-color {
  /* 흰박스 안에 내용까지 여백 */
  padding: 10vh 5vh;
  height: 90vh;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 20px;
}
</style>
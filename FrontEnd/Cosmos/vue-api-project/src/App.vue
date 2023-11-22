<template>
  <div class="main-container">
    <div class="background-container">
      <!-- 이미지가 들어갈 부분 -->
      <img src="./assets/MainImg2.jpg" alt="Background Image" style="object-fit: cover;" />
    </div>
    <MenuBar />
    <RouterView />
  </div>
</template>

<script setup>
import MenuBar from "./components/common/MenuBar.vue";

import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute();

</script>

<style  scoped>
.main-container {
  position: relative;
  height: 100%;
}

.background-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100%;
  z-index: -2;
  /* 메뉴바 위에 나타나도록 */
  overflow: hidden;
}

.background-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  /* 이미지가 부모 요소를 가득 채우도록 설정 */
}

div {
  font-family: Poppins, 'Noto Sans KR', sans-serif;
}
</style>
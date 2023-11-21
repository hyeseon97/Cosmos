<template>
    <aside class="side-bar">
      <ul>
        <li style="margin-bottom: 20%;">
          <RouterLink to="/">
            <div class="centered-content">
                Course 모스<img src="../../assets/free-icon-pink-cosmos-8116980.png" class="image-container"
              style="width: 60px; margin-left: 10px;">

            </div>
        </RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'recommandList' }" :class="{ 'active': $route.path.includes('/recommand') }">Today's Course 모스</RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'courseList' }" :class="{ 'active': $route.path.includes('/course') }">우리나라 Course</RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'travelList' }" :class="{ 'active': $route.path.includes('/travel') }">자전거 여행 플래너</RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'freeBoardList' }" :class="{ 'active': $route.path.includes('/free') }">자유게시판</RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'infoBoardList' }" :class="{ 'active': $route.path.includes('/info') }">정보게시판</RouterLink>
        </li>
        <li v-if="!loginStatus">
            <RouterLink :to="{ name: 'login' }" :class="{ 'active': $route.path.includes('/user') }">로그인</RouterLink>
        </li>
        <li v-if="loginStatus">
            <RouterLink :to="{ name: 'userInfo' }" :class="{ 'active': $route.path.includes('/user') }">마이페이지</RouterLink>
        </li>
        <li v-if="loginStatus" @click="logout">
            <RouterLink :to="{ name: 'home' }">로그아웃</RouterLink>
        </li>
        <li>
            <RouterLink :to="{ name: 'adminUserList' }" :class="{ 'active': $route.path.includes('/admin') }">관리자페이지</RouterLink>
        </li>

      </ul>
      <div class="menu">
        <!-- <div>MENUMENU</div> -->
        <div>M</div>
        <div>E</div>
        <div>N</div>
        <div>U</div>
      </div>
    </aside>
    
</template>

<script setup>

//로그인 상태 유지시키고, 로그아웃 버튼으로 변경하는 것
import { ref,computed,onMounted } from 'vue';
import { useUserStore } from '../../stores/user';

const userStore = useUserStore();

const loginStatus = computed(() => {
  return userStore.isAuthenticated
})

const logout = function () {
  sessionStorage.clear()
  userStore.isAuthenticated = false;
}

onMounted(() => {
  if(sessionStorage.getItem('login-token') == null){
    userStore.isAuthenticated = false;
  }else{
    userStore.isAuthenticated = true;
  }
})

</script>

<style>
ul,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.side-bar ul {
  margin-top: 10%;
}

.side-bar ul ul {
  display: none;
  color: black;
}

:root {
  --side-bar-width: 330px;
  --side-bar-height: 90vh;
}

.side-bar:hover .menu {
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
  /* 부드러운 트랜지션 효과 추가 */
}

.menu {
  position: absolute;
  top: 50%;
  right: 20px;
  /* 원하는 간격으로 조절 */
  transform: translateY(-50%);
  opacity: 1;
  transition: opacity 0.3s ease-in-out;
  /* 부드러운 트랜지션 효과 추가 */
  color: #353535;
  font-size: 1.2rem;
  font-weight: bold;
}

.menu div {
  margin: calc(var(--side-bar-height) * 0.10) 0;
}

.image-container {
  opacity: 0;
  transition: opacity 0.1s ease-in-out;
  /* 부드러운 트랜지션 효과 추가 */
}

.side-bar:hover .image-container {
  opacity: 1;
  transition: opacity 1.0s ease-in-out;
}

.centered-content {
  display: flex;
  align-items: center; /* 수직 중앙 정렬을 위한 속성 */
}

.side-bar {
  position: fixed;
  /* 스크롤을 따라오도록 지정 */
  background-color: #68A67D;
  width: var(--side-bar-width);
  min-height: var(--side-bar-height);
  /* 사이드바의 높이를 전체 화면 높이의 90%로 지정 */
  margin-top: calc((100vh - var(--side-bar-height)) / 2);
  /* 사이드바 위와 아래의 마진을 동일하게 지정 */
  border-radius: 20px;
  transform: translate(calc(var(--side-bar-width) * -0.8), 0);
  /* X축 이동, Y축 고정 */
  transition: .5s;
  z-index: 1;
}

.side-bar:hover {
  transform: translate(-20px, 0);
  /* 둥근 모서리의 너비만큼 X축 이동, Y축 고정 */
}

/* 모든 메뉴의 a에 속성값 부여 */
.side-bar ul>li>a {
  display: block;
  color: white;
  font-size: 1.4rem;
  font-weight: bold;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 50px;
}

.side-bar li a.active {
  color: #24613b; /* 원하는 활성화된 색상으로 변경 */
  /* 다른 스타일 속성들... */
}

.side-bar>ul>li {
  position: relative;
}

/* 모든 메뉴가 마우스 인식 시 반응 */
.side-bar ul>li:hover>a {
  /* background-color: #24613b; */
  color: #24613b;
}

/* 1차 메뉴의 항목이 마우스 인식 시에 2차 메뉴 등장 */
.side-bar>ul>li:hover>ul {
  display: block;
  position: absolute;
  background-color: #888;
  top: 100%;
  /* 2차 메뉴의 상단을 1차 메뉴의 상단에 고정 */
  left: 0;
  /* 2차 메뉴를 1차 메뉴의 너비만큼 이동 */
  width: 100%;
  /* 1차 메뉴의 너비를 상속 */
}

@media screen and (max-width: 768px) {
  .side-bar ul>li>a {
    font-size: 1rem;
    /* 작은 화면일 때 원하는 크기로 변경 */
  }
}
</style>
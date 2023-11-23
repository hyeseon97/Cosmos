<template>
  <section class="notice">
    <div class="page-title">
      <div class="free-board-container">
        <h3>자유게시판</h3>
      </div>
    </div>

    <!-- board seach area -->
    <div id="free-board-search">
      <div class="free-board-container">
        <div class="search-window">
          <form @submit.prevent="search">
            <div class="search-wrap">
              <label for="search" class="blind">공지사항 내용 검색</label>
              <input v-model="searchQuery" id="search" type="search" placeholder="검색어를 입력해주세요.">
              <button type="submit" class="btn btn-dark">검색</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- board list area -->
    <div id="free-board-list">
      <div class="free-board-container">
        <table class="free-board-table">
          <thead>
            <tr>
              <th scope="col" class="th-num">번호</th>
              <th scope="col" class="th-title">제목</th>
              <th scope="col" class="th-num">작성자</th>
              <th scope="col" class="th-date">날짜</th>
              <th scope="col" class="th-title">조회수</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in displayedArr" :key="index" @click="godetail(item.fb_num)">
              <td>{{ item.fb_num }}</td>
              <th>
                {{ item.fb_title }}
              </th>
              <th>{{ item.fb_writer }}</th>
              <td>{{ item.fb_regDate }}</td>
              <td>{{ item.fb_viewCnt }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div @click="create" class="create-button">글쓰기</div>
    </div>

    <!-- 페이지네이션 -->
    <div class="pagination">
      <button :disabled="currentPage === 1" @click="changePage(currentPage - 1)">이전</button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <button :disabled="currentPage === totalPages" @click="changePage(currentPage + 1)">다음</button>
    </div>
  </section>
</template>

<script setup>
import { ref, onMounted, computed, } from 'vue';
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '@/stores/freeBoard'
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

const searchQuery = ref('');

const arr = ref([]);

const itemsPerPage = 10; // 한 페이지에 보여줄 항목 수
const totalPages = Math.ceil(arr.value.length / itemsPerPage);


const currentPage = ref(1);

const freeBoardList = computed(() => store.freeBoardList)
const startIndex = computed(() => currentPage.value - 1);
const endIndex = computed(() => startIndex.value + itemsPerPage)
const displayedArr = computed(() => freeBoardList.value.slice(startIndex.value, endIndex.value))


onMounted(() => {
  store.getFreeBoardList();
  // updateDisplayedArr();
});


function changePage(newPage) {
  if (newPage >= 1 && newPage <= totalPages) {
    currentPage.value = newPage;
    // updateDisplayedArr();
  }
}

// 글쓰기 버튼
const create = function () {
  if (userStore.loginUserId != null) {
    router.push({ name: "freeBoardCreate" })
  } else {
    alert("로그인 후 사용 가능")
  }
}

// 표 클릭하면 해당 게시글로 이동
const godetail = function (num) {
  console.log(num);
  router.push({ name: "freeBoardDetail", params: { num: num } })
}

</script>

<style scoped>
table {
  border-collapse: collapse;
  border-spacing: 0;
}

section.notice {
  padding: 80px 0;
}

.page-title {
  margin-bottom: 60px;
}

.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 1000;
  text-align: center;
}

#free-board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}

#free-board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}

#free-board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 15px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}

#free-board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}

#free-board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.free-board-table {
  font-size: 15px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.free-board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}

.free-board-table a:hover {
  text-decoration: underline;
}

.free-board-table th {
  text-align: center;
}

.free-board-table .th-num {
  width: 100px;
  text-align: center;
}

.free-board-table .th-date {
  width: 200px;
}

.free-board-table th,
.free-board-table td {
  padding: 14px 0;
}

.free-board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.free-board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.free-board-table tbody th p {
  display: none;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* reset */

* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

.clearfix:after {
  content: '';
  display: block;
  clear: both;
}

/* .info-board-container {
  width: 1100px;
  margin: 0 auto;
} */

.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}

.page-btn {
  margin: 0 5px;
  padding: 5px 10px;
  cursor: pointer;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-btn:disabled:hover {
  cursor: context-menu;
}

.pagination span {
  display: inline-block;
  margin: 0 10px;
  font-size: 16px;
}
.create-button {
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  position: absolute;
  right: 0px;
  margin: 10px;
  padding: 10px 20px;
  background-color: #8FBF9F;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.create-button:hover {
  background-color: #68a67d;
}
@media (max-width: 600px) {
  .create-button {
    bottom: 20px;
    right: 20px;
  }
}

#free-board-list{
position: relative;
}

</style>
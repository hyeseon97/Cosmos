<template>
  <section class="notice">
    <div class="page-title">
      <div class="info-board-container">
        <h3>공지사항</h3>
      </div>
    </div>

    <!-- board seach area -->
    <div id="info-board-search">
      <div class="info-board-container">
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
    <div id="info-board-list">
      <div class="info-board-container">
        <table class="info-board-table">
          <thead>
            <tr>
              <th scope="col" class="th-num">번호</th>
              <th scope="col" class="th-title">제목</th>
              <th scope="col" class="th-date">등록일</th>
              <th scope="col" class="th-title">조회수</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in displayedArr" :key="index" @click="godetail(item.ib_num)">
              <td>{{ item.ib_num }}</td>
              <th>
                <router-link :to="`/notice/${item.ib_id}`">{{ item.ib_title }}</router-link>
              </th>
              <td>{{ item.ib_regDate }}</td>
              <td>{{ item.ib_viewCnt }}</td>
            </tr>
          </tbody>
        </table>
      </div>
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
import { useRoute, useRouter } from 'vue-router'
import { useInfoBoardStore } from '../../stores/infoBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useInfoBoardStore();
const userStore = useUserStore();

const user = ref("11");

const searchQuery = ref('');

const arr = ref([]);

const itemsPerPage = 10; // 한 페이지에 보여줄 항목 수
const totalPages = computed(() => Math.ceil(infoBoardList.value.length / itemsPerPage));

const currentPage = ref(1);

const infoBoardList = computed(() => store.infoBoardList)
const startIndex = computed(() => currentPage.value - 1);
const endIndex = computed(() => startIndex.value + itemsPerPage)
const displayedArr = computed(() => infoBoardList.value.slice(startIndex.value, endIndex.value))

onMounted(() => {
  store.getInfoBoardList();
});

function changePage(newPage) {
  if (newPage >= 1 && newPage <= totalPages) {
    currentPage.value = newPage;
  }
}

// 글쓰기 버튼
const create = function () {
  router.push({ name: "infoBoardCreate" })
}

// 표 클릭하면 해당 게시글로 이동
const godetail = function (num) {
  console.log(num);
  router.push({ name: "infoBoardDetail", params: { num: num } })
}

// methods: {
//   search() {
//     // 검색 기능을 추가할 때 이 메서드를 활용
//     console.log('검색어:', this.searchQuery);
//     // 검색 로직을 구현하면 됩니다.
//   },
// }
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

#info-board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}

#info-board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}

#info-board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 15px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}

#info-board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}

#info-board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.info-board-table {
  font-size: 15px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.info-board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}

.info-board-table a:hover {
  text-decoration: underline;
}

.info-board-table th {
  text-align: center;
}

.info-board-table .th-num {
  width: 100px;
  text-align: center;
}

.info-board-table .th-date {
  width: 200px;
}

.info-board-table th,
.info-board-table td {
  padding: 14px 0;
}

.info-board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.info-board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.info-board-table tbody th p {
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

@media (max-width: 600px) {
  .create-button {
    bottom: 20px;
    right: 20px;
  }
}
</style>
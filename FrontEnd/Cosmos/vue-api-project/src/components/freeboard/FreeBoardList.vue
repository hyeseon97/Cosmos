<template>
  <div>
    <div @click="create" class="create-button">글쓰기</div>
    <!-- <router-link :to="{ name: 'freeBoardCreate' }">글쓰기</router-link> -->
    <!-- <RouterLink :to="{ name: 'freeBoardCreate'}">글쓰기</RouterLink> -->
    <table>
      <tr>
        <th style="width: 6%;">번호</th>
        <th style="width: 50%;">제목</th>
        <th style="width: 12%;">작성자</th>
        <th style="width: 12%;">날짜</th>
        <th style="width: 10%;">조회수</th>
        <th style="width: 10%;">좋아요</th>
      </tr>
      <tr v-for="(item, index) in displayedArr" :key="index" @click="godetail(item.fb_num)">
        <td>{{ item.fb_num }}</td>
        <td class="left-align">{{ item.fb_title }}</td>
        <td>{{ item.fb_writer }}</td>
        <td>{{ item.fb_regDate }}</td>
        <td>{{ item.fb_viewCnt }}</td>
        <td>{{ item.fb_rcm }}</td>
      </tr>
    </table>
    <div class="pagination">
      <button :disabled="currentPage === 1" @click="changePage(currentPage - 1)">이전</button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <button :disabled="currentPage === totalPages" @click="changePage(currentPage + 1)">다음</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed,  } from 'vue';
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '@/stores/freeBoard'
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

const arr = ref([]);

const itemsPerPage = 10; // 한 페이지에 보여줄 항목 수
const totalPages = Math.ceil(arr.value.length / itemsPerPage);


const currentPage = ref(1);

const freeBoardList = computed(()=> store.freeBoardList)
const startIndex = computed(() => currentPage.value -1);
const endIndex = computed(()=> startIndex.value + itemsPerPage)
const displayedArr = computed(()=>freeBoardList.value.slice(startIndex.value,endIndex.value))


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
const create = function(){
  if(userStore.loginUserId!=null){
    router.push({ name: "freeBoardCreate" })
  } else{
    alert("로그인 후 사용 가능")
  }
}

// 표 클릭하면 해당 게시글로 이동
const godetail = function(num){
  console.log(num);
  router.push({ name: "freeBoardDetail", params: { num: num }})
}

</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #f5f5f5;
}

.create-button {
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.left-align {
  text-align: left;
  padding-left: 30px;
}

.create-button {
  padding: 10px 20px;
  background-color: #8FBF9F;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* 부드러운 효과를 위한 트랜지션 추가 */
}

.create-button:hover {
  background-color: #68a67d; /* 마우스 오버시 색상 변경 */
}

.pagination {
  margin-top: 20px;
  text-align: center;
}

.pagination button {
  margin: 0 5px;
  padding: 5px 10px;
  cursor: pointer;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.pagination button:disabled:hover {
  /* 비활성화 상태에서의 마우스 오버 스타일 설정 */
  cursor: context-menu;
  /* 다른 스타일 추가 가능 */
}

</style>
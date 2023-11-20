<template>
  <div>
    <button @click="create" v-if="user!=null">글쓰기</button>
    <!-- <router-link :to="{ name: 'infoBoardCreate' }">글쓰기</router-link> -->
    <!-- <RouterLink :to="{ name: 'infoBoardCreate'}">글쓰기</RouterLink> -->
    <table>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>날짜</th>
        <th>조회수</th>
        <th>좋아요</th>
      </tr>
      <tr v-for="(item, index) in displayedArr" :key="index" @click="godetail(item.ib_num)">
        <td>{{ item.ib_num }}</td>
        <td class="left-align">{{ item.ib_title }}</td>
        <td>{{ item.ib_regDate }}</td>
        <td>{{ item.ib_viewCnt }}</td>
        <td>{{ item.ib_rcm }}</td>
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
import { ref, onMounted, computed, } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useInfoBoardStore } from '../../stores/infoBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useInfoBoardStore();
const userStore = useUserStore();

// 로그인유저 저장 -> template 수정해야함
// const user = ref(null);
const user = ref("11");

const arr = ref([]);

const itemsPerPage = 10; // 한 페이지에 보여줄 항목 수
const totalPages = Math.ceil(arr.value.length / itemsPerPage);


const currentPage = ref(1);

const infoBoardList = computed(()=> store.infoBoardList)
const startIndex = computed(() => currentPage.value -1);
const endIndex = computed(()=> startIndex.value + itemsPerPage)
const displayedArr = computed(()=>infoBoardList.value.slice(startIndex.value,endIndex.value))


onMounted(() => {
  store.getInfoBoardList();
});

// function updateDisplayedArr() {
//   const startIndex = (currentPage.value - 1) * itemsPerPage;
//   const endIndex = startIndex + itemsPerPage;
//   displayedArr.value = arr.value.slice(startIndex, endIndex);
// }

function changePage(newPage) {
  if (newPage >= 1 && newPage <= totalPages) {
    currentPage.value = newPage;
    // updateDisplayedArr();
  }
}


// 글쓰기 버튼
const create = function(){
  router.push({ name: "infoBoardCreate" })
}

// 표 클릭하면 해당 게시글로 이동
const godetail = function(num){
  console.log(num);
  router.push({ name: "infoBoardDetail", params: { num: num }})
}

</script>

<style scoped></style>
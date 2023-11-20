import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/freeComment`

export const useFreeCommentStore = defineStore('freeComment', () => {
  const freeCommentList = ref([])
  const getFreeCommentList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
      freeCommentList.value = response.data
      })
  }

  //게시글 등록
  const createComment = function (comment) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: comment.value
    })
      .then(() => {
        
      })
      .catch((err) => {
      console.log(err)
    })
  }
  return { freeCommentList, getFreeCommentList, createComment }
})

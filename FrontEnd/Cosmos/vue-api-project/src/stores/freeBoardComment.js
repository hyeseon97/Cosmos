import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/freeComment`

export const useFreeCommentStore = defineStore('freeComment', () => {
  const freeCommentList = ref([])
  const getFreeCommentList = function (boardNum) {
    axios.get(`${REST_BOARD_API}/${boardNum}`)
      .then((response) => {
      freeCommentList.value = response.data
      })
  }

  //게시글 등록
  const createComment = function (comment) {
    return axios.post(REST_BOARD_API, comment.value)
      .then((response) => {
        return response.data;
      })
  }
  return { freeCommentList, getFreeCommentList, createComment }
})

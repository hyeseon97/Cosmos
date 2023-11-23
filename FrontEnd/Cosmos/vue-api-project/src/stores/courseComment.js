import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/courseComment`

export const useCourseCommentStore = defineStore('courseComment', () => {
  const courseCommentList = ref([])
  const getCourseCommentList = function (courseNum) {
    axios.get(`${REST_BOARD_API}/${courseNum}`)
      .then((response) => {
      courseCommentList.value = response.data
      })
  }

  //게시글 등록
  const createComment = function (comment) {
    return axios.post(REST_BOARD_API, comment.value)
      .then((response) => {
        return response.data;
      })
  }
  return { courseCommentList, getCourseCommentList, createComment }
})

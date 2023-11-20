import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/free`

export const useFreeBoardStore = defineStore('freeBoard', () => {
  const freeBoardList = ref([])

  const getFreeBoardList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
        freeBoardList.value = response.data
      })
  }

  //게시글 한개
  const freeBoard = ref({})
  const getFreeBoard = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        freeBoard.value = response.data
      })
  }

  //게시글 등록
  const createFreeBoard = function (freeBoard) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: freeBoard.value
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        router.push({ name: 'freeBoardList' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const updateFreeBoard = function () {
    axios.put(REST_BOARD_API, freeBoard.value)
      .then(() => {
        router.push({ name: 'freeBoardDetail', params: { num: freeBoard.value.fb_num } })
      })
  }

  const deleteFreeBoard = function (id) {
    axios.delete(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        router.push({ name: 'freeBoardList'})
      })
  }

  const searchFreeBoardList = function (searchCondition) {
    axios.get(REST_BOARD_API, {
      params: searchCondition
    })
      .then((res) => {
        freeBoardList.value = res.data
      })
  }
  return { freeBoardList, getFreeBoardList, freeBoard, getFreeBoard, createFreeBoard, updateFreeBoard, deleteFreeBoard, searchFreeBoardList }
})

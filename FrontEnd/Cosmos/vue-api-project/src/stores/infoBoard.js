import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/info`

export const useInfoBoardStore = defineStore('infoBoard', () => {
  const infoBoardList = ref([])

  const getInfoBoardList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
        infoBoardList.value = response.data
      })
  }

  //게시글 한개
  const infoBoard = ref({})
  const getInfoBoard = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        infoBoard.value = response.data
      })
  }

  //게시글 등록
  const createInfoBoard = function (infoBoard) {
    console.log(infoBoard.value)
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: infoBoard.value
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        console.log("성공")
        router.push({ name: 'infoBoardList' })
      })
      .catch((err) => {
        console.log("실패")
        console.log(err)
      })
  }

  const updateInfoBoard = function () {
    axios.put(REST_BOARD_API, infoBoard.value)
      .then(() => {
        router.push({ name: 'infoBoardDetail', params: { num: infoBoard.value.ib_num } })
      })
  }

  const deleteInfoBoard = function (id) {
    axios.delete(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        router.push({ name: 'infoBoardList'})
      })
  }

  const searchInfoBoardList = function (searchCondition) {
    axios.get(REST_BOARD_API, {
      params: searchCondition
    })
      .then((res) => {
        infoBoardList.value = res.data
      })
  }
  return { infoBoardList, getInfoBoardList, infoBoard, getInfoBoard, createInfoBoard, updateInfoBoard, deleteInfoBoard, searchInfoBoardList }
})

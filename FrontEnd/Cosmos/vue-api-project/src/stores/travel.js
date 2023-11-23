import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/travel`

export const useTravelStore = defineStore('travel', () => {
  const travelList = ref([])

  const getTravelList = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        travelList.value = response.data
      })
  }

  //게시글 등록
  const createTravel = function (travel) {
    console.log(travel.value)
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: travel.value
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        router.push({ name: 'travelList' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  // const updateFreeBoard = function () {
  //   axios.put(REST_BOARD_API, freeBoard.value)
  //     .then(() => {
  //       router.push({ name: 'freeBoardDetail', params: { num: freeBoard.value.fb_num } })
  //     })
  // }

  const deleteTravel = function (id) {
    axios.delete(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        router.push({ name: 'travelList'})
      })
  }

  return { travelList, getTravelList, createTravel, deleteTravel }
})

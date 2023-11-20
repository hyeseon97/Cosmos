import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api`

export const useUserStore = defineStore('user', () => {
  const userList = ref([])
  const getUserList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
        UserList.value = response.data
      })
  }

  // //게시글 한개
  // const user = ref({})
  // const getUser = function (id) {
  //   axios.get(`${REST_BOARD_API}`+"/user"+`/${id}`)
  //     .then((response) => {
  //     user.value = response.data
  //   })
  // }

  //유저 등록
  const signup = function (user) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: user
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        router.push({ name: 'info' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const updateUser = function () {
    axios.put(REST_BOARD_API, board.value)
      .then(() => {
        router.push({ name: 'userList' })
      })
  }

  const searchUserList = function (searchCondition) {
    axios.get(REST_BOARD_API, {
      params: searchCondition
    })
      .then((res) => {
        userList.value = res.data
      })
  }

  // 로그인
  const loginUserId = ref('')
  const userLogin = function (user) {
    console.log(user.value)
    axios({
      url: REST_BOARD_API + "/login",
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: user.value
    })
      .then((response) => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        console.log(response)

        sessionStorage.setItem('login-token', response.data["login-token"])

        const token = response.data['login-token'].split('.')
        console.log(token)
        let user_id = token[1]
        user_id = atob(user_id)
        user_id = JSON.parse(user_id)
        console.log(user_id['id'])
        loginUserId.value = user_id['id']

        router.push({ name: "userInfo" })

      })
      .catch((err) => {
        console.log("로그인실패")
      })
  }

  // 로그아웃
  const userLogout = function () {
    loginUserId.value = null

    router.push({ name: "home" })
  }

  return { userList, getUserList, signup, updateUser, searchUserList, userLogin, loginUserId }
})

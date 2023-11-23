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
        userList.value = response.data
      })
  }



  //게시글 한개
  const user = ref({})
  const getUser = function (id) {
    axios.get(`${REST_BOARD_API}`+'/user'+`/${id}`)
      .then((response) => {
      user.value = response.data
      console.log(user.value)
    }).catch(()=>{
      console.log("유저없음")
    })
  }

  //유저 등록
  const signup = function(user){
    axios.post(`${REST_BOARD_API}`+'/signup',user.value)
      .then((response) => {
      user.value = response.data
      console.log("가입성공",user.value)
    }).catch((error) => {
      console.log("가입실패",error);
    })
  }

  //유저 개인정보 업데이트
  const updateUser = function () {
    axios.put(`${REST_BOARD_API}`+'/user', user.value)
      .then(() => {
        editing.value = false;
        router.push({ name: 'home' })
      })
      .catch((error) => {
        console.log("업데이트 실패", error)
      })
  }

  // 로그인
  const loginUserId = ref('')
  const isAuthenticated = ref(false);
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
        isAuthenticated.value = true;
        sessionStorage.setItem('login-token', response.data["login-token"])

        const token = response.data['login-token'].split('.')
        console.log(token)
        let user_id = token[1]
        user_id = atob(user_id)
        user_id = JSON.parse(user_id)
        console.log(user_id['id'])
        loginUserId.value = user_id['id']

        router.push({ name: "home" })

      })
      .catch((err) => {
        console.log("로그인실패")
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



  return { userList, user, isAuthenticated ,getUser, getUserList, signup, updateUser, searchUserList, userLogin, loginUserId }
})

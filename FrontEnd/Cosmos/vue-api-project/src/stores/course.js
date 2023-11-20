import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/course`

export const useCourseStore = defineStore('course', () => {
  const courseList = ref([])
  const getCourseList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
      courseList.value = response.data
      })
  }

  //게시글 한개
  const course = ref({})
  const getCourse = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
      course.value = response.data
    })
  }

  //게시글 등록
  const createCourse = function (course) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      //아래꺼 없어도 알아서 보내더라 axios 쵝오~ 
      headers: {
        "Content-Type": "application/json"
      },
      data: {
        course: course,
        courseMap: courseMap
      }
    })
      .then(() => {
        //response 응답으로 들어온 게시글의 id를 이용해서
        //상세보기로 바로 점프도 가넝이야~~
        router.push({ name: 'cousreList'})
      })
      .catch((err) => {
      console.log(err)
    })
  }

  const updateCourse = function () {
    axios.put(REST_BOARD_API, course.value)
      .then(() => {
      router.push({name: 'courseList'})
    })
  }

  const searchCourseList = function (searchCondition) {
    axios.get(REST_BOARD_API, {
      params: searchCondition
    })
      .then((res) => {
        courseList.value = res.data
    })
  }
  return { courseList, getCourseList, course, getCourse, createCourse, updateCourse,searchCourseList }
})

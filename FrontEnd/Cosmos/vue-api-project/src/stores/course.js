import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/course`

export const useCourseStore = defineStore('course', () => {
  const courseList = ref([])
  const getCourseList = function () {
    return axios.get(REST_BOARD_API)
      .then((response) => {
        courseList.value = response.data
        return response.data
      })
  }

  const getCourseListByKeyword = function () {
    return axios.get(REST_BOARD_API+'/keyword')
      .then((response) => {
        courseList.value = response.data
        return response.data
      })
  }

  //코스 한개
  const course = ref({})
  const getCourse = function (id) {
    console.log("요청왔니")
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
      course.value = response.data
    })
  }

  //코스 등록
  const createCourse = function (course) {
    console.log("store의 요청 전")
    console.log(course)
    axios.post(REST_BOARD_API, course.value)
      .then(()=>{
        router.push({ name: 'courseList' })
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

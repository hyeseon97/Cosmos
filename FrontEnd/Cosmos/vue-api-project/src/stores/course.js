import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/api/course`

export const useCourseStore = defineStore('course', () => {
  const courseList = ref([])

  const getCourseList = function (condition) {
    return axios.get(REST_BOARD_API, {
      params: {
        key: condition.key,
        word: condition.word,
        orderBy: condition.orderBy,
        orderByDir: condition.orderByDir
      }
    })
      .then((response) => {
        courseList.value = response.data
        return response.data
      })
  }

  // 키워드로 리스트 가져오기
  const keywords = ref([])
  const getCourseListByKeyword = function (senddata) {
    // console.log("함수호출이 되었니?")
    // console.log(Array.isArray(keywords.value))
    // keywords.value.forEach((element) => {
    //   console.log('뚱돼')
    //   if (element.checked) {
    //     console.log('Checked Keyword in method:', element);
    //   }
    // });
    console.log(senddata)
    return axios.get(`${REST_BOARD_API}/keyword/${senddata}`)
      .then((response) => {
        courseList.value = response.data
        console.log("잘들어왔다")
        return response.data
      })
  }

  //코스 한개
  const course = ref({})
  const getCourse = function (id) {
    axios.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        course.value = response.data
      })
  }

  //코스 등록
  const createCourse = function (course) {
    axios.post(REST_BOARD_API, course.value)
      .then(() => {
        router.push({ name: 'courseList' })
      })
  }

  const updateCourse = function () {
    axios.put(REST_BOARD_API, course.value)
      .then(() => {
        router.push({ name: 'courseList' })
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
  return { courseList, getCourseList, keywords, getCourseListByKeyword, course, getCourse, createCourse, updateCourse, searchCourseList }
})

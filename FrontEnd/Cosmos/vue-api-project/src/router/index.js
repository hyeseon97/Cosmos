import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";

import RecommandView from "@/views/RecommandView.vue";
import CourseView from "@/views/CourseView.vue";
import TravelView from "@/views/TravelView.vue";
import FreeBoardView from "@/views/FreeBoardView.vue";
import InfoBoardView from "@/views/InfoBoardView.vue";
import UserView from "@/views/UserView.vue";

import RecommandList from "@/components/recommand/RecommandList.vue"
import RecommandDetail from "@/components/recommand/RecommandDetail.vue"
import RecommandComment from "@/components/recommand/RecommandComment.vue"

import CourseList from "@/components/course/CourseList.vue"
import CourseSearchList from "@/components/course/CourseSearchList.vue"
import CourseCreate from "@/components/course/CourseCreate.vue"
// import KakaoMapCreateBeauty from "@/components/map/KakaoMapCreateBeauty.vue"
// import KakaoMap from "@/components/map/KakaoMap.vue"
import CourseComment from "@/components/course/CourseComment.vue"

import KakaoMap from "@/components/map/KakaoMap.vue"
import KakaoMapCreate from "@/components/map/KakaoMapCreate.vue"
import KakaoMapCreateBeauty from "@/components/map/KakaoMapCreateBeauty.vue"
import MapTravel from "@/components/map/MapTravel.vue"
import MapCourse from "@/components/map/MapCourse.vue"
import Weather from "@/components/map/Weather.vue"

import FreeBoardList from "@/components/freeboard/FreeBoardList.vue"
import FreeBoardCreate from "@/components/freeboard/FreeBoardCreate.vue"
import FreeBoardDetail from "@/components/freeboard/FreeBoardDetail.vue"
import FreeBoardUpdate from "@/components/freeboard/FreeBoardUpdate.vue"
import FreeBoardComment from "@/components/freeboard/FreeBoardComment.vue"

import InfoBoardList from "@/components/infoboard/InfoBoardList.vue"
import InfoBoardCreate from "@/components/infoboard/InfoBoardCreate.vue"
import InfoBoardDetail from "@/components/infoboard/InfoBoardDetail.vue"
import InfoBoardUpdate from "@/components/infoboard/InfoBoardUpdate.vue"
import InfoBoardComment from "@/components/infoboard/InfoBoardComment.vue"

import TravelList from "@/components/travel/TravelList.vue"
import TravelCreate from "@/components/travel/TravelCreate.vue"
import TravelDetail from "@/components/travel/TravelDetail.vue"
import TravelUpdate from "@/components/travel/TravelUpdate.vue"

import UserLogin from "@/components/user/UserLogin.vue"
import UserSign from "@/components/user/UserSign.vue"
import UserInfo from "@/components/user/UserInfo.vue"
import UserUpdate from "@/components/user/UserUpdate.vue"


const router = createRouter({
  // history: createWebHistory(import.meta.env.BASE_URL),
  history: createWebHistory("13.124.154.154:8080"),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/recommand",
      name: "recommand",
      component: RecommandView,
      children: [
        {
          path: "",
          name: "recommandList",
          component: RecommandList,
        },
        {
          path: ":num",
          name: "recommandDetail",
          component: RecommandDetail,
        },
        {
          path: ":num",
          name: "recommandComment",
          component: RecommandComment,
        },
      ]
    },
    {
      path: "/course",
      name: "course",
      component: CourseView,
      children: [
        {
          path: "",
          name: "courseList",
          component: CourseList,
        },
        {
          path: "/search",
          name: "courseSearchList",
          component: CourseSearchList,
        },
        {
          path: "create",
          name: "courseCreate",
          component: CourseCreate,
        },
        {
          path: ":num",
          name: "courseComment",
          component: CourseComment,
        },
      ]
    },
    {
      path: "/map",
      name: "map",
      component: KakaoMap,
    },
    {
      path: "/weather",
      name: "weather",
      component: Weather,
    },
    {
      path: "/freeboard",
      name: "freeBoard",
      component: FreeBoardView,
      children: [
        {
          path: "",
          name: "freeBoardList",
          component: FreeBoardList,
        },
        {
          path: "create",
          name: "freeBoardCreate",
          component: FreeBoardCreate,
        },
        {
          path: ":num",
          name: "freeBoardDetail",
          component: FreeBoardDetail,
        },
        {
          path: ":num",
          name: "freeBoardUpdate",
          component: FreeBoardUpdate,
        },
        {
          path: "comment",
          name: "freeBoardComment",
          component: FreeBoardComment,
        },
      ]
    },
    {
      path: "/infoboard",
      name: "infoBoard",
      component: InfoBoardView,
      children: [
        {
          path: "",
          name: "infoBoardList",
          component: InfoBoardList,
        },
        {
          path: "create",
          name: "infoBoardCreate",
          component: InfoBoardCreate,
        },
        {
          path: ":num",
          name: "infoBoardDetail",
          component: InfoBoardDetail,
        },
        {
          path: ":num",
          name: "infoBoardUpdate",
          component: InfoBoardUpdate,
        },
        {
          path: "comment",
          name: "infoBoardComment",
          component: InfoBoardComment,
        },
      ]
    },
    {
      path: "/travel",
      name: "travel",
      component: TravelView,
      children: [
        {
          path: "",
          name: "travelList",
          component: TravelList,
        },
        {
          path: "create",
          name: "travelCreate",
          component: TravelCreate,
        },
        {
          path: ":num",
          name: "travelDetail",
          component: TravelDetail,
        },
        {
          path: ":num",
          name: "travelUpdate",
          component: TravelUpdate,
        },
      ]
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          path: "",
          name: "login",
          component: UserLogin,
        },
        {
          path: "",
          name: "sign",
          component: UserSign,
        },
        {
          path: "info",
          name: "userInfo",
          component: UserInfo,
        },
        {
          path: "",
          name: "userUpdate",
          component: UserUpdate,
        },
      ]
    },


    {
      path: "/test/kakaomap",
      name: "kakaomap",
      component: KakaoMap,
    },
    {
      path: "/test/kakaomapcreate",
      name: "kakaomapcreate",
      component: KakaoMapCreate,
    },
    {
      path: "/test/kakaomapcreatebeauty",
      name: "kakaomapcreatebeauty",
      component: KakaoMapCreateBeauty,
    },
    {
      path: "/test/maptravel",
      name: "maptravel",
      component: MapTravel,
    },
    {
      path: "/test/mapcourse",
      name: "mapcourse",
      component: MapCourse,
    },
  ],
});

export default router;

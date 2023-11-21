<template class="template-weather">
  <div class="weather">
    <div>기온 : {{ tmp }}℃</div>
    <div>하늘상태 : {{ sky }}</div>
    <div>강수형태 : {{ pty }}</div>
    <div>{{ rain }} : {{ pop }}%</div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
const tmp = ref(null);
const sky = ref(null);
const pty = ref(null);
const pop = ref(0);
const rain = "💧";
onMounted(() => {
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;

  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  const todayStr = `${year}${month}${day}`;
  console.log(todayStr);
  //발표시간을 전부 넣어둬
  const currentTime = today.getHours().toString().padStart(2, "0") + "00";
  // 가장 가까운 시간 찾기
  const closestTime = findClosestTime(currentTime, [
    "0200",
    "0500",
    "0800",
    "1100",
    "1400",
    "1700",
    "2000",
    "2300",
  ]);
  axios
    .get(API_URL, {
      params: {
        ServiceKey: import.meta.env.VITE_WEATHER_API_KEY,
        dataType: "JSON",
        base_date: todayStr,
        base_time: closestTime,
        numOfRows: 15,
        //대전 위치
        nx: 67,
        ny: 101,

      },
    })
    .then((response) => {
      return response.data.response.body.items.item;
    })
    .then((response) => {
      //TMP : 1시간 기온 ℃
      //UUU : 풍속(동서) m/s
      //VVV : 풍속(남북) m/s
      //VEC : 풍향 deg
      //WSD : 풍속 m/s
      //SKY : 하늘상태 --> 코드 : 맑음(1), 구름많음(3), 흐림(4)
      //PTY : 강수형태 --> 코드 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
      //POP : 강수확률 %
      //WAV : 파고 M
      //PCP : 1시간 강수량 범주(1mm)
      //REH : 습도 %
      //SNO : 1시간 적설량
      //TMN : 일 최저기온 ℃ x
      //TMX : 일 최고기온 ℃ x
      console.log("API Response:", response);
      const responseData = response.data?.response?.body?.items?.item;
      console.log("API Data:", responseData);
      if (responseData) {

        response.forEach((item) => {
          if (item.category === "TMP") {
            tmp.value = item.fcstValue;
          } else if (item.category === "SKY") {
            switch (item.fcstValue) {
              case "1":
                sky.value = "☀️";
                break;
              case "3":
                sky.value = "🌥️";
                break;
              case "4":
                sky.value = "☁️";
                break;
            }
          } else if (item.category === "PTY") {
            pty.value = item.fcstValue;
          } else if (item.category === "POP") {
            pop.value = item.fcstValue;
          }
        });
      }
    });
});
</script>

<style scoped>
.template-weather {
  display: inline;
}

.weather {
  width: 40%;
  background-color: #F5ECD7;
  /* border-style: solid; */
  /* border-width: 5px; */
  /* border-color: #F18F01; */
  border-radius: 50px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);

  display: flex;
  flex-direction: column;
  justify-content: center; /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center; /* 세로 중앙 정렬을 위한 스타일 */
}
</style>

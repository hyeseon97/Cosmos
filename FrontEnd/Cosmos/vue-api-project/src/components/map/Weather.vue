<template>
  <div>
    <p>{{ tmp }}℃ | {{ sky }} | {{ rain }} {{ pop }}%</p>
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
const findClosestTime = (currentTime, times) => {
  return times.reduce((prev, curr) => {
    return Math.abs(curr - currentTime) < Math.abs(prev - currentTime)
      ? curr
      : prev;
  });
};
onMounted(() => {
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;
  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  const todayStr = `${year}${month}${day}`;
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
      console.log("API Response:", response);
      const responseData = response.data?.response?.body?.items?.item;
      console.log("API Data:", responseData);
      if (responseData) {
        responseData.forEach((item) => {
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
      } else {
        console.error("응답없음");
      }
    })
    .catch((error) => {
      console.error("Error fetching weather data:", error);
    });
});
</script>

<style scoped>
* {
  color: white;
}
</style>

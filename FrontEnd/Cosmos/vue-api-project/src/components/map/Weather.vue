<template>
  <div>
    <div class="weather-info">
      <div style="font-size: 20px;">오늘의 날씨</div><br>
      <div class=weather-item>
        <div class="weather-sky">
          <img :src="weatherImage" alt="날씨">
        </div>
        <div class="weather-tmp">
          <img src="https://cdn-icons-png.flaticon.com/128/7089/7089846.png" alt="날씨"><span>{{ tmp }}℃</span>
        </div>
        <div class="weather-pop">
          <img src="https://cdn-icons-png.flaticon.com/128/4814/4814304.png" alt="날씨"><span style="margin-left: 20px;">{{
            pop }}%</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
const tmp = ref(null);
const weatherImage = ref(null);
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
                weatherImage.value = "https://cdn-icons-png.flaticon.com/128/4814/4814268.png";
                break;
              case "3":
                weatherImage.value = "https://cdn-icons-png.flaticon.com/128/4814/4814489.png";
                break;
              case "4":
                weatherImage.value = "https://cdn-icons-png.flaticon.com/128/4814/4814293.png";
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
.weather-info {
  font-size: 30px;
  /* font-weight: 600; */
  text-align: center;
  justify-content: space-around;
  /* 가로로 나열하며 각 아이템 사이의 간격을 균등하게 설정 */
}

.weather-item {
  text-align: center;
  display: inline-flex;
  justify-content: center;
  /* 가로 중앙 정렬을 위한 스타일 */
  align-items: center;
  /* 세로 중앙 정렬을 위한 스타일 */
}

.weather-item>div {
  margin: 0 30px;
}

.weather-sky img,
.weather-tmp img,
.weather-pop img {
  width: 100px;
  /* 이미지 크기 조절 */
  margin-bottom: 10px;
  /* 아이콘 아래 간격 추가 */
}

.weather-tmp, .weather-pop {
  display: flex;
  align-items: center;
}
</style>

<template>
  <div>
    <h4>TravelUpdate</h4>
    <div class="travel">
      <div>
        <input type="text" v-model="name" placeholder="여행 계획 이름">
      </div>
      <div>
        <input type="date" v-model="date">
      </div>
      <div>
        <textarea cols="30" rows="10" v-model="memo" placeholder="메모"></textarea>
      </div>
    </div>

    <div class="map">
      지도
    </div>
    <div class="place">
      <table>
        <tr v-for="(p, index) in place" :key="p.num">
          <td>{{ p.num }}</td>
          <td><input type="text" v-model="p.name" placeholder="장소이름"></td>
          <td><input type="text" v-model="p.address" placeholder="장소주소"></td>
          <td><button @click="addPlace">추가</button></td>
          <td v-if="index!=0"><button @click="removePlace(index)">삭제</button></td>
        </tr>
      </table>
    </div>

    <button @click="update">수정</button>
    <button @click="cancel">취소</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();

// 이미 저장되어 있는 데이터 받아와서 넣기
const place = ref([
  {
    name: '',
    address: '',
  }
])

const addPlace = function () {

  if (place.value.length == 5) {
    alert("장소는 최대 5개 등록할 수 있습니다")
  } else {
    place.value.push({
      name: '',
      address: '',
    })
  }
}

const removePlace = function(index){
  place.value.splice(index, 1)
}

const update = function(){
  router.push({ name: "travelDetail", params: { num: 1 } })
}

const cancel = function(){
  router.push({ name: "travelDetail", params: { num: 1 } })
}
</script>

<style scoped>

</style>
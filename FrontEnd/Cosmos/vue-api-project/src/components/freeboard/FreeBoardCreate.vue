<template>
  <div>
    <h4>FreeBoardCreate</h4>
    <div>
      <input type="text" v-model="freeBoard.fb_title" placeholder="제목">
    </div>
    <div>
      <textarea cols="30" rows="10" v-model="freeBoard.fb_content" placeholder="내용"></textarea>
    </div>

    <div>
      <input type="file" @change="imageUpload" ref="boardImage" id="photo" accept="image/*">이미지 등록
                <img :src="imageUploaded" alt="사용자가 업로드한 이미지">
    </div>
    
    <button @click="cancel">취소</button>
    <button @click="regist">등록</button>
  </div>

  
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '../../stores/freeBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

const freeBoard = ref({
  fb_num: 0,
  fb_title: "",
  fb_writer: "",
  fb_content: "",
  fb_userId: userStore.loginUserId,
  fb_viewCnt: 0,
  fb_rcm: 0,
  fb_regDate: "",
})

const cancel = function(){
  router.push({ name: "freeBoardList" })
}

const regist = function () {
  store.createFreeBoard(freeBoard)
}

// 파일업로드
const file = ref({})
const imageUploaded = ref({})

const imageUpload = function () {
    const photo = document.getElementById("photo")
    file.value = photo.files[0];
    const image = file.value
    console.log(file.value)
    imageUploaded.value=URL.createObjectURL(image)
}

</script>

<style scoped></style>
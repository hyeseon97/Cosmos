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
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useFreeBoardStore } from '../../stores/freeBoard';
import { useUserStore } from '../../stores/user';

const router = useRouter();
const store = useFreeBoardStore();
const userStore = useUserStore();

// 파일업로드
const file = ref({})
const imageUploaded = ref({})

const freeBoard = ref({
  fb_num: 0,
  fb_title: "",
  fb_writer: userStore.user.user_name,
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
  const frm = new FormData()
  frm.append("fb_num", freeBoard.value.fb_num)
  frm.append("fb_title", freeBoard.value.fb_title)
  frm.append("fb_writer", freeBoard.value.fb_writer)
  frm.append("fb_content", freeBoard.value.fb_content)
  frm.append("fb_userId", freeBoard.value.fb_userId)
  frm.append("fb_viewCnt", freeBoard.value.fb_viewCnt)
  frm.append("fb_rcm", freeBoard.value.fb_rcm)
  frm.append("fb_regDate", freeBoard.value.fb_regDate)
  frm.append("file",file.value)
  store.createFreeBoard(frm)
}

const imageUpload = function () {
    const photo = document.getElementById("photo")
    file.value = photo.files[0];
    const image = file.value
    console.log(file.value)
    imageUploaded.value=URL.createObjectURL(image)
}

onMounted(() => {
  userStore.getUser(userStore.loginUserId);
})

</script>

<style scoped></style>
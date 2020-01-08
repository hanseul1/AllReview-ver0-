<template>
  <v-container>
    <v-row class="mx-2" align="center">
      <h2>리뷰 작성</h2>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="8">
        <v-text-field
          label="제목"
          v-model="title"
          color="rgb(203, 203, 77)"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="4">
        <v-text-field
          label="모델명"
          v-model="model"
          color="rgb(203, 203, 77)"
        />
      </v-col>
      <v-col cols="4">
        <span>평점</span>
        <v-rating
          v-model="rating"
          background-color="rgb(203, 203, 77)"
          size="40"
          color="rgb(203, 203, 77)"
        ></v-rating>
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="8">
        <v-textarea
          label="리뷰 내용"
          color="rgb(203, 203, 77)"
          outlined
          v-model="context"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="8">
        <v-file-input
          multiple
          label="사진 업로드"
        >
        </v-file-input>
      </v-col>
    </v-row>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn color="rgb(203, 203, 77)">submit</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'WriteReview',
  data () {
    return {
      title: '',
      model: '',
      rating: 0,
      context: '',
      additions: []
    }
  }
}
</script>

<style>

</style>

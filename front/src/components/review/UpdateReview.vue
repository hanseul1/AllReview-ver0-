<template>
  <v-container
    align-baseline="true"
    class="container">
    <v-row class="mx-2">
      <h2>리뷰 수정</h2>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
        <v-text-field
          label="제목"
          v-model="title"
          :color="this.$store.state.color"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="4">
        <v-text-field
          label="모델명"
          v-model="model"
          :color="this.$store.state.color"
        />
      </v-col>
      <v-col cols="4">
        <v-select
          :items="categoryList"
          v-model="category"
          label="카테고리"
          class="input-group--focused"
          item-text="name"
        ></v-select>
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="4">
        <v-layout row wrap>
        <v-menu
          lazy
          :close-on-content-click="false"
          v-model="menu"
          transition="scale-transition"
          offset-y
          full-width
          :nudge-right="40"
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              label="사용 날짜"
              prepend-icon="event"
              readonly
              :value="useDate"
              v-on="on"
              color="rgb(203, 203, 77)"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="useDate"
            no-title
            color="rgb(203, 203, 77)"
            @input="menu = false"
            scrollable></v-date-picker>
        </v-menu>
        </v-layout>
      </v-col>
      <v-col cols="4">
        <v-rating
          v-model="rating"
          :background-color="this.$store.state.color"
          size="40"
          :color="this.$store.state.color"
        ></v-rating>
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="8">
        <v-textarea
          label="리뷰 내용"
          :color="this.$store.state.color"
          outlined
          v-model="context"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2" align="center">
      <v-col cols="8">
        <v-file-input
          multiple
          :color="this.$store.state.color"
          v-model="files"
          label="사진 업로드"
        >
        </v-file-input>
      </v-col>
    </v-row>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn
          :color="this.$store.state.color"
          @click="writeReview">
          수정</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import reviewApi from '@/api/review'
export default {
  name: 'UpdateReview',
  data () {
    return {
      id: null,
      title: '',
      model: '',
      rating: 0,
      menu: false,
      useDate: '',
      context: '',
      files: [],
      categoryList: [],
      category: ''
    }
  },
  mounted () {
    // 카테고리 리스트 검색
    axios
      .get('http://localhost:8080/category')
      .then(response => {
        this.categoryList = response.data.data
      })

    // 수정할 리뷰 데이터
    if (this.$store.state.review !== {}) {
      var data = this.$store.state.review
      this.id = data._id
      this.title = data.title
      this.model = data.model
      this.category = data.category
      this.rating = data.rating
      this.useDate = data.useDate
      this.context = data.context
    }
  },
  methods: {
    writeReview () {
      var reviewData = {
        '_id': this.id,
        'title': this.title,
        'writer': this.$session.get('id'),
        'model': this.model,
        'category': this.category,
        'regDate': new Date(),
        'useDate': this.useDate,
        'rating': this.rating,
        'context': this.context,
        'files': this.files
      }

      reviewApi.requestUpdateReview(reviewData, response => {
        if (response === 'success') {
          alert('리뷰가 수정되었습니다.')
          this.$router.push('/review/list?category=my')
        } else {
          alert('리뷰 수정에 실패했습니다.')
        }
      })
    }
  }
}
</script>

<style scoped>
  .container {
    margin-top: 20px;
  }
</style>

<template>
  <v-container
    align-baseline="true"
    class="container">
    <v-row class="mx-2">
      <h2>리뷰 작성</h2>
    </v-row>
    <v-form
      v-model="valid"
      ref="form"
      lazy-validation>
        <v-row class="mx-2">
          <v-col cols="8">
            <v-text-field
              label="제목"
              :rules="titleRule"
              v-model="title"
              :color="this.$store.state.color"
            />
          </v-col>
        </v-row>
        <v-row class="mx-2" align="center">
          <v-col cols="4">
            <v-text-field
              label="모델명"
              :rules="modelRule"
              v-model="model"
              :color="this.$store.state.color"
            />
          </v-col>
          <v-col cols="4">
            <v-select
              :items="categoryList"
              v-model="category"
              label="카테고리"
              :rules="categoryRule"
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
              :rules="contextRule"
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
      </v-form>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn
          :disabled="!valid"
          :color="this.$store.state.color"
          @click="writeReview">
          등록</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import userApi from '@/api/user'
import reviewApi from '@/api/review'
export default {
  name: 'WriteReview',
  data () {
    return {
      valid: false,
      title: '',
      model: '',
      rating: 0,
      menu: false,
      useDate: new Date().toISOString().substr(0, 10),
      context: '',
      files: [],
      categoryList: [],
      category: '',
      titleRule: [
        (v) => !!v || '제목을 입력해주세요'
      ],
      modelRule: [
        (v) => !!v || '모델명을 입력해주세요'
      ],
      categoryRule: [
        (v) => !!v || '카테고리를 선택해주세요'
      ],
      contextRule: [
        (v) => !!v || '내용을 입력해주세요'
      ]
    }
  },
  mounted () {
    // Authorization token validating
    var component = this
    userApi.validateUser(
      function success (response) {
        // 카테고리 리스트 검색
        axios
          .get('http://localhost:8080/category', {
            headers: {
              'Authorization': sessionStorage.getItem('token')
            }
          })
          .then(response => {
            component.categoryList = response.data.data
          })
      },
      function error () {
        alert('로그인이 필요한 서비스입니다.')
        component.$router.push('/user/login')
      }
    )
  },
  methods: {
    writeReview () {
      if (this.$refs.form.validate()) {
        if (this.files.length > 0) {
          // image file 있으면 file api 호출해서 먼저 저장
          reviewApi.requestInsertFiles(this.files, response => {
            if (response.data.state === 'ok') {
              this.saveReview(response.data.data)
            } else {
              alert('파일 업로드에 실패했습니다.')
            }
          }, () => {
            alert('파일 업로드 중 오류가 발생했습니다.')
          })
        } else {
          // image file 없으면 리뷰 내용만 저장
          this.saveReview([])
        }
      }
    },
    saveReview (fileNames) {
      var reviewData = {
        'title': this.title,
        'writer': this.$session.get('userId'),
        'model': this.model,
        'category': this.category,
        'regDate': new Date(),
        'useDate': this.useDate,
        'rating': this.rating,
        'context': this.context,
        'files': fileNames
      }

      reviewApi.requestInsertReview(reviewData, response => {
        if (response === 'success') {
          alert('리뷰가 등록되었습니다.')
          this.$router.push('/')
        } else {
          alert('리뷰 등록에 실패했습니다.')
        }
      }, () => {
        alert('리뷰 등록 중 오류가 발생했습니다.')
      })
    }
  }
}
</script>

<style>
  .container {
    margin-top: 20px;
  }
</style>

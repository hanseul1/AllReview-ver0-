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
      <v-col cols="3">
        <v-text-field
          label="모델명"
          v-model="model"
          color="rgb(203, 203, 77)"
        />
      </v-col>
      <v-col cols="3">
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
      <v-col cols="3">
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
          color="rgb(203, 203, 77)"
          label="사진 업로드"
        >
        </v-file-input>
      </v-col>
    </v-row>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn
          color="rgb(203, 203, 77)"
          @click="writeReview">
          submit</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'WriteReview',
  data () {
    return {
      title: '',
      model: '',
      rating: 0,
      menu: false,
      useDate: new Date().toISOString().substr(0, 10),
      context: '',
      additions: [
        {'test': 'testvalue'},
        {'test2': 'testvalue2'}
      ]
    }
  },
  methods: {
    writeReview () {
      var reviewData = {
        'title': this.title,
        'writer': this.$session.get('id'),
        'model': this.model,
        'regDate': new Date(),
        'useDate': this.useDate,
        'rating': this.rating,
        'context': this.context
      }

      axios
        .post('http://localhost:8080/review/save', reviewData)
        .then(response => {
          if (response.data.data === 'success') {
            alert('리뷰가 등록되었습니다.')
            this.$router.push('/')
          } else {
            alert('리뷰 등록에 실패했습니다.')
          }
        })
    }
  }
}
</script>

<style>

</style>

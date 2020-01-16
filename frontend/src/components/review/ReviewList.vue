<template>
  <v-container
    align-baseline="true">
    <v-row class="mx-2 mb-4">
      <v-col cols="8"><h2>리뷰 리스트 - {{category}}</h2></v-col>
      <v-col cols="4">
        <v-btn-toggle
            v-model="display"
            mandatory
            dense
        >
            <v-btn>
            <v-icon>list</v-icon>
            </v-btn>
            <v-btn>
            <v-icon>dashboard</v-icon>
            </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row>
    <v-row class="mx-2 mb-4">
      <v-data-table
        v-if="display == 0"
        :headers="headers"
        :items="reviewList"
        :items-per-page="10"
        @click:row="dialogOpen"
        class="elevation-1 list-table"
      >
        <template v-slot:item.rating="{ item }">
          <v-rating
            v-model="item.rating"
            background-color="rgb(203, 203, 77)"
            size="35"
            readonly
            color="rgb(203, 203, 77)"
          ></v-rating>
        </template>
      </v-data-table>
      <template v-else v-for="(review, index) in reviewList">
        <v-card
            :key="index"
            max-width="344"
            class="mx-4 mb-4"
        >
            <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title class="headline">{{review.title}}</v-list-item-title>
                <v-list-item-subtitle>by {{review.writer}}</v-list-item-subtitle>
            </v-list-item-content>
            </v-list-item>

            <v-img
            src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
            height="194"
            ></v-img>

            <v-card-text>{{review.context}}</v-card-text>

            <v-card-actions>
              <v-rating
                v-model="review.rating"
                background-color="rgb(203, 203, 77)"
                size="35"
                color="rgb(203, 203, 77)"
                readonly
              ></v-rating>
            <v-spacer></v-spacer>
            <v-btn icon @click="clickHeart(index)">
                <v-icon>mdi-heart</v-icon>
            </v-btn>
            <v-btn icon>
                <v-icon>thumb_up</v-icon>
            </v-btn>
            </v-card-actions>
        </v-card>
      </template>
    </v-row>

    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          {{review.title}}
        </v-card-title>

        <v-card-text>
          {{review.context}}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            CLOSE
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ReviewList',
  props: ['category'],
  data () {
    return {
      headers: [
        {
          text: 'Title',
          align: 'left',
          sortable: false,
          value: 'title'
        },
        { text: 'Model', value: 'model' },
        { text: 'Rating', value: 'rating' },
        { text: 'Used date', value: 'useDate' },
        { text: 'Writer', value: 'writer' }
      ],
      reviewList: [],
      display: 0,
      dialog: false,
      review: {},
      keywordList: []
    }
  },
  mounted () {
    // 카테고리에 해당하는 리뷰 불러오기
    this.getReviewList()

    // 카테고리에 해당하는 키워드 불러오기
    this.getKeywordList()
  },
  watch: {
    category: function (v) {
      this.getReviewList()
      this.getKeywordList()
    }
  },
  methods: {
    clickHeart (index) {
      console.log(this.reviewList[index].title)
    },
    dialogOpen (value) {
      console.log(value)
      this.dialog = true
      this.review = value
    },
    getKeywordList () {
      // 카테고리에 해당하는 키워드 불러오기
      axios
        .get('http://localhost:8080/keyword/' + this.category)
        .then(response => {
          this.keywordList = response.data.data
        })
    },
    getReviewList () {
      // 카테고리에 해당하는 리뷰 불러오기
      var url = ''
      if (this.category !== 'all') url = '/category/' + this.category
      axios
        .get('http://localhost:8080/review' + url)
        .then(response => {
          this.reviewList = response.data.data
        })
    }
  }
}
</script>

<style>
  .list-table {
    width: 1000px;
  }
</style>

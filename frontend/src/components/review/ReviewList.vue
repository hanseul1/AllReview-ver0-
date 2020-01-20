<template>
  <v-container
    align-baseline="true">
    <v-row v-if="category !== 'my'" class="mx-2 mb-4">
      <v-col cols="10">
        <v-sheet elevation="2" class="pa-4">
          <v-chip-group
            column
            multiple
            v-model="keywords"
          >
            <v-chip
              v-for="keyword in keywordList"
              :key="keyword.no"
              outlined
              color="rgb(203, 203, 77)">
              {{ keyword.word }}
            </v-chip>
          </v-chip-group>
        </v-sheet>
      </v-col>
    </v-row>
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
        item-key="_id"
        sort-by="regDate"
        sort-desc
        show-expand
        :expanded.sync="expanded"
        class="elevation-1 list-table"
      >
        <template v-slot:item.title="{ item }">
          <span
            @click="dialogOpen(item)"
            class="context"
          >{{item.title}}</span>
        </template>

        <template v-slot:item.model="{ item }">
          <span
            class="context model"
          >{{item.model}}</span>
        </template>

        <template v-slot:item.rating="{ item }">
          <v-rating
            v-model="item.rating"
            background-color="rgb(203, 203, 77)"
            size="35"
            readonly
            color="rgb(203, 203, 77)"
          ></v-rating>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon
            v-if="item.writer == $session.get('id')"
            small
            class="mr-2"
            @click="editReview(item)"
          >
            edit
          </v-icon>
          <v-icon
            v-if="item.writer == $session.get('id')"
            small
            @click="deleteReview(item)"
          >
            delete
          </v-icon>
        </template>

        <template v-slot:expanded-item="{ headers, item }">
          <td :colspan="headers.length">{{item.context}}</td>
        </template>
      </v-data-table>
      <template v-else v-for="(review, index) in reviewList">
        <v-card
            :key="index"
            max-width="300"
            max-height="500"
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

            <v-card-text class="context">{{review.context}}</v-card-text>

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
        { text: 'Writer', value: 'writer' },
        { text: 'update/delete', value: 'action', sortable: false },
        { text: '', value: 'data-table-expand' }
      ],
      reviewList: [],
      display: 0,
      dialog: false,
      review: {},
      keywordList: [],
      keywords: [],
      expanded: []
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
      if (v === 'my' && this.$session.get('id') == null) {
        alert('로그인이 필요한 서비스입니다.')
        this.$router.push('/user/login')
      }
      this.reviewList = []
      this.getReviewList()
      this.getKeywordList()
      this.keywords = []
      this.expanded = []
    },
    keywords: function (v) {
      this.expanded = []
      // 선택한 키워드가 존재하면 키워드 검색 API 호출
      if (v.length !== 0) {
        var keywordData = []
        for (var i = 0; i < v.length; i++) {
          keywordData.push(this.keywordList[v[i]].word)
        }

        var data = {
          'keywords': keywordData,
          'category': this.category
        }

        axios
          .post('http://localhost:8080/review/keyword', data)
          .then(response => {
            this.reviewList = response.data.data
          })
      } else {
        // 선택한 키워드가 존재하지 않으면 전체 리뷰 리스트 조회
        this.getReviewList()
      }
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
      if (this.category === 'my') url = '/writer/' + this.$session.get('id')

      axios
        .get('http://localhost:8080/review' + url)
        .then(response => {
          this.reviewList = response.data.data
        })
    },
    editReview (review) {
      this.$store.state.review = review
      this.$router.push('/review/update')
    },
    deleteReview (review) {
      axios
        .delete('http://localhost:8080/review/' + review._id)
        .then(response => {
          if (response.data.data === 'success') {
            alert('삭제되었습니다.')
            // 리뷰 리스트 다시 검색
            this.getReviewList()
          }
        })
    }
  }
}
</script>

<style>
  .list-table {
    width: 1000px;
  }
  .context {
    display: block;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
  .text-left {
    max-width: 300px;
  }
  .model {
    max-width: 100px;
  }
  .v-data-table td {
    padding : 20px;
  }
</style>

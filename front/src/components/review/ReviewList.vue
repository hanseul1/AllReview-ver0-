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
            v-if="item.writer == $session.get('userId')"
            small
            class="mr-2"
            @click="updateReview(item)"
          >
            edit
          </v-icon>
          <v-icon
            v-if="item.writer == $session.get('userId')"
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
            width="300"
            max-height="500"
            class="mx-4 mb-4"
        >
            <v-list-item>
            <v-list-item-avatar>
              <v-img :src="profileImage"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title class="headline">{{review.title}}</v-list-item-title>
                <v-list-item-subtitle>by {{review.writer}}</v-list-item-subtitle>
            </v-list-item-content>
            </v-list-item>

            <v-carousel
              hide-delimiters
              style="height:194px"
              v-if="review.files !== null">
              <v-carousel-item
                v-for="(item,i) in review.files"
                :key="i"
                :src="'http://localhost:8080/static/img/' + item"
              ></v-carousel-item>
            </v-carousel>
            <!-- <v-img
            v-if="review.files !== null"
            :src="'http://localhost:8080/static/img/' + review.files[0]"
            height="194"
            ></v-img> -->
            <v-img
            v-else
            :src="noImage"
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
import '@/assets/style/css/review.css'
import userApi from '@/api/user'
import reviewApi from '@/api/review'
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
      expanded: [],
      profileImage: require('../../assets/Olive.png'),
      noImage: require('../../assets/no-image.png')
    }
  },
  mounted () {
    if (this.category === 'my') {
      this.validating()
    }
    // 카테고리에 해당하는 리뷰 불러오기
    this.getReviewList()

    // 카테고리에 해당하는 키워드 불러오기
    this.getKeywordList()
  },
  watch: {
    category: function (v) {
      if (v === 'my') {
        this.validating()
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
    validating () {
      // Authorization token validating
      var component = this
      userApi.validateUser(
        function success (response) {},
        function error () {
          alert('로그인이 필요한 서비스입니다.')
          component.$router.push('/user/login')
        }
      )
    },
    clickHeart (index) {
      console.log(this.reviewList[index].title)
    },
    dialogOpen (value) {
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
      reviewApi.requestReviewListByCategory(this.category, response => {
        this.reviewList = response
      })
    },
    updateReview (review) {
      this.$store.state.review = review
      this.$router.push('/review/update')
    },
    deleteReview (review) {
      reviewApi.requestDeleteReview(review._id, () => {
        alert('리뷰가 삭제되었습니다.')
        // 리뷰 리스트 다시 검색
        this.getReviewList()
      }, () => {
        alert('로그인이 필요한 서비스입니다.')
        this.$router.push('/user/login')
      })
    }
  }
}
</script>

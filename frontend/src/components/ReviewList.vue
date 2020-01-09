<template>
  <v-container
    fluid
    align-baseline="true"
    class="grey lighten-4 fill-height">
    <v-row class="mx-2 mb-4">
      <v-col cols="6"><h2>리뷰 리스트 - 전체</h2></v-col>
      <v-col cols="6">
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
        class="elevation-1 list-table"
      ></v-data-table>
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
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ReviewList',
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
      display: 0
    }
  },
  mounted () {
    axios
      .get('http://localhost:8080/review')
      .then(response => {
        this.reviewList = response.data.data
      })
  },
  methods: {
    clickHeart (index) {
      console.log(this.reviewList[index].title)
    }
  }
}
</script>

<style>
  .list-table {
    width: 1200px;
  }
</style>

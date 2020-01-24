<template>
  <v-container align-baseline="true">
    <v-carousel
      cycle
      height="250"
      hide-delimiter-background
      show-arrows-on-hover>
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.src"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-3">{{item.text}}</div>
        </v-row>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      items: [
        {
          src: require('../../assets/foodBanner.jpg'),
          text: 'Food'
        },
        {
          src: require('../../assets/digitalBanner.jpg'),
          text: 'Digital'
        },
        {
          src: require('../../assets/beautyBanner.jpg'),
          text: 'Beauty'
        }
      ]
    }
  }
}
</script>

<style>
    .v-content__wrap {
      background-image: url('../../assets/banner.jpg');
      background-repeat : repeat;
    }
    .banner-text {
      font-size: 40px;
      text-align: center;
    }
    .display-3 {
      color: white;
      font-weight: 700;
    }
</style>

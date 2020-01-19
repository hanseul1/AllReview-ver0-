<template>
  <v-container align-baseline="true">
    <v-row class="mx-12 banner-text">
      <h2 class="font-italic font-weight-light">All of Reviews For You</h2>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Main'
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
</style>

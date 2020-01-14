<template>
  <v-container
    align-baseline="true"
    class="container">
    <v-row class="mx-2">
      <h2>사용자 정보</h2>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
      </v-col>
    </v-row>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn
          :color="this.$store.state.color"
          @click="updateInfo">
          수정</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Userinfo',
  data () {
    return {
      user_id: '',
      name: '',
      password: '',
      phone: ''
    }
  },
  mounted () {
    this.user_id = this.$session.get('id')
    if (this.user_id == null) {
      alert('로그인이 필요한 서비스입니다.')
      this.$router.push('/user/login')
    }

    axios
      .get('http://localhost:8080/user/' + this.user_id)
      .then(response => {
        this.name = response.data.data.name
        this.phone = response.data.data.phone
      })
  },
  methods: {
    updateInfo () {
    }
  }
}
</script>

<style>

</style>

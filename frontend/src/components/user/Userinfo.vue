<template>
  <v-container
    align-baseline="true"
    class="container">
    <v-row class="mx-2">
      <h2>사용자 정보</h2>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
        <v-text-field
          prepend-icon="account_circle"
          v-model="user_id"
          outlined
          label="아이디"
          disabled
        />
      </v-col>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
        <v-text-field
          prepend-icon="local_activity"
          v-model="name"
          outlined
          label="이름"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
        <v-text-field
          prepend-icon="lock"
          v-model="password"
          outlined
          type="password"
          label="비밀번호"
        />
      </v-col>
    </v-row>
    <v-row class="mx-2">
      <v-col cols="8">
        <v-text-field
          prepend-icon="phone_iphone"
          v-model="phone"
          outlined
          label="전화번호"
        />
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
      phone: '',
      token: ''
    }
  },
  mounted () {
    // Authorization token validating
    this.token = this.$store.state.userToken
    this.user_id = this.$store.state.userId
    axios
      .get('http://localhost:8080/user/' + this.user_id, {
        headers: {
          'Authorization': this.token
        }
      })
      .then(response => {
        this.name = response.data.data.name
        this.phone = response.data.data.phone
      })
      .catch(() => {
        alert('로그인이 필요한 서비스입니다.')
        this.$store.state.userToken = ''
        this.$store.state.userId = ''
        this.$router.push('/user/login')
      })
  },
  methods: {
    updateInfo () {
      let requestData = {
        'id': this.user_id,
        'pw': this.password,
        'name': this.name,
        'phone': this.phone
      }
      axios
        .put('http://localhost:8080/user', requestData)
        .then(response => {
          if (response.data.data === 'success') {
            alert('수정 완료되었습니다.')
            this.$router.push('/')
          }
        })
    }
  }
}
</script>

<style>

</style>

<template>
  <v-container>
          <v-row align="center">
            <h2>회원가입</h2>
          </v-row>
          <div style="width:500px">
            <v-text-field
              class="pl-3 pr-3"
              required
              v-model="id"
              label="아이디"
              :rules="idRule"
              prepend-icon="account_circle">
            </v-text-field>
            <v-text-field
              class="pl-3 pr-3"
              required
              v-model="name"
              label="이름"
              :rules="nameRule"
              prepend-icon="local_activity">
            </v-text-field>
            <v-text-field
              class="pl-3 pr-3"
              required
              v-model="pw"
              label="비밀번호"
              :rules="pwRule"
              type="password"
              prepend-icon="lock">
            </v-text-field>
            <v-text-field
              class="pl-3 pr-3"
              required
              label="비밀번호확인"
              :rules="pwValidate"
              type="password"
              prepend-icon="lock">
            </v-text-field>
            <v-text-field
              class="pl-3 pr-3"
              required
              v-model="phone"
              label="전화번호"
              prepend-icon="phone_iphone">
            </v-text-field>
            <v-layout align-end justify-end>
              <v-btn
                class="mt-3"
                outlined
                @click="signup">
                Signup
              </v-btn>
            </v-layout>
          </div>
        </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Signup',
  data () {
    return {
      id: '',
      pw: '',
      name: '',
      phone: ''
    }
  },
  methods: {
    signup () {
      var formData = {
        'id': this.id,
        'pw': this.pw,
        'name': this.name,
        'phone': this.phone
      }

      axios
        .post('http://localhost:8080/user/signup', formData)
        .then(response => {
          if (response.data.data === 'success') {
            alert('회원 가입 완료')
            this.$router.push('/login')
          } else {
            alert('회원 가입 실패ㅜ.ㅜ 다시 시도해 주세요')
          }
        })
    }
  }
}
</script>

<style>
  .container {
      margin-top : 50px;
  }
</style>

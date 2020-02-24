<template>
        <v-container>
          <v-row align="center">
            <h2>로그인</h2>
          </v-row>
          <v-row>
            <v-col
              class="align-center justify-space-between"
              cols="4"
            >
              <v-text-field
                prepend-icon="account_circle"
                v-model="id"
                placeholder="ID"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4">
              <v-text-field
                prepend-icon="lock"
                v-model="pw"
                type="password"
                placeholder="Password"
                v-on:keyup.enter="login"
              />
            </v-col>
          </v-row>
          <v-row class="mx-2">
            <v-btn
              outlined
              :color="this.$store.state.color"
              @click="login"
            >Login</v-btn>
          </v-row>
        </v-container>
</template>

<script>
import userApi from '@/api/user'
export default {
  name: 'Login',
  data () {
    return {
      id: '',
      pw: ''
    }
  },
  methods: {
    login () {
      var loginData = {
        'id': this.id,
        'pw': this.pw
      }

      userApi.requestLogin(loginData, () => {
        window.location.reload()
      }, () => {
        alert('로그인 중 오류가 발생했습니다.')
      })
    }
  }
}
</script>

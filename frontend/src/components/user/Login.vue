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
import axios from 'axios'
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

      axios
        .post('http://127.0.0.1:8080/user/login', loginData)
        .then(response => {
          if (response.data.state === 'ok') {
            this.$session.set('userToken', response.data.data)
            this.$session.set('userId', this.id)
            window.location.reload()
          } else {
            alert('login fail')
          }
        })
    }
  }
}
</script>

<style>

</style>

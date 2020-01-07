<template>
    <v-card>
        <v-card-title class="grey darken-2">
          Login
        </v-card-title>
        <v-container>
          <v-row class="mx-2">
            <v-col
              class="align-center justify-space-between"
              cols="12"
            >
              <v-text-field
                prepend-icon="account_circle"
                v-model="id"
                placeholder="ID"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="lock"
                v-model="pw"
                placeholder="Password"
              />
            </v-col>
          </v-row>
        </v-container>
        <v-card-actions>
          <v-spacer />
          <v-btn
            text
            color="primary"
            @click="login"
          >Login</v-btn>
        </v-card-actions>
      </v-card>
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
          if (response.data.data === 'success') {
            this.$session.set('id', this.id)
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

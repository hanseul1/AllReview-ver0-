<template>
  <v-container class="container">
          <v-row align="center">
            <h2>회원가입</h2>
          </v-row>
          <div style="width:500px">
            <v-form
              v-model="valid"
              ref="form"
              lazy-validation>
              <v-row>
                <v-text-field
                  class="pl-3 pr-3"
                  required
                  v-model="id"
                  label="아이디"
                  :rules="idRule"
                  :color="this.$store.state.color"
                  prepend-icon="account_circle">
                </v-text-field>
                <v-btn
                  class="mt-3"
                  outlined
                  :color="this.$store.state.color"
                  @click="checkId">
                  중복확인
                </v-btn>
              </v-row>
              <v-row>
                <v-text-field
                  class="pl-3 pr-3"
                  required
                  v-model="name"
                  label="이름"
                  :rules="nameRule"
                  :color="this.$store.state.color"
                  prepend-icon="local_activity">
                </v-text-field>
              </v-row>
              <v-row>
                <v-text-field
                  class="pl-3 pr-3"
                  required
                  v-model="pw"
                  label="비밀번호"
                  :rules="pwRule"
                  type="password"
                  :color="this.$store.state.color"
                  prepend-icon="lock">
                </v-text-field>
              </v-row>
              <v-row>
                <v-text-field
                  class="pl-3 pr-3"
                  required
                  label="비밀번호확인"
                  v-model="pw2"
                  :rules="pwValidate"
                  type="password"
                  :color="this.$store.state.color"
                  prepend-icon="lock">
                </v-text-field>
              </v-row>
              <v-row>
                <v-text-field
                  class="pl-3 pr-3"
                  required
                  v-model="phone"
                  label="전화번호"
                  :color="this.$store.state.color"
                  prepend-icon="phone_iphone">
                </v-text-field>
              </v-row>
              <v-row>
                <v-layout align-end justify-end>
                  <v-btn
                    class="mt-3"
                    outlined
                    :disabled="!(valid&&idCheck)"
                    :color="this.$store.state.color"
                    @click="signup">
                    Signup
                  </v-btn>
                </v-layout>
              </v-row>
            </v-form>
          </div>
        </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Signup',
  data () {
    return {
      valid: false,
      id: '',
      pw: '',
      name: '',
      phone: '',
      pw2: '',
      idCheck: false,
      idRule: [
        (v) => !!v || 'id를 입력해주세요.'
      ],
      nameRule: [
        (v) => !!v || '이름을 입력해주세요.'
      ],
      pwRule: [
        (v) => !!v || '비밀번호를 입력해주세요.'
      ],
      pwValidate: [
        (v) => !!v || '비밀번호 확인을 입력해주세요.',
        (v) => this.pw === v || '비밀번호 확인이 다릅니다.'
      ]
    }
  },
  methods: {
    signup () {
      if (this.$refs.form.validate()) {
        var formData = {
          'id': this.id,
          'pw': this.pw,
          'name': this.name,
          'phone': this.phone
        }

        axios
          .post('http://localhost:8080/user/signup', formData)
          .then(response => {
            if (response.data.state === 'ok') {
              alert('회원 가입 완료')
              this.$session.set('userToken', response.data.data)
              this.$session.set('userId', this.id)
              window.location.reload()
            } else {
              alert('회원 가입 실패ㅜ.ㅜ 다시 시도해 주세요')
            }
          })
      }
    },
    checkId () {
      // id 중복 확인
      axios
        .get('http://localhost:8080/user/idcheck/' + this.id)
        .then(response => {
          if (response.data.data === 'not ok') {
            alert('사용할 수 없는 아이디 입니다.')
            this.id = ''
          } else {
            this.idCheck = true
            alert('사용할 수 있는 아이디 입니다.')
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

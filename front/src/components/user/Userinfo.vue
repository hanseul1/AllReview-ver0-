<template>
  <v-container
    align-baseline="true"
    class="container">
    <v-row class="mx-2">
      <h2>사용자 정보</h2>
    </v-row>
    <v-form
      v-model="valid"
      ref="form"
      lazy-validation>
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
              :rules="nameRule"
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
              :rules="pwRule"
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
    </v-form>
    <v-row>
      <v-spacer/>
      <v-col cols="5">
        <v-btn
          :disabled="!valid"
          :color="this.$store.state.color"
          @click="updateInfo">
          수정</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import userApi from '@/api/user'
export default {
  name: 'Userinfo',
  data () {
    return {
      valid: false,
      user_id: '',
      name: '',
      password: '',
      phone: '',
      nameRule: [
        (v) => !!v || '이름을 입력해주세요.'
      ],
      pwRule: [
        (v) => !!v || '비밀번호를 입력해주세요.'
      ]
    }
  },
  mounted () {
    // Authorization token validating
    var component = this
    userApi.validateUser(
      function success (response) {
        component.name = response.data.name
        component.phone = response.data.phone
      },
      function error () {
        alert('로그인이 필요한 서비스입니다.')
        component.$router.push('/user/login')
      }
    )
  },
  methods: {
    updateInfo () {
      if (this.$refs.form.validate()) {
        let requestData = {
          'id': this.user_id,
          'pw': this.password,
          'name': this.name,
          'phone': this.phone
        }

        userApi.requestUpdateUser(requestData, () => {
          alert('수정 완료되었습니다.')
          this.$router.push('/')
        }, () => {
          alert('회원 정보 수정 중 오류가 발생했습니다.')
        })
      }
    }
  }
}
</script>

<style>

</style>

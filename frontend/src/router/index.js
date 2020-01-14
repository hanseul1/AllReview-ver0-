import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/user/Login'
import WriteReview from '@/components/review/WriteReview'
import ReviewList from '@/components/review/ReviewList'
import Signup from '@/components/user/Signup'
import Userinfo from '@/components/user/Userinfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/user/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/review/write',
      name: 'WriteReview',
      component: WriteReview
    },
    {
      path: '/review/list',
      name: 'ReviewList',
      component: ReviewList
    },
    {
      path: '/user/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/user/info',
      name: 'Userinfo',
      component: Userinfo
    }
  ]
})

import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import WriteReview from '@/components/WriteReview'
import ReviewList from '@/components/ReviewList'
import Signup from '@/components/Signup'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/writeReview',
      name: 'WriteReview',
      component: WriteReview
    },
    {
      path: '/reviewList',
      name: 'ReviewList',
      component: ReviewList
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    }
  ]
})

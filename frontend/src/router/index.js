import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import WriteReview from '@/components/WriteReview'

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
    }
  ]
})

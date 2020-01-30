// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import VueSession from 'vue-session'
import {store} from './vuex/store'

Vue.config.productionTip = false
Vue.use(Vuetify)

var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)

export default new Vuetify({
  icons: {
    iconfont: 'mdiSvg'
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  vuetify: new Vuetify(),
  components: { App },
  template: '<App/>'
})

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    color: 'rgb(203, 203, 77)',
    review: {}
  }
})

import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

const state = {
  color: 'rgb(203, 203, 77)',
  review: {},
  userImage: '',
  serverIP: 'http://localhost:8080'
}

export default new Vuex.Store({
  state,
  actions,
  mutations,
  getters
})

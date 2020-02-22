import axios from 'axios'
import store from '../vuex/store'

export default {
  validateUser,
  requestLogin,
  requestIdCheck,
  requestSignup,
  requestUpdateUser
}

var serverIP = store.state.serverIP
var session = sessionStorage

/** 사용자 token 유효성 인증 */
function validateUser (successCallBack, errorCallBack) {
  var token = session.getItem('token')
  var id = session.getItem('id')

  if (token === undefined) errorCallBack()
  else {
    axios
      .get(serverIP + '/user/' + id, {
        headers: {
          'Authorization': token
        }
      })
      .then(response => {
        successCallBack(response.data)
      })
      .catch(() => {
        errorCallBack()
      })
  }
}

/** 사용자 로그인 요청 */
function requestLogin (data, callback, errorCallback) {
  axios
    .post('http://127.0.0.1:8080/user/login', data)
    .then(response => {
      if (response.data.data !== 'not success') {
        session.setItem('token', response.data.data)
        session.setItem('id', data.id)
        callback()
      } else {
        alert('login fail')
      }
    })
    .catch(() => errorCallback())
}

/** id 중복 확인 요청 */
function requestIdCheck (data, callback, errorCallback) {
  axios
    .get('http://localhost:8080/user/idcheck/' + data)
    .then(response => {
      callback(response.data.data)
    })
    .catch(() => errorCallback())
}

/** 사용자 회원가입 요청 */
function requestSignup (data, callback, errorCallback) {
  axios
    .post('http://localhost:8080/user/signup', data)
    .then(response => {
      if (response.data.state === 'ok') {
        session.setItem('token', response.data.data)
        session.setItem('id', data.id)
        callback()
      }
    })
    .catch(() => errorCallback())
}

/** 사용자 정보 수정 요청 */
function requestUpdateUser (data, callback, errorCallback) {
  axios
    .put('http://localhost:8080/user', data, {
      headers: {
        'Authorization': session.getItem('token')
      }
    })
    .then(response => {
      if (response.data.data === 'success') {
        callback()
      }
    })
    .catch(() => errorCallback())
}

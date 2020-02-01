import axios from 'axios'
import store from '../vuex/store'

export default {
  validateUser
}

var serverIP = store.state.serverIP

function validateUser (userToken, userId, successCallBack, errorCallBack) {
  if (userToken === undefined) errorCallBack()
  else {
    axios
      .get(serverIP + '/user/' + userId, {
        headers: {
          'Authorization': userToken
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

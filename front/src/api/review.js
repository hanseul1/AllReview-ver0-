import axios from 'axios'

export default {
  requestReviewListByCategory,
  requestInsertFiles,
  requestInsertReview,
  requestUpdateReview,
  requestDeleteReview
}

var session = sessionStorage

/** 카테고리에 해당하는 리뷰 목록 요청 */
function requestReviewListByCategory (data, callback) {
  var url = ''
  if (data === 'all') url = '/all'
  else url = '/category/' + data
  if (data === 'my') url = '/writer/' + session.getItem('id')
  axios
    .get('http://localhost:8080/review' + url)
    .then(response => {
      callback(response.data.data)
    })
}

/** 리뷰 파일 목록 등록 요청 */
function requestInsertFiles (data, callback, errorCallback) {
  var fileData = new FormData()
  for (var i = 0; i < data.length; i++) {
    fileData.append('files', data[i])
  }

  axios
    .post('http://localhost:8080/review/files', fileData, {
      headers: {
        'enctype': 'multipart/form-data',
        'Authorization': session.getItem('token')
      }
    })
    .then(response => {
      callback(response)
    })
    .catch(() => errorCallback())
}

/** 리뷰 등록 요청 */
function requestInsertReview (data, callback, errorCallback) {
  axios
    .post('http://localhost:8080/review', data, {
      headers: {
        'Authorization': session.getItem('token')
      }
    })
    .then(response => {
      callback(response.data.data)
    })
    .catch(() => errorCallback())
}

/** 리뷰 수정 요청 */
function requestUpdateReview (data, callback, errorCallback) {
  axios
    .put('http://localhost:8080/review', data, {
      headers: {
        'Authorization': session.getItem('token')
      }
    })
    .then(response => {
      callback(response.data.data)
    })
    .catch(() => errorCallback())
}

/** 리뷰 삭제 요청 */
function requestDeleteReview (data, callback, errorCallback) {
  axios
    .delete('http://localhost:8080/review/' + data, {
      headers: {
        'Authorization': session.getItem('token')
      }
    })
    .then(response => {
      if (response.data.data === 'success') {
        callback()
      }
    })
    .catch(() => {
      errorCallback()
    })
}

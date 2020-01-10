# Javascript

#### javascript

- 객체 기반의 스크립트 프로그래밍 언어

- 브라우저에서 주로 사용, 서버사이드 네트워크 프로그래밍에도 쓰인다.

  => 즉, 브라우저 뿐만 아니라 서버, 자바스크립트 엔진이 내장되어있는 프로그램에서도 실행 가능

  ​		(브라우저에는 자바스크립트 가상 머신이라는 엔진 내장되어있음)

  - 자바스크립트 엔진

    ```
    - v8 : Chrome, Opera
    - SpiderMonkey : FireFox
    - 그 외 Trident, Chakra 등
    ```

- 장점 
  - 대부분의 브라우저에서 지원
  - HTML/CSS 와 완전히 통합 가능
  - 간단한 작업 처리 가능



#### Promise

- Javascript는 대부분 비동기적으로 작업 수행

  즉, 어떤 작업을 요청하면서 callback 함수를 등록하면, 작업 수행 후 결과를 callback 함수를 통해 리턴하는 식이다.

- Promise는 이러한 비동기 작업의 수행 후 결과 처리를 좀 더 수월하게 하기 위한 handler 객체이다.

  => callback 함수 대신 활용 가능

- Promise의 생성과 실행

  ```javascript
  // 프로미스 생성
  const testFunction = function(param){
    return new Promise(function(resolve,reject){
      if(param){
        resolve("성공");
      }
      else{
        reject("실패");
      }
    });
  }
  // 프로미스 실행
  testFunction(true).then(function(result){
    console.log(result);	// 성공
  },function(err){
    console.log(err);		// 실패
  });
  ```

  - resolve : 비동기 함수의 결과가 성공일 때 수행되는 함수

    reject : 비동기 함수의 결과가 실패일 때 수행되는 함수

  ```javascript
  new Promise(function (resolve, reject) {
  }).then(function (resolve) {
      //resolve 값 처리
  }).catch(function (reject) {
      //reject 값 처리
  }).finally(function(){
      //항상 처리
  });
  ```

  - 함수 결과가 성공이면 resolve 함수를 호출하여 resolve 함수의 parameter를 then으로 받는다.

    함수 결과가 실패이면 reject 함수를 호출하여 reject 함수의 parameter를 catch로 받는다.



#### Arrow Function

- 함수 선언문이나 함수 표현식보다 함수를 간단하게 정의할 수 있는 방식

  ```javascript
  let func = (arg1, arg2, ...argN) => expression
  ```

- callback 함수에서도 사용 가능

  ```javascript
  var numbers = [1, 4, 9];
  
  // 일반 callback 함수
  var oddArr = numbers.filter(function (x) { return x % 2 !== 0;});
  
  // arrow function을 활용한 callback 함수
  var oddArr = numbers.filter( x => (x % 2) !== 0 );
  ```

- this

  - 일반 함수의 경우, 함수의 내부함수, 콜백 함수에 사용되는 this는 window 이다.

    ```javascript
    let cat = {
          sound: "meow",
          soundPlay: function () {
              console.log(this) // cat{sound: "meow", soundPlay: ƒ}
              
              // 내부 함수
              setTimeout(function () {
                console.log(this) // window
                console.log(this.sound); // undefined(window에 sound가 정의되지 않았음)
            }, 1000);
        }
    }
    ```

  - arrow 함수의 경우, 내부함수, 콜백 함수에 사용되는 this는 바로 상위의 객체이다.

    ```javascript
    let cat = {
        sound: "meow",
          soundPlay: function () {
            setTimeout(() => {
                console.log(this.sound);  // meow
            }, 1000);
        }
    }
    ```

  - axios에서 promise의 콜백 함수에서 this를 사용하는 이유

    - HTTP 통신 후 결과 처리할 때 해당 컴포넌트의 data를 활용하기 위해서

      (일반 함수를 활용했을 때 data에 접근할 수 있는 방법이 없음)


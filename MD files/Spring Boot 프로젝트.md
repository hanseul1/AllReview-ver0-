## Spring Boot 프로젝트 (With Vue.js)

#### 프로젝트 생성

```
- Spring starter project : 2.2.2 ver
- java : 1.8
- maven library
- DB : mysql
- Persistence framework : mybatis
```


#### Error logging

1. spring starter project 생성 직후 pom.xml Line1 unknown error

   - 원인 : maven에서 프로젝트 빌드 및 컴파일 시 의존성이 맞지 않아 발생하는 오류라고 생각됨

   - 해결 방법
   	
   	- project 우클릭 -> maven -> update project
   		
   	- pom.xml의 프로젝트 의존성을 체크하는 필드인 spring-boot-starter-parent 수정
   	  (version 정보 변경)
   	
   	- pom.xml에서 property 추가
   	
   	  ```xml
   	  <properties>
   	  	<java.version>1.8</java.version>
   	  	<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
   	  </properties>
   	  ```

   - 참고 : [https://caileb.tistory.com/entry/pomxml-%ED%8C%8C%EC%9D%BC%EC%9D%98-line-1%EB%B2%88-Unknown-Error](https://caileb.tistory.com/entry/pomxml-파일의-line-1번-Unknown-Error)

2. jsp 연동 오류 (index.jsp 404 error)

   - 원인 

     - spring boot 프로젝트의 jar 패키징 후 내장 톰캣 사용할 시 jsp를 web template으로 사용할 수 없다.

       (spring boot에서는 jsp의 사용을 지양하고 있다.)

     - jsp를 사용하기 위해서는 war 패키징하여야 한다.

   - 해결 방법

     - pom.xml의 packaging 방식 변경(없으면 추가)

       ```xml
       <packaging>war</packaging>
       ```

     - pom.xml의 의존성 추가

       ```xml
       <dependency>
           <groupId>org.apache.tomcat.embed</groupId>
           <artifactId>tomcat-embed-jasper</artifactId>
       </dependency>
       <dependency>
       	<groupId>jstl</groupId>
       	<artifactId>jstl</artifactId>
       	<version>1.2</version>
       </dependency>
       ```

     - jsp 파일을 view template으로 사용하기 위해서 jsp 파일은 src/main/webapp 아래에 위치한다.

     - controller 작성

       ```java
       @RequestMapping("/")
       public String index() {
       	return "/jsp/index";
       }
       ```

   - JAR 와 WAR 

     - JAR : Java Archive. 압축된 .class파일, 컴파일된 java 라이브러리, 어플리케이션 리소스 등을 포함하는 압축파일

       => 자바 플랫폼에 응용 소프트웨어나 라이브러리를 배포하기 위한 소프트웨어 패키지 파일 포맷

       => 추가 소프트웨어를 사용하지 않고 바로 실행 가능(embedded tomcat)

     - WAR : Web application Archive. 모든 servlet/ JSP 컨테이너에 배치할 수 있는 웹 어플리케이션을 압축한 파일

       => 실행시 별도의 서버가 필요함

3. HTTP 요청 CORS 에러

   - 원인

     - CORS : Cross Origin Resource Sharing
   
     - domain 또는 port가 다른 서버의 자원을 요청하는 방식을 뜻한다.

     - 브라우저에서는 동일 출처 정책(same-origin policy)으로 인해 외부 서버에 요청한 데이터를 보안 목적으로 차단한다.
   
       - 동일 출처 정책
   
         : 다른 출처에서 가져온 리소스와 현재 가지고 있는 리소스가 상호작용 하는 것을 제한하는 보안 방식
   
         => 잠재적 악성 문서를 격리하여 공격 경로를 줄인다.
   
     - 즉, localhost:8081(Vue 서버)에서 localhost:8080(spring boot 서버)로 자원을 요청하였기 때문에 발생한 에러이다.
   
   - 해결방법
   
     - Spring boot 프로젝트의 controller에 CrossOrigin annotation 추가
     
       ```java
     @CrossOrigin(origins = {"*"}, maxAge = 6000)
       ```
     
     - node.js의 미들웨어 CORS 추가(Express 사용할 경우)
     
       ```
       npm install --save cors
       ```
     
       => 이 경우 모든 외부 서버에 대한 요청에 대해 허가를 하게 되어 보안이 취약해진다.
   
   - 참고 : https://velog.io/@wlsdud2194/cors
   
   

#### Vue.js 연동

- **GUI Framework에서 중요하게 봐야 할 4가지 요소

  ```
  - Component
  - Render
  - Life cycle
  - User event
  ```

- Vue.js를 활용한 Front-End 개발을 위해 Vue 프로젝트를 생성하여 Spring Boot 프로젝트 폴더 하위에 위치시킨다.

- Vue가 빌드한 html을 렌더링하기 위해서는 프로젝트를 jar로 바꿔줘야 한다.

  - Spring boot(jar)가 자동 설정으로 src/main/resources 에서 정적 파일(html, css, javascript)을 인식한다. (war의 경우 src/main/webapp 에서 인식한다. - WEB-INF 디렉토리 존재)

  - application.properties에서 servlet 관련 설정들을 삭제한다.

    ```
    #server.servlet.context-path=/allreview
    #spring.mvc.view.prefix=/static
    #spring.mvc.view.suffix=.html
    ```

- npm run build 명령 실행 => 설정한 경로에 html 파일이 생성되고, spring boot application을 실행하면 해당 페이지를 볼 수 있다.

- Vue webpack 프로젝트 생성 참고 : https://blog.naver.com/PostView.nhn?blogId=spdlqjdudghl&logNo=221349293910&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView



#### Vuetify 라이브러리 설치

- npm install vuetify

- main.js 수정

  ```js
  import Vuetify from 'vuetify'
  
  Vue.use(Vuetify)
  export default new Vuetify({})
  
  new Vue({
      vuetify: new Vuetify(),
      ...
  })
  ```

- App.vue 수정

  ```vue
  <template>
  	<v-app id="app">
          ...
      </v-app>
  </template>
  ```



#### Vue axios / Vue-session 설치

```
npm install --save axios vue-session
```

- axios : javascript용 서버 HTTP통신 라이브러리

  - Promise 기반의 API 형식으로 비동기 처리 방식 사용

  - get / post / put / delete 함수 사용

- --save : 설치한 라이브러리의 의존성을 package.json에 자동 저장하는 명령

- node_modules의 경우 git에 push할 필요 없음(용량이 크기 때문에 ignore하는 것이 좋음)

  => 해당 프로젝트 git clone 했을 때 node_modules 필요

  - 기존 package.json에 설정된 의존성에 맞게 모듈 설치할 필요가 있음
  - npm install : 프로젝트에 포함된 package.json의 설정에 맞게 모듈을 설치하는 명령어



#### MongoDB 연동

- maven dependency 추가 - pom.xml

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-mongodb</artifactId>
  </dependency>
  ```

- DB 연결 정보 설정 - application.properties

  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017
  spring.data.mongodb.database=allreview 
  spring.data.mongodb.username=hs
  spring.data.mongodb.password=12345
  ```

- DTO 클래스 수정

  ```java
  @Document("review")  // Document annotation 추가
  public class Review{
      // 각 Document의 unique한 primary key 역할의 _id 필드와 매칭되는 속성임을 annotation으로 지정한다.
  	@Id
      private ObjectId id;
  }
  ```

  - MongoDB는 schemaless한 특징을 가지고 있는데, 미리 정의한 DTO의 attribute에 포함되지 않는 필드가 있는 경우는 어떻게 처리해야 할까?

    - 첫번째 해결 시도 :  Map<String, Object> 타입의 attribute로 한번에 dynamic field를 저장하도록 한다.

      ```java
    Map<String, Object> additions;	// 추가 내용(동적 필드)
      ```
      
      => Vue 컴포넌트에서 post로 http 요청할 때 RequestBody의 데이터 타입과 맞지 않아 400 Error 발생(Bad Request)

- Service 클래스 작성 - MongoDB 쿼리 수행 (2가지 방법)

  1. MongoTemplate bean 주입 후 활용

     - Query 타입의 쿼리문을 생성 후 find(Query, entityClass) 함수를 호출하여 쿼리 수행
     - Query는 Criteria 클래스로 쿼리 조건을 생성한다.

     ```java
     @Autowired
     private MongoTemplate mongoTemplate;
     ```

  2. MongoRepository<DataType, SerializeType> 클래스 상속받은 Repo 인터페이스 생성 후 활용

     - 쿼리가 단순한 경우 사용 가능

     - MongoDB에서 제공하는 쿼리 action(find, update 등)과 Document DTO에 선언한 속성 이름을 기준으로 함수 이름을 선언

       => 지정한 함수 이름에 맞게 알아서 쿼리 수행

     ```java
     public interface ReviewRepo extends MongoRepository<Review, String>{
         // 해당 model 값을 가지고있는 리뷰들을 찾는 쿼리문 수행
         List<Review> findByModel(String model);
     }
     ```



#### Vuex 라이브러리

- 복잡한 어플리케이션의 컴포넌트들을 관리하는 '상태 관리 라이브러리'

- React.js의 Flux 패턴에서 기인한 상태관리 패턴

  => MVC 패턴의 복잡한 데이터 흐름 관리 문제를 해결하는 개발 패턴으로, 단일 방향 Flow를 가진다.

  ![img](https://github.com/namjunemy/TIL/blob/master/Vue/img/07.PNG?raw=true)

  ```
  1. actions : view에서 발생하는 이벤트 또는 사용자 입력
  2. dispatcher : 데이터를 변경하는 방법, 메서드 (model 데이터를 변경하는 역할)
  3. model : 화면에 표시할 데이터
  4. view : 사용자에게 비춰지는 화면.(즉, 이 화면에서 다시 actions을 호출한다.)
  ```

  - MVC 패턴의 문제점

    - view와 model이 양방향 통신이 가능하다.

      => 하나의 view가 model을 변경하면, 해당 model이 연관된 view를 업데이트 한다.

      ​	(view에서 model을 이용하기 때문에 view와 model은 의존적이게 됨)

      ​	=> 업데이트된 view가 연관된 model을 다시 업데이트하고,... ----> 반복 -> 복잡한 업데이트 루프

    ![img](https://github.com/namjunemy/TIL/blob/master/Vue/img/06.PNG?raw=true)



- 컴포넌트간 데이터 전달 역할 수행

  - 부모-자식 관계의 컴포넌트의 경우 props도 가능하지만 컴포넌트가 많아질 경우 props를 하위 컴포넌트로 계속해서 전달해야하는 경우가 생길 수 있다.

  - 이벤트 버스도 사용 가능하지만 역시 컴포넌트가 많아질 경우 어디서 이벤트를 보내고 받는지 찾기 어려운 문제가 생긴다.

    => 즉, 컴포넌트 간 데이터 전달이 명시적이거나 효율적이지 않다.

- 컨셉
  - State : 컴포넌트 간 공유하는 데이터 - data()
  - View : 데이터를 표시하는 화면 - template
  - Actions : 사용자의 입력에 따라 데이터를 변경하는 함수 - methods

- 구조

  ```
  Vue component => 비동기 로직(Actions) => 동기 로직(Mutations) => State
  ```

  ![img](https://github.com/namjunemy/TIL/blob/master/Vue/img/10.PNG?raw=true)

  - Vue 컴포넌트에서 비동기 로직(methods에서 API 콜하는 함수 등)인 Actions를 수행한다.
  - Actions는 비동기 로직만 처리할 뿐 State(data)를 직접 변경하지 않는다.
  - Actions에서 동기 로직(data를 변경하는 역할)인 Mutations를 호출한다.

- Vuex 개념 정리 참고 : https://ict-nroo.tistory.com/106

- 설치

  ```
  npm install --save vuex
  ```

- 모듈화 

  - src/store.js 파일 생성

  ```js
  import Vue from 'vue'
  import Vuex from 'vuex'
  
  Vue.use(Vuex)
  
  export const store = new Vuex.Store({
    state: {
      color: 'rgb(203, 203, 77)'
    },
    getter: {
      // state를 화면에 바인딩하는 함수  
    },
    mutations: {
      // state 값을 업데이트하는 함수(동기 로직)
    },
    actions: {
      // 비동기 로직을 처리하는 함수
    }
  })
  ```

  - main.js에 모듈 추가

  ```js
  import {store} from './store'
  
  new Vue({
      el: '#app',
      router,
      store,
      ...
  })
  ```

  => 다른 컴포넌트에서 this.$store.state.color로 데이터를 사용할 수 있다.

  - 참고 : [https://kamang-it.tistory.com/entry/Vue14vuex-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0](https://kamang-it.tistory.com/entry/Vue14vuex-사용하기)
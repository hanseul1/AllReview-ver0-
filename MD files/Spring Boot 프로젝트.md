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

   - 해결방법 : Spring boot 프로젝트의 controller에 CrossOrigin annotation 추가

     ```
     @CrossOrigin(origins = {"*"}, maxAge = 6000)
     ```

     

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

    => Map<String, Object> 타입의 attribute로 한번에 dynamic field를 저장하도록 한다.

    ```java
    Map<String, Object> additions;	// 추가 내용(동적 필드)
    ```

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


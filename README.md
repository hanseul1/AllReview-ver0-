# AllReview.com - 올리브유 닷컴

#### 서비스 소개

```
각종 제품이나 서비스에 대해 실제 고객(사용자)들이 직접 사용한 리뷰를 공유하는 커뮤니티
```

#### 기획 의도

```
현재 어떤 제품이나 서비스에 대한 리뷰를 찾아보기 위해서는 각종 포털사이트나 SNS, 커뮤니티를 일일히 탐색해야 한다.
그런데 기존의 포털사이트나 커뮤니티에는 간접 광고가 무분별하게 퍼져있고, 그것들을 일일히 필터링하여 보기 어렵고 귀찮다.
이러한 사용자들을 위해 올리브유 닷컴은 제품이나 서비스를 직접 사용한 사용자들의 리뷰를 한 곳에 모아 바로 검색하고 통계를 확인할 수 있는 커뮤니티를 제작한다.
```



## Spring Boot 프로젝트 (With Vue.js)

#### 프로젝트 생성

```
- Spring starter project : 2.2.2 ver
- java : 1.8
- maven library
- DB : mysql
- Persistence framework : mybatis
```



#### Database tables

##### MySQL

- User 테이블

  | Column | Type         | Definition             | Remark      |
  | ------ | ------------ | ---------------------- | ----------- |
  | id     | varchar(100) | 사용자 로그인 아이디   | primary key |
  | pw     | varchar(100) | 사용자 로그인 비밀번호 |             |
  | phone  | varchar(20)  | 사용자 전화번호        |             |
  | name   | varchar(10)  | 사용자 닉네임          |             |

- Category 테이블

  | Column | Type        | Definition           | Remark      |
  | ------ | ----------- | -------------------- | ----------- |
  | no     | int         | 카테고리 구분 아이디 | primary key |
  | name   | varchar(50) | 카테고리 이름        |             |

- Keyword 테이블

  | Column   | Type        | Definition           | Remark                      |
  | -------- | ----------- | -------------------- | --------------------------- |
  | no       | int         | 키워드 구분 아이디   | primary key, auto_increment |
  | word     | varchar(30) | 키워드               |                             |
  | category | int         | 카테고리 구분 아이디 | foreign key(category.no)    |

- Company 테이블

  | Column | Type        | Definition         | Remark                      |
  | ------ | ----------- | ------------------ | --------------------------- |
  | no     | int         | 제조사 구분 아이디 | primary key, auto_increment |
  | name   | varchar(50) | 제조사 이름        |                             |

- Product 테이블

  | Column   | Type        | Definition           | Remark                   |
  | -------- | ----------- | -------------------- | ------------------------ |
  | no       | int         | 제품 구분 아이디     | primary key              |
  | name     | varchar(50) | 제품 이름            |                          |
  | category | int         | 카테고리 구분 아이디 | foreign key(category.no) |
  | com      | int         | 제조사 구분 아이디   | foreign key(company.no)  |



##### MongoDB

- Reveiw Document

  | Column   | Type             | Definition                   | Remark        |
  | -------- | ---------------- | ---------------------------- | ------------- |
  | _id      | String(ObjectId) | document 고유 키 값          |               |
  | title    | String           | 리뷰 제목                    |               |
  | writer   | String           | 리뷰 작성자 아이디           | user.id       |
  | model    | String           | 리뷰 제품 이름               | product.name  |
  | category | String           | 리뷰 작성 제품 카테고리 이름 | category.name |
  | regDate  | Date             | 리뷰 작성 날짜               |               |
  | useDate  | Date             | 리뷰 제품 사용 날짜          |               |
  | rating   | double           | 리뷰 별점                    | 0.5 단위      |
  | context  | String           | 리뷰 내용                    | text index    |
  | files    | String[]         | 리뷰 사진 파일 이름          | sFileName     |

  

#### Problem & Solving

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
   
     
   
4. Vue data-table expand 오류

   - 상황 및 원인

     - v-data-table에서 expand 옵션을 추가했을 때, expand 버튼 클릭시 해당 리뷰 내용만 보여지는 것이 아니라 모든 리뷰 내용이 expand 되었다.

       (즉, 모든 expand 버튼이 동기화됨)

     - 이는 v-data-table에서 item을 반복시켜 렌더링할 때 반복되는 item들을 구분해줄 고유 key값을 부여하지 않아서 생기는 문제이다.

   - 해결방법

     - v-data-table 태그에 key 옵션을 추가하여 지정해준다.

       ```html
       <v-data-table
          ...
          item-key="title"
          ...
       >
       ```

       - 각 review data의 고유 키값인 _id 값을 설정하려고 했으나, 모두 같은 Object로 인식되어 duplicate key 에러가 발생하였다.
       - 임의로 title 값을 고유 키 값으로 설정해 주었다.

5. RESTful API의 구현

   - 이전

     - Front-end와 HTTP통신을 하는 API를 구성할 때 controller에서  GET / POST 방식의 메소드만 정의하여 사용하였다.

       ```java
       @GetMapping("/review")
       ...
       @PostMapping("/review/save")
       ...
       @PostMapping("/review/update")
       ...
       @DeleteMapping("/review/delete")
       ...
       ```

       - 이는 REST API 설계 규칙에 맞지 않는다.

         (REST API의 URI 구성 시 자원에 대한 행위(동사 형태) 보다는 자원(명사 형태)을 표현하는 데 중점을 두어야 한다.)

   - 이후

     - update 작업을 하는 메소드는 PUT 방식으로, delete 작업을 하는 메소드는 DELETE 방식으로 정의하였다.

     - 에러 발생

       - DeleteMapping시 @RequestBody를 선언하여 요청 데이터를 받는 경우,

         클라이언트에서 해당 API를 호출하면 400(Bad Request) 에러가 발생하였다.

       - 이는 Spring boot의 내장 톰캣이 POST 요청에 대해서만 Request body의 parsing 작업을 수행하기 때문이다.

         => 톰캣 서버의 parseBodyMethods 설정을 POST 뿐만 아니라 PUT, DELETE도 추가해주는 방법도 있으나, Spring boot에서 내장 톰캣 서버 설정을 바꾸는 방법을 찾지 못함

         - Delete 작업 요청시 review 데이터 전체를 요청 데이터에 넣어 전달하지 않고, review의 고유 키 값인 _id만 @PathVariable 로 전달하는 방법으로 개선하였다.

         - 기존 ObjectId 타입의 _id값이 타입 문제로 spring boot와 vue에서 collection의 고유 키 값 역할을 하지 못하는 문제가 있어 String 타입으로 수정

           ```java
           @Document("review")
           public class Review {
           	@Id
           	private   String _id; 
               ...
           }
           ```

         - controller 클래스의 method 수정

           ```java
           @DeleteMapping("/review/{id}")
           public ResponseEntity<Map<String,Object>> removeReview(@PathVariable String id){
               ...
           }
           	
           @PutMapping("/review")
           public ResponseEntity<Map<String,Object>> updateReview(@RequestBody Review review){
               ...
           }
           ```

         - service 클래스의 MongoTemplate 메소드 호출 수정

           ```java
           /** 리뷰 삭제 */
           public void removeReview(String id) {
               // _id 값으로 Document 찾은 후 삭제
           	Query query = new Query(new Criteria("_id").is(id));
           	mongoTemplate.remove(query, "review");
           }
           	
           /** 리뷰 수정 */
           public void updateReview(Review review) {
           	Update update = new Update();
           	update.set("title", review.getTitle());
           	...
           	
               // _id 값으로 Document 찾은 후 업데이트 정보 반영
           	Query query = new Query(new Criteria("_id").is(review.get_id()));
           		
           	mongoTemplate.updateFirst(query, update, Review.class);
           }
           ```

   
   
6. MultipartFile 업로드

   - Spring boot에서의 REST API 구현

     - pom.xml에 dependency 추가

       ```xml
       <dependency>
       	<groupId>commons-io</groupId>
       	<artifactId>commons-io</artifactId>
       	<version>2.5</version>
       </dependency>
       <dependency>
       	<groupId>commons-fileupload</groupId>
       	<artifactId>commons-fileupload</artifactId>
       	<version>1.3.1</version>
       </dependency>
       ```

     - Controller 클래스에 MultipartFile 타입 요청 데이터를 받는 API 정의

       ```java
       @PostMapping("/review/files")
       public ResponseEntity<Map<String,Object>> saveReviewFiles
       							(@RequestParam("files") MultipartFile[] files) 										throws IllegalStateException, IOException{
       	reviewService.insertFiles(files);
       	return RestUtil.handleSuccess("success");
       }
       ```

     - 일반 Spring 프로젝트에서는 파일 처리를 위해 MultipartResolver 빈을 등록해주어야 한다.

       ```xml
       <!-- mvc-config.xml 설정파일에 선언 -->
       <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
       ```

       - Spring Boot에서는 기본 MultipartResolver를 StandardServletMultipartResolver로 사용하고 있다.

         => 따라서 직접 MultipartResolver bean을 주입해주지 않아도 된다.

         - 그러나 CommonsMultipartResolver를 MultipartResolver로 사용하고 싶다면 자동 설정을 제외하고 해당 리졸버를 등록해주어야 한다.

           ```java
           @EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
           @Configuration
           public class MultipartConfig {
           
               @Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
               public MultipartResolver multipartResolver() {
                   CommonsMultipartResolver multipartResolver = new 															CommonsMultipartResolver();
                   return multipartResolver;
               }
           
               @Bean
               @Order(0)
               public MultipartFilter multipartFilter() {
                   MultipartFilter multipartFilter = new MultipartFilter();
                   																		multipartFilter.setMultipartResolverBeanName                                      (DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME);
                   return multipartFilter;
               }
           }
           ```

           - configuration annotation의 우선순위 때문에 

             `@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})`

             해당 annotation이 없으면 MultipartAutoConfiguration이 활성화되어 StandardServletMultipartResolver가 자동 설정되므로 주의해야 한다.

         - 참고 : https://gist.github.com/dlxotn216/18622dd08ee430f0cb9610fb0e60c5ba

   - Vue component에서의 axios 구현

     ```js
     if (this.files.length > 0) {
     	var fileData = new FormData()
         for (var i = 0; i < this.files.length; i++) {
         	fileData.append('files', this.files[i])
             fileNames.push(this.files[i].name)
         }
     
         axios
           .post('http://localhost:8080/review/files', fileData, {
           	headers: {
               'enctype': 'multipart/form-data'
             }
           })
           .then(response => {
             if (response.data.data === 'success') {
               console.log('file upload')
             } else {
               alert('파일 업로드에 실패했습니다.')
             }
           })
     }
     ```

     - FormData : <form> 태그를 직접 HTML에서 사용하지 않고도 ajax 방식으로 데이터를 key-value 쌍으로 서버에서 전송할 수 있는 api

       => multipart/form-data 인코딩이 가능함

     - `fileData.append('files', this.files)` 로 배열 전체를 하나의 key 값 안에 넣으면 server에서 MultipartFile[] 타입으로 받을 수 없음 - 타입 오류로 null 값이 됨

     - `fileData.append('review', reviewData)` 코드를 추가하여 리뷰 데이터와 파일 데이터를 한번에 전송하려고 하였으나 server에서 두개 이상의 requestParam을 받지 못함 - 500 에러 발생

     - 파일 데이터와 리뷰 데이터를 각각 API 호출하여 전송하여 server의 service 단에서 함께 처리하도록 구현하였다.

   - Spring boot Service 클래스에서의 파일 업로드 구현
   
     ```java
     public List<String> insertFiles(MultipartFile[] files) 
         						throws IllegalStateException, IOException {
     	// 각 파일들의 새로 생성된 이름을 저장할 리스트(뷰 컴포넌트에 다시 전달)
     	List<String> fileNames = new ArrayList<>();
         
         for(MultipartFile file: files) {
     		String rfileName = file.getOriginalFilename();
             // 중복된 이름의 파일이 서버에 이미 있을 경우를 대비해 
     		// 파일 이름 앞에 현재 시간을 붙여 새로운 파일 이름을 생성한다.
     		String sfileName = String.format("%d%s"
   											, System.currentTimeMillis()
     											, rfileName);
           
     		File realFile = 
               new File(new ClassPathResource("application.properties") 										.getURI().getPath()
     							+ "/../static/static/img/" + sfileName);
   		file.transferTo(realFile);
             fileNames.add(sfileName);
   	}
         return fileNames;
     }
     ```
     
     - `new ClassPathResource("application.properties").getURI().getPath()`
     
       : 프로젝트의 classpath(resource 경로)를 찾기위해 target 폴더 하위에 있는 application.properties의 경로를 반환받는다.
     
     - 파일은 '프로젝트 경로\target\classes\static\static\img' 에 저장된다.
     
       => 'http://{server_ip:port}/static/img/{filename} 으로 해당 파일에 접근 가능하다.
     
       

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



#### Lombok 라이브러리

- 자바에서 DTO를 생성할 때 멤버 필드에 대한 Getter/Setter, ToString, Constructor 코드를 annotation을 통해 자동으로 생성해주는 라이브러리

- Annotation - DTO 클래스에 추가해주어야 함

  ```
  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode  : equals(..), hashcode() 메소드 생성
  @RequiredArgsConstructor : 필수 인자만 있는 Constructor 생성
  @NoArgsConstructor : 인자 없는 constructor 생성
  @AllArgsConstructor : 모든 인자를 가진 constructor 생성
  @Data : 위의 모든 annotation 처리
  ```

- 라이브러리 추가 - pom.xml 수정

  ```xml
  <dependency>
  	<groupId>org.projectlombok</groupId>
  	<artifactId>lombok</artifactId>
  	<optional>true</optional>
  </dependency>
  ```

- 설치

  - Lombok 파일 설치 후 STS 재시작이 필요하다.
  - 참고 : https://countryxide.tistory.com/16

- 참고 : https://goddaehee.tistory.com/95



#### Vue Router

- SPA(Single Page Application)를 구성하기 위해 서버에서 URI에 따라 해당하는 정적 파일을 연결해주는 방식

  => 요청 URI에 따라 브라우저에서 DOM을 변경하는 방식

- vue-router 라이브러리

  ```
  npm install --save vue-router
  ```

- Vue router 컴포넌트에 props 전달

  - 같은 '/review/list' 링크이더라도 사용자가 선택한 카테고리에 따라 다른 리뷰 데이터를 조회하고 싶다.

    => 사용자가 선택한 카테고리에 대한 값을 같이 전달해야함

  - 해당 컴포넌트를 router에 등록할 때 props 옵션을 추가한다.

    ```js
    new Router({
        routes:[
            ...
         	{
              path: '/review/list',
              name: 'ReviewList',
              component: ReviewList,
              props: (route) => ({category: route.query.c})
            },
            ...
        ]
    })
    ```

    - '/review/list?c=all' 이라는 URL로 접속하면 ReviewList 컴포넌트에 {category: 'all'} 값의 props가 설정된다.

  - 참고 : https://beomy.tistory.com/73



#### MongoDB index 

- 리뷰의 카테고리 별 검색과 키워드 검색이 자주 일어나기 때문에 검색 속도 향상을 위해 index를 생성하였다.

- text index

  - MongoDB는 문자열 필드에 대한 텍스트 검색 쿼리를 지원하기 위해 text index를 제공한다.

  - 텍스트 검색 쿼리를 사용하기 위해서는 해당 collection에 text index가 있어야 한다.

  - 하나의 collection은 하나의 text index만 가질 수 있다.

    (하나의 text index에 여러 개의 field를 포함할 수 있다.)

  - 문자열 필드의 일반 index와의 차이점

    ```
    db.review.createIndex({context: 1})
    ```

    - 해당 필드의 전체 문자열을 인덱싱한다.
    - 즉, 정확히 일치하는 문자열 검색이나 단일 단어 필드에 유용하다.

    ```
    db.review.createIndex({context:"text"})
    ```

    - 해당 필드의 문자열 값을 word 단위로 tokenizing 하여 인덱싱한다.

    - 각 단어에서 파생되어 변형된 문자열 검색에 유용하다.

      ```
      ex) '사용' 이라는 단어로 검색했을 때
      '사용하다', '많이 사용', '사용감', '사용하는데' 등의 문자열과 match될 수 있다.
      ```

  - text search

    - $text 쿼리 연산자를 사용하여 text index가 있는 collection에서 텍스트 검색을 할 수 있다.

    - 검색 조건 문자열을 공백을 기준으로 tokenizing하여 모든 토큰에 대해 OR 논리 조건으로 검색을 수행한다.

      ```
      db.review.find({$text: {$search: "화면 크기 가격"}});
      ```

      => 화면, 크기, 가격으로 각각 tokenizing되고, 각 토큰 중 하나라도 포함하는 document를 결과값에 포함시킨다.

    - query 조건과 document가 얼마나 잘 일치하는지 score를 계산한다.

      => score를 기준으로 sort가 가능하다.

  - 참고 : https://stackoverflow.com/questions/24316117/mongodb-difference-between-index-on-text-field-and-text-index

- keyword filtering 함수 구현

  - vue component에서 사용자가 선택한 keyword list를 POST로 받아오면, 

    해당 키워드 조건으로 text search를 수행하여 키워드가 포함되어있는 review 데이터 검색

  - DTO class에 text index를 생성할 field 표시

    ```java
    @Document("review")
    public class Review{
        private ObjectId _id;
        ...
        // 리뷰 내용에서 키워드 검색을 하기 위해 
        // context field에 text index 생성 annotation 작성
        @TextIndexed protected String context;
    }
    ```

  - Criteria를 활용하여 text search 쿼리 작성

    ```java
    public List<Review> getReviewsByKeywords(Map<String, Object> data){
    	List<String> keywords = (List<String>) data.get("keywords");
    	String category = (String) data.get("category");
    		
    	String[] keywordList = new String[keywords.size()];
    	for(int i=0; i<keywordList.length; i++) {
    		keywordList[i] = keywords.get(i);
    	}
    		
    	TextCriteria criteria = TextCriteria.forDefaultLanguage()
    							.matchingAny(keywordList);
    		
    	Query query = TextQuery.queryText(criteria);
    		
    	// 카테고리가 지정되어 있다면 해당 카테고리 조건으로 조회
    	if(!category.equals("all"))
    		query.addCriteria(new Criteria().andOperator(new 													Criteria("category").is(category)));
  		
    	return mongoTemplate.find(query, Review.class);
    }
    ```
  
  - text search 조건과 category match 조건을 한 쿼리에 적용하기 위해 
  
    2개의 Criteria 객체를 생성하고, addCriteria(...) 함수를 사용하였다.
  
    (and 조건으로 지정하기 위해 andOperator(...) 사용)
  
  - 참고 : https://spring.io/blog/2014/07/17/text-search-your-documents-with-spring-data-mongodb
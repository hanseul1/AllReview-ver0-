## Spring Boot 프로젝트

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

#### Vue.js 연동

- Vue.js를 활용한 Front-End 개발을 위해 Vue 프로젝트를 생성하여 Spring Boot 프로젝트 폴더 하위에 위치시킨다.

- Vue가 빌드한 html을 렌더링하기 위해서는 프로젝트를 jar로 바꿔줘야 한다.

  - Spring boot(jar)가 자동 설정으로 src/main/resources 에서 정적 파일(html, css, javascript)을 인식한다. (war의 경우 src/main/webapp 에서 인식한다. - WEB-INF 디렉토리 존재)

  - application.properties에서 servlet 관련 설정들을 삭제한다.

    ```
    #server.servlet.context-path=/allreview
    #spring.mvc.view.prefix=/static
    #spring.mvc.view.suffix=.html
    ```

- Vue webpack 프로젝트 생성 참고 : https://blog.naver.com/PostView.nhn?blogId=spdlqjdudghl&logNo=221349293910&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView

  
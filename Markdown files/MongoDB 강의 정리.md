# Mongo DB

## Cloud

- 개인 컴퓨터는 주로 입출력만 이루어지고, 정보 분석 및 처리, 저장, 관리 등은 클라우드 공간에서 이루어지는 컴퓨팅 시스템

- 물리적인 서버를 논리적인 서버로 추상화한 개념

- 수십~수백대의 서버들을 논리적인 인스턴스처럼 동적으로 사용할 수 있다.

  (즉, 필요할 때 서버를 더 할당받고, 필요하지 않을 때 서버를 줄일 수 있는 유연성이 있다.)

- 장점

  ```
  - 컴퓨터 가용률이 높음
  - 다양한 기기를 단말기로 사용 가능
  - 서비스를 통한 일관성있는 사용자 환경 구성 가능 
  - 사용자의 데이터를 신뢰성 높은 서버에 보관함으로써 안전하게 보관 가능
  - 전문적인 하드웨어에 대한 지식 없이 쉽게 사용 가능
  ```

- 단점

  ```
  - 서버가 공격 당하면 개인정보가 유출될 수 있음
  - 데이터가 손상되면 백업하지 않은 정보 복구 불가
  - 어플리케이션 설치 제약
  - 열악한 통신 환경에서의 서비스 이용 어려움
  - 개별 정보의 물리적인 위치 파악 불가
  ```



## 빅데이터

- 휴대폰 통화내역, 기상 정보, 실시간 교통량, 인터넷 검색 내역, SNS 메시지 등 방대한 양의 데이터

- IT 기술의 발전으로 방대한 양의 데이터를 실시간으로 관리, 처리할 수 있음

- 스마트폰의 등장으로 컴퓨터 뿐만 아니라 핸드폰으로도 실시간 트래픽 생성이 가능해짐

  => 24시간 어디서든 데이터 생성이 가능해짐으로써 데이터 양이 기하급수적으로 증가하게 된다.

- 빅데이터의 특징

  ```
  - 데이터의 양(Volume)
  - 데이터의 생성 속도(Velocity)
  - 데이터 형태의 다양성(Variety)
  ```

- 빅데이터에서는 이름이나 주민번호같은 개인 식별 정보, 성별, 나이 같은 정형적인 특성보다 '어디에서 무엇을 하고, 어떤 행동방식을 선호하는가'가 의미있는 자료가 된다.



| 구분                        | 기존                                                         | 빅데이터 환경                                                |
| --------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 데이터                      | 정형화된 수치자료 중심                                       | - 비정형의 다양한 데이터<br />- 문자데이터(SMS, 검색어)<br />- 영상데이터<br />- 위치 데이터 |
| 하드웨어                    | - 고가의 저장 장치<br />- 데이터베이스<br />- data warehouse | - 클라우드 컴퓨팅                                            |
| 소프트웨어<br />/ 분석 방법 | - 관계형 데이터베이스(RDBMS)<br />- 통계 패키지(SAS, SPSS)<br />- 데이터 마이닝<br />- 머신러닝 | - 오픈소스 형태의 무료 소프트웨어<br />- Hadoop, NoSQL<br />- 오픈소스 통계 솔루션(R)<br />- 텍스트 마이닝<br />- opinion mining<br />- sentiment analysis |



## NoSQL

- 등장 배경
  - SNS의 활성화로 전세계 사용자의 대규모 데이터가 발생함
  - 데이터의 패러다임이 한정된 규모의 복잡한 데이터에서 대량의 단순한 데이터로 넘어가게 됨
  - 기존의 데이터 저장 시스템으로 커버할 수 없는 문제가 발생하고 새로운 형태의 데이터 저장 기술이 필요하게 됨

- Not Only SQL : 기존 RDBMS 형태의 관계형 데이터베이스가 아닌 다른 형태의 데이터 저장 기술을 의미함

- NoSQL과 RDBMS는 서로 상반관계가 아닌 기존 RDBMS의 단점을 NoSQL이 보완해주는 형태

- NoSQL의 특징

  ```
  - RDBMS와 같이 공통된 형태의 저장방식과 접근방식을 갖지 않기 때문에 동등 비교가 불가능함
  - 기존 RDBMS가 데이터의 관계를 정의하고(foreign key) 관계형 연산(join)을 하는 것에 비해 NoSQL은 데이터 간의 관계를 정의하지 않음
  - 페타바이트급의 대용량 데이터를 저장할 수 있음
  - 기존 RDBMS처럼 하나의 고성능 머신에 데이터를 저장하는 것이 아니라 일반적인 서버 수십대를 연결해 데이터를 저장 및 처리함
  - 분산형 구조를 통해 데이터를 여러 대의 서버에 분산 저장하고, 데이터를 상호 복제함
  	=> 특정 서버에 장애가 발생하더라도 데이터 유실이나 서비스 중지가 없음
  - 기존 RDBMS와 다르게 테이블의 스키마가 유동적임
  ```

- CAP 이론
  - 분산 컴퓨팅 환경은 Consistency(일관성), Availability(가용성), Partitioning(부분결함 용인) 3가지 특징을 가지고 있으며, 이 중 2가지만 만족할 수 있다.
  - Consistency + Availability = RDBMS
  - Partitioning + Consistency or Availability = NoSQL

- 종류
  - key-value store
    - unique key에 하나의 value를 가지고 있는 형태
    - put(key, value) 
    - value := get(key)
    - Redis
  - ordered key-value store
    - 데이터가 내부적으로 key 기준으로 sorting되어 저장됨
    - 하나의 key안에 (column:value) 조합으로 된 여러개의 필드를 가지는 구조
    - Hbase, Cassandra
  - Document key-value store
    - key-value store의 확장된 형태
    - 저장되는 value의 타입으로 "Document"라는 구조화된 데이터 타입(JSON, XML 등)을 사용
    - 복잡한 계층구조 표현 가능
    - 제품에 따라 sorting, join, grouping 같은 추가기능 지원
    - MongoDB, CouchDB

- 데이터 모델링

  - 어플리케이션의 필요한 쿼리와 성능을 정의한 이후, 요구사항에 부합하도록 데이터 모델을 구성
  - 기존의 RDBMS가 데이터 모델을 먼저 구성하고 그에 맞는 쿼리를 생성하는 것과는 반대된다.

  ```
  1. 도메인 모델 분석
  2. 쿼리 결과 도출
  3. 테이블(데이터 저장 모델) 설계
  ```

  - NoSQL 쿼리가 실제 몇개의 물리 노드에 걸쳐서 수행되는지에 대한 이해가 있어야 제대로된 쿼리 디자인이 가능함
  - 대부분의 NoSQL은 인증 체계가 없어서 보안에 취약하므로 별도의 보안 체계를 마련해야 한다.



#### NoSQL 데이터 모델링 기법

- Denormalization(비정규화)
  - 쿼리 프로세싱을 단순화, 최적화 하기 위해서 또는 사용자 데이터를 특정 데이터 모델에 맞추기 위해서 같은 데이터를 여러 Document나 Table에 중복하여 저장하는 것을 허용함
  - Trade-Off
    - 쿼리당 I/O, 쿼리 데이터 사이즈는 감소
    - 전체 데이터 사이즈는 증가

- Aggregates

  - 유연한 스키마 속성

    => 복잡하고 다양한 구조의 nested entities를 가지고 있는 데이터 클래스 구성을 가능하게 함

    - 1:n 관계를 최소화하여 join 연산을 줄임

    - 복잡하고 다양한 비즈니스 요소를 담을 수 있음

    - 데이터 클래스의 구조 변경이 용이함

      => 추후 확장성 및 변동성에 유연한 대응 가능

- Application Side Joins

  - 대용량 데이터의 빠른 응답 성능, 확장성, 가능성을 최우선 목적으로 하기 때문에 join 쿼리 연산을 최대한 피하도록 함

  - join 대상 데이터에 대해 비정규화, aggregation을 수행할 때 문제가 발생하는 경우 존재

    - join 대상 데이터가 n:n 관계를 가지는 경우
    - join 대상 데이터가 수시로 변동되는 경우

    => 불가피하게 join 연산을 수행해야 한다.

  - join 연산이 필요한 데이터들을 각각 추출한 다음, application 단에서 join 연산을 수행하도록 함

- Composite Key

  - 하나 이상의 필드를 deliminator를 이용하여 구분지어 key로 사용하는 방법

  - ordered key-value store에서는 이를 이용하여 sorting, grouping 구현 가능

  - 데이터들이 key를 기준으로 n개의 서버에 나누어 저장됨

    => 전체 서버에 걸쳐서 부하가 골고루 분산될 수 있는 key를 선정해야함

  ```
  windows:etc
  windows:programfile
  windows:temp
  msoffice:msword
  msoffice:powerpoint
  ...
  ```

  

- Inverted Search Index

  - value의 내용을 key로, key의 내용을 value로 반대로 저장하는 패턴

  - 검색엔진에서 많이 사용함

    - 검색엔진은 사이트의 모든 페이지에 대해서 검색 로봇이 문서내의 단어들을 색인함 

      => key를 URL로 하고 단어들을 value로 저장함(수집시)

    - 검색은 단어로 하기 때문에 value에 검색 키워드들이 저장되어 있을 경우 효과적인 검색이 불가능함
    - 검색 키워드를 key로 하고 URL을 value로 하는 테이블을 다시 만들어 신속한 검색을 할 수 있도록 재구성함(검색시)

![image-20200106111240703](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200106111240703.png)

![image-20200106111257406](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200106111257406.png)

- 계층 데이터 구조 모델링 패턴

  - Tree Aggregation
    - Tree 구조 자체를 하나의 value에 저장하는 방식
    - JSON, XML 등을 이용하여 트리구조를 정의함
    - document가 계속하여 nested 되는 경우 수정이나 삭제가 있을 때 데이터를 변경하기 까다로운 단점이 있음

  - Materialized Path
    - Tree 구조를 테이블에 저장할 때, root에서부터 현재 노드까지의 전체 경로를 key로 저장하는 방법(Composite key 사용)
    - key에 대한 search를 할 때 정규식을 사용하여 특정 노드의 하위 트리를 쿼리해오는 기능 등 다양한 쿼리가 가능하게 된다.



## MongoDB

- 10gen 사에서 개발한 NoSQL 솔루션
- 스키마를 고정하지 않는 형태
  - 데이터를 구조화해서 json 형태로 저장
- Join이 불가능함
- 메모리맵 형태의 파일엔진 DB -> 메모리에 의존적
- 트랜잭션이 필요한 금융, 결제, 빌링, 회원 정보 등에는 부적합 (RDBMS 활용이 좋음)
- Document data model
  - 속성의 이름과 값으로 이루어진 쌍의 집합
  - 속성은 문자열, 숫자, 날짜 모두 가능(배열, 다른 Document도 가능)
  - 하나의 Document에 필요한 정보를 모두 담아야 함(join이 불가능하기 때문)
  - one query로 모두 해결이 되게끔 collection model을 설계해야함

- 장점
  - schema-less 구조 : 데이터 모델의 유연한 변화 가능
  - read/write 성능 뛰어남
  - Scale Out 구조 : 많은 데이터 저장, 장비 확장 가능
  - JSON 구조

- 단점
  - 데이터 업데이트 중 장애 발생시 데이터 손실 가능
  - 많은 인덱스 사용 시 충분한 메모리 확보 필요
  - 데이터 공간 소모가 RDBMS에 비해 많음
  - transaction 지원이 RDBMS에 비해 미약함
  - 제공되는 MapReduce 작업이 Hadoop에 비해 성능이 떨어짐



#### MongoDB 기능

1. Query

````
1. Create
db.person.save({"name":"john"});
2. Read
db.person.find();
3. Update
db.users.update({'name':'johnny'}, {"name":"Cash", "languages":["english"]});
4. Delete
db.users.remove({"name":"Sue"});
````

2. 인덱스
   - 다수 인덱스 설정 가능
   - 복합 인덱스 지원
   - 빠른 검색 지원
   - 메모리 부족하면 검색 속도 저하됨

3. 복제(Replica)

   - master-slave 구조

   - 데이터 복사본을 slave에 배치

   - master 장애에 따른 데이터 손실 없이 slave 데이터 사용 가능

   - master 장애 발생시 slave에서 master를 선출 가능

     => 중단없는 서비스 가능

     (master의 장애 발생을 능동적으로 감지하고 그에 따른 대안을 스스로 실행함)

   - 데이터 손실을 최소화 하기 위해 저널링 지원

     (MongoDB의 데이터 변화에 따른 모든 연산에 대한 로그 적재 => 데이터 손실시 로그들을 활용하여 복구 가능)

4. 샤딩(sharding)

   - 대용량의 데이터를 저장하기 위한 방법

     (소프트웨어적으로 DB를 분산시켜 처리하는 구조)

   - 방식
     - 테이블을 테이블 단위로 분리하는 방법
     - 테이블 자체를 분할하는 방법

   - 분산 데이터베이스의 분할 3계층 구조 지원
     - 응용 계층, 중개자 계층, 데이터 계층
     - 응용 계층은 중개자를 통해 모든 데이터의 입출력을 처리함으로써 추상화된 하나의 데이터베이스가 존재하는 것처럼 사용이 가능하다.

   ![image-20200106135341363](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200106135341363.png)

5. MapReduce

   - 대용량의 데이터를 안전하고 빠르게 처리하기 위한 방법
     - Map + Reduce (사용자 정의 가능)
     - 데이터를 분산하여 연산하고 다시 합치는 기술
     - 입출력 데이터는 key-value 형태로 구성

   - 한대 이상의 하드웨어를 활용하는 분산 프로그래밍 모델
   - 대용량 파일에 대한 로그분석, 색인 구축 검색 등에 활용
   - 일괄처리 방식으로 전체 데이터 셋을 분석할 필요가 있는 문제에 적합

   ![image-20200106135400961](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200106135400961.png)



#### Node.js

- 네트워크 프로그래밍을 위한 자바스크립트 플랫폼
  - 자바스크립트 엔진(V8)을 사용하여 어플리케이션을 구축할 수 있게 해주는 프레임워크
- 표준 자바스크립트를 실행하기 위한 V8엔진을 기반으로 동작(일종의 자바스크립트 개발환경)
- 간단하게 HTTP 처리와 URL 처리 가능
- JSON 처리 용이
- Event-driven
  - 사용자 또는 외부에서 만들어낸 사건을 기반으로 어떤 처리를 할지 지정해주는 개발 방식
  - 특정 이벤트에 대한 처리만하여 웹 서버와 연결시키면 되기 때문에 자원 최소화가 가능하다.

- Non-blocking I/O (비동기식 I/O)
  - read/write 이벤트가 시작되어도 해당 모듈을 반환할 수 있다.
  - 다른 작업이 가능한 준비상태로 전환함
  - 동기식보다 빠르고 메모리도 적게 차지함



#### MongoDB Query

- 사용자 계정 생성 

  ```
  use [collections];
  db.createUser({
  	user: "username",
  	pwd: "password",
  	roles: ["dbAdmin"]
  });
  ```

  roles 종류 : "readWrite", "dbAdmin", "clusterAdmin", ...

- 데이터 삽입 쿼리 : insert / save

  ```
  db.[collectionName].save({a:100})
  db.[collectionName].save({name:'hs', languages:['java','python']})
  ```
- 데이터 조회 쿼리 : find

  ```
    db.[collectionName].find()   => 전체 데이터 조회
    db.[collectionName].find({a: {'$gte':50, '$lte':200}})    
    	=> a필드 값이 50이상 200이하인 데이터 조회
  ```

-  데이터 수정 쿼리 : update

  ```
   db.[collectionName].update({name:'hs'}, {'$push': {'languages': 'c++'}})
   db.[collectionName].update({a:100}, {a:200, b:100})
  ```

- 데이터 삭제 쿼리 : remove

   ```
   db.[collectionName].remove({})	=> 전체 데이터 삭제
   db.[collectionName].remove({a:100})
   ```

  

#### Index

- DB의 데이터 검색 속도 개선을 위해 데이터의 순서를 미리 정해두는 과정

  - index 없이 데이터가 정렬되어 있지 않다면 query 조건을 만족하는 데이터를 모두 찾기 위해 모든 document를 스캔해야 한다.

    => 데이터 양에 따라 시간이 많이 걸릴 수 있다.

- MongoDB에서는 data field를 index로 지정하여 검색결과를 빠르게 하는 것이 가능하다.

  - MongoDB에서는 B-tree 구조로 index를 구현함

  - _id는 기본적으로 생성되는 index : document를 가리키는 유일한 키값

  - 한 query당 하나의 index만 유효함 

    => 두 개의 index가 필요하면 복합 index 사용 

    	(복합 index에서 키의 순서가 매우 중요함)

- index를 사용하면 데이터 read 속도는 빨라지지만 write 속도는 느려지게 된다.
  - 어떤 데이터가 추가되거나 수정될 때마다 해당 collection에 대해 생성된 index도 그 document를 포함시키도록 수정되어야 하기 때문이다.
  - 즉, 데이터 write가 있을때 마다 데이터를 다시 정렬해야하기 때문에 성능이 감소된다.
  - 따라서 index는 데이터 쓰기보다 조회가 자주 일어나는 서비스에서 유용하다.

- B-Tree 구조의 index

  - Tree의 각 노드는 여러개의 key를 가질 수 있음

  - B-Tree가 데이터베이스 index에 적합한 두가지 특징
  
    ```
    1. 정확한 일치, 범위 조건, 정렬, prefix 일치 등 다양한 쿼리를 용이하게 처리하도록 도와줌
    2. key가 추가되거나 삭제되더라도 트리의 밸런스를 유지함
    ```
  
- index 사용

  - index 조회

    ```
    db.[collectionName].getIndexes()
    ```

  - index 생성

    ```
    db.[collectionName].ensureIndex({"name":1})
    	=> name field를 key로 하는 index 생성
    	(1 : 오름차순, -1 : 내림차순)
    ```

  - index 삭제

    ```
    db.[collectionName].dropIndexes()  => _id 필드 인덱스 제외한 모든 index 삭제
    db.[collectionName].dropIndex({"name": 1})
    ```

- index에서 쿼리 실행하여 데이터 검색하기

  ![index diagram](https://docs.mongodb.com/manual/_images/index-for-sort.bakedsvg.svg)

- index 종류

  - single field index
    
  - 단일 필드에 대해 사용자 정의 오름차순 / 내림차순 인덱스를 생성한다.
    
  - compound index

    - 여러 필드에 대해 사용자 정의 인덱스를 생성한다. 

    - 인덱스를 구성하는 키의 순서가 매우 중요하다.

      ```
      {userId : 1, score : -1}
      ```

      index가 위와 같이 구성되어 있다면, userId를 기준으로 먼저 오름차순 정렬하고, 각 userId 내에서 score 기준으로 내림차순 정렬을 한다.

  - text index
    
    - collection의 문자열 내용 검색을 지원하는 텍스트 인덱스를 생성한다.


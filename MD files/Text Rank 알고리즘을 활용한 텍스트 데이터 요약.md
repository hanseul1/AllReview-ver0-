# Text Rank 알고리즘을 활용한 텍스트 데이터 요약

#### Text Rank

- 텍스트에 관한 graph-based ranking model로, Google의 pageRank 알고리즘을 활용한 알고리즘

  - PageRank

    - Google 검색 엔진의 랭킹 알고리즘

    ```
    - 많은 유입 링크를 가진 page가 중요한 page라는 가정
    - 중요한 페이지로부터 유입을 받는 페이지가 더 많은 점수를 갖는다.
      => 다른 페이지로부터의 유입 링크 수가 같더라도 같은 양의 점수(ranking)를 가지는 것은 아니다.
    ```

    - 각 페이지의 중요도는 유입 받는 페이지의 중요도로 결정된다. 

      => 재귀(recursive)적인 정의

    - pageRank 수식

      ```
      PR(A) = (1-d)/N + d (PR(T1)/C(T1) + … + PR(Tn)/C(Tn))
      
      PR : page rank
      Tn : 그 페이지를 가리키는 다른 페이지들 
      d : damping factor
      C(Tn) : Tn 페이지가 가지고 있는 링크의 총 갯수
      N : 모든 페이지의 숫자
      ```

      - page A의 rank는 그 페이지를 인용하고 있는 다른 페이지 T1, T2, ..., Tn 이 가진 rank를 정규화시킨 값의 합

      - 페이지를 가리키는 다른 페이지들의 rank 값을 정규화 시킨 이유?

        : 해당 page의 유입 기여도 비중을 계산하기 위해서

        (예를 들어, T1 페이지의 rank가 높아도 그 페이지의 링크가 수천개라면 해당 페이지가 A 페이지로 유입하는데 기여하는 비중이 낮아지기 때문이다.)

      - d : damping factor란?

        : 웹 서핑을 하는 사람이 그 페이지에 만족하지 못하고 다른 페이지의 링크를 클릭할 확률

        (유입 링크에 의해 해당 페이지에 접속할 확률)

        => 보통 0.85로 잡는다.

    - 참고 : https://sungmooncho.com/2012/08/26/pagerank/

  - TextRank 수식

    ![image-20200111194742053](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200111194742053.png)

    ```
    TR : Text Rank
    d : damping factor(0.85)
    Wij : 단어(또는 문장) 사이의 가중치
    ```

- TextRank algorithm flow

  ```
  1. 텍스트 데이터 크롤링
  2. 텍스트 데이터 -> 문장 단위 분리(KoNLPy 사용)
  3. 문장 -> 키워드 단위 분리(KoNLPy 사용)
  4. 불용어(stop words) 처리
  5. TF-IDF model 생성 및 Correlation Matrix 생성
  6. TextRank 수식 적용
  7. 상위 10개의 키워드 추출
  ```

  


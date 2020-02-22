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
  5. TF-IDF model 생성 및 Correlation Matrix 생성(scikit-learn 사용)
  6. TextRank 수식 적용
  7. 상위 10개의 키워드 추출
  ```

- package install

  ```
  pip install jpype1
  pip install konlpy
  pip install scikit-learn
  ```

- 참고 : https://excelsior-cjh.tistory.com/93

  ​		   https://lovit.github.io/nlp/2019/04/30/textrank/

  ​		   https://bab2min.tistory.com/552

  

#### TF-IDF model

- TextRank 알고리즘을 적용할 때, 단어(또는 문장) 사이의 가중치를 계산하기 위해 사용하는 머신러닝 모델

- 여러 Document에서 어떤 단어(term)가 특정 document 내에서 얼마나 중요한 것인지 나타내는 통계적 수치

  => TF와 IDF의 곱으로 계산할 수 있다.

  ```
  tf-idf(t,d)=tf(t,d)⋅idf(t)
  ```

- TF(Term Frequency)

  - 현재 term이 문서 내에서 등장하는 빈도수 

  - 값이 높을수록 문서 내에서 중요하다고 생각할 수 있다.

  - 문서의 크기나 길이 등에 따라 무한대로 발산할 수 있기 때문에 다양한 정규화 방법을 사용한다.

    - Boolean 빈도 

      ```
      tf(t,d) = t가 d에 등장하면 1, 등장하지 않으면 0
      ```

    - log scale 빈도

      ```
      tf(t,d) = log(f(t,d) + 1)
      ```

    - 증가 빈도

      - 문서의 길이가 길 경우, t의 빈도수를 최빈 단어의 빈도수로 나누어 계산한다.
      - ![{\mathrm  {tf}}(t,d)=0.5+{\frac  {0.5\times {\mathrm  {f}}(t,d)}{\max\{{\mathrm  {f}}(w,d):w\in d\}}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/9116cd515075990e05a5489020384c714408d63f)

      

- IDF(Inverse Document Frequency)

  - DF : 현재 term이 등장하는 문서의 수

  - DF의 역수값이 IDF이다. 

    => 즉, 특정 문서뿐만 아니라 다른 문서에서도 일반적으로 자주 등장하는 term은 특정 문서에서의 중요도가 낮아진다.

  - stop words를 제외시키는 데 사용할 수 있다.

  - ![{\mathrm  {idf}}(t,D)=\log {\frac  {|D|}{|\{d\in D:t\in d\}|}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/cc5cc57e5b68902a0bfaf42f04e53458503601c4)

- 참고 : https://ko.wikipedia.org/wiki/Tf-idf 

- scikit-learn 라이브러리

  - feature_extraction.text 서브패키지에서 제공하는 TfidfVectorizer 클래스와 CountVectorizer 클래스를 활용

  - BOW 방식을 통해 document를 숫자 vector로 변환하여 Document-Term(Sentence-Term) matrix를 만든다.

    - BOW(Bag of Words)

      : 전체 document에 포함된 단어들로 단어장(vocabulary)을 만들고, 개별 document에 대해 단어장의 단어들이 포함되어 있는지(tf-idf의 경우 가중치) 표시하는 방법

  - TfidfVectorizer : TF-IDF 방식으로 각 term의 가중치를 조정한 BOW 벡터를 만든다.

    ```python
    from sklearn.feature_extraction.text import TfidfVectorizer
    
    tfidv = TfidfVectorizer().fit(sentences)
    sentence_term_matrix = tfidv.transform(sentences).toarray()
    ```

    ![img](https://t1.daumcdn.net/cfile/tistory/2220A04A593D75DD34)

  - CountVectorizer : 각 term의 count를 세어 BOW 벡터를 만든다.

    ```python
    from sklearn.feature_extraction.text import CountVectorizer
    
    vect = CountVectorizer()
    vect.fit(sentences)
    vect.vocabulary_  # sentences 문서 집합에서 단어장 생성
    cnt_vec_mat = vect.transform(sentences).toarray()
    ```

    - vocabulary는 {term : id} 형식의 dictionary 형태로 리턴된다.

  - 참고 : https://datascienceschool.net/view-notebook/3e7aadbf88ed4f0d87a76f9ddc925d69/

- 참고 : https://m.blog.naver.com/vangarang/221072014624   (TF-IDF 직접 구현)

  

#### Correlation Matrix

- term(또는 sentence) 간의 가중치를 나타내는 matrix

- TF-IDF 모델로 생성한 sentence-term matrix를 활용하여 term(또는 sentence) 간의 correlation matrix를 구할 수 있다.

  - sentence-term matrix와 그의 전치행렬을 곱해 correlation matrix를 구한다.

  ```python
  # sentence 간 가중치 그래프 
  self.graph_sentence = np.dot(tfidf_matrix, tfidf_matrix.T)  
  # term 간 가중치 그래프
  self.graph_term = np.dot(tfidf_matrix.T, tfidf_matrix)  # TfidfVectorizer 이용
  self.graph_term2 = np.dot(cnt_vec_mat.T, cnt_vec_mat)   # CountVectorizer 이용
  ```

  ![img](https://t1.daumcdn.net/cfile/tistory/246A2C4C593D760536)

- 여기서 구한 문장(또는 단어)간 가중치로 위에서 본 TextRank의 다음 수식을 계산할 수 있다.

  ![image-20200111194742053](C:\Users\조한슬\AppData\Roaming\Typora\typora-user-images\image-20200111194742053.png)

  

#### TextRank 수식 적용

```python
def get_ranks(self, graph, d=0.85): # d = damping factor
    A = graph
    matrix_size = A.shape[0]
    for id in range(matrix_size):
        A[id, id] = 0 # diagonal 부분을 0으로
        link_sum = np.sum(A[:,id]) # A[:, id] = A[:][id]
        if link_sum != 0:
        A[:, id] /= link_sum
        A[:, id] *= -d
        A[id, id] = 1
        
    B = (1-d) * np.ones((matrix_size, 1))
    ranks = np.linalg.solve(A, B) # 연립방정식 Ax = b
    return {idx: r[0] for idx, r in enumerate(ranks)}
```



참고 : https://excelsior-cjh.tistory.com/93
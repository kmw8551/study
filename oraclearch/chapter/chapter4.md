# 라이브러리 캐시 최적화 원리
> 해당 챕터는 `친절한 SQL`의 내용 중 [SQL 옵티마이저와 SQL 처리 과정](https://github.com/t0e8r1r4y/SQLP_STUDY/wiki/SQL-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80-&-SQL-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95%EA%B3%BC-I-O#7%EC%9E%A5-sql-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80)에 대해 정리한 내용을 함께 참고하면 됩니다.

<br>


## SQL과 옵티마이저
- `옵티마이저`는 최적화를 담당하는 프로세스다.
- 사용자가 입력한 SQL을 옵티마이저가 해석하고 최적화된 실행계획을 프로시저한테 넘겨주는 역할을 한다.
- SQL 옵티마이저는 최소비용, 최적의 경로를 선택해서 사용자가 원하는 작업을 가장 효율적으로 수행할 수 있는 프로시저를 자동으로 생성해주는 DBMS의 가장 핵심이다.

<br>


## SQL 처리 과정
- 가장 기본 프로세스는 아래와 같다.  
<img width="515" alt="스크린샷 2022-11-04 오후 12 36 55" src="https://user-images.githubusercontent.com/91730236/199880026-e0a892f0-60ee-43bc-a1b1-8adfeddbdfc3.png">

- 각 단계별 상세 내용은 아래와 같다.

#### SQL 파싱
- SQL Parser : 사용자가 던진 SQL을 가장 먼저 받아서 처리하는 End Point 엔진이다.
- 순서 : 개별 구성요소 분석 -> 파싱 트리 생성 -> 문법 체크(Syntax check) -> 문맥 체크(Semantic check) -> 캐싱 여부 확인 -> cache hit fail 시  SQL 커서 생성 ( 성공시 다음 실행단계 이동)


#### SQL 최적화
- Core 엔진. 시스템 통계 및 오브젝트 통계정보를 판단기준으로 삼아 다양한 액세스 경로를 비교하고 그 중 가장 효율적인 실행 계획을 선택
- 아래 3가지 쿼리엔진을 사용한다.
  - Query Transformer
  - Plan Generator
  - Estimator
- 최적화 과정에 대한 정리는 책 후반부에서 좀 더 상세하게 다뤄질 예정
- 간략한 요약은 [링크](https://github.com/t0e8r1r4y/SQLP_STUDY/wiki/SQL-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80-&-SQL-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95%EA%B3%BC-I-O#7%EC%9E%A5-sql-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80) 참고

#### Row-Source Generation
- 옵티마이저가 만들어낸 실행 계획을 실행 가능한 코드 또는 프로시저 형태로 포맷팅 하는 작업을 하는 프로세서


<br>


## 라이브러리 캐시 구조


<br>


## 커서 공유


<br>


## 바인드 변수의 중요성


<br>


## 바인드 변수의 부작용과 해법


<br>


## 세션 커서 캐싱


<br>


## 애플리케이션 커서 캐싱


<br>


## Static  vs Dynamic SQL


<br>


## Dynamic SQL 사용 기준


<br>


## Static SQL 구현을 위한 기법들


<br>


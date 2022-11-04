# 라이브러리 캐시 최적화 원리
> 해당 챕터는 `친절한 SQL`의 내용 중 [SQL 옵티마이저와 SQL 처리 과정](https://github.com/t0e8r1r4y/SQLP_STUDY/wiki/SQL-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80-&-SQL-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95%EA%B3%BC-I-O#7%EC%9E%A5-sql-%EC%98%B5%ED%8B%B0%EB%A7%88%EC%9D%B4%EC%A0%80)에 대해 정리한 내용을 함께 참고하면 됩니다.

<br>


## 1️⃣ SQL과 옵티마이저
- `옵티마이저`는 최적화를 담당하는 프로세스다.
- 사용자가 입력한 SQL을 옵티마이저가 해석하고 최적화된 실행계획을 프로시저한테 넘겨주는 역할을 한다.
- SQL 옵티마이저는 최소비용, 최적의 경로를 선택해서 사용자가 원하는 작업을 가장 효율적으로 수행할 수 있는 프로시저를 자동으로 생성해주는 DBMS의 가장 핵심이다.

<br>


## 2️⃣ SQL 처리 과정
- 가장 기본 프로세스는 아래와 같다.  
<img width="515" alt="스크린샷 2022-11-04 오후 12 36 55" src="https://user-images.githubusercontent.com/91730236/199880026-e0a892f0-60ee-43bc-a1b1-8adfeddbdfc3.png">

- 각 단계별 상세 내용은 아래와 같다.

#### SQL 파싱
- SQL Parser : 사용자가 던진 SQL을 가장 먼저 받아서 처리하는 End Point 엔진이다.
- 순서 : 개별 구성요소 분석 -> 파싱 트리 생성 -> 문법 체크(Syntax check) -> 문맥 체크(Semantic check) -> 캐싱 여부 확인 -> cache hit fail 시  SQL 커서 생성 ( 성공시 다음 실행단계 이동)

> SQL 커서란?  
> 커서는 하드 파싱 과정을 거쳐 메모리에 적재된 SQL과 Parse Tree, 실행계획, 그리고 그것을 실행하는데 필요한 정보를 담은 SQL Area를 말한다.  
> 뒤에 더 상세히 다룬다고는 하는데, 요정도 알고 읽어야 읽히더라.


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


## 3️⃣ 라이브러리 캐시 구조

#### 🏗️ 전반적인 구조  

- 라이브러리 캐시는 Shared Pool 내에 위치하고 있다. ( 여기쯤 왔다면 분명 Oracle Architecture에서 기억이 안날법도 하기에 해당 내용에 대해 [링크](https://github.com/kmw8551/study/blob/main/oraclearch/chapter/20220925_1%EC%9E%A5.md)를 건다. )  
![192124730-2667fd0d-7ca7-49cc-a82f-f2ccbb67ca51](https://user-images.githubusercontent.com/91730236/199882368-6f274d3f-96d5-49f5-9378-a9ab205fa696.png)  


#### ℹ️ 저장되는 정보
- SQL 공유 커서 및 데이터베이스 오브젝트(테이블, 인덱스 등)에 대한 정보를 관리한다. 그 외 컴파일을 거친  프로시저, 함수, 패키지, 트리거 등 PL/SQL 프로그램을 담는 PL/SQL Area도 라이브러리 캐시에 저장한다.
- 저장되는 정보의 단위를 `라이브러리 캐시 오브젝트` LCO라고 부른다.
  - `실행가능 LCO` : 실행이 가능한 오브젝트 내용들
  - `오브젝트 LCO` : 실행가능 오브젝트뿐 아니라 거기서 참조하는 테이블, 인덱스, 클러스터 같은 데이터베이스 오브젝트 정보들
- 스키마 오브젝트 정보는 데이터 딕셔너리 캐시에도 저장이 되어있지만 `LCO간 의존성 관리 목적`으로 라이브러리 캐시에도 저장한다.
- 저장 기간으로는 아래 2가지로 구분이 가능하다.
  - 영구적으로 보관되는 오브젝트 : 테이블, 인덱스, 클러스터, 뷰, 트리거, 패키지, 사용자 정의 함수/프로시저 등
  - 인스턴스가 떠있는 동안에만 존재하는 오브젝트 : 커서와 Anonymous PL/SQL문

#### 🎛️ 정보가 관리되는 구조
- Shared Pool도 DB 버퍼 캐시처럼 LRU 알고리즘에 의해 관리가 됨.  [링크](https://github.com/kmw8551/study/blob/main/oraclearch/chapter/20220925_1%EC%9E%A5.md)
- Shared Pool 래치를 사용하여 특정 오브젝트 정보 또는 SQL 커서를 위한 Free Chunk를 할당 받으려 할 때 shared pool 래치를 사용한다.
- 라이브러리 캐시도 DB 버커 캐시처럼 해시구조로 관리된다.
<img width="515" alt="스크린샷 2022-11-04 오후 12 36 55" src="https://user-images.githubusercontent.com/91730236/199883470-f28abd72-a146-4b6f-82dd-59264a6a0663.png">  
- 해시 구조로 관리되는 방식은 1장의 설명과 유사하다.
- 아래 성능 이슈가 발생할 수 있는 부분
  - shared pool 래치와 library cache 래치 경합은 소프트/하드 파싱을 동시에 심하게 일으킬 때 발생한다.
  - library cache lock과 library cache pin 대기이벤트는 주로 SQL 수행 도중 DDL을 날릴 때 발생한다.
  - 트랜잭션이 활발한 시간대에 DDL문을 날려 데이터베이스의 오브젝트 정의를 변경하면 라이브러리 캐시에 심한 부하가 발생한다.

#### ✅ 중요한 점
- 커서를 공유할 수 있는 형태로 SQL을 작성한다. -> 바인드 변수를 사용
- 세션 커서 캐싱 기능을 이용해 라이브러리 캐시에서 SQL 찾는 비용을 줄인다.
- 애플리케이션 커서 캐싱을 이용해 Parse Call 발생량을 줄인다.



<br>


## 4️⃣ 커서 공유

#### 커서란?
- 3종류다.
<img width="508" alt="스크린샷 2022-11-04 오후 1 22 42" src="https://user-images.githubusercontent.com/91730236/199886978-499d3e25-2a16-4bc8-a187-02af44c10232.png">




<br>


## 5️⃣ 바인드 변수의 중요성


<br>


## 6️⃣ 바인드 변수의 부작용과 해법


<br>


## 7️⃣ 세션 커서 캐싱


<br>


## 8️⃣ 애플리케이션 커서 캐싱


<br>


## 9️⃣ Static  vs Dynamic SQL


<br>


## 🔟 Dynamic SQL 사용 기준


<br>


## Static SQL 구현을 위한 기법들


<br>


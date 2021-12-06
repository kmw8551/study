# 내용 공유 Repository


## 목차

1. [README.md] 
2. [개발 간 참고사항]
3. [Kubernetes]
4. [다음 토픽 지정시 추가]



---

## 1. 개요

Repository 운영을 위해서 Guide Line 내용을 해당 문서에 작성함.  
Repository 구성은 다음의 형태를 따름

study (repo)
  - README.md
  - Project 1 (Kubernetes)
  - Project 2 (미정)
  -  ...


해당 Repository에 관련하여 운영인원은 3명  

---

## 2. 개발 간 참고사항

### 2-1. Naming Rule

  폴더 명, 파일 명, 패키지 명 및 변수 명의 명명 규칙은 "자유" 가 아닌 포맷을 지켜 가독성 확보할 것 
  [Java Naming Convention](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
  [File Naming Convention](https://libguides.princeton.edu/c.php?g=102546&p=930626)

### 2-2. 들여쓰기 또는 괄호 

중괄호로 묶이거나 소괄호로 범위를 지정할 수 있는 언어라도 정확한 범위 표시 할 것.   

``` python
# 단락 구별
# Bad Case
if True : print(124) 

# Good Case
if True:
   print(1234)


```  

JSON, SQL 경우도 줄바꿈으로 정확하게 가독성을 확보할 것.

예시  
```sql

SELECT emp.name, emp.address
FROM employees emp
WHERE emp.salary >  5000
  AND emp.field = 'CS'
;
```
환경 변수, SQL 쿼리문, 전역 변수는 전부 대문자로 작성, 나머지 변수 및 코드에 대해서는 소문자로 작성할 것.  
이외에도 각 언어별 기본적인 코드 작성 간 표기법 및 변수 명명 규칙, 템플릿을 필히 준수할 것.  

### 2-3. Git 사용 간 습관

시작 전, `git pull` 을 이용하여 항상 최신의 상태로 유지한 다음, 작업 진행할 것.  
`git push` 전, 항상 log를 통해 HEAD 위치 파악 및 push 배제할 파일 목록 확인한 뒤 진행.  

Master Branch 에 대한 접근 권한은 한명으로 제한하여 Master Code가 변경되거나 복구 불가능한 일이 없도록 진행하려고 함.  
따라서 모든 인원은 개발 간, 개인 Branch에서 코드 수정 후, Master에 반영하는 형태로 진행.  
   

---
   

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
README.md 내용 추가시, 다음의 문법 규칙을 참고 할 것. [링크](https://www.markdownguide.org/basic-syntax/)  


---

## 2. 개발 간 참고사항

### 2.1 Naming Rule

  폴더 명, 파일 명, 패키지 명 및 변수 명의 명명 규칙은 "자유" 가 아닌 포맷을 지켜 가독성 확보할 것 
  [Java Naming Convention](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
  [File Naming Convention](https://libguides.princeton.edu/c.php?g=102546&p=930626)

### 2.2 들여쓰기 또는 괄호 

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

### 2.3 Git 사용 간 습관

시작 전, `git pull` 을 이용하여 항상 최신의 상태로 유지한 다음, 작업 진행할 것.  
`git push` 전, 항상 log를 통해 HEAD 위치 파악 및 push 배제할 파일 목록 확인한 뒤 진행.  

Master Branch 에 대한 접근 권한은 한명으로 제한하여 Master Code가 변경되거나 복구 불가능한 일이 없도록 진행하려고 함.  
따라서 모든 인원은 개발 간, 개인 Branch에서 코드 수정 후, Master에 반영하는 형태로 진행.  

*Git Conflict 발생 시, 나머지 2인에게 공지하고 상황 설명 후, Merge/Rebase 작업 할 것.*

[Git 작업에 유용한 툴](https://www.sourcetreeapp.com/)  


---
   
## Kubernetes 

K8s Version :   

### 3.1 YAML 작성 포맷

```YAML


```

YAML 작성 시 포맷 준수.  


### 3.2 명명 규칙

리소스에 해당하는 약어를 명칭에 붙여줌으로써 빠르게 파악 가능  


| Short name           | Full name                    |
| -------------------- | ---------------------------- |
|  csr                 |  certificatesigningrequests  |
|  cs                  |  componentstatuses           |
|  cm                  |  configmaps                  |
|  ds                  |  daemonsets                  |
|  deploy              |  deployments                 |
|  ep                  |  endpoints                   |
|  ev                  |  events                      |
|  hpa                 |  horizontalpodautoscalers    |
|  ing                 |  ingresses                   |
|  limits              |  limitranges                 |
|  ns                  |  namespaces                  |
|  no                  |  nodes                       |
|  pvc                 |  persistentvolumeclaims      |
|  pv                  |  persistentvolumes           |
|  po                  |  pods                        |
|  pdb                 |  poddisruptionbudgets        |
|  psp                 |  podsecuritypolicies         |
|  rs                  |  replicasets                 |
|  rc                  |  replicationcontrollers      |
|  quota               |  resourcequotas              |
|  sa                  |  serviceaccounts             |
|  svc                 |  services                    |

예를 들어 서비스를 만들었을 경우, 다음과 같이 Naming을 하면 된다.  
service: flink-svc
pv: flink-pv  
<br>
위의 규칙에서 예외로, 네임스페이스는 명확한 정보를 가진 2개 이하 단어로 표현. (ns라는 약어를 달 필요가 없음)  


### 3.3 운영간 주의사항

1. 개인이 만든 Pod 중에서 전체 리소스에 영향을 줄 수 있는 것은 공지 후 삭제
2. service, persitent volumen, persistent volume claim 은 함부로 삭제하지 말 것.
3. 필요에 의해 전체 리소스를 삭제해야 할 경우, namespace 삭제로 해결할 것. 
4. Kubernetes YAML 파일 또한 명명 규칙을 이용하여 생성할 것. Pod, Deployment 등은 해당 명칭이 아닌 컴포넌트 명칭으로 작성
   ex) airflow-pod.yaml (x)   /  airflow-worker.yaml (o)  
5.


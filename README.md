## 프로젝트 개요

인원 : **1명(본인)**

기간 : **2024.10 ~ 2024.11**

담당 : 전체 개발 및 설계

**프로젝트 소개**

- **대용량 트래픽** 대응을 목표로 한 홈쇼핑의 한정 판매 시스템 구축 프로젝트
- **MSA 기반** 분산 시스템을 개발하며, 이를 통해 분산 아키텍처에 대한 경험을 쌓기 위한 목적으로 진행

## 기술스택

- Java 21 , Kafka , Redis , MySQL
- Spring Cloud Gateway , Eureka , Eureka-client , Spring kafka , Spring Data Redis , Spring Data JPA, Feign Client
- nGrinder , PostMan , Docker Compose

![프로젝트 아키텍처](https://github.com/user-attachments/assets/7fa37491-b6d9-4d7e-8afb-2f3f27eda3c7)


## API 명세서

[API 명세서](https://www.notion.so/13f9a382c1a2804896bbd94871f287d3?pvs=21)


## 핵심 기능 FlOW

1. API Gateway 를 통한 JWT 필터 및 각 서비스로 라우팅
2. 그 외 서비스 끼리의 호출 **(Feign Client)** 또한 Gateway 통과
3. API Gateway 요청 시 Token 전달을 위해 Filter 에서 Thread Local 을 통해 **토큰 저장 및 전달**
4. Member-Service 로그인 시 에서 엑세스 및 리프레쉬 **토큰 제공**
5. Product-Service 에서 kafka 로 발행된 메시지 컨슘하여 **재고 DB 감소**
6. Order-Service 에서 주문 요청 시 Member 와 Product 정보 **비동기 호출**하여 주문 저장
7. Payment-Service 에서 결제 요청 시 **대기 상태**의 Order를 가져와 결제 정보 저장 및 Order-Id 발행하여 Order-Status 변경 및 **ProductId:Stock** 메시지 발행하여 재고 감소

![전체 FLOW](https://github.com/user-attachments/assets/38d2b8dd-8860-4c08-abc2-4fdfd6585746)


## 서비스 별 역할

- API Gateway
    - request를 각 서비스에 라우팅 시켜준다. (/서비스명/url)
    - JWT 토큰의 필터링을 한다.
- Member-Service
    - 회원 가입
    - 로그인 토큰 발행
    - 이메일 확인 코드 전송
    - 이메일 확인 코드 검증
- Product-Service
    - 상품 목록 조회
    - 상품 상세 조회
    - 재고 감소 메시지 소모
- Order-Service
    - 주문 및 재고 감소
    - 주문 정보 API
    - 주문 상태 변경 메시지 발행
- Payment-Service
    - 결제 검증 및 저장
    - 주문 상태 변경 메시지 발행
    - 재고 감소 메시지 발행

## 의사결정 및 트러블슈팅
<details>
        
<summary><b>결제 완료 후 재고 감소 및 주문 상태 변경 로직</b></summary>
<br>

***결제 완료 Flow***<br>
![결제 완료 FLOW](https://github.com/user-attachments/assets/d23a24a7-b2a7-4996-839c-697854b09744)


***재고 반영 Flow***<br>
![재고 DB 반영 FLOW](https://github.com/user-attachments/assets/b54ce317-0766-4d7b-8acd-21b41d6306cc)

    
- 결제 시 일시적으로 몰릴 수 있는 **재고 변동을 DB 부하**를 줄이기 위하여 **대규모 데이터 처리에 이점**을 가진 **kafka** 통하여 처리하도록 하였고 그 외 **UX 를 높이기 위해** 관심사 외의 작업을 메시지를 발행하여 처리
- **Kafka** vs RabbitMQ : 대용량 데이터와 실시간 스트리밍에 안정적인 **Kafka** 선택
- 휘발성 : Kafka는 메시지를 가져가더라도 EventStreamer 에 저장하여 재생 가능하지만 RabbitMQ는 삭제해 불가능
- 실시간 스트리밍 : 대규모 실시간 스트리밍에서 Kafka는 RabbitMQ에 비해 우위를 가짐
- 응답 속도 : RabbitMQ는 낮은 지연 시간과 빠른 응답성으로 실시간 요청-응답 기반 애플리케이션에 최적화

</details>

<details>
<summary><b>재고 관리 방식</b></b></summary> 
<br>

![재고 관리 방식](https://github.com/user-attachments/assets/37b03943-ad29-4a99-9235-d7c1812c69af)

    
- **Redis** 를 통해 도메인 특성 상 구매 속도가 **빨라야하며 재고에 오류가 있어선 안된다** 생각하여 InMemory DB인 레디스를 선택하여 이를 통해 빠른 조회에 **원자적 연산**으로 동시성 제어
- 재고를 감소 한 후 감소한 값이 0 미만이라면 재고가 부족하다 판단하고 application 레벨에서 이를 체크하고 복구하는 로직으로 작성하였으나 감소한 시점에 또 다른 요청이 오면 해당 요청이 통과해야 하는 재고임에도 실패하는 문제 발생
- **루아스크립트** vs 분산 락
  - 루아스크립트
    - 레디스 내에서 로직이 가능
    - 이를 통해 원자적 연산으로 다른 클라이언트의 개입을 봉쇄하여 경쟁 상태 방지
    - 서버에서의 로직이 필요한 경우는 사용 불가

  - 분산 락
    - 레디스에서 값을 가져와서 사용하는 로직의 형태로 이용 가능
    - Redisson 의 경우 pub,sub 형태로 락을 사용함
</details>

<details>
<summary><b>결제 완료 후 동시성 이슈</b></summary> 
<br>

**주문 상태 Flow**
        
![image](https://github.com/user-attachments/assets/5d9c5ba4-6092-4c13-8ac6-b5c7582ef252)

    
**AsIs**
    
- 주문이 시작된 후 **10분 내에 결제 완료**를 하지 못하면 스케줄러(1분 간격 실행)를 통해 주문이 실패로 처리
- 결제가 완료되면 **Order 번호를 메시지로 발행**하여 주문 상태를 완료로 업데이트
- 하지만 다음과 같은 상황에서는 동시성 문제가 발생
  - 결제가 10분 내에 완료되었으나, 메시지가 10분 안에 컨슘되지 못한 경우
  - 메시지가 처리 완료되었지만 스케줄러에서 처리되지 않은 주문으로 조회된 경우
    
**Tobe**
    
- 실패 처리와 성공 처리를 동일한 Kafka 토픽에서 관리
- **UpdateAt 필드 확인을 통해** 값이 존재하면 상태 변경 X
 - 이로 인해 Order가 공유자원이 되어 DB 레벨의 쓰기 락을 통해 해결

</details>
<details>
        <summary><b>가상 스레드를 적용하여 주문 API 성능 개선</b></summary>
        <br>
        
**성능 개선 상세** : [부하테스트 기록](https://desert-elk-95d.notion.site/1429a382c1a280e884bcfbb793ecaaad?pvs=4) 
| **버전** | **주요 변경 사항** | **TPS** | **TPS 변화량** | **증감률** |
| --- | --- | --- | --- | --- |
| Version 0 (조정 전) | 기본 설정 | 180 | - | - |
| Version 0 (조정 후) | 커넥션 풀 최적화 | 220 | +40 | +22.22% |
| Version 1 | Feign 요청 비동기 처리 | 138 | -42 | -23.33% |
| Version 2 | 스레드 풀 크기 500으로 조정 | 229 | +49 | +27.22% |
| Version 3 | 비동기 Task 가상 스레드 변경 | 130.5 | -49.5 | -27.50% |
| Version 3.5 | MariaDB Driver 및 가상 스레드 비동기 요청 처리 | 265 | +85 | +47.22% |
| Version 4 | 톰캣 스레드 가상 스레드 전환, ThreadLocal 제거 | 262 | +82 | +45.56% |

최종적으로 **Version 3.5**를 적용하여 TPS **180 → 265**로 **47.22% 성능 향상**을 달성
</details>

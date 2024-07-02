<h1 align='center'> <img src='https://cdn-icons-png.flaticon.com/512/5208/5208370.png' style='width: 300px; height: 200px;'>&nbsp;</h1>
<h1  align='center'>👕라이브 커머스 프로젝트</h1>



## 목차
- [개요](https://github.com/YoungQWER/LiveCommerce#-개요)
- [기술 스택](https://github.com/YoungQWER/LiveCommerce#-기술-스택)
- [프로젝트 설계](https://github.com/YoungQWER/LiveCommerce#-프로젝트-설계)
- [핵심 기능](https://github.com/YoungQWER/LiveCommerce#-핵심-기능)
- [주요기능 실행화면](https://github.com/YoungQWER/LiveCommerce#-주요기능-실행화면)
- [개선사항](https://github.com/YoungQWER/LiveCommerce#-개선사항)
  


## 🚩 개요
- 프로젝트 목표 : 다양한 `API`를 활용한 `스프링` , `마이바티스` 라이브 커머스 프로젝트
- 개발기간 : 24/05/02 ~ 24/05/24



## 🔧 기술 스택
- API : `카카오페이 API`
- Language : `Java(11)` `JavaScript(3.22)`
- Library & Framework : `Spring(5.3.23)` `Junit(4.12)` `Servlet(4.0.1)` `Spring Security` `websocket(5.2.7)` `JSP(2.5)`
- Database : `MySQL(8.0.35)`
- Target : `Web Browser`
- Tool : `SpringSource Tool Suite (STS) 3.9.18.RELEASE`
- Infra : `Linux(Ubuntu)` `EC2`
- Etc : `Git`

## 👾 프로젝트 설계, 구현 📂 PPT 📂 (ERD, USECASE)

<details><summary>프로젝트 설계, 구현, PPT 눌러서 확인</summary>   
<div align="center">   

**![1](https://github.com/YoungQWER/LiveCommerce/assets/157094828/9f6401a4-513b-4c22-911a-3a8d28542624)**
**![2](https://github.com/YoungQWER/LiveCommerce/assets/157094828/8ff0b5bc-dc4b-4864-b073-b05e3e001317)**
**![3](https://github.com/YoungQWER/LiveCommerce/assets/157094828/00e75f3e-1a45-4881-9af3-d761346175c1)**
**![4](https://github.com/YoungQWER/LiveCommerce/assets/157094828/a4ac30d7-8a50-4f57-b7e9-4f8de0babc28)**
**![5](https://github.com/YoungQWER/LiveCommerce/assets/157094828/98d67baf-a9dc-4572-9225-5a0df5f86895)**
**![6](https://github.com/YoungQWER/LiveCommerce/assets/157094828/066c8938-1873-4493-a97e-9fe546174ff9)**
**![7](https://github.com/YoungQWER/LiveCommerce/assets/157094828/59141d23-f40b-4f3e-b49e-6709673de680)**
**![8](https://github.com/YoungQWER/LiveCommerce/assets/157094828/80f5da1c-d693-4da6-883c-f3a0cec5afbd)**
**![9](https://github.com/YoungQWER/LiveCommerce/assets/157094828/5ce640bf-24ec-4956-8602-18ce98e36506)**
**![10](https://github.com/YoungQWER/LiveCommerce/assets/157094828/5325ca18-0479-4100-9314-e540b9659641)**
**![11](https://github.com/YoungQWER/LiveCommerce/assets/157094828/367d32a0-ce89-4992-ab42-e8fd1e204492)**
**![12](https://github.com/YoungQWER/LiveCommerce/assets/157094828/3838dc5b-f5a7-45e0-a6c6-638153a973ee)**
**![13](https://github.com/YoungQWER/LiveCommerce/assets/157094828/efcacf9c-024a-4b3f-b90a-f5e67546e098)**
**![14](https://github.com/YoungQWER/LiveCommerce/assets/157094828/38978333-1afa-4b3b-ae77-c983f0dbae2a)**
**![15](https://github.com/YoungQWER/LiveCommerce/assets/157094828/bdaf929a-4838-495d-bfcf-2e68cec9306c)**
**![16](https://github.com/YoungQWER/LiveCommerce/assets/157094828/fe3574c9-0b8a-4d86-8fe5-99ae093a77fc)**
**![17](https://github.com/YoungQWER/LiveCommerce/assets/157094828/d78b24c2-a9a2-41cd-9a9b-17c9efbe5560)**
**![18](https://github.com/YoungQWER/LiveCommerce/assets/157094828/1b6d772c-3678-4f64-8594-cbe55db9d04a)**
**![19](https://github.com/YoungQWER/LiveCommerce/assets/157094828/1daca093-b435-48f5-8066-e85409a77902)**
**![20](https://github.com/YoungQWER/LiveCommerce/assets/157094828/e77f9e03-9d13-4371-ab35-c17c5b821a65)**
**![21](https://github.com/YoungQWER/LiveCommerce/assets/157094828/de07feb9-0f61-4325-b484-0d9ce5115dc2)**
**![22](https://github.com/YoungQWER/LiveCommerce/assets/157094828/43444f54-07f6-4b9b-95fe-9930e264c66c)**
**![23](https://github.com/YoungQWER/LiveCommerce/assets/157094828/96b28b8b-7cb6-4a4e-9690-2eb517d8d676)**

</div>            
</details>

## 💻 핵심 기능



#### 상품
- 상품 정보 DB 저장
- 상품 검색
- 카테고리 기능
- 관련 상품 추천

#### 유저
- 로그인 및 회원가입
- 비밀번호 암호화 처리
- 마이 페이지
- 개인 정보 수정
- 라이브 채팅

#### 장바구니
- 상품 장바구니에 담기 및 제거
- 실시간 수량 수정 후 결제
- 같은 상품, 같은 사이즈 장바구니 담을 시 수량 증가

#### 주문
- 장바구니 상품 주문
- KakaoPayAPI 이용한 결제
- 유저만 구매가능

## 🎇 주요기능 실행화면

<details>
<summary>주요기능 실행화면 눌러서 확인</summary>

![라이브커머스_이진영_박우주](https://github.com/YoungQWER/LiveCommerce/assets/157094828/6f3e2bc6-4c5d-40e2-82be-992a6ac8e18b)

</details>


## 🌄 개선사항
- 관리자 CRUD 추가
- 장바구니 구매 후 자동삭제
 

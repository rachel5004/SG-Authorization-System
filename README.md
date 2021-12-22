# SG Dev Camp Authorization System

# 👉 목차

- [Stack](#Stack)   
- [구현 기능](#구현-기능)
- [프로젝트 명세](#프로젝트-명세)
   - [System Architecture](#System-Architecture)
   - [API Document](#API-Document) 
   - [DB ERD](#DB-ERD) 


---

## 1) Stack 

- **Front-end**
  - React.js
  - Redux
  - Bootstrap
- **Back-end**
  - Java 11 + Spring Boot 2.5.7
  - JPA
  - PostgresQL
---

## 2) 구현 기능

### Phase1

- 회원 페이지
    - 로그인
    - 회원 가입
    - 마이 페이지
- 관리자 페이지
    - 회원 목록 조회

- jwt 인증·인가 (access token, refresh token) 
- Salt 암호화
- Redis Cache

### Phase2

- [ ] 이메일 인증
- [ ] 비밀번호 찾기
- [ ] SCG + Eureka 연결
- [ ] 환경변수 secrets 분리
- [ ] init.sql 생성 및 local start setting 가이드 작성

---

## 3) 프로젝트 명세

### System Architecture
<details>
<summary>phase1</summary>
<div markdown="1">

![system architecture](https://user-images.githubusercontent.com/75432228/146877511-89b78837-7b97-4e70-8240-49e44844b7a9.jpg)

</div>
</details>
<details>
<summary>phase2</summary>
<div markdown="1">
   
![system architecture (1)](https://user-images.githubusercontent.com/75432228/147056325-abc7b86f-8210-4b3e-acaf-7a08b6f9e5be.jpg)

</div>
</details>



---

### API Document

- 로그인 `[POST] /api/v1/auth`
- 회원가입  ` [POST] /api/v1/user` 
- 회원탈퇴 `[DELETE] /api/v1/user`
- 내 프로필 조회 `[GET] /api/v1/user/me`
- 유저 리스트 조회 `[GET] /api/v1/user`
- 유저 상세정보 조회 `[GET] /api/v1/user/{id}`
- TOKEN 재발급 `[GET] /api/v1/auth/reissue`


---

### DB ERD

<img width="1128" alt="스크린샷 2021-12-20 오후 5 58 36" src="https://user-images.githubusercontent.com/75432228/146877534-485b4598-1c96-4355-b53c-9ded3c9d8839.png">



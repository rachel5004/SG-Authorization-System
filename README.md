# SG Dev Camp Authorization System

# 목차

- [Stack](#Stack)   
- [기능 설명](#기능-설명)
- [프로젝트 명세](#프로젝트-명세)
   - [System Architecture](#System-Architecture)
   - [API Document](#API-Document) 
   - [DB ERD](#DB-ERD) 


---

## ⚒ Stack

- **Front-end**
  - React.js
  - Redux
  - Bootstrap
- **Back-end**
  - Java 11 + Spring Boot 2.5.7
  - JPA
  - PostgresQL
---

## 🖥 기능 설명

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

---

## 🔧 프로젝트 명세

### System Architecture



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




# 프로젝트 후기

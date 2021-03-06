# SG Dev Camp Authorization System

# ๐ ๋ชฉ์ฐจ

- [Stack](#Stack)   
- [๊ตฌํ ๊ธฐ๋ฅ](#๊ตฌํ-๊ธฐ๋ฅ)
- [ํ๋ก์ ํธ ๋ช์ธ](#ํ๋ก์ ํธ-๋ช์ธ)
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
  - Spring Cloud Gateway + Eureka + Feign Cleint
---

## 2) ๊ตฌํ ๊ธฐ๋ฅ

### Phase1

- ํ์ ํ์ด์ง
    - ๋ก๊ทธ์ธ
    - ํ์ ๊ฐ์
    - ๋ง์ด ํ์ด์ง
- ๊ด๋ฆฌ์ ํ์ด์ง
    - ํ์ ๋ชฉ๋ก ์กฐํ

- jwt ์ธ์ฆยท์ธ๊ฐ (access token, refresh token) 
- Salt ์ํธํ
- Redis Cache

### Phase2

- [ ] ์ด๋ฉ์ผ ์ธ์ฆ
- [ ] ๋น๋ฐ๋ฒํธ ์ฐพ๊ธฐ
- [x] SCG + Eureka ์ฐ๊ฒฐ
- [ ] ํ๊ฒฝ๋ณ์ secrets ๋ถ๋ฆฌ
- [ ] init.sql ์์ฑ ๋ฐ local start setting ๊ฐ์ด๋ ์์ฑ

---

## 3) ํ๋ก์ ํธ ๋ช์ธ

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

- ๋ก๊ทธ์ธ `[POST] /api/v1/auth`
- ํ์๊ฐ์  ` [POST] /api/v1/user` 
- ํ์ํํด `[DELETE] /api/v1/user`
- ๋ด ํ๋กํ ์กฐํ `[GET] /api/v1/user/me`
- ์ ์  ๋ฆฌ์คํธ ์กฐํ `[GET] /api/v1/user`
- ์ ์  ์์ธ์ ๋ณด ์กฐํ `[GET] /api/v1/user/{id}`
- TOKEN ์ฌ๋ฐ๊ธ `[GET] /api/v1/auth/reissue`


---

### DB ERD

<img width="1128" alt="แแณแแณแแตแซแแฃแบ 2021-12-20 แแฉแแฎ 5 58 36" src="https://user-images.githubusercontent.com/75432228/146877534-485b4598-1c96-4355-b53c-9ded3c9d8839.png">



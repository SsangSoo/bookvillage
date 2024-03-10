# TDD + 현재까지 배운 실무적인 느낌으로 만드는 Bookvillage

- 메인 프로젝트를 실무적인 요소와 TDD를 통해서 구현
  - 실무적인 요소~~라고 해봐야 데이터 삭제없이 삭제여부만 구분하도록 하는 거 외에~~별 거 없음.
  - 최대한 TDD를 적용할 예정

### 개발 환경 및 스택
- 일단 local
  - 서버 까지 올릴 건 아님

- 스프링 부트 3
- jdk 21 ~~(최신버전에 쫄 거 없음 jakarta 외에 다 똑같음)~~
- 스프링 데이터 JPA + QueryDSL
- GOOGLE API를 이용한 로그인
 
- 로컬 MySQL
- 개발 Tool : 인텔리제이
- 데이터베이스 Tool : DataGrip(JetBrain사 Tool)
- JWT 사용으로 Redis

### 기존의 프로젝트에서 추가해볼 부분
- 게시물에 댓글이 달렸을 때 mail 등록한 이메일로 메일 받게 하기
- 좋아요
- 최대한 데이터를 삭제하지 않고 구분 컬럼을 넣어서 삭제가 처리되도록
- 검색 기능 최대한 반영


### ERD

<div align="left">
  <img src="https://velog.velcdn.com/images/tjdtn4484/post/81775380-cd4d-4e0d-bc0f-207dca0659e3/image.png">
</div>


### 기록
<details>
<summary>개발 일지</summary>

#### 3월 11일 
- entity 작성
- 연관관계는 일단 모두 다대일 단방향으로 설계
  - 후에 필요에 의해 양방향 필요시 그 때 양방향 관계를 맺어줄 계획



</details>
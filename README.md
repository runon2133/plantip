# plantip
> 식물을 키우며 겪는 경험을 공유하는 게시판 형식의 웹 프로젝트입니다. 


## Index
- [Description](#Description)
- [Preview](#preview)
- [Built With](#built-with)


## Description
#### 로그인
- id, pw 유효성 검사
- 유효성 검사 만족할 경우 로그인 버튼 활성화
- id, pw 값이 DB와 일치할 시 MEMBER 객체 session 등록

#### 회원가입
- name, id, pw, email 유효성 검사
- id, email 중복 검사(Ajax)
- 유효성 검사, 중복 검사 만족할 경우 회원가입 버튼 활성화
- pw 값 암호화(SHA-256)

#### 아이디 찾기
- email 값이 DB와 일치할 시 등록한 email로 id 발송

#### 비밀번호 찾기
- id, email 값이 DB와 일치할 시 등록한 email로 임시 pw 발송

#### 게시판(상담)
- 제목, 내용에 일치하는 값에 따른 검색 기능
- MEMBER 객체 session 값 등록 여부에 따라 글쓰기 가능(등록되어 있지 않다면 로그인 페이지 이동)
- 게시글 등록 시 확장자, 용량, 첨부파일 수 체크(사진 업로드 없이 등록 가능)
- 게시글 등록 시 title, content 유효성 검사
- 유효성 검사 만족할 경우 저장 버튼 활성화
- 게시글 관심 등록

#### 마이페이지
- 회원 정보, 비밀번호 변경 기능
- 회원 탈퇴 기능
- 관심 글, 작성 글, 작성 댓글 조회


## Preview
#### 로그인
![로그인](https://user-images.githubusercontent.com/105443273/175956930-899bcb11-f561-472e-a44e-43d424c01c28.gif)

#### 회원가입
![회원가입](https://user-images.githubusercontent.com/105443273/175957288-96d1cd5b-89ec-4e65-8ea6-5057e21037c0.gif)

#### 아이디 찾기
![아이디 찾기](https://user-images.githubusercontent.com/105443273/175957715-98ac98d5-bde3-440d-877c-1d657e396316.gif)

#### 비밀번호 찾기
![비밀번호 찾기](https://user-images.githubusercontent.com/105443273/175957785-38cfde9b-1a1b-4d76-964e-b35ed1882b22.gif)

#### 게시판(상담)
![상담](https://user-images.githubusercontent.com/105443273/175961159-dcf6d785-a99d-4e66-adf9-adbc3dea84a8.gif)

#### 마이페이지
![마이페이지](https://user-images.githubusercontent.com/105443273/175965732-3a8fe595-cf25-49de-ae72-37992fd88742.gif)


## Built With
#### Front-end
- JavaScript
- jQuery-3.3.1
- Bootstrap 5([Modern Business](https://startbootstrap.com/template/modern-business) 수정)

#### Back-end
- Java 8
- Spring Framework 5.0.6
- Maven 3.8.3
- MyBatis 3.5.6

#### DataBase
- Oracle 11g
- Sql Developer

#### Web-server
- Apache-Tomcat-8.5.71

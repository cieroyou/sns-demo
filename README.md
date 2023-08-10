# sns-demo

## swagger api

* http://localhost:8080/swagger-ui/index.html

## 아키텍처

domain-service 는 코어도메인으로써 비즈니스로직만 수행한다. 서로 다른 도메인의 데이터를 주고받기 위한 도메인을 db로부터 가져오고 저장하는 행위는 application 패키지에서 수행하고, 여러 도메인의
흐름을 제어하는 오케스트레이션 역할을 한다.  
domain-service 는 읽기만 수행하는 ReadService, 쓰기만 수행하는 WriteService 로 나눔으로써, Usecase(application)에서 두 개를 모두 의존하지 않고, 도메인에 대한 읽기
권한만 가지도록 할 수 있는 이점을 취한다.

* application
* controller
* domain
    * entity
    * repository
    * service

## 요구사항

### 회원관리

- 이메일, 닉네임, 생년월일을 입력받아 저장한다.
- 닉네임은 10자를 초과할 수 없다.
- 회원은 닉네임을 변경할 수 없다.
    - 회원 닉네임 변경이력을 조회할 수 있어야 한다.

### 팔로잉
- 팔로잉 기능을 제공한다.
- 내가 팔로잉 하는 멤버목록을 조회할 수 있다.

### 게시글

- 회원은 게시글을 등록할 수 있다.
- 특정 기간, 특정 멤버의 데일리 게시글 개수를 조회할 수 있다.
- 게시글을 페이징으로 조회할 수 있다.
- 게시글 수정
- 댓글 등록
- 이미지 업로드
- 영상 업로드
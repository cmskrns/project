# G.C.R 커뮤니티 게시판

![페인페이지](https://user-images.githubusercontent.com/106217265/187130284-683c9b8f-93f9-4b20-baf3-db77ecd95a99.png)


1. 개발기간 및 개발 인원
```
- 2022.07.01 ~ 2022.08.25
- 1인 개발
```

2. 개발 환경
```
- 언어 : JAVA, HTML/CSS, JS
- 프레임워크 : Spring MVC, Mybatis
- DB : MySQL
```

3. 기획의도
```
- 코로나 발병이후 배달음식점의 발달로 인해 특정지역의 배달음식점의 정보를 공유하기 위한 커뮤니티 사이트를 제작하였습니다
```

4. 구현기술
```
- 게시판 CRUD/ 페이징 처리와 검색처리/ 댓글 / 파일 업로드 / 카테고리 구분/ 마이페이지
- 회원 가입 및 로그인 구현 / 회원 등급에 따른 권한 설정 / 관리자 권한으로 회원 강퇴기능
- 업로드 파일 중 잘못 올려진 파일을 일정 시간마다 자동 삭제
- 게시글 작성 및 회원가입시 Validation구현
```

5. 힘들었던 점과 부족했던 점
```
- 데이터 베이스 설계력 부족 
    -> 처음 설계할 때 기간을 가지고 설계에 집중했더라면 중복되는 데이터베이스를 좀더 함축 할 수 있었을 것이다

- 에러페이지 설정
    -> 사용자들의 악의적인 접근에 대한 경우의 수를 예상하고 그에 따른 에러페이지를 설정하기 힘들었다
    
- 다양한 SQL문, 표현식
    -> 처음 프로젝트를 제작하기 때문에 필요한 SQL문과 표현식들을 찾아서 사용하기 힘들었다
    
- 자바 스크립트와 jQuery의 이해도 부족
    -> 자바 스크립트와 jQuery의 이해도가 부족하여 모듈화를 하지 못했다
    
- 시큐리티 토큰 설정
    -> 시큐리티 설정후 post로 전송하는 부분은 전부 토큰 처리를 해주어야하는데 이점이 힘들었다
```

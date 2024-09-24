# 🧩 GIVEUS

## 1. 개요

- 개발 기간: 2024.02.26 - 2024.04.05 (6주)
- 삼성 청년 소프트웨어 아카데미(SSAFY) 블록체인 프로젝트  
  `블록체인` `크라우드 펀딩` `기부`

## 2. 프로젝트 소개

병원비 크라우드 펀딩 프로젝트

## 3. 서비스 주요 기능

### 3.1 소셜로그인
       - 소셜로그인을 통한 회원가입 ( 카카오 로그인 )

### 3.2 메인
       - 실시간 기부 참여자 확인  
       - 총 기부 금액 확인 
       - 최근 등록된 후기 및 곧 종료되는 펀딩 확인  
       - 펀딩 선택시 펀딩 상세 조회 페이지로 이동 

### 3.3 검색

       - 최근 검색어 저장     
       - 지우기를 통한 최근 검색어 모두 삭제  
       - 펀딩 선택시 펀딩 상세 조회 페이지로 이동 
 
### 3.4 펀딩 목록

       - 펀딩 상태 ( 진행중, 진행완료 ), 나이 필터링  
       - 진행 완료된 펀딩은 병원이 등록한 진료비 사용 내역 확인 가능
       - 기간순, 참여자순, 최신 등록순, 높은 목표금액순, 낮은 목표금액순 정렬


### 3.5 후원하기
       - 금액 버튼을 누르거나 직접 입력하여 후원 금액 설정  
       - 결제 수단으로 토스페이, 카카오페이 선택  
       - 기부자 공개 여부를 위한 실명 및 닉네임 선택 
       - 후원 완료시 기부자 명단 내 기부 내역 실시간 확인 가능

### 3.6 알림
      - 최근에 온 알림순으로 기본 정렬 
      - 참여한 펀딩에 대한 진료비 사용 내역이 등록되었을 때 알림
      - 참여한 펀딩에 대한 기부 후기가 등록되었을 때 알림  
      - 알림 선택시 펀딩 상세 조회 페이지로 이동
### 3.7 마이페이지
      - 현재 보유 포인트 확인 
      - 조회 기간, 펀딩상태 ( 전체, 진행중, 진행완료 )으로 필터링
      - 금액 버튼을 누르거나 직접 입력하여 포인트 충전 금액 설정 <br/>
      - 총 후원금액 확인 
      - 펀딩 선택시 펀딩 상세 조회 페이지로 이동 
      - 조회 기간, 포인트 사용 유형 ( 전체, 충전만, 사용만 ) 으로 필터링 
      - 라이트모드, 다크모드 설정 가능
## 5. 기술 스택

### 5.1 FrontEnd
Typescript, React.js, Vite, PWA, styled component, recoil, react query


## 7. Team

<table>
  <tr>    
    <td align="center">
      <a href="https://github.com/yihoney">
        <div>이하늬(팀장)</div>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/naerim">
        <div>김내림</div>
      </a>
    </td>
        <td align="center">
      <a href="https://github.com/gudrb4869">
        <div>박형규</div>
      </a>
    </td>
        <td align="center">
      <a href="https://github.com/seojihyeon99">
        <div>서지현</div>
      </a>
    </td>
        <td align="center">
      <a href="https://github.com/jjaehong">
        <div>승재홍</div>
      </a>
    </td>
        <td align="center">
      <a href="https://github.com/seungho4222">
        <div>이승호</div>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/yihoney" width="150px;"  alt="이하늬"/>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/naerim" width="150px;" alt="김내림"/>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/gudrb4869" width="150px;" alt="박형규"/>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/seojihyeon99" width="150px;" alt="서지현"/>
    </td>    
    <td align="center">
      <img src="https://avatars.githubusercontent.com/jjaehong" width="150px;" alt="승재홍"/>
    </td>    
    <td align="center">
      <img src="https://avatars.githubusercontent.com/seungho4222" width="150px;" alt="이승호"/>
    </td>
  </tr>
  <tr>
    <td>
      - BE <br/>
      - 프로젝트 초기 설정 <br />
      - 사용자 및 관리자 펀딩 API <br />
    </td>
    <td>
      - FE <br/>
      - 포인트 내역 조회 및 필터링 <br />
      - 검색 및 알림 <br />
    </td>
    <td>
      - BE <br/>
      - CI/CD <br />
      - 카카오페이 및 토스페이
    </td>
    <td>
      - BE <br/>
      - 소셜 로그인 및 회원가입 <br />
      - 알림 구현
    </td>
    <td>
      - FE <br/>
      - 블록체인 <br />
      - OCR(진단서, 진료비 사용 내역)
    </td>
    <td>
      - FE <br/>
      - 사용자 및 관리자 펀딩 UI/UX <br />
      - 결제 및 포인트 사용 <br />
    </td>
  </tr>

</table>

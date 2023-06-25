# 근태 작성 및 결재 그룹웨어 (프로젝트 : DoWooRi_ver.2)
<br>
<div align="center">
    <img width="100%" alt="image" src="https://github.com/gomshiki/DowooriVer2/assets/105576721/6c2bc785-dd11-4e64-98cf-ed8336bd7cab">
    <a href="http://3.22.221.9:8080/">
      프로젝트 페이지 이동
    </a>
</div>

<br>
<br>

### 개발목표
- Springboot와 JPA의 기반으로 프로젝트 개발
- 기안문 작성 및 결재 시스템 개발
- 결재된 기안문 통계 차트 개발

<br>

### 개발배경 및 과정
- [Rasa 오픈소스를 활용한 대화형 업무비서 챗봇](https://github.com/2022-SMHRD-KDT-BigData-6/DoWoori) 챗봇 토크나이저와 오픈소스 개발을 담당
- Spring 프레임워크에 대한 스터디가 부족하다고 생각해 Springboot와 JPA를 이용하여 프로젝트 리빌딩을 하기로 결정
- [개발 이슈 및 기록 블로그 정리](https://better-tomorrow-than-today.tistory.com/category/%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/Spring%20boot%EB%A5%BC%20%EC%9D%B4%EC%9A%A9%ED%95%9C%20%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%20%EA%B0%9C%EB%B0%9C?page=3)

<br>


### 주요 개발 이슈
- [페이징 개발](https://better-tomorrow-than-today.tistory.com/35)
- [Fullcalendar 랜더링 에러](https://better-tomorrow-than-today.tistory.com/33)

<br>
<br>

## 프로젝트 구조
<div align="center">
  <img width="100%" alt="image" src="https://github.com/gomshiki/DowooriVer2/assets/105576721/c6d72650-492c-4c51-ac1a-704cec0f2462">
</div>

<br>
<br>

## ER 다이어그램
<div align="center">
  <img width="50%" alt="image" src="https://github.com/gomshiki/DowooriVer2/assets/105576721/c359652a-8d16-4588-8e81-21668caa7129">
</div>

<br>
<br>

## 워크플로우
<div align="center">
  <img width="100%" alt="image" src="https://github.com/gomshiki/DowooriVer2/assets/105576721/d5742a3b-e189-47d2-9035-f1def0a23e3f">
</div>

<br>
<br>

## 추가 개선 및 개발 계획
- package convention 자바 스타일로 수정
- 약어 변수 -> Full name으로 수정
- JPA -> Spring Data Jpa 로 개선
- 서비스 이중화 및 스케일 아웃 학습 후 적용



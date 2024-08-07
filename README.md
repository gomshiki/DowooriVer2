# 근태 작성 및 결재 그룹웨어 서비스
<br>
<div align="center">
    <img width="100%" alt="image" src="https://github.com/gomshiki/DowooriVer2/assets/105576721/6c2bc785-dd11-4e64-98cf-ed8336bd7cab">
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
- [개발 이슈 및 기록 블로그 정리](https://better-tomorrow-than-today.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EA%B0%9C%EC%9D%B8%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?page=1)

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

## 아쉬운 점과 개선해야할 사항
- package convention 자바 스타일로!  -> [링크 내용 참고하여 블로그 정리](https://stackoverflow.com/questions/49890803/naming-conventions-of-composed-package-names)
- class, method convention 또한 자바 스타일로 ! -> [링크 내용 참고하여 블로그 정리](https://sas-study.tistory.com/445)
- 변수 선언 시 약어 사용 -> Full name 으로 선언할 것!
- @Data 사용 보단 필요한 조건에 따라 @Getter, @Setter 사용
- System.out.println 사용 지양 -> Logger 사용 지향!
- Hashmap 사용 지양 -> DTO를 사용 지향!



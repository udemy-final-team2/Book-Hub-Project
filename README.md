# Book-Hub Project 📚

## 👨‍🏫 프로젝트 소개

문서 작업을 하는 모든이에게, 문서의 히스토리를 저장해주고 문서 간 비교기능을 제공하는 서비스를 기획하였습니다.

## 📅 프로젝트 기간

- 2023년 2월 6일(월) ~ 2023년 3월 2일(목)

## 👨‍👩‍👧‍👦 Team

|                   정태현<br>(Team Leader)                    |                   김지운<br>(Scrum Master)                   |                    곽희진<br>(Developer)                     |                    황영환<br>(Developer)                     |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img alt="정태현" src="https://avatars.githubusercontent.com/u/52318666?s=400&u=db2c82385286405ec4bffb145519a1b80f95b06a&v=4" height="100" width="100"> | <img alt="김지운" src="https://avatars.githubusercontent.com/u/109801772?v=4" height="100" width="100"> | <img alt="곽희진" src="https://avatars.githubusercontent.com/u/79350383?v=4" height="100" width="100"> | <img alt="황영환" src="https://avatars.githubusercontent.com/u/104403136?v=4" height="100" width="100"> |
|            [@alweiis](https://github.com/alweiis)            |             [@Jimoou](https://github.com/Jimoou)             |             [@new-h2](https://github.com/new-h2)             |        [@Hambak-note](https://github.com/Hambak-note)        |

## 📌 Ground Rule

- 팀 회의시에는 쿠션어를 사용해서 대화한다.
- 데일리 KPT는 매일 오전 9시 15분에 라운지에서 진행한다.
- 스프린트 계획 회의는 프로젝트 기간동안 매주 수요일 오전 9시 15분에 라운지에서 진행한다.
- 스프린트 회고는 매주 화요일 오후 5시에 진행한다.
- **효율적인 회의를 통해 회의 시간은 50분으로 제한, 회의를 통해 결정할 내용을 명확히 설정한 후 회의에 돌입한다.**
- main, develop, feature, hotfix, realese와 같은 **Git Workflow 전략으로 개발한다.**
- 커밋, PR 관리를 수월하게 하기 위해 깃 GUI툴을 사용한다.
- **커밋 메세지는 이슈넘버를 붙여 작성한다. (예: #3-회원가입/로그인 구현)**
- **브랜치의 이름에 타입과 이슈넘버를 붙인다. (예: Feature/#3-user-package)**

## 🌐 기술 스택

- Spring Boot, Gradle
- Java, JavaScript, HTML, CSS
- MariaDB
- MyBatis, AJAX, jQuery,  Bootstap
- Git, Github
- AWS(EC2, RDS, S3)
- Jenkins

## 📁 프로젝트 트리

```
📦 Book-Hub Project
├─ .gitignore
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ example
   │  │        └─ BookHub
   │  │           ├─ BookHubApplication.java
   │  │           ├─ Docs
   │  │           ├─ Encrypt
   │  │           ├─ Folder
   │  │           ├─ HomeController.java
   │  │           ├─ Post
   │  │           ├─ S3
   │  │           ├─ Security
   │  │           ├─ ServletInitializer.java
   │  │           ├─ User
   │  │           └─ Util
   │  ├─ resources
   │  │  ├─ application-aws.yml
   │  │  ├─ application-oauth.yml
   │  │  ├─ application-rds.yml
   │  │  ├─ application.yml
   │  │  ├─ mybatis
   │  │  │  ├─ mapper
   │  │  │  └─ mybatis-config.xml
   │  │  └─ static
   │  │     ├─ css
   │  │     ├─ img
   │  │     └─ js
   │  └─ webapp
   │     └─ WEB-INF
   │        └─ views
   └─ test
```

## 💭ERD

![image](https://user-images.githubusercontent.com/52318666/222539237-c69c62c3-c630-47e3-ac95-5dc82ac3c64d.png)

## 🔀 Git Flow Branch Strategy

- `main` : 제품으로 출시할 수 있는 브랜치
- `develop` : 출시 버전을 개발하는 브랜치
- `feat` : 기능을 개발하는 브랜치

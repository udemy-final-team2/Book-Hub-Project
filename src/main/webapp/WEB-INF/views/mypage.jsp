<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/docs.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <script type="text/javascript">
        function validateForm() {
            let email = document.querySelector('#email').value;
            let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            let mailError = document.querySelector('#mailError');
            let password = document.querySelector('#password').value;
            let pwError = document.querySelector('#pwError');
            let button = document.querySelector('#button');
            let emailButton = document.querySelector('#emailButton');

            if (emailRegex.test(email) && email !== "" && password !== "") {
                mailError.textContent = "";
                pwError.textContent = "";
                button.disabled = false;
            } else {
                if (email === "") {
                    mailError.textContent = "이메일을 입력하세요.";
                } else if (!emailRegex.test(email)) {
                    mailError.textContent = "잘못된 형식입니다.";
                } else {
                    mailError.textContent = "";
                    emailButton.disabled = false;
                }
                if (password === "") {
                    pwError.textContent = "비밀번호를 입력하세요.";
                } else {
                    pwError.textContent = "";
                }
                button.disabled = true;
            }
        }

        function startTimer() {
            event.preventDefault();
            let codeForm = document.querySelector("#verificationCode");
            codeForm.disabled = false;

            const timerDisplay = document.querySelector('#timerDisplay');

            const timerDuration = 180;

            let timerInterval = null;
            let timerRemaining = timerDuration;
            timerDisplay.innerHTML = `남은시간: ${timerRemaining} 초`;

            timerInterval = setInterval(() => {
                timerRemaining--;
                timerDisplay.innerHTML = `남은시간: ${timerRemaining} 초`;

                if (timerRemaining === 0) {
                    clearInterval(timerInterval);
                    timerDisplay.innerHTML = "제한시간 초과";

                }
            }, 1000);
        }

        function emailAuth() {
            event.preventDefault();
            let codeForm = document.querySelector("#verificationCode");
            codeForm.disabled = false;
            startTimer();
        }
        
        
        
    </script>
    <style type="text/css">
    	.formContainer {
    	    display: flex;
		    flex-direction: column;
		    text-align: left;
		    padding: 30px 50px 20px;
		    width: 500px;
		    margin: auto;
		    box-shadow: rgb(99 99 99 / 20%) 0px 2px 8px 0px;
    	}
		.side {
			font-size: 20px;
		    width: 95%;
		    height: 49px;
		    padding: 10px 10px 10px 10px;
		    border-radius: 10px;
		    box-shadow: rgb(0 0 0 / 10%) 0px 1px 3px 0px, rgb(0 0 0 / 6%) 0px 1px 2px 0px;
		    border: 0px;
		    margin: 1px auto 20px 1px;
		}
		
		.table>:not(caption)>*>* {
	    padding: 0.5rem 2.5rem;
	    background-color: var(--bs-table-bg);
	    border-bottom-width: var(--bs-border-width);
	    box-shadow: inset 0 0 0 9999px var(--bs-table-accent-bg);
	}    	
    
    </style>
</head>
<body>
<div class="App">
	<div class="Appbar">
	        <div class="right">
	            <div class="brandName">
	                <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">북 허브</span></a>
	            </div>
	        </div>
	        <div class="search">
	            <div class="inputWrap">
	                <input id="searchInput" oninput="searchDocs()" class="searchin" type="text"
	                       placeholder="문서 검색하기...">
	                <button class="material-symbols-outlined" id="clearSearch" onclick="searchClear()">close</button>
	            </div>
	        </div>
	        <div class="left">
	            <div class="dropdown">
	                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
	                        aria-expanded="false">
	                ${dto.name}
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a class="dropdown-item" href="/mypage">내 정보</a></li>
	                    <li><a class="dropdown-item" href="/insertpost">문의글작성</a></li>
	                    <li><a class="dropdown-item" href="/postlist">문의리스트</a></li>
	                    <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="grid">
	        <div class="sidebar">
	            <ul class="folder-list">
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/mypage'">내정보</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/postlist'">문의리스트</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/post/insert'">문의글작성</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side">자주묻는질문</span>
	                </li>
	            </ul>
	        </div>
<!-- 	      </div> -->
	    <div class="main">
	        <div class="formContainer">
	       		 <form action="updateuser" method="post" id="mypageform">
	                <div class="row mb-3">
	                    <h4 class="header">내 정보</h4>
	                    <hr/>
	                    <label for="email" class="col-sm-4 col-form-label">
	                        이메일
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="email" id="email"  oninput='validateForm()'
	                               class="form-control" value="${dto.email}" readonly="readonly">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="password" class="col-sm-4 col-form-label">
	                        패스워드
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="password" id="password"  class="form-control"
	                               placeholder="변경할 패스워드 입력"  name="password" oninput='validateForm()'>
	                        <span id="pwError" style="color: red;">${pwError}</span>
	                    </div>
	                    </div>
	                    <div class="row mb-3">
	                     <label for="password" class="col-sm-4 col-form-label">
	                        패스워드 확인
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="password" id="passwordnew"  class="form-control"
	                               placeholder="변경할 패스워드 재입력 "  name="passwordnew" oninput='validateForm()'>
	                        <span id="pwError" style="color: red;">${pwError}</span>
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="inputNickname" class="col-sm-4 col-form-label">
	                        닉네임
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="text" id="inputNickname" class="form-control" name="name" value="${dto.name}"></input>
	                    </div>
	                </div>
				<input type="submit" id="button" class="btn btn-primary" value="내정보수정">
				<input type="hidden" name="id" value="${dto.id}">
				<input type="hidden" name="role" value="${dto.role}">
				<input type="reset"  class="btn btn-primary" id ="reset" value="취소"> 
	            </form>
	        </div>
   		</div>
   </div>
</body>
</html>
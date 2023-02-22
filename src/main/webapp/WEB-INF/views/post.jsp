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
    	.insertform {
    	    display: flex;
		    flex-direction: column;
		    text-align: left;
		    padding: 30px 50px 20px;
		    width: 1100px;
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
	
    
    .comment{
    resize: none;
/*     width: 660px; */
/*     height: 100px; */
/* 	width: 100%;  */
/* 	height: 50%; */
    margin: 10px 0px 0px 0px;
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
	                ${postdto.name}
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a class="dropdown-item" href="#">내 정보</a></li>
	                    <li><a class="dropdown-item" href="#">내 문서</a></li>
	                    <li><a class="dropdown-item" href="#">고객센터</a></li>
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
	                    <span class="side" onclick="location.href='/postlist'">문의글리스트</span>
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
	        <div class="formContainer insertform">
	       		 <form action="updatecomment" id="mypageform">
	                <div class="row mb-3">
	                    <h4 class="header">상 세 화 면</h4>
	                    <hr/>
	                    <label for="text" class="col-sm-4 col-form-label">
	                        제목
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="text" id="title" class="form-control" name="title" value="${postdto.title}" readonly="readonly">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="category" class="col-sm-4 col-form-label">
	                        카테고리
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="text" id="category"  class="form-control" name="category" value="${postdto.category}" readonly="readonly">
	                    </div>
	                    </div>
	                    <div class="row mb-3">
	                     <label for="password" class="col-sm-4 col-form-label">
	                        작성자 , 날짜
	                    </label>
	                    <div class="col-sm-4">
	                        <input type="text" id="writer" class="form-control" name="writer" value="${postdto.name}" readonly="readonly">
	                    </div>
	                    <div class="col-sm-4">
	                        <input type="text" id="date"  class="form-control" name="date" value="${postdto.createdAt}" readonly="readonly">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="content" class="col-sm-4 col-form-label">
	                        내용
	                    </label>
	                    <div class="col-sm-8">
	                    	<textarea class="form-control comment" rows="10" cols="55" id="content" name="content" readonly="readonly"><c:out value="${postdto.content}"/></textarea>
	                    </div>
	                     <label for="comment" class="col-sm-4 col-form-label">
	                        댓글
	                    </label>
	                    <div class="col-sm-8">
	                        <textarea class="form-control comment" rows="10" cols="55" id="comment" name="comment">회원탈퇴 진행하겠습니다.</textarea>
	                    </div>
	                </div>
				<input type="submit" id="button" class="btn btn-primary" value="댓글작성">
				<input type="hidden" name="id" value="${postdto.userid}">
				<input type="reset"  class="btn btn-primary" id ="reset" value="취소"> 
	            </form>
	        </div>
   		</div>
   </div>
</body>
</html>
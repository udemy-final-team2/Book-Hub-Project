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
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
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
	        <div class="search"></div>
	        <div class="left">
	            <div class="dropdown">
	                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
	                        aria-expanded="false">
	                ${dto.name}
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a class="dropdown-item" href="/mypage">내 정보</a></li>
	                    <li><a class="dropdown-item" href="/folder/list">내 문서</a></li>
	                    <li><a class="dropdown-item" href="/postlist">내 문의</a></li>
	                    <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
	                    
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="grid">
	        <div class="sidebar">
	            <ul class="folder-list">
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/mypage'">내 정보</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/postlist'">내 문의</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/post/insert'">문의글작성</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/post/qna'">자주묻는질문</span>
	                    
	                </li>
	            </ul>
	        </div>
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
	                        <span class="pwError" style="color: red;">${pwError}</span>
	                    </div>
	                    </div>
	                    <div class="row mb-3">
	                     <label for="password" class="col-sm-4 col-form-label">
	                        패스워드 확인
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="password" id="passwordnew"  class="form-control"
	                               placeholder="변경할 패스워드 재입력 "  name="passwordnew" oninput='validateForm()'>
	                        <span class="pwError" style="color: red;">${pwError}</span>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
			crossorigin="anonymous"></script>
</body>
</html>
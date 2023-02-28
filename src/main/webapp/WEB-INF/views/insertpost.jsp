<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.BookHub.User.UserDTO" %>
<%@ page import="static com.example.BookHub.Util.SessionConst.LOGIN_USER" %>
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
.insertform {
	display: flex;
	flex-direction: column;
	text-align: left;
	padding: 30px 50px 20px;
	width: 1100px;
	margin: auto;
	box-shadow: rgb(99 99 99/ 20%) 0px 2px 8px 0px;
}

.side {
	font-size: 20px;
	width: 95%;
	height: 49px;
	padding: 10px 10px 10px 10px;
	border-radius: 10px;
	box-shadow: rgb(0 0 0/ 10%) 0px 1px 3px 0px, rgb(0 0 0/ 6%) 0px 1px 2px
		0px;
	border: 0px;
	margin: 1px auto 20px 1px;
}

.table>:not(caption)>*>* {
	padding: 0.5rem 2.5rem;
	background-color: var(- -bs-table-bg);
	border-bottom-width: var(- -bs-border-width);
	box-shadow: inset 0 0 0 9999px var(- -bs-table-accent-bg);
}

.comment {
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
	        <div class="search"></div>
	        <div class="left">
	            <div class="dropdown">
	                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
	                        aria-expanded="false">
	               <%= ((UserDTO) session.getAttribute(LOGIN_USER)).getName()%>
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a class="dropdown-item" href="/mypage">내 정보</a></li>
	                    <li><a class="dropdown-item" href="/folderList">내 문서</a></li>
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
	                    <span class="side"onclick="location.href='/mypage'">내 정보</span>
	                </li>
	                <li class="folder-name">
	                    <span class="side" onclick="location.href='/postlist'">내 문의</span>
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
	       		 <form action="/post/insert/<%= ((UserDTO)(session.getAttribute(LOGIN_USER))).getId()%>" method="post">
	                <div class="row mb-3">
	                    <h4 class="header">문 의 글 작 성</h4>
	                    <hr/>
	                    <label for="text" class="col-sm-4 col-form-label">
	                        제목
	                    </label>
	                    <div class="col-sm-2">
	                    <select class="form-select" aria-label="Default select example" name="category">
						  <option value="기타문의" selected>기타 문의</option>
						  <option value="탈퇴문의">탈퇴 문의</option>
						</select>
	                    </div>
	                    <div class="col-sm-6">
	                        <input type="text" id="title" class="form-control" name="title" maxlength="20">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="content" class="col-sm-4 col-form-label">
	                        내용
	                    </label>
	                    <div class="col-sm-8">
	                    	<textarea class="form-control comment" rows="10" cols="55" id="content" name="content"></textarea>
	                    </div>
	                </div>
				<input type="submit" id="button" class="btn btn-primary" value="글작성">
				<input type="hidden" name="userid" value="<%= ((UserDTO)(session.getAttribute(LOGIN_USER))).getId()%>">
				<input type="reset"  class="btn btn-primary" id ="reset" value="취소"> 
	            </form>
	        </div>
   		</div>
   </div>
</body>
</html>
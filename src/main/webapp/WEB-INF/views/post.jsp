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
	                ${postdto.name}
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a class="dropdown-item" href="/mypage">내 정보</a></li>
	                    <li><a class="dropdown-item" href="/doc">내 문서</a></li>
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
	                    <span class="side">자주묻는질문</span>
	                </li>
	            </ul>
	        </div>
<!-- 	      </div> -->
	    <div class="main">
	        <div class="formContainer insertform">
	                <div class="row mb-3">
	                    <h4 class="header">상 세 화 면</h4>
	                    <hr>
	                    <label for="text" class="col-sm-4 col-form-label">
	                        제목
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="text" id="title" class="form-control" value="${postdto.title}" readonly="readonly">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="category" class="col-sm-4 col-form-label">
	                        카테고리
	                    </label>
	                    <div class="col-sm-8">
	                        <input type="text" id="category"  class="form-control" value="${postdto.category}" readonly="readonly">
	                    </div>
	                    </div>
	                    <div class="row mb-3">
	                     <label for="password" class="col-sm-4 col-form-label">
	                        작성자 , 날짜
	                    </label>
	                    <div class="col-sm-4">
	                        <input type="text" id="writer" class="form-control"  value="${postdto.name}" readonly="readonly">
	                    </div>
	                    <div class="col-sm-4">
	                        <input type="text" id="date"  class="form-control" value="${postdto.createdAt}" readonly="readonly">
	                    </div>
	                </div>
	                <div class="row mb-3">
	                    <label for="content" class="col-sm-4 col-form-label">
	                        내용
	                    </label>
	                    <div class="col-sm-8">
	                    	<textarea class="form-control comment" rows="10" cols="55"  readonly="readonly"><c:out value="${postdto.content}"/></textarea>
	                    </div>
	                <c:choose>
						<c:when test="${not empty postdto.comment}">
							<label for="comment" class="col-sm-4 col-form-label">
								댓글 
							</label>
							<div class="col-sm-8">
								<textarea class="form-control comment" rows="10" cols="55" readonly><c:out value="${postdto.comment}" /></textarea>
								<input type="hidden" name="userid" value="${postdto.userid}">
								<input type="hidden" name="postid" value="${postdto.id}">
							</div>
						</c:when>
						<c:when test="${ empty postdto.comment && role == '관리자' }">
							<label for="comment" class="col-sm-4 col-form-label">
								댓글
							</label>
							<div class="col-sm-8">
								<form action="/post/comment" method="post">
									<textarea class="form-control comment" rows="10" cols="55"
										name="content" maxlength="5000"></textarea>
									<input type="hidden" name="userid" value="${postdto.userid}">
									<input type="hidden" name="postid" value="${postdto.id}">
									<input type="submit" id="button" class="btn btn-primary" value="댓글작성">
									<input type="reset" class="btn btn-primary" id="reset" value="취소">
								</form>
							</div>
						</c:when>
				</c:choose>
	        </div>
   		</div>
   </div>
</body>
</html>
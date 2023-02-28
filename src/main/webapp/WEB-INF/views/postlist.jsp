<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>북허브</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/docs.css" rel="stylesheet" type="text/css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<style type="text/css">
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
	
	#title{
		text-decoration: none;
		color: black;
	}
</style>
<script type="text/javaScript">
	$(document).ready(function(){
		$("#search").keyup(function(event) {
			if (event.which === 13) {
				search();
			}
		});
		function search() {
			let keyword = $('#search').val();
			console.log(keyword);
			window.location.href = '/postlist?keyword=' + keyword;
		};
	});
</script>
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
            <div>
                <input type="text" id="search" class="searchin" name="keyword" placeholder="게시글 제목 검색">
                <button class="button" id="search()" style="display:none">검색</button>
            </div>
        </div>
        <div class="left">
            <div class="dropdown">
                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    ${name}
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="grid">
        <div class="sidebar">
            <c:if test="${role eq 'ADMIN'}">
            <ul class="folder-list">
                <li class="folder-name">
                    <span class="side" onclick="location.href='/usermanage'">유저 관리</span>
                </li>
                <li class="folder-name">
                    <span class="side" onclick="location.href='/postlist'">문의 관리</span>
                </li>
            </ul>
            </c:if>
            <c:if test="${role eq 'USER'}">
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
            </c:if>
        </div>
        <div class="main">
            <h2 class="folder-header"><span>문의 관리</span>
            </h2>
            <div class="menuBar">
                <h5 class="menuBar-header"></h5>
                <ul class="menuBar-menu"></ul>
            </div>
            <div class="tableStyle">
            <form name="searchForm">
                <table class="table table-striped table-hover" id="usertable">
                    <thead>
                    <tr>
                        <th class="col-sm"scope="col">번호</th>
                        <th class="col-sm"scope="col">닉네임</th>
                        <th class="col-sm"scope="col">카테고리</th>
                        <th class="col-sm-4" scope="col">제 목</th>
                        <th class="col-sm" scope="col">상태</th>
                    </tr>
                    </thead>
                    <tbody>
						<c:choose>
							<c:when test="${empty postList}">
								<tr>
									<td colspan="5" class="text-center">조회된 목록이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${postList}" var="postList">
									<tr>
										<td>${postList.id}</td>
										<td>${postList.name}</td>
										<td>${postList.category}</td>
										<td><a href="/post/${postList.id}" id="title">${postList.title}</a></td>
										<td>${postList.status}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
                </table>
                </form>
                <%
				int totalcount = (Integer) request.getAttribute("totalPost");
							int totalpage = 0;
							if (totalcount % 10 == 0) {
								totalpage = totalcount / 10;
							} else {
								totalpage = totalcount / 10 + 1;
							}
							for (int i = 1; i <= totalpage; i++) {
				%>
				<a href="postlist?page=<%=i%>"><%=i%>페이지</a>
				<%
				}
				%>
			</div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
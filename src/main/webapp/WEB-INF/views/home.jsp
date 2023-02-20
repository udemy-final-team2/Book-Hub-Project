<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Book Hub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link href="css/App.css" rel="stylesheet" type="text/css">
    <link href="css/tape.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="App">
    <div class="Appbar">
        <div class="right">
            <div class="brandName">
                <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">북 허브</span></a>
            </div>
            <div class="menuName">북 허브</div>
            <div class="menuName">요금제</div>
        </div>
        <div class="left">
            <div class="menuName">
                <span class="menu"><a class="link" href="/guest/qna">문의하기</a></span></div>
            <c:if test="${not empty sessionScope.loginid}">
            	<div class="menuName"><a class="link" href="/user/<%= session.getAttribute("loginid")%>">마이페이지</a></div>
            </c:if>
            <div class="menuName">
                <c:if test="${not empty sessionScope.loginid}">
             	   <span class="menu"><a class="link" href="/logout">로그아웃</a></span>
            	</c:if>
            	<c:if test="${empty sessionScope.loginid}">
               	 <span class="menu"><a class="link" href="/signin">로그인</a></span>
               	 </c:if>
            </div>
            
            <button class="button">
                <a class="link" href="/docs">무료체험</a>
            </button>
        </div>
    </div>
    <div class="main">
        <div class="ExploreSite">
            <h1>번거로운 교정, 교열작업은 그만, 북허브와 함께하세요.</h1>
            <div class="paperboard">
                <p>글이 머무는 곳에 생기를 불어넣는 당신에게, 북허브는 최고의 서비스를 제공하겠습니다.</p>
                <button class="button2"><a class="link" href="/signin">시작하기</a></button>
            </div>
        </div>
        <div class="imageContainer">
            <img class="image" src="/img/image.png"/>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>

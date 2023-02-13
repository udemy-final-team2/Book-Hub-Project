<%--
  Created by IntelliJ IDEA.
  User: jiwoon
  Date: 2023/02/13
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>북허브 - 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="App">
    <div class="brandheader">
        <a href="/" class="link">
            <img class="brandlogo" width="80" height="80" src="/img/logo.png" alt="로고"/>
        </a>
        <a href="/" class="logoName">북 허브</a>
    </div>
    <div class="formContainer">
        <form>
            <div class="row mb-3">
                <h4 class="header">로그인</h4>
                <hr/>
                <label for="inputEmail3" class="col-sm-4 col-form-label">이메일</label>
                <div class="col-sm-8">
                    <input type="email" id="inputEmail3" class="form-control" placeholder="이메일을 입력하세요."></input>
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-4 col-form-label">패스워드</label>
                <div class="col-sm-8">
                    <input type="password" id="inputPassword3" class="form-control" placeholder="패스워드를 입력하세요."></input>
                </div>
            </div>
            <p><a href="/signup">회원가입이 필요하신가요?</a></p>
            <button class="btn btn-primary">로그인</button>
        </form>
        <div class="form">
            <form>
                <h5>다른 이메일로 로그인하기</h5>
                <hr/>
                <img class="logo" src="/img/google.png" alt="google"/>
                <img class="logo" src="/img/kakao.png" alt="kakao"/>
                <img class="logo" src="/img/naver.png" alt="naver"/>
            </form>
        </div>
    </div>


</div>

</body>
</html>

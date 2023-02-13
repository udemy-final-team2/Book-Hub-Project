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
    <title>북허브 - 회원가입</title>
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
    <div class="main">
        <div class="formContainer">
            <form>
                <div class="row mb-3">
                    <h4 class="header">회원가입</h4>
                    <hr/>
                    <label for="inputEmail" class="col-sm-4 col-form-label">
                        이메일
                    </label>
                    <div class="col-sm-8">
                        <input type="email" id="inputEmail" class="form-control" placeholder="이메일을 입력하세요.">
                    </div>
                    <div class="valueEmail">
                        <button class="button">이메일 인증</button>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword" class="col-sm-4 col-form-label">
                        패스워드
                    </label>
                    <div class="col-sm-8">
                        <input type="password" id="inputPassword" class="form-control"
                               placeholder="패스워드를 입력하세요.">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputNickname" class="col-sm-4 col-form-label">
                        닉네임
                    </label>
                    <div class="col-sm-8">
                        <input type="text" id="inputNickname" class="form-control" placeholder="닉네임을 입력하세요."></input>
                    </div>
                </div>
                <p><a href="/signin">이미 계정이 있으신가요?</a></p>
                <button class="btn btn-primary">
                    회원가입
                </button>
            </form>
        </div>


    </div>

</body>
</html>

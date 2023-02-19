<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>북허브 - 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link href="css/login.css" rel="stylesheet" type="text/css">
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
                }
                if (password === "") {
                    pwError.textContent = "비밀번호를 입력하세요.";
                } else {
                    pwError.textContent = "";
                }
                button.disabled = true;
            }
        }
    </script>
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
        <form action="/docs" method="post">
            <div class="row mb-3">
                <h4 class="header">로그인</h4>
                <hr/>
                <label for="email" class="col-sm-4 col-form-label">이메일</label>
                <div class="col-sm-8">
                    <input type="email" id="email" value="${email}" oninput='validateForm()'
                           class="form-control" placeholder="이메일을 입력하세요." required>
                    <span id="mailError" style="color: red;">${mailError}</span>
                </div>
            </div>
            <div class="row mb-3">
                <label for="password" class="col-sm-4 col-form-label">패스워드</label>
                <div class="col-sm-8">
                    <input type="password" id="password" value="${password}" oninput='validateForm()'
                           class="form-control" placeholder="패스워드를 입력하세요." required>
                    <span id="pwError" style="color: red;">${pwError}</span>
                </div>
            </div>
            <p><a href="/signup">회원가입이 필요하신가요?</a></p>
            <button id="submit" class="btn btn-primary" >로그인</button>
        </form>
        <div class="form">
            <form>
                <h5>다른 이메일로 로그인하기</h5>
                <hr/>
                <a href="/oauth2/authorization/google"><img class="logo" src="/img/google.png" alt="google"/></a>
                <a href="/oauth2/authorization/kakao"><img class="logo" src="/img/kakao.png" alt="kakao"/></a>
                <a href="/oauth2/authorization/naver"><img class="logo" src="/img/naver.png" alt="naver"/></a>
            </form>
            <div class="returnIcon">
                <a href="/" class="link">
                    <p class="returnText">뒤로가기</p><span class="material-symbols-outlined">west</span>
                </a>
            </div>
        </div>
    </div>


</div>

</body>
</html>

<%@ page import="static com.example.BookHub.Util.SessionConst.LOGIN_USER" %>
<%@ page import="com.example.BookHub.User.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>북허브 - 문서 비교</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <script src="https://cdn.tiny.cloud/1/l4hh05dskpwmkxxbt2c4q7ilmuby0zfysfofsdaujbvuyjr1/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/App.css" rel="stylesheet" type="text/css">
    <link href="/css/compareview.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/diff_match_patch.js"></script>
</head>
<body>
<div class="App">
    <div class="Appbar">
        <div class="right">
            <div class="brandName">
                <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">문서비교</span></a>
            </div>
        </div>
        <div class="left">
            <div class="dropdown">
                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <%= ((UserDTO) session.getAttribute(LOGIN_USER)).getName()%>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/mypage">내 정보</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/folder/list">내 문서</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/postlist">고객센터</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
                </ul>
            </div>
        </div>
    </div>
    <br>
    <%
        String[] documentList = (String[]) request.getAttribute("documentList");
        String htmlContent1 = documentList[0];
        String htmlContent2 = documentList[1];
    %>
    <script>
        window.onload = function () {
            const htmlContent1 = `<%= htmlContent1 %>`;
            const htmlContent2 = `<%= htmlContent2 %>`;
            const dmp = new diff_match_patch();
            const diff = dmp.diff_main(htmlContent1, htmlContent2);
            dmp.diff_cleanupSemantic(diff);
            for (let i = 0; i < diff.length; i++) {
                const operation = diff[i][0];
                switch (operation) {
                    case 0: // no change
                        break;

                    case -1: // deletion
                        const deletedText = diff[i][1];
                        console.log("삭제된 텍스트")
                        console.log(deletedText);
                        const el1 = document.getElementById("documentBeforeChange").querySelectorAll('*');
                        const el2 = document.getElementById("documentAfterChange").querySelectorAll('*');
                        for (let j = 0; j < el1.length; j++) {
                            console.log(deletedText);
                            console.log(el1);
                            if (el1[j].innerText.includes(deletedText)) {
                                el1[j].style.backgroundColor = "red";
                            }
                        }
                        for (let j = 0; j < el2.length; j++) {
                             if(el2[j].innerHTML === deletedText) {
                                el2[j].style.backgroundColor = "red";
                            }
                        }
                        break;

                    case 1: // insertion
                        const insertedText = diff[i][1];
                        console.log("삽입된 텍스트")
                        console.log(insertedText);
                        const el3 = document.getElementById("documentAfterChange").querySelectorAll('*');
                        for (let j = 0; j < el3.length; j++) {
                            if (el3[j].outerHTML === insertedText) {
                                el3[j].style.backgroundColor = "green";
                            }
                        }
                        break;
                }
            }
        };
    </script>
    <div class="documentContainer">
        <div id="documentBeforeChange"><%= htmlContent1%>
        </div>
        <div id="documentAfterChange"><%= htmlContent2%>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static com.example.BookHub.Util.SessionConst.LOGIN_USER" %>
<%@ page import="com.example.BookHub.User.UserDTO" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset='utf-8'>
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
  <title>북허브 - 문서 작성</title>
  <meta name='viewport' content='width=device-width, initial-scale=1'>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
          crossorigin="anonymous"></script>
  <link href="/css/writeform.css" rel="stylesheet" type="text/css">
  <script src="https://cdn.tiny.cloud/1/l4hh05dskpwmkxxbt2c4q7ilmuby0zfysfofsdaujbvuyjr1/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<div class="App">
  <div class="Appbar">
    <div class="right">
      <div class="brandName">
        <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">북 허브</span></a>
      </div>
    </div>
    <div class="buttonContainer">
      <button class="btn btn-outline-dark" id="save" onclick="saveEditorContent()"><img class="buttonImg" src="/img/save.png"/></button>
      <button class="btn btn-outline-dark" id="copy" onclick="copyEditorContent()"><img class="buttonImg" src="/img/copy.png"/></button>
      <button class="btn btn-outline-dark" id="spellCheckLink" onclick="spellCheck()">맞춤법 검사</button>
      <button class="btn btn-outline-dark" id="export" onclick="printEditorContent()">내보내기</button>
      <button class="btn btn-outline-dark" id="cancel" onclick="cancelWrite()">취소</button>
    </div>
    <div class="left">
      <div class="dropdown">
        <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                aria-expanded="false">
          <% Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = null;
            if (auth != null && !auth.getName().equals("anonymousUser")) {
              userName = auth.getName();
            } else if (session != null){
              userName = ((UserDTO) session.getAttribute(LOGIN_USER)).getName();
            }%>
          <%= userName %>
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="/mypage">내 정보</a></li>
          <li><a class="dropdown-item" href="/insertpost">문의글작성</a></li>
          <li><a class="dropdown-item" href="/postlist">문의리스트</a></li>
          <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
        </ul>
      </div>
    </div>
  </div>

  <div class="editorContainer">
    <textarea id="editor"></textarea>
    <textarea id="spellCheckResult" disabled></textarea>
  </div>

  <div id="myModal" class="modal">
    <div class="modal-content">
      <h4>Title</h4>
      <input type="text" id="modal-title">
      <h4>Memo</h4>
      <textarea id="modal-memo"></textarea>

      <div class="modal-button">
        <button id="save-button" class="btn btn-primary">저장</button>
        <button id="close-button" class="btn btn-danger">취소</button>
      </div>
    </div>

  </div>

</div>

<script src="/js/writeform.js"></script>

</body>
</html>


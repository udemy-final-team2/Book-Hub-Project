<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset='utf-8'>
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
  <title>TinyMCE compare document</title>
  <meta name='viewport' content='width=device-width, initial-scale=1'>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link href="/css/writeform.css" rel="stylesheet" type="text/css">

  <!-- tinymce6 라이브러리 추가 -->
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
      <button class="btn btn-outline-dark" id="spellCheckLink" onclick="openSpellCheck()">Naver 맞춤법 검사</button>
      <button class="btn btn-outline-dark" id="export" onclick="printEditorContent()">내보내기</button>
      <button class="btn btn-outline-dark" id="cancel" onclick="cancelWrite()">취소</button>
    </div>
    <div class="left">
      <div class="dropdown">
        <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                aria-expanded="false">
          이름
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
<script>
  tinymce.init({
    selector: '#editor',
    height: '700px'
  });

  function printEditorContent() {
    let editorContent = tinymce.activeEditor.getContent();

    let printFrame = document.createElement('iframe');
    printFrame.style.display = 'none';
    document.body.appendChild(printFrame);
    printFrame.contentDocument.write(editorContent);
    printFrame.contentWindow.print();
    document.body.removeChild(printFrame);
  }

  function saveEditorContent() {
    let editorContent = tinymce.activeEditor.getContent();
    console.log(editorContent);

    let modal = document.getElementById("myModal");
    modal.style.display = "block";

    let saveButton = document.getElementById("save-button");
    saveButton.onclick = function() {
      let title = document.getElementById("modal-title").value;
      let memo = document.getElementById("modal-memo").value;
      $.ajax({
        type: "POST",
        url: "/save",
        data: {
          editorContent: editorContent,
          title: title,
          memo: memo
        },
        success: function(response) {
          console.log(response);
        },
        error: function(xhr, status, error) {
          console.error(error);
        }
      });
      modal.style.display = "none";
    }

    let closeButton = document.getElementById("close-button");
    closeButton.onclick = function() {
      modal.style.display = "none";
    }
  }

  function cancelWrite() {
    if (confirm("작성중인 내용이 초기화됩니다. 진행하시겠습니까?")) {
      tinymce.activeEditor.setContent('');
      location.href = '/folder/list';
    }
  }

  function copyEditorContent() {
    let editorContent = tinymce.activeEditor.getContent({format: 'text'});
    navigator.clipboard.writeText(editorContent)
            .then(() => {
              console.log('Editor content copied to clipboard');
              alert('복사가 완료되었습니다.');
            })
            .catch((error) => {
              console.error('Error copying editor content to clipboard:', error);
            });
  }

  function openSpellCheck() {
    const naverSpellCheckerUrl = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EB%A7%9E%EC%B6%A4%EB%B2%95+%EA%B2%80%EC%82%AC'
    window.open(naverSpellCheckerUrl);
  }
</script>


</body>
</html>


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

    <!-- tinymce6 라이브러리 추가 -->
    <script src="https://cdn.tiny.cloud/1/l4hh05dskpwmkxxbt2c4q7ilmuby0zfysfofsdaujbvuyjr1/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

  </head>
  <body>
    <div class="editorContainer">
      <textarea id="editor"></textarea>
    </div>

    <div class="buttonContainer">
      <button id="export" onclick="printEditorContent()">내보내기</button>
      <button id="save" onclick="saveEditorContent()">저장</button>
    </div>

    <!-- 모달창 추가 -->
    <div id="myModal" class="modal">
      <div class="modal-content">
        <h2>Title</h2>
        <input type="text" id="modal-title">
        <h2>Memo</h2>
        <textarea id="modal-memo"></textarea>
        <button id="save-button">저장</button>
        <button class="close">취소</button>
      </div>
    </div>

  </div>
    <script>
      tinymce.init({
        selector: '#editor',
      });

      function printEditorContent() {
        let editorContent = tinymce.activeEditor.getContent();

        let printFrame = document.createElement('iframe');
        printFrame.style.display = 'none';
        document.body.appendChild(printFrame);
        // iframe에 에디터 내용 로드
        printFrame.contentDocument.write(editorContent);
        // 출력 실행
        printFrame.contentWindow.print();
        // iframe 제거
        document.body.removeChild(printFrame);
      }

      function saveEditorContent() {
        let editorContent = tinymce.activeEditor.getContent();
        console.log(editorContent);

        // 모달창 띄우기
        let modal = document.getElementById("myModal");
        modal.style.display = "block";

        // 모달창 안에 저장 버튼에 이벤트 추가
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

          // 모달창 닫기
          modal.style.display = "none";
        }

        // 모달창의 X 버튼을 누르면 모달창 닫기
        let closeButton = document.getElementsByClassName("close")[0];
        closeButton.onclick = function() {
          modal.style.display = "none";
        }
      }
    </script>
  </body>
</html>


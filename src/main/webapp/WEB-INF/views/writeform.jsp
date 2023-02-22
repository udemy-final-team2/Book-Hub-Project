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

  </div>
    <script>
      tinymce.init({
        selector: '#editor',
      });
    </script>
  </body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>문서 작성</title>
</head>
<body>
    <form action="/docs/write" method="post" enctype="multipart/form-data">
        <label for="title">제목: </label><input type="text" name="title" id="title">
        <label for="memo">메모: </label><input type="text" name="memo" id="memo">
        <label for="file">업로드 파일: </label><input type="file" name="file" id="file">
        <input type="submit" value="문서 저장">
    </form>
</body>
</html>

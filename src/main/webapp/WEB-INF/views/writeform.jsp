<!-- Include Quill stylesheet -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.quilljs.com/1.0.0/quill.snow.css" rel="stylesheet" />
<!-- Create the toolbar container -->
<div id="toolbar">
  <button class="ql-bold">Bold</button>
  <button class="ql-italic">Italic</button>
</div>

<!-- Create the editor container -->
<div id="editor">
  <p>Hello World!</p>
</div>

<!-- Include the Quill library -->
<script src="https://cdn.quilljs.com/1.0.0/quill.js"></script>

<!-- Initialize Quill editor -->
<script>
  let editor = new Quill('#editor', {
    modules: { toolbar: '#toolbar' },
    theme: 'snow',
  });
</script>
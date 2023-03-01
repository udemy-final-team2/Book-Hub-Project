<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static com.example.BookHub.Util.SessionConst.LOGIN_USER" %>
<%@ page import="com.example.BookHub.User.UserDTO" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>북허브 - 문서 리스트</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
  <link href="/css/index.css" rel="stylesheet" type="text/css">
  <link href="/css/docs.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/js/docs.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="App">
  <div class="Appbar">
    <div class="right">
      <div class="brandName">
        <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">북 허브</span></a>
      </div>
    </div>
    <div class="search">
      <div class="inputWrap">
        <input id="searchInput" oninput="searchDocs()" class="searchin" type="text"
               placeholder="문서 검색하기...">
        <button class="material-symbols-outlined" id="clearSearch" onclick="searchClear()">close</button>
      </div>
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
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/mypage">내 정보</a></li>
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/folder/list">내 문서</a></li>
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/postlist">고객센터</a></li>
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="grid">
    <div class="sidebar">
      <ul class="folder-list">
        <c:if test="${folderList != null}">
          <c:forEach var="folder" items="${folderList}">
            <li class="folder-name">
              <input type="hidden" id="folderId" name="folderId" value="${folder.id}">
              <span class="material-symbols-outlined">folder</span>
              <p class="folder-title">
                  ${folder.name}
              </p>
              <p>
                <span class="material-symbols-outlined folder-edit-btn" onclick="editFolderTitle(this)">edit</span>
                <span class="material-symbols-outlined folder-del-btn">delete</span>
              </p>
              <div id="folder-delete-modal" class="modal">
                <div class="modal-content">
                  <p>선택하신 폴더를 삭제하시겠습니까?</p>
                  <div>
                    <button class="btn btn-danger" id="folder-delete-btn">삭제</button>
                    <button class="btn btn-secondary" onclick="closeFolderDeleteModal()">취소</button>
                  </div>
                </div>
              </div>
            </li>
          </c:forEach>
        </c:if>
        <li class="new-folder" onclick="openCreateModal()">
          <span class="material-symbols-outlined">add</span>
          <p class="new-folder-button">새 폴더</p>
        </li>
        <div id="create-modal" class="modal">
          <div class="modal-content">
            <p>폴더명을 입력해주세요.</p>
              <label>폴더명: <input type="text" name="folder-name"></label>
            <div>
              <button class="btn btn-primary" id="folder-create-btn">생성</button>
              <button class="btn btn-secondary" onclick="closeCreateModal()">취소</button>
            </div>
          </div>
        </div>
      </ul>
    </div>
    <div class="main">
      <h2 class="folder-header">
        <span id="icon" class="material-symbols-outlined">folder_open</span>
        <span id="selected-folder-name"></span>
        <input type="hidden" id="folder-hidden">
      </h2>
      <div class="menuBar">
        <h5 class="menuBar-header">최근문서</h5>
        <ul class="menuBar-menu">
          <li>
            <button class="add-btn" id="write-form">새 문서</button>
          </li>
          <li>
              <button id="button" class="comp-btn">비교하기</button>
          </li>
          <li>
            <button id="delete-button" class="del-btn" onclick="openDeleteModal()">삭제</button>
          </li>
        </ul>
        <div id="delete-modal" class="modal">
          <div class="modal-content">
            <p>선택한 문서를 삭제하시겠습니까?</p>
            <div>
              <button class="btn btn-danger" id="delete-btn">삭제</button>
              <button class="btn btn-secondary" onclick="closeDeleteModal()">취소</button>
            </div>
          </div>
        </div>
      </div>

      <div class="tableStyle">
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col-2" style="text-align: center;" width="50">선택</th>
            <th scope="col" style="text-align: center;" width="50">번호</th>
            <th scope="col">제목</th>
            <th scope="col" width="165">작성일자</th>
            <th scope="col">메모</th>
          </tr>
          </thead>
            <tbody id="docsList"></tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script>
  const fetchFolderContents = (folderId) => {
    $.ajax({
      url: `/folderlist`,
      method: 'GET',
      data: {folderId: folderId},
      success: function (data) {
        $('#docsList').html('');
        for (let i = 0; i < data.length; i++) {
          $('#docsList').append(
                  "<tr><td style='text-align: center' class='checkbox'><input type='checkbox' class='document-checkbox' value=" +
                  data[i].id + "></td><td>"+
                  data[i].id + "</td><td><a href='/document/read/" +
                  data[i].id + "'>" + data[i].title + "</a></td><td>" +
                  data[i].saveAt + "</td><td>" +
                  data[i].memo + "</td></tr>");
        }
      },
      error: function (request, status, error) {
        alert("code:" + request.status + "\n"
                + "message:" + request.responseText + "\n"
                + "error:" + error);
      }
    });
  };

  $(document).ready(function () {
    $('#folder-create-btn').click(function() {
      let folderName = $('input[name="folder-name"]').val();
      if (folderName === "") {
        alert('폴더명을 입력해주세요.')
        return false;
      }
      $.ajax({
        url: '/folder/create',
        method: 'POST',
        data: {name: folderName},
        success: function(response) {
          location.reload();
        },
        error: function(error) {
          console.error(error);
        }
      });
    });

    $('p.folder-title').on('click', function () {
      const folderId = $(this).siblings('input#folderId').val();
      $('input[id=folder-hidden]').val(folderId);
      fetchFolderContents(folderId);
    });

    $('.folder-title').click(function() {
      const folderName = $(this).text();
      $('#selected-folder-name').text(folderName);
    });

    $('.folder-del-btn').on('click', function() {
      document.getElementById("folder-delete-modal").style.display="block";
      let li = $(this).parents('li');

      $('#folder-delete-btn').on('click', function() {
        let folderId = li.find('input[name="folderId"]').val();
        $.ajax({
          url: '/folder/delete',
          type: 'POST',
          async: false,
          data: { folderId: folderId },
          success: function(response) {
            console.log(response)
            document.getElementById("folder-delete-modal").style.display = "none";
            location.reload();
          },
          error: function(error) {
            console.log(error);
          }
        });
      });
    });
  });

  $('#delete-btn').on('click', function () {
      let checkedCheckboxes = $('input[type="checkbox"]:checked');
      let documentId = checkedCheckboxes.val();
      if (checkedCheckboxes.length === 1) {
        let folderId = $('input[id=folder-hidden]').val();
        $.ajax({
          url: `/document/delete`,
          data: {documentId: documentId},
          method: 'POST',
          success: function () {
            document.getElementById("delete-modal").style.display = "none";
            fetchFolderContents(folderId);
          },
          error: function (error) {
            console.error(error);
          }
        });
      } else if (checkedCheckboxes.length === 0) {
        alert('삭제하실 문서를 선택해주세요.');
      } else if (checkedCheckboxes.length === 2) {
        alert('삭제하실 문서를 한 개만 선택해주세요.');
      }
      document.getElementById("delete-modal").style.display = "none";
      return false;
    });

    $('#write-form').on('click', function () {
      window.location.href = '/document/write';
    });

    $('#docsList').on('change', '.document-checkbox', function() {
      const checkedCount = $('.document-checkbox:checked').length;
      if (checkedCount === 2) {
        $('.document-checkbox:not(:checked)').attr('disabled', 'disabled');
      } else {
        $('.document-checkbox').removeAttr('disabled');
      }
    });

    $('.comp-btn').on('click', function () {
      const documentId1 = $('.document-checkbox:checked:first').val();
      const documentId2 = $('.document-checkbox:checked:last').val();
      if (documentId1 === undefined || documentId2 === undefined) {
        alert('비교하실 문서 두가지를 선택해주세요.')
        return false;
      }
      if (documentId1 === documentId2) {
        alert('서로 다른 문서를 선택해주세요.')
        return false;
      }
        window.location.href = '/document/compare?documentId1='+ documentId1 + '&documentId2=' + documentId2;
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
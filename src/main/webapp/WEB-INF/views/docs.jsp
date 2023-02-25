<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.BookHub.Docs.DocsDTO" %>
<%@ page import="static com.example.BookHub.Util.SessionConst.LOGIN_USER" %>
<%@ page import="com.example.BookHub.Folder.FolderDTO" %>
<%@ page import="com.example.BookHub.User.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>북허브 - 문서</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/docs.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/docs.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
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
    <div class="grid">
        <div class="sidebar">
            <ul class="folder-list">
                <c:if test="${folderList != null}">
                    <c:forEach var="folder" items="${folderList}">
                        <li class="folder-name">
                            <input type="hidden" id="folderId" name="folderId" value="${folder.id}">
                            <span class="material-symbols-outlined">folder</span>
                            <p class="folder-title" id="folderTitle">
                                    ${folder.name}
                            </p>
                            <p>
                                <span class="material-symbols-outlined folder-edit-btn" onclick="editFolderTitle(this)">edit</span>
                                <span class="material-symbols-outlined folder-del-btn">delete</span>
                            </p>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="new-folder">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        새 폴더
                    </button>
                    <%--<span class="material-symbols-outlined">add</span>
                    <p class="new-folder-button">새 폴더</p>--%>
                </li>
            </ul>
        </div>
        <div class="main">
            <h2 class="folder-header">
                <span id="icon" class="material-symbols-outlined">folder_open</span>
                <input type="hidden" id="folder-hidden">
            </h2>
            <div class="menuBar">
                <h5 class="menuBar-header">최근문서</h5>
                <ul class="menuBar-menu">
                    <li>
                        <button class="add-btn" id="writeform">새 문서</button>
                    </li>
                    <li>
                        <a href="/docs/view">
                            <button class="comp-btn">비교하기</button>
                        </a>
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
                    <tbody id="docsList">
                    <c:forEach items="${documentList}" var="document">
                        <tr>
                            <td>
                                    ${document.title}
                            </td>
                            <td>
                                    ${document.saveAt}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">폴더 추가하기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <label>
                폴더명: <input type="text" name="folder-name">
            </label>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="folder-create-btn">폴더 생성</button>
            </div>
        </div>
    </div>
</div>

<script>
    const fetchFolderContents = (folderId) => {
        $.ajax({
            url: `/folderlist`,
            method: 'GET',
            data: { folderId },
            success: function (data) {
                $('#docsList').html('');
                for (let i = 0; i < data.length; i++) {
                    $('#docsList').append(
                        "<tr><td style='text-align: center' class='checkbox'><input type='checkbox' id='check' class='document-checkbox' value=" +
                        data[i].id + "></td><td>"
                        + data[i].id + "</td><td>"
                        + data[i].title + "</td><td>" +
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
        $('#folder-create-btn').click(function () {
            let folderName = $('input[name=folder-name]').val();
            $.ajax({
                url: '/folder/create',
                type: 'POST',
                data: {name: folderName},
                success: function (data) {
                    console.log('폴더 생성 요청이 성공했습니다.');
                },
                error: function (xhr, status, error) {
                    console.log('폴더 생성 요청이 실패했습니다.');
                }
            });
            this.style.display = "none";
            location.reload();
        });


        $('p.folder-title').on('click', function () {
            const folderId = $(this).siblings('input#folderId').val();
            fetchFolderContents(folderId);
        });

        $('#delete-btn').on('click', function () {
            let documentId = $('input[type="checkbox"]:checked').val();
            $.ajax({
                url: `/docs/delete`,
                data: {documentId: documentId},
                method: 'GET',
                success: function () {
                    document.getElementById("delete-modal").style.display = "none";
                },
                error: function (request, status, error) {
                    alert("code:" + request.status + "\n"
                        + "message:" + request.responseText + "\n"
                        + "error:" + error);
                }
            });
        });

        $('#writeform').on('click', function () {
            window.location.href = '/docs/write';
        });

    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>

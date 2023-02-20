<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.example.BookHub.Docs.DocsDTO" %>
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
                    김지운
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">내 정보</a></li>
                    <li><a class="dropdown-item" href="#">내 문서</a></li>
                    <li><a class="dropdown-item" href="#">고객센터</a></li>
                    <li><a class="dropdown-item" href="#">로그아웃</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="grid">
        <div class="sidebar">
            <ul class="folder-list">
                <%
                    List<String[]> folders = new ArrayList<>();
                    folders.add(new String[]{"folder_open", "제목 없음"});
                    folders.add(new String[]{"folder", "탕짜면을 먹고나서 용사로 환생했습니다."});
                    folders.add(new String[]{"folder", "제목없음 (1)"});
                    for (String[] folder : folders) {
                %>
                <li class="folder-name">
                    <span class="material-symbols-outlined"><%= folder[0] %></span>
                    <p class="folder-title"><%= folder[1] %>
                    </p>
                    <p>
                        <span class="material-symbols-outlined edit-btn" onclick="editFolderTitle(this)">edit</span>
                        <span class="material-symbols-outlined">delete</span>
                    </p>
                </li>
                <% } %>
                <li class="new-folder"><span class="material-symbols-outlined">add</span>
                    <p class="new-folder-button">새문서</p></li>
            </ul>
        </div>
        <div class="main">
            <h2 class="folder-header"><span id="icon"
                                            class="material-symbols-outlined"><%= folders.get(0)[0] %></span><%= folders.get(0)[1]%>
            </h2>
            <div class="menuBar">
                <h5 class="menuBar-header">최근문서</h5>
                <ul class="menuBar-menu">
                    <li>
                        <button class="add-btn">새 문서</button>
                    </li>
                    <li>
                        <button class="comp-btn">비교하기</button>
                    </li>
                    <li>
                        <button id="delete-button" class="del-btn" onclick="openDeleteModal()">삭제</button>
                    </li>
                </ul>
                <div id="delete-modal" class="modal">
                    <div class="modal-content">
                        <p>선택한 문서를 삭제하시겠습니까?</p>
                        <div>
                            <button class="btn btn-danger" onclick="deleteSelectedDocuments()">삭제</button>
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
                        <th scope="col" width="160">작성일자</th>
                        <th scope="col">메모</th>
                    </tr>
                    </thead>
                    <tbody id="docsList">
                    <%
                        int pageSize = 10;
                        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                        List<DocsDTO> docs = (List<DocsDTO>) request.getAttribute("documentList");
                        int startIndex = 0;
                        int endIndex = 0;
                        if (docs != null) {
                            startIndex = (currentPage - 1) * pageSize;
                            endIndex = Math.min(startIndex + pageSize, docs.size());
                        }
                    %>
                    <% for (int i = startIndex; i < endIndex; i++) {%>
                    <tr>
                        <td style="text-align: center" class="checkbox"><input type="checkbox" class="document-checkbox" data-document-id="<%= docs.get(i).getId() %>"></td>
                        <td style="text-align: center"><%= i + 1 %>
                        </td>
                        <td><%= docs.get(i).getTitle() %>
                        </td>
                        <td><%= docs.get(i).getSaveAt() %>
                        </td>
                        <td><a href="#" id="memo" onclick="openModal(`<%= docs.get(i).getMemo() %>`)"><%= docs.get(i).getMemo() %>
                        </a>
                            <div id="modal">
                                <div id="modal-window">
                                    <div>
                                        <p id="modal-content"></p>
                                    </div>
                                    <div>
                                        <button id="close-btn" class="btn btn-outline-secondary" onclick="closeModal()">
                                            닫기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>

                <%--페이지네이션--%>
                <div class="paging">
                    <ul class="pagination">
                        <% if (currentPage > 1) { %>
                        <li class="page-item"><a class="page-link" href="<%= "?page=" + (currentPage - 1) %>">
                            <span aria-hidden="true">&laquo;</span></a></li>
                        <% } else { %>
                        <li class="page-item disabled"><a class="page-link"><span aria-hidden="true">&laquo;</span></a>
                        </li>
                        <% } %>

                        <% int startPage = Math.max(currentPage - 2, 1);
                            int endPage = Math.min(currentPage + 2, (int) Math.ceil(docs.size() / (double) pageSize));
                            for (int i = startPage; i <= endPage; i++) { %>
                        <% if (i == currentPage) { %>
                        <li class="page-item active"><a class="page-link"><%= i %>
                        </a></li>
                        <% } else { %>
                        <li class="page-item"><a class="page-link" href="<%= "?page=" + i %>"><%= i %>
                        </a></li>
                        <% } %>
                        <% } %>

                        <% if (currentPage < Math.ceil(docs.size() / (double) pageSize)) { %>
                        <li class="page-item"><a class="page-link" href="<%= "?page=" + (currentPage + 1) %>">
                            <span aria-hidden="true">&raquo;</span></a></li>
                        <% } else { %>
                        <li class="page-item disabled"><a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                                <% } %>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>

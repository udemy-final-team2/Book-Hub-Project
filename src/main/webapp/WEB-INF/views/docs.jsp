<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>북허브 - 문서</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link href="css/docs.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">

        function editFolderTitle(button) {
            const parent = button.parentElement.parentElement;
            const title = parent.querySelector('.folder-title');
            const titleText = title.innerText;
            const input = document.createElement('input');
            input.value = titleText;
            input.classList.add('folder-title-input');
            parent.replaceChild(input, title);

            input.addEventListener('keypress', function (e) {
                if (e.key === 'Enter') {
                    const newTitleText = input.value.trim();
                    if (newTitleText !== '') {
                        const newTitle = document.createElement('p');
                        newTitle.classList.add('folder-title');
                        newTitle.innerText = newTitleText;
                        parent.replaceChild(newTitle, input);
                    } else {
                        const originalTitle = document.createElement('p');
                        originalTitle.classList.add('folder-title');
                        originalTitle.innerText = titleText;
                        parent.replaceChild(originalTitle, input);
                    }
                }
            });
            input.focus(); // Set focus on the input field
        }
    </script>
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
            <div>
                <input id="search" class="searchin" type="text" placeholder="문서 검색하기...">
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
                        <button class="button">새 문서</button>
                    </li>
                    <li>
                        <button class="button">비교하기</button>
                    </li>
                    <li>
                        <button class="button">삭제</button>
                    </li>
                </ul>
            </div>
            <div class="tableStyle">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col" style="text-align: center">번호</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성일자</th>
                        <th scope="col">메모</th>
                        <th scope="col" style="text-align: center">선택</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int pageSize = 10;
                        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

                        List<String[]> docs = new ArrayList<>();
                        docs.add(new String[]{"1", "탕짜면을 먹고나서 용사로 환생했습니다.", "2023-02-12 09:12", "내가 생각해도 천재가 아닐까"});
                        docs.add(new String[]{"2", "탕짜면을 먹고나서 용사로 환생했습니다.(1)", "2023-02-17 09:12", "창작의 고통이란.."});
                        docs.add(new String[]{"3", "탕짜면을 먹고나서 용사로 환생했습니다.(2)", "2023-02-18 09:12", "짬짜면을 먹었다면..."});
                        docs.add(new String[]{"4", "내 아이폰이 이렇게 귀여울리 없어.", "2023-02-19 09:12", "맥북으로 제목을 바꿀까?"});
                        docs.add(new String[]{"5", "내 아이폰이 이렇게 귀여울리 없어.(1)", "2023-02-20 10:12", "갤럭시로 쓸걸 그랬나.."});
                        docs.add(new String[]{"6", "탕짜면을 먹고나서 용사로 환생했습니다.(3)", "2023-02-22 04:12", "다음이면 마지막회네.."});
                        docs.add(new String[]{"7", "탕짜면을 먹고나서 용사로 환생했습니다_프리뷰", "2023-02-27 15:20", "부자가 될 수 있을까."});
                        docs.add(new String[]{"8", "탕짜면을 먹고나서 용사로 환생했습니다_완", "2023-03-01 18:12", "완결 가즈아"});
                        docs.add(new String[]{"9", "탕짜면을 시켰는데, 짬짜면이 나온것에 대하여(가제)_외전", "2023-03-03 09:12", "외전 가보자!!"});
                        docs.add(new String[]{"10", "탕짜면을 먹고나서 용사로 환생했습니다_프리뷰", "2023-02-27 15:20", "부자가 될 수 있을까."});
                        docs.add(new String[]{"11", "탕짜면을 먹고나서 용사로 환생했습니다_완", "2023-03-01 18:12", "완결 가즈아"});
                        docs.add(new String[]{"12", "탕짜면을 시켰는데, 짬짜면이 나온것에 대하여(가제)_외전", "2023-03-03 09:12", "외전 가보자!!"});
                        int startIndex = (currentPage - 1) * pageSize;
                        int endIndex = Math.min(startIndex + pageSize, docs.size());

                        Collections.sort(docs, new Comparator<String[]>() {
                            @Override
                            public int compare(String[] o1, String[] o2) {
                                return o1[2].compareTo(o2[2]);
                            }
                        });
                        Collections.reverse(docs);
                    %>
                    <% for (int i = startIndex; i < endIndex; i++) { %>
                    <tr>
                        <td style="text-align: center"><%= docs.get(i)[0] %>
                        </td>
                        <td><%= docs.get(i)[1] %>
                        </td>
                        <td><%= docs.get(i)[2] %>
                        </td>
                        <td><%= docs.get(i)[3] %>
                        </td>
                        <td style="text-align: center"><input type="checkbox"></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
                <div class="paging">
                    <ul class="pagination">
                        <% if (currentPage > 1) { %>
                        <li class="page-item"><a class="page-link" href="<%= "?page=" + (currentPage - 1) %>">
                            <span aria-hidden="true">&laquo;</span></a></li>
                        <% } else { %>
                        <li class="page-item disabled"><a class="page-link"><span aria-hidden="true">&laquo;</span></a></li>
                        <% } %>

                        <% int startPage = Math.max(currentPage - 2, 1);
                            int endPage = Math.min(currentPage + 2, (int) Math.ceil(docs.size() / (double) pageSize));
                            for (int i = startPage; i <= endPage; i++) { %>
                        <% if (i == currentPage) { %>
                        <li class="page-item active"><a class="page-link"><%= i %></a></li>
                        <% } else { %>
                        <li class="page-item"><a class="page-link" href="<%= "?page=" + i %>"><%= i %></a></li>
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

<%@ page import="com.amazonaws.services.s3.AmazonS3" %>
<%@ page import="com.amazonaws.services.s3.model.S3Object" %>
<%@ page import="com.amazonaws.services.s3.AmazonS3ClientBuilder" %>
<%@ page import="com.amazonaws.auth.AWSStaticCredentialsProvider" %>
<%@ page import="com.amazonaws.auth.BasicAWSCredentials" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.stream.Collectors" %>
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
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <script src="https://cdn.tiny.cloud/1/l4hh05dskpwmkxxbt2c4q7ilmuby0zfysfofsdaujbvuyjr1/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/App.css" rel="stylesheet" type="text/css">
    <link href="/css/compareview.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/diff_match_patch.js"></script>
</head>
<body>
<div class="App">
    <div class="Appbar">
        <div class="right">
            <div class="brandName">
                <a href="/" class="link"><img class="brandlogo" src="/img/logo.png" alt="로고"/><span class="logoName">문서비교</span></a>
            </div>
        </div>
        <div class="left">
            <div class="dropdown">
                <button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    황영환
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
    <br>
    <%
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion("ca-central-1")
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("AKIA2JW73SBWSFCIJUUA", "vF1U1RG4bdPx3wRz8yAt9rYsf/KAeQW2WGP9gTCt")))
                .build();
        S3Object s3Object1 = s3.getObject("team2-s3-bucket", "3db63783-4e7a-4c1f-b6e0-43dc84b162c4_myDocument (1).html");
        String htmlContent1 = new BufferedReader(
                new InputStreamReader(s3Object1.getObjectContent(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        S3Object s3Object2 = s3.getObject("team2-s3-bucket", "28ee8de7-a415-48fb-b888-6c92ac4f8431_myDocument (2).html");
        String htmlContent2 = new BufferedReader(
                new InputStreamReader(s3Object2.getObjectContent(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    %>
    <script>
        window.onload = function () {
            const htmlContent1 = `<%= htmlContent1 %>`;
            const htmlContent2 = `<%= htmlContent2 %>`;
            const dmp = new diff_match_patch();
            const diff = dmp.diff_main(htmlContent1, htmlContent2);
            dmp.diff_cleanupSemantic(diff);
            for (let i = 0; i < diff.length; i++) {
                const operation = diff[i][0];
                switch (operation) {
                    case 0: // no change
                        break;

                    case -1: // deletion
                        const deletedText = diff[i][1];
                        console.log("삭제된 텍스트")
                        console.log(deletedText);
                        const el1 = document.getElementById("documentBeforeChange").querySelectorAll('*');
                        const el2 = document.getElementById("documentAfterChange").querySelectorAll('*');
                        for (let j = 0; j < el1.length; j++) {
                            console.log(deletedText);
                            console.log(el1);
                            if (el1[j].innerText.includes(deletedText)) {
                                el1[j].style.backgroundColor = "red";
                            }
                        }
                        for (let j = 0; j < el2.length; j++) {
                             if(el2[j].innerHTML === deletedText) {
                                el2[j].style.backgroundColor = "red";
                            }
                        }
                        break;

                    case 1: // insertion
                        const insertedText = diff[i][1];
                        console.log("삽입된 텍스트")
                        console.log(insertedText);
                        const el3 = document.getElementById("documentAfterChange").querySelectorAll('*');
                        for (let j = 0; j < el3.length; j++) {
                            if (el3[j].outerHTML === insertedText) {
                                el3[j].style.backgroundColor = "green";
                            }
                        }
                        break;
                }
            }
        };
    </script>
    <div class="documentContainer">
        <div id="documentBeforeChange"><%= htmlContent1%>
        </div>
        <div id="documentAfterChange"><%= htmlContent2%>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>文件上传错误</title>
    <style>
        .error-container { max-width: 600px; margin: 50px auto; padding: 20px; }
        .error-box { border: 1px solid #e74c3c; background: #fdeded; padding: 20px; }
        h1 { color: #e74c3c; }
        .btn { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #3498db; color: white; text-decoration: none; }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-box">
            <h1>文件上传失败</h1>
            <p th:text="${error}">错误原因</p>
            <p th:text="${detail}">错误详情</p>

            <a href="/" class="btn">返回首页</a>
            <a href="javascript:history.back()" class="btn">返回上一页</a>
        </div>
    </div>
</body>
</html>

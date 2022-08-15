
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body style="text-align:center;">
<form action="register" method="post">
    姓名:<input type="text" name="name">
    <input type="submit" name="注册">
</form>
</body>
</html>

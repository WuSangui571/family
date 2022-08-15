<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>修改姓名</title>
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body style="text-align:center;">
<form action="member/update" method="post">
  原姓名<input name="name" type="text" readonly value=${param.name}>
  新姓名<input name="newName" type="text">
  <input type="submit" value="修改">
</form>
</body>
</html>
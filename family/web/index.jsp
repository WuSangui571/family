<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>family</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
  </head>
  <body style="text-align:center;">
  <form method="post" action="login">
    姓名:<input type="text" name="name"><br>
    <input type="submit" value="登录">
    <input type="checkbox" name="f" value="1">三天内免登录
  </form>
  <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
  </body>
</html>

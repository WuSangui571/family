<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script>
        function del(name){
            if (window.confirm("是否删除" + name + "？")){
                document.location.href="<%=request.getContextPath()%>/member/delete?name=" + name
            }
        }
        function end(){
            if (window.confirm("确认注销？")){
                document.location.href="<%=request.getContextPath()%>/exit"
            }
        }
    </script>
</head>
<body style="text-align:center;">
在线人数${onlineCount}人
<h3>欢迎${userName.name}！</h3>
<table border="1px" align="center">
    <tr>
        <td><h3>姓名</h3></td>
        <td><h3>操作</h3></td>
    </tr>
    <c:forEach items="${families}" var="f">
        <tr>
            <td><h3>${f.name}</h3></td>
            <td>
                <a href="javascript:void(0)" onclick="del('${f.name}')">删除</a>
                <a href="${pageContext.request.contextPath}/update.jsp?name=${f.name}">修改姓名</a>
                <a href="${pageContext.request.contextPath}/detail.jsp?name=${f.name}">详情</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="bill/list">展示消费</a>
<a href="javascript:void(0)"  onclick="end()">注销</a>
</body>
</html>

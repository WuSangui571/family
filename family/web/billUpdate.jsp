<%@ page import="com.haoze.javaweb.bean.Family" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帐单修改</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body style="text-align:center;">
<h3>帐单修改</h3>
<h3>欢迎${userName.name}！</h3>

<form action="bill/update" method="post">
  帐单编号：<input type="text" name="billID" readonly value=${param.billID}>
  帐单日期：<input type="date" name="billDate" value=${param.billDate}>
  消费类型：
  <select name="consumptionType" >
    <option value="物质文化消费" <%="物质文化消费".equals(request.getParameter("consumptionType"))?"selected":""%>>物质文化消费</option>
    <option value="精神文化消费" <%="精神文化消费".equals(request.getParameter("consumptionType"))?"selected":""%>>精神文化消费</option>
    <option value="劳务消费" <%="劳务消费".equals(request.getParameter("consumptionType"))?"selected":""%>>劳务消费</option>
  </select>
  消费金额：<input type="text" name="consumptionAmount" value="${param.consumptionAmount}">
  消费人：<input type="text" name="consumer" value=${param.consumer} readonly>
  <input type="submit" value="修改">
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消费展示</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script>
        function del(id){
            if (window.confirm("是否删除" + id + "号订单？")){
                document.location.href="${pageContext.request.contextPath}/bill/delete?billID=" + id
            }
        }
        function end(){
            if (window.confirm("确认注销？")){
                document.location.href="${pageContext.request.contextPath}/exit"
            }
        }
    </script>
</head>
<body style="text-align:center;">
<h3>欢迎${userName.name}！</h3>
<a href="javascript:void(0)"  onclick="end()">注销</a>
<a href="member">返回成员界面</a>
<hr>
<h3>录入帐单（消费人一经确认不可修改）</h3>
<form method="post" action="bill/insert">
    帐单日期：<input type="date" name="billDate">
    ，消费类型：
    <select name="consumptionType" >
        <option value="物质文化消费">物质文化消费</option>
        <option value="精神文化消费">精神文化消费</option>
        <option value="劳务消费">劳务消费</option>
    </select>
    ，消费金额：<input name="consumptionAmount" type="text">
    ，消费人：
    <select name="consumer">
        <option value="整个家庭">整个家庭</option>
        <c:forEach items="${families}" var="f">
            <option value="${f.name}">${f.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="录入">
</form>
<hr>
<h3>查找帐单</h3>
<form method="post" action="bill/search">
    按照
    <select name="year">
        <option value="2022">2022</option>
        <option value="2023">2023</option>
        <option value="2024">2024</option>
        <option value="2025">2025</option>
        <option value="2026">2026</option>
        <option value="2027">2027</option>
        <option value="2028">2028</option>
        <option value="2029">2029</option>
        <option value="2030">2030</option>
        <option value="2031">2031</option>
        <option value="2032">2032</option>
        <option value="2033">2033</option>
        <option value="2034">2034</option>
        <option value="2035">2035</option>
        <option value="2036">2036</option>
        <option value="2037">2037</option>
        <option value="2038">2038</option>
        <option value="2039">2039</option>
        <option value="2040">2040</option>
        <option value="2041">2041</option>
        <option value="2042">2042</option>
        <option value="2043">2043</option>
        <option value="2044">2044</option>
        <option value="2045">2045</option>
        <option value="2046">2046</option>
        <option value="2047">2047</option>
        <option value="2048">2048</option>
        <option value="2049">2049</option>
        <option value="2050">2050</option>
        <option value="2051">2051</option>
    </select>
    <select name="month">
        <option value="whole">整年</option>
        <option value="01">一月</option>
        <option value="02">二月</option>
        <option value="03">三月</option>
        <option value="04">四月</option>
        <option value="05">五月</option>
        <option value="06">六月</option>
        <option value="07">七月</option>
        <option value="08">八月</option>
        <option value="09">九月</option>
        <option value="10">十月</option>
        <option value="11">十一月</option>
        <option value="12">十二月</option>
    </select>
    为单位，统计
    <select name="consumer">
        <option value="whole">整个家庭</option>
        <c:forEach items="${families}" var="f">
            <option value="${f.name}">${f.name}</option>
        </c:forEach>

    </select>
    的
    <select name="consumptionType">
        <option value="whole">全部类型</option>
        <option value="物质文化消费">物质文化消费</option>
        <option value="精神文化消费">精神文化消费</option>
        <option value="劳务消费">劳务消费</option>
    </select>
    的消费
    <input type="submit" value="查找">
</form>
<hr>
<table border="1px" align="center">
    <tr>
        <td><h3>帐单编号</h3></td>
        <td><h3>账单日期</h3></td>
        <td><h3>消费类型</h3></td>
        <td><h3>消费金额</h3></td>
        <td><h3>消费人</h3></td>
        <td><h3>操作</h3></td>
    </tr>
    <c:set var="count" value="0.0f"></c:set>
    <c:set var="number" value="0"></c:set>
    <c:forEach items="${bills}" var="bill">
        <c:set var="count" value="${count + bill.consumptionAmount}"></c:set>
        <c:set var="number" value="${number + 1}"></c:set>
        <tr>
            <td><h3>${bill.billID}</h3></td>
            <td><h3>${bill.billDate}</h3></td>
            <td><h3>${bill.consumptionType}</h3></td>
            <td><h3>${bill.consumptionAmount}</h3></td>
            <td><h3>${bill.consumer}</h3></td>
            <td>
                <a href="billUpdate.jsp?billID=${bill.billID}&billDate=${bill.billDate}&consumptionType=${bill.consumptionType}&consumptionAmount=${bill.consumptionAmount}&consumer=${bill.consumer}">修改</a>
                <a href="javascript:void(0)" onclick="del('${bill.billID}')">删除</a>
                <a href="billDetail.jsp?billID=${bill.billID}&billDate=${bill.billDate}&consumptionType=${bill.consumptionType}&consumptionAmount=${bill.consumptionAmount}&consumer=${bill.consumer}">详情</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h3>总金额：<fmt:formatNumber value="${count}" maxFractionDigits="2" minFractionDigits="2"/></h3>
<h3>总订单数：${number}</h3>
</body>
</html>

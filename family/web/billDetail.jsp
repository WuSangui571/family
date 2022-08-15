<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帐单详情</title>
</head>
<body style="text-align:center;">
  <h3>物质文化消费：</h3>主要是指吃、穿住、行的消费。<br>
  <h3>精神文化消费：</h3>主要是娱乐身心，发展提高自身的各种消费。<br>
  <h3>劳务消费：</h3>是家庭花钱购买的各种服务。<br>
  <hr>
  帐单编号<input name="billID" type="text" value=${param.billID} readonly>
  帐单日期<input name="billDate" type="date" value=${param.billDate} readonly>
  消费类型<input name="consumptionType" type="text" value=${param.consumptionType} readonly>
  消费金额<input name="consumptionAmount" type="text" value=${param.consumptionAmount} readonly>
  消费人<input name="consumer" type="text" value=${param.consumer} readonly>
</body>
</html>

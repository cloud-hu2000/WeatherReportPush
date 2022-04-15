<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action="/addUser" method="post">
    <h1>天气预报订阅</h1>
    请输入您的电话号码：<input name="phone">(示例:13888123456)<br>
    省：<input name="province" />(示例:广东)<br>
    市：<input name="city" >(示例:广州)<br>
    <input type="submit" value="提交">
</form>
</body>
</html>
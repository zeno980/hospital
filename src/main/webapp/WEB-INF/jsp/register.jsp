<%--
  Created by IntelliJ IDEA.
  User: Zakary
  Date: 2019/3/21
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ pageDao contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<table>
    <tr>
        <td><label>用户名</label></td>
        <td><input type="text" id="username" name="username"></td>
    </tr>
    <tr>
        <td><label>密码</label></td>
        <td><input type="text" id="password" name="password"></td>
    </tr>
    <tr>
        <select>
            性别：
            <option value ="man">男</option>
            <option value ="woman">女</option>
        </select>
    </tr>
    <tr>
        <td><label>职位</label></td>
        <td><input type="text" id="position" name="position"></td>
    </tr>
    <tr>
        <td><label>部门</label></td>
        <td><input type="text" id="dapartment" name="dapartment"></td>
    </tr>
    <tr>
        <td><label>电话</label></td>
        <td><input type="text" id="tel" name="tel"></td>
    </tr>
</table>
</body>
</html>

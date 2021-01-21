<%--
  Created by IntelliJ IDEA.
  User: Zakary
  Date: 2019/3/21
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/doctorRegister.do" method="post" autocomplete="off">
<table>
    <tr>
        <td><label>账号</label></td>
        <td><input type="text" id="doctorid" name="doctorid"></td>
    </tr>
    <tr>
        <td><label>性名</label></td>
        <td><input type="text" id="doctorname" name="doctorname"></td>
    </tr>
    <tr>
        <td><label>密码</label></td>
        <td><input type="text" id="password" name="password"></td>
    </tr>
    <tr>
        <td><label> 性别</label></td>
        <td><select name="doctorgender">
            性别
            <option value ="男">男</option>
            <option value ="女">女</option>
        </select></td>
    </tr>
    <tr>
        <td><label> 职位</label></td>
        <td><select name="doctorposition">
            职位
            <option value ="主任医师">主任医师</option>
            <option value ="副主任医师">副主任医师</option>
            <option value ="主治医师">主治医师</option>
            <option value ="住院医师">住院医师</option>
        </select></td>
    </tr>
    <tr>
        <td><label> 部门</label></td>
        <td><select name="doctordepartment">
            部门
            <option value ="门诊部">门诊部</option>
            <option value ="住院部">住院部</option>
            <option value ="急诊部">急诊部</option>
            <option value ="药房">药房</option>
        </select></td>
    </tr>
    <tr>
        <td><label>电话</label></td>
        <td><input type="number" placeholder="请输入您的11位手机号" oninput="if(value<0)value=0" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11" id="doctortel" name="doctortel"></td>
    </tr>
    <tr>
        <td><input type="hidden"  id="type" value="1" name="type"></td>
    </tr>
    <tr>
        <td>
    <input type="submit" value="提交" style="width:100px;">
        </td>
    </tr>
</table>
</form>
</body>
</html>

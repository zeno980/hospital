<%--
  Created by IntelliJ IDEA.
  User: Zakary
  Date: 2019/3/27
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录测试html</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<form name="user">
    <label>用户名</label>
    <input type=number id="name"/><br>
    <label>密码</label>
    <input type="password" id="pwd"/><br>
    <button onclick="sub()" type="button">登录</button>
</form>
<script type="text/javascript"><!--检查用户名 密码-->
function sub() {
    var username = document.getElementById("name").value;
    var password = document.getElementById("pwd").value;
    $.ajax({
        type:"POST",
        url:"/hospital/login.do",
        contentType:"application/json",
        dataType:"json",
        data:JSON.stringify({
            "doctorid":username,
            "password":password
        }),
        success:function (data) {
            if(data.code=="root"){
                window.location.href="/hospital/rootSelectPage"
            }
            else if(data.code=="doctor"){
                window.location.href="/hospital/doctorManagerPage"
            }
            else{
                alert(data.data)
            }
        }
    });
    // alert("用户名: " + username + " " + "密码: " + password);
}
</script>
</body>
</html>

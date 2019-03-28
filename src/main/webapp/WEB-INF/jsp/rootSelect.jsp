<%--
  Created by IntelliJ IDEA.
  User: Zakary
  Date: 2019/3/27
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<h2>欢迎登录住院管理系统！</h2>
<br/>
<button onclick="doc()" type="button">医生管理系统</button>
<button onclick="pat()" type="button">患者管理系统</button>
<script type="text/javascript">
    var user;
    function doc(){
        user="doctor";
        $.ajax({
            type: "POST",
            url: "/hospital/rootSelect",
            contentType: "application/x-www-form-urlencoded",

        data:{
                "user": user
            },
            success: function () {
                window.location.href = "/hospital/rootDoctor"
            }
        })
    }
    function pat(){
        user="patient";
        $.ajax({
            type: "POST",
            url: "/hospital/rootSelect",
            contentType: "application/x-www-form-urlencoded",
            data:{
                "user": user
            },
            success: function () {
                window.location.href = "/hospital/rootPatient"
            }
        })
    }

</script>
</body>
</html>

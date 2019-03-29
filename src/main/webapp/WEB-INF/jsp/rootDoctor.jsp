<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zakary
  Date: 2019/3/27
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RootDoctor</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <style>/* 定义模态对话框外面的覆盖层样式 */
    #modal-insert {
        visibility: hidden;
        position: absolute;   /* 使用绝对定位或固定定位  */
        left: 0;
        top: 0;
        width:100%;
        height:100%;
        text-align:center;
        z-index: 1000;
        background-color: #333;
        opacity: 1;   /* 背景半透明 */
    }
    /* 模态框样式 */
    .modal-data{
        width:300px;
        margin: 100px auto;
        background-color: #fff;
        border:1px solid #000;
        padding:15px;
        text-align:center;
    }
    </style>

</head>
<body>
    <br/>
    <table border="1">
           <tr>
               <th>DoctorId</th>
               <th>DoctorName</th>
               <th>DoctorDepartment</th>
               <th>DoctorPosition</th>
               <th>DoctorGender</th>
               <th>DoctorTel</th>
           </tr>
    <c:forEach items="${requestScope.doctors}" var="Doctor">
        <tr>
            <td>${Doctor.doctorid}</td>
            <td>${Doctor.doctorname}</td>
            <td>${Doctor.doctordepartment}</td>
            <td>${Doctor.doctorposition}</td>
            <td>${Doctor.doctorgender}</td>
            <td>${Doctor.doctortel}</td>
            <td><button onclick="updateinformation(Doctor.doctorid)" type="button">修改</button></td>
            <td><button onclick="deletedocotr(Doctor.doctorid)" type="button">删除</button></td>
        </tr>
    </c:forEach>
    </table>
    <!--
    <form>
        <button onclick="insertdoctor()" type="button">添加</button>
    </form>
    -->
<!--模态框-->
    <div id="modal-insert" style="visibility: hidden;">
        <div class="modal-data">
            <p>添加医生信息 </p>
            <label>编号</label>
            <input type=number id="id"/><br>
            <label>姓名</label>
            <input type=text id="name"/><br>
            <label>部门</label>
            <input type=text id="department"/><br>
            <label>职位</label>
            <input type=text id="position"/><br>
            <label>性别</label>
            <input type=text id="gender"/><br>
            <label>电话</label>
            <input type=text id="tel"/><br>
            <label>密码</label>
            <input type="password" id="pwd"/><br>
            <p><a onclick="insertdoctor()" href="">提交</a></p>
        </div>
    </div>
    <a href="#" onclick="insertdoctor()">添加</a>
    <script>
        function insertdoctor(){

            var e1 = document.getElementById('modal-insert');
            e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";

            var doctorid = document.getElementById("id").value;
            var doctorname=document.getElementById("name").value;
            var department=document.getElementById("department").value;
            var position=document.getElementById("position").value;
            var gender=document.getElementById("gender").value;
            var tel=document.getElementById("tel").value;
            var pwd=document.getElementById("pwd").value;

            $.ajax({
                type: "POST",
                url: "/hospital/RootInsertDoctor",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    "doctorid": doctorid,
                    "doctorname": doctorname,
                    "doctordepartment": department,
                    "doctorposition": position,
                    "doctorgender": gender,
                    "doctortel": tel,
                    "password": pwd
                }),
                success: function (data) {
                    if (data.code == "success") {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor";
                    } else if (data.code == "alreadyExist") {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor#";
                    } else {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor#";
                    }
                }
            })
        };
    </script>
<!---->
</body>
</html>

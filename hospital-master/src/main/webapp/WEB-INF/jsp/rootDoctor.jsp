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
    #modal-update{
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
    #model-delete{
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

            <td><button onclick="popupdate(${Doctor.doctorid})" type="button">修改</button></td>

            <td><button onclick="popdelete(${Doctor.doctorid})" type="button">删除</button></td>

        </tr>
    </c:forEach>
    </table>

    <form>
        <button onclick="popinsert()" type="button">添加</button>
    </form>

<!--插入模态框-->
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
    <!--
    <a href="#" onclick="insertdoctor()">添加</a>
    -->
    <!--修改模态框-->
    <div id="modal-update" style="visibility: hidden;">
        <div class="modal-data">
            <p>修改医生信息 </p>
            <!--
            <label>编号</label>
            <input type=text value="${Doctor.doctorid}"/><br>
            -->
            <label>姓名</label>
            <input type=text id="uname"/><br>
            <label>部门</label>
            <input type=text id="udepartment"/><br>
            <label>职位</label>
            <input type=text id="uposition"/><br>
            <label>性别</label>
            <input type=text id="ugender"/><br>
            <label>电话</label>
            <input type=text id="utel"/><br>
            <label>密码</label>
            <input type="password" id="upwd"/><br>
            <input id="hid" type="hidden"/>

            <td><button onclick="updateinfomation()" type="button">提交</button></td>
        </div>
    </div>

    <!--删除模态框-->
    <div id="modal-delete" style="visibility: hidden;">
        <div class="modal-data">
            <p>确定删除此用户？ </p>
            <!--
            <label>编号</label>
            <input type=text value="${Doctor.doctorid}"/><br>
            -->
            <input id="did" type="hidden"/>

            <td>
                <button onclick="deletedoctor()" type="button">确定</button>
                <button onclick="" type="button">取消</button>
            </td>
        </div>
    </div>


    <script>
        //插入信息
        function popinsert() {
            var e1 = document.getElementById('modal-insert');
            e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";
        }
        function insertdoctor(){

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
        function popupdate(doctorid) {
            var e1 = document.getElementById('modal-update');
            e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";
            document.getElementById("hid").value=doctorid;
            console.log(doctorid);
        }
        function updateinfomation() {
            //console.log(e.parentNode.parentNode.childNodes)
            //console.log(hid.value);
            var doctorid = document.getElementById("hid").value;
            var udoctorname = document.getElementById("uname").value;
            var udepartment = document.getElementById("udepartment").value;
            var uposition = document.getElementById("uposition").value;
            var ugender = document.getElementById("ugender").value;
            var utel = document.getElementById("utel").value;
            var upwd = document.getElementById("upwd").value;
            console.log(uname);
            console.log(udepartment);
            console.log(uposition);
            console.log(ugender);
            $.ajax({
                type: "POST",
                url: "/hospital/RootUpdateDoctor",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    "doctorid": doctorid,
                    "doctorname": udoctorname,
                    "doctordepartment": udepartment,
                    "doctorposition": uposition,
                    "doctorgender": ugender,
                    "doctortel": utel,
                    "password": upwd
                }),
                success: function (data) {
                    if (data.code == "success") {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor";
                    } else {
                        alert(data.data);
                    }
                }
            })
        }
        function popdelete(doctorid) {
            var e1 = document.getElementById('modal-delete');
            e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";
            document.getElementById("did").value=doctorid;
            console.log(doctorid);
        }
        function deletedoctor() {
            var doctorid=document.getElementById("did").value;
            console.log(doctorid);
            $.ajax({
                type: "POST",
                url: "/hospital/RootDeleteDoctor",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    "doctorid": doctorid
                }),
                success: function (data) {
                    if (data.code == "success") {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor";
                    } else {
                        alert(data.data);
                        window.location.href = "/hospital/rootDoctor";
                    }
                }
            })
        }
    </script>
<!---->
</body>
</html>

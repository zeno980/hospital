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
    <title>RootPatient</title>
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
    #modal-delete{
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
        <th>PatientId</th>
        <th>PatientName</th>
        <th>PatientAge</th>
        <th>PatientGender</th>
        <th>PatientTel</th>
    </tr>
    <c:forEach items="${requestScope.patients}" var="Patient">
        <tr>
            <td>${Patient.patientid}</td>
            <td>${Patient.patientname}</td>
            <td>${Patient.patientage}</td>
            <td>${Patient.patientgender}</td>
            <td>${Patient.patienttel}</td>

            <td><button onclick="popupdate(${Patient.patientid})" type="button">修改</button></td>

            <td><button onclick="popdelete(${Patient.patientid})" type="button">删除</button></td>

        </tr>
    </c:forEach>
</table>

<form>
    <button onclick="popinsert()" type="button">添加</button>
</form>

<!--插入模态框-->
<div id="modal-insert" style="visibility: hidden;">
    <div class="modal-data">
        <p>添加患者信息 </p>
        <label>编号</label>
        <input type=number id="id"/><br>
        <label>姓名</label>
        <input type=text id="name"/><br>
        <label>年龄</label>
        <input type=text id="age"/><br>
        <label>性别</label>
        <input type=text id="gender"/><br>
        <label>电话</label>
        <input type=text id="tel"/><br>

        <p><a onclick="insertpatient()" href="">提交</a></p>
    </div>
</div>
<!--
<a href="#" onclick="insertdoctor()">添加</a>
-->
<!--修改模态框-->
<div id="modal-update" style="visibility: hidden;">
    <div class="modal-data">
        <p>修改患者信息 </p>
        <!--
        <label>编号</label>
        <input type=text value="doctorid"/><br>
        -->
        <label>姓名</label>
        <input type=text id="uname"/><br>
        <label>年龄</label>
        <input type=text id="uage"/><br>
        <label>性别</label>
        <input type=text id="ugender"/><br>
        <label>电话</label>
        <input type=text id="utel"/><br>
        <input id="hid" type="hidden"/>

        <td><button onclick="updateinfomation()" type="button">提交</button></td>
    </div>
</div>

<!--删除模态框-->
<div id="modal-delete" style="visibility: hidden;">
    <div class="modal-data">
        <p>确定删除此患者？ </p>
        <!--
            <label>编号</label>
            <input type=text value="${Doctor.doctorid}"/><br>
            -->
        <input id="did" type="hidden"/>

        <td>
            <button onclick="deletepatient()" type="button">确定</button>
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
    function insertpatient(){

        var patientid = document.getElementById("id").value;
        var patientname=document.getElementById("name").value;
        var age=document.getElementById("age").value;
        var gender=document.getElementById("gender").value;
        var tel=document.getElementById("tel").value;

        $.ajax({
            type: "POST",
            url: "/hospital/RootInsertPatient",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                "patientid": patientid,
                "patientname": patientname,
                "patientage": age,
                "patientgender": gender,
                "patienttel": tel
            }),
            success: function (data) {
                if (data.code == "success") {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient";
                } else if (data.code == "alreadyExist") {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient#";
                } else {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient#";
                }
            }
        })
    };
    function popupdate(patientid) {
        var e1 = document.getElementById('modal-update');
        e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";
        document.getElementById("hid").value=patientid;
        console.log(patientid);
    }
    function updateinfomation() {
        //console.log(e.parentNode.parentNode.childNodes)
        //console.log(hid.value);
        var patientid = document.getElementById("hid").value;
        var upatientname = document.getElementById("uname").value;
        var uage = document.getElementById("uage").value;
        var ugender = document.getElementById("ugender").value;
        var utel = document.getElementById("utel").value;
        console.log(uname);
        console.log(uage);
        console.log(ugender);
        $.ajax({
            type: "POST",
            url: "/hospital/RootUpdatePatient",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                "patientid": patientid,
                "patientname": upatientname,
                "patientage": uage,
                "patientgender": ugender,
                "patienttel": utel,
            }),
            success: function (data) {
                if (data.code == "success") {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient";
                } else {
                    alert(data.data);
                }
            }
        })
    }
    function popdelete(patientid) {
        var e1 = document.getElementById('modal-delete');
        e1.style.visibility = (e1.style.visibility == "visible")? "hidden" : "visible";
        document.getElementById("did").value=patientid;
        console.log(patientid);
    }
    function deletepatient() {
        var patientid=document.getElementById("did").value;
        console.log(patientid);
        $.ajax({
            type: "POST",
            url: "/hospital/RootDeletePatient",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                "patientid": patientid
            }),
            success: function (data) {
                if (data.code == "success") {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient";
                } else {
                    alert(data.data);
                    window.location.href = "/hospital/rootPatient";
                }
            }
        })
    }
</script>
<!---->
</body>
</html>
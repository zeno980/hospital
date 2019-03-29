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
    <form>
        <button onclick="insertdoctor()" type="button">添加</button>
    </form>
<script>

</script>
</body>
</html>

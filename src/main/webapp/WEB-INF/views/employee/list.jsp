<%--
  Created by IntelliJ IDEA.
  User: mr697
  Date: 4/4/2023
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Employees</title>
</head>
<body class="container">
<h1>Employees</h1>
<input type="button" value="Add Employee" onclick="window.location.href='create';return false;">
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Type</th>
        <th>Join Date</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">

        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.name}</td>
            <td>${employee.employeeType.toString()}</td>
            <td>${employee.joiningDate.toString()}</td>
            <td><a href="/employee/update/${employee.employeeId}">Update</a> | <a
                    href="/employee/delete/${employee.employeeId}">Delete</a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</body>
</html>

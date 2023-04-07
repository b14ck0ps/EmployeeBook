<%--
  Created by IntelliJ IDEA.
  User: mr697
  Date: 4/7/2023
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Info</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <h1 class="display-4">Employee Information:</h1>
            <div class="card">
                <div class="card-body">
                    <p class="card-text"><strong>Employee ID:</strong> ${employee.employeeId}</p>
                    <p class="card-text"><strong>Name:</strong> ${employee.name}</p>
                    <p class="card-text"><strong>Type:</strong> ${employee.employeeType}</p>
                    <p class="card-text"><strong>Join Date:</strong> ${employee.joiningDate}</p>
                </div>
            </div>
            <br>
            <h2 class="display-5">Leave Information:</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Type</th>
                    <th>Days</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employee_leave}" var="leave">
                    <tr>
                        <td>${leave.leaveType}</td>
                        <td>${leave.numberOfDays}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/employees/list">back</a>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Azran
  Date: 4/4/2023
  Time: 10:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Create Employee</title>
</head>
<body class="container">

<h1>Create Employee</h1>

<form:form action="store" modelAttribute="employee" class="row g-3">

    <div class="col-md-6">
        <label for="name" class="form-label">Name:</label>
        <form:input path="name" id="name" class="form-control"/>
        <form:errors path="name" class="invalid-feedback"/>
    </div>
    <div class="col-md-6">
        <label for="employeeType" class="form-label">Employee Type:</label>
        <form:select path="employeeType" id="employeeType" class="form-select">
            <form:options items="${['STUFF', 'NON_STUFF']}"/>
        </form:select>
        <form:errors path="employeeType" class="invalid-feedback"/>
    </div>

    <div class="col-12">
        <input type="submit" class="btn btn-primary">
    </div>

</form:form>

</body>
</html>

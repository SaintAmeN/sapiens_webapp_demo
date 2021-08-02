<%--
  Created by IntelliJ IDEA.
  User: AmeN
  Date: 02.08.2021
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Student details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<h2>Details of student with id <c:out value="${student.id}"/></h2>
<hr>
<table>
    <tr>
        <td>Name:</td>
        <td><c:out value="${student.firstName}"/></td>
    </tr>
    <tr>
        <td>Surname:</td>
        <td><c:out value="${student.lastName}"/></td>
    </tr>
    <tr>
        <td>Birth date:</td>
        <td><c:out value="${student.birthDate}"/></td>
    </tr>
</table>
<hr>
<h3>
    Grades <a href="<c:out value="${pageContext.request.contextPath}"/>/grade/form?student_id=<c:out value="${student.id}"/>">+</a>
</h3>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Grade</th>
        <th>Subject</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${student.grades}">
        <tr>
            <td><c:out value="${grade.id}"></c:out></td>
            <td><c:out value="${grade.gradeValue}"></c:out></td>
            <td><c:out value="${grade.subject}"></c:out></td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

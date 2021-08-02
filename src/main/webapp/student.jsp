<%--
  Created by IntelliJ IDEA.
  User: AmeN
  Date: 29.07.2021
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%-- USUNĄLEM: Linia nizej--%>
<%--<%@ page isELIgnored="false" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<h2>Hello student</h2>
<div>
    <table>
        <thead>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Index number</th>
        <th>Birth date</th>
        <th></th>
        <th></th>
        </thead>
        <c:forEach var="element" items="${studentList}">
            <tr>
                <td><c:out value="${element.id}"/></td>
                <td><c:out value="${element.firstName}"/></td>
                <td><c:out value="${element.lastName}"/></td>
                <td><c:out value="${element.indeks}"/></td>
                <td><c:out value="${element.birthDate}"/></td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}"/>/student/edit?studentId=<c:out value="${element.id}"/> ">
                        Edit
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}"/>/student/remove?studentId=<c:out value="${element.id}"/> ">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

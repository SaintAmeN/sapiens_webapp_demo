<%--
  Created by IntelliJ IDEA.
  User: AmeN
  Date: 29.07.2021
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <title>Grade form</title>
</head>
<body>
<div>
    <form action="<c:out value="${pageContext.request.contextPath}"/>/grade/form" method="post">
        <input name="edited_grade_id" value="<c:out value="${grade.id}"/>" readonly hidden>
        <input name="studentId" value="<c:out value="${student_identifier}"/>" readonly hidden>
        <div>
            <div>Grade:</div>
            <div>
                <input type="number" step="0.5" min="1" max="6" name="grade-value"
                       value="<c:out value="${grade.gradeValue}"/>"></div>
        </div>
        <div>
            <div>Subject:</div>
            <div>
                <select class="browser-default" name="grade-subject">
                    <c:forEach var="singleSubject" items="${gradeSubjectsAvailable}">
                        <option value="<c:out value="${singleSubject}"/>"><c:out value="${singleSubject}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>

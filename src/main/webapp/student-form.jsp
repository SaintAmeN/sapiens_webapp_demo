<%--
  Created by IntelliJ IDEA.
  User: AmeN
  Date: 29.07.2021
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <title>Student form</title>
</head>
<body>

<div>
    <form action="${pageContext.request.contextPath}/student/form" method="post">
        <div>
            <div>First name:</div>
            <div><input type="text" name="student-first-name"></div>
        </div>
        <div>
            <div>Last name:</div>
            <div><input type="text" name="student-last-name"></div>
        </div>
        <div>
            <div>Index:</div>
            <div><input type="text" name="student-indeks"></div>
        </div>
        <div>
            <div>Birth date:</div>
            <div><input type="date" name="student-birth-date"></div>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>

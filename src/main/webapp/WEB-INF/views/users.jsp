<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.03.2024
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <th>${user.id}</th>
                <th>${user.login}</th>
                <th>${user.name}</th>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.request.contextPath}/app">Назад</a>
</body>
</html>

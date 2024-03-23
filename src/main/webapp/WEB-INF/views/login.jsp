<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label>
            Login:
            <input name="login" type="text" placeholder="Enter login" />
        </label>
        <span style="color: red">
            <c:if test="${(signInLoginError != null)}">
                ${signInLoginError}
            </c:if>
        </span>
    </div>
    <div>
        <label>
            Password:
            <input name="pass" type="password" placeholder="Enter password" />
        </label>
        <span style="color: red">
            <c:if test="${(signInPassError != null)}">
                ${signInPassError}
            </c:if>
        </span>
    </div>
    <input type="submit" value="Submit"/>
    <p>Do not have an account? <a href="${pageContext.request.contextPath}/sign-up">Register</a></p>
    <p style="color: red">
        <c:if test="${(signInAuthError != null)}">
            ${signInAuthError}
        </c:if>
    </p>
</form>
</body>
</html>

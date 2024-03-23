<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.03.2024
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign-up</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/sign-up" method="post">
    <div>
        <label>
            Enter your first name:
            <input type="text" name="name" placeholder="Enter your first name" />
        </label>
        <span style="color: red">
            <c:if test="${(signUpNameError != null)}">
                ${signUpNameError}
            </c:if>
        </span>
    </div>
    <div>
        <label>
            Enter your login:
            <input type="text" name="login" placeholder="Enter your login" />
        </label>
        <span style="color: red">
            <c:if test="${(signUpLoginError != null)}">
                ${signUpLoginError}
            </c:if>
        </span>
    </div>
    <div>
        <label>
            Enter password:
            <input type="password" name="pass" placeholder="Enter password" />
        </label>
        <span style="color: red">
            <c:if test="${(signUpPassError != null)}">
                ${signUpPassError}
            </c:if>
        </span>
    </div>
    <div>
        <label>
            Repeat password:
            <input type="password" name="repeatPass" placeholder="Repeat password" />
        </label>
        <span style="color: red">
            <c:if test="${(signUpRepeatPassError != null)}">
                ${signUpRepeatPassError}
            </c:if>
        </span>
    </div>
    <input type="submit" value="Submit"/>
    <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
    <p style="color: red">
        <c:if test="${(signUpAuthError != null)}">
            ${signUpAuthError}
        </c:if>
    </p>
</form>
</body>
</html>

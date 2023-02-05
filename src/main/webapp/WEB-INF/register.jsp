<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
    <h1>Shopping List</h1>
    <form method="POST" action="<c:url value='/ShoppingList' />">
        <label for="username">Username: </label><input type="text" name="username" value="<c:out value='${username}'></c:out>"/>
        <input type="hidden" name="action" value="register"/>
        <input type="submit" value="Register name"/>
    </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping List</title>
</head>
<body>
    <h1>Shopping List</h1>
    Hello, ${username} <a href="<c:url value='/ShoppingList'>
                                  <c:param name='action' value='logout'/>
                                </c:url>">
                                Logout
                       </a>
    <h2>List</h2>
    <form method="POST" action="<c:url value='/ShoppingList'/>">
        Add item: <input type="text" name="itemname"/>
        <input type="submit" value="Add"/>
        <input type="hidden" name="action" value="add"/>
    </form> 
    
    <form method="POST" action="<c:url value='/ShoppingList'/>">
        <c:forEach var="item" items="${items}">
        <input type="radio" name="itemname" value="${item}"> ${item}<br>
        </c:forEach>
        <input type="submit" value="Delete"/>
        <input type="hidden" name="action" value="delete"/>
    </form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.10.2020
  Time: 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p>
    <a href="meals?action=add">Add Meal</a>
</p>
<table>
    <style>
        TABLE {
            border-collapse: collapse;
        }
        TD, TH {
            padding: 8px;
            border: 1px solid black;
        }
    </style>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <jsp:useBean id="meals" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
    <c:forEach var="meal" items="${meals}">
        <tr>
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
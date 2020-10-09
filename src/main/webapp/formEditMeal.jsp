<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
    <c:if test="${meal == null}">
        <title>Add</title>
    </c:if>

    <c:if test="${meal != null}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<c:if test="${meal == null}">
    <h2>Add meal</h2>
</c:if>

<c:if test="${meal != null}">
    <h2>Edit meal</h2>
</c:if>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td>DateTime:</td>
            <td align="left"><input type="datetime-local" name="date" value="${meal.dateTime}"/>
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td align="left"><input type="text" name="description" value="${meal.description}"/></td>
        </tr>
        <tr>
            <td>Calories:</td>
            <td align="left"><input type="number" step="1" min="0" value="0" name="calories" value="${meal.calories}"/>
            </td>
        </tr>
    </table>
    <p>
        <button type="submit" formmethod="post" formaction="meals?action=listMeals">Save</button>
        <button type="reset">Cancel</button>
    </p>
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<link >
<h2>Список книг</h2>
<br>

<table>

    <tr>
        <th>   Название   </th>
        <th>   Автор   </th>
        <th>   Издательство   </th>
        <th>   Год издания   </th>
        <th>   Жанр   </th>
    </tr>

    <c:forEach var="book" items="${allBooks}">

        <c:url var="updateButton" value="/update">
            <c:param name="bookId" value="${book.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/delete">
            <c:param name="bookId" value="${book.id}"/>
        </c:url>

        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.years}</td>
            <td>${book.genre}</td>
            <td>
                <input type="button" value="Изменить"
                       onclick="window.location.href='${updateButton}'"/>

                <input type="button" value="Удалить"
                       onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>

    </c:forEach>


</table>

<br>

<input type="button" value="Добавить"
       onclick="window.location.href = 'add'"/>

</body>
</html>
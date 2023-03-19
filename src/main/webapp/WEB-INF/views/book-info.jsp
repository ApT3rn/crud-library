<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<h2>Информация о книге</h2>
<br>

<form:form action="save" modelAttribute="book">

    <form:hidden path="id"/>

    Название   <form:input path="name"/>
    <br><br>
    Автор   <form:input path="author"/>
    <br><br>
    Издательство   <form:input path="publisher"/>
    <br><br>
    Год издания   <form:input path="years"/>
    <br><br>
    Жанр   <form:input path="genre"/>
    <br><br>

    <input type="submit" value="OK">

</form:form>
</body>
</html>
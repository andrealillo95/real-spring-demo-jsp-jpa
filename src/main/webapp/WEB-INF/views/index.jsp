<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Just Watch It - Home Page</title>
</head>
<body>
    <h1>Just Watch It</h1>
    <h2>It Works!</h2>

    <div>
        <img src="/res/img/logo.png" alt="logo">
    </div>

    <div>${elencoFilm}</div>

    <ul>
    <c:forEach var="film" items="${elencoFilm}">
        <li><c:out value="${film.titolo}" /> di
            <c:out value="${film.regista}" />
            <c:out value="${film.anno}" />
        </li>
    </c:forEach>
    </ul>

</body>
</html>
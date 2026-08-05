<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL Test</title>
</head>
<body>
    <h1>JSP 포워딩 성공!</h1>
    
    <ul>
        <c:forEach var="i" begin="1" end="5">
            <li>${i}번째 항목입니다.</li>
        </c:forEach>
    </ul>
</body>
</html>

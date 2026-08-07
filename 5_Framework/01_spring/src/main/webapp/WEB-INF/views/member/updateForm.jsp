<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>

	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>회원 정보 수정</title>
	</head>

	<body>
		<h1>회원 정보 수정</h1>

	
		<form action="/member/update" method="POST">
		
			<input type="hidden" name="id" value="${member.id}">

			회원번호: ${member.id} <br><br>
			이름: <input type="text" name="name" value="${member.name}"><br>
			이메일: <input type="email" name="email" value="${member.email}"><br>
			나이: <input type="number" name="age" value="${member.age}"><br><br>

			<button type="submit">수정 완료</button>
			<a href="/member/list">취소</a>
		</form>
	</body>

	</html>
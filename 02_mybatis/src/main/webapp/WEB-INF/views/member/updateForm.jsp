<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>회원 수정</title>
	</head>

	<body>
		<h1>회원 수정</h1>
		<form action="/member/update" method="post">
			<input type="hidden" name="id" value="${member.id}">
			<label>
				이름:
				<input type="text" name="name" value="${member.name}" required><br>
			</label>
			<label>
				이메일:
				<input type="text" name="email" value="${member.email}" required><br>
			</label>
			<label>
				나이:
				<input type="number" name="age" value="${member.age}"><br><br>
			</label>
			<input type="submit" value="수정 완료">
		</form>
		<a href="/">첫 화면으로 돌아가기</a>
	</body>

	</html>
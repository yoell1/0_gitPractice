<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>회원 정보 수정</title>
	</head>

	<body>
		<h1>회원 정보 수정</h1>

		<!-- [수정 완료] 버튼을 누르면 서버의 [POST] /member/update 로 데이터가 날아갑니다 -->
		<form action="/member/update" method="POST">
			<!-- 몇 번 회원을 수정할지 식별하기 위해 id 값을 hidden(숨김) 칸으로 같이 보냅니다 -->
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
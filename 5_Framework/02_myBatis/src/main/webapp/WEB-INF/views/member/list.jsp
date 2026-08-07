<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>mybatis practice</title>
</head>	
<body>
	<%-- message 값이 있을 경우 alert로 메시지 내용 출력 --%>
	<c:if test="${message != null}">
		<script>
			alert("${message}");
		</script>
		<c:remove var="message" scope="session"/>
	</c:if>
	
	<h1>회원 목록</h1>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>이메일</th>
				<th>나이</th>
				<th>수정</th> <!-- 수정 전용 열 -->
				<th>삭제</th> <!-- 삭제 전용 열 -->
			</tr>
		</thead>
		<tbody>
			<%-- JSTL 반복문을 사용하여 조회 결과(memberList)를 한 행씩 출력 --%>
			<c:forEach var="m" items="${memberList}" varStatus="status">
				<tr>
					<td>${m.id}</td>
					<td>${m.name}</td>
					<td>${m.email}</td>
					<td>${m.age}</td>
					
					<!-- 수정 버튼 칸 -->
					<td>
						<a href="/member/update?id=${m.id}">수정</a>
					</td>
					
					<!-- 삭제 버튼 칸 -->
					<td>
						<a href="/member/delete?id=${m.id}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
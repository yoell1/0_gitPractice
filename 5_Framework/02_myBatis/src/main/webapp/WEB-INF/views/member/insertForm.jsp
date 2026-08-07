<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<DOCTYPE html>
	<html>
	<head>
		<meta char="UTF-8">
		<title>회원 등록</title>
	</head>	
	<body>
		<h1>회원 가입</h1>

		<form aciton="/member/insert" method="post">
			<!-- 이름 나이 이메일을 입력받아 서버로 요청-->
			
				이름:
				<input type="text" name="name" required><br>
				</label>
				<lable>
					이메일:
					<input type="text" name="email" required><br> 
				</lable>
				<lable>
					나이:
					<input type="age" name="age" value="20"><br>
				</lable><br><br>
				<input type="submit" value="가입">
		</form>		
	</body>
	
	</html>
</DOCTYPE>
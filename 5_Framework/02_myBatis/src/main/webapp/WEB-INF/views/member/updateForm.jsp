<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
    <h2>회원 정보 수정</h2>
    
    <!-- 수정 요청을 보낼 폼 -->
    <form action="/member/update" method="post">
        
        <!-- 중요: 수정할 대상인 ID값은 hidden(숨김)으로 넘겨줘야 합니다 -->
        <input type="hidden" name="id" value="${member.id}">
        
        <div>
            이름: <input type="text" name="name" value="${member.name}">
        </div>
        <div>
            이메일: <input type="text" name="email" value="${member.email}">
        </div>
        <div>
            나이: <input type="number" name="age" value="${member.age}">
        </div>
        
        <button type="submit">수정 완료</button>
        <a href="/member/list">취소</a>
    </form>
</body>
</html>
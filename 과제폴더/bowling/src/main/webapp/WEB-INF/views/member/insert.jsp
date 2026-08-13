<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>신규 볼러 등록</title>
    <c:url var="cssUrl" value="/resources/css/style.css" />
    <link rel="stylesheet" href="${cssUrl}">
    <c:url var="jsUrl" value="/resources/js/common.js" />
    <script src="${jsUrl}"></script>
</head>
<body>
    <h1>신규 볼러 등록</h1>

    <c:url var="insertUrl" value="/member/insert" />
    <c:url var="listUrl" value="/member/list" />

    <div class="form-card">
        <form action="${insertUrl}" method="post">
            <table border="1" cellpadding="6" cellspacing="0">
                <tr>
                    <th>이름</th>
                    <td><input type="text" name="name" required></td>
                </tr>
                <tr>
                    <th>레인 번호</th>
                    <td><input type="number" name="laneNumber" min="1" max="20" required></td>
                </tr>
                <tr>
                    <th>게임 수</th>
                    <td><input type="number" name="gameCount" min="0" value="1" required></td>
                </tr>
                <tr>
                    <th>등급</th>
                    <td>
                        <select name="grade" required>
                            <option value="일반">일반</option>
                            <option value="VIP">VIP</option>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="form-actions">
                <button type="submit">등록</button>
                <a href="${listUrl}">목록으로</a>
            </div>
        </form>
    </div>
</body>
</html>
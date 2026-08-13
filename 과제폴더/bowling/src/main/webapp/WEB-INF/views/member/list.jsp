<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>볼링장 이용 현황</title>
    <c:url var="cssUrl" value="/resources/css/style.css" />
    <link rel="stylesheet" href="${cssUrl}">
    <c:url var="jsUrl" value="/resources/js/common.js" />
    <script src="${jsUrl}"></script>
</head>
<body>
    <h1>볼링장 이용중인 회원 목록</h1>

    <c:url var="insertUrl" value="/member/insert" />
    <c:url var="salesUrl" value="/member/sales" />
    <c:url var="payUrl" value="/member/pay" />
    <c:url var="deleteUrl" value="/member/delete" />

    <div class="toolbar">
        <a href="${insertUrl}">신규 등록</a>
        <a href="${salesUrl}">매출 결산 보기</a>
    </div>

    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>레인</th>
            <th>게임수</th>
            <th>등급</th>
            <th>예상 요금</th>
            <th>정산</th>
            <th>삭제</th>
        </tr>

        <c:if test="${empty list}">
            <tr>
                <td colspan="8">현재 이용중인 회원이 없습니다.</td>
            </tr>
        </c:if>

        <c:forEach var="m" items="${list}">
            <tr>
                <td>${m.bowlerId}</td>
                <td>${m.name}</td>
                <td>${m.laneNumber}</td>
                <td>${m.gameCount}</td>
                <td>${m.grade}</td>
                <td>
                    <c:choose>
                        <c:when test="${m.grade == 'VIP'}">
                            <fmt:formatNumber value="${m.gameCount * 4000}" groupingUsed="true" />원
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${m.gameCount * 6000}" groupingUsed="true" />원
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="${payUrl}" method="post" onsubmit="return confirmPay();">
                        <input type="hidden" name="bowlerId" value="${m.bowlerId}">
                        <button type="submit">정산</button>
                    </form>
                </td>
                <td>
                    <form action="${deleteUrl}" method="post" onsubmit="return confirmDelete();">
                        <input type="hidden" name="bowlerId" value="${m.bowlerId}">
                        <button type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
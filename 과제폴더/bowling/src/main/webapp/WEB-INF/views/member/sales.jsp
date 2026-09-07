<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매출 결산</title>
    <c:url var="cssUrl" value="/resources/css/style.css" />
    <link rel="stylesheet" href="${cssUrl}">
    <c:url var="jsUrl" value="/resources/js/common.js" />
    <script src="${jsUrl}"></script>
</head>
<body>
    <h1>매출 결산</h1>

    <c:url var="listUrl" value="/member/list" />
    <c:url var="initUrl" value="/member/init" />
    <a href="${listUrl}">목록으로</a>

    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>결제일시</th>
            <th>회원명</th>
            <th>금액</th>
        </tr>

        <c:if test="${empty salesList}">
            <tr>
                <td colspan="3">정산 내역이 없습니다.</td>
            </tr>
        </c:if>

        <c:forEach var="b" items="${salesList}">
            <tr>
                <td><fmt:formatDate value="${b.paymentDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>${b.bowlerName}</td>
                <td><fmt:formatNumber value="${b.totalFee}" groupingUsed="true" />원</td>
            </tr>
        </c:forEach>

        <c:if test="${not empty salesList}">
            <tr class="grand-total-row">
                <td colspan="2">총 ${fn:length(salesList)}건 합계</td>
                <td><fmt:formatNumber value="${grandTotal}" groupingUsed="true" />원</td>
            </tr>
        </c:if>
    </table>

    <div class="reset-area">
        <form action="${initUrl}" method="post" onsubmit="return confirmReset();">
            <button type="submit">전체 데이터 리셋</button>
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="includes/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Success</title>
</head>
<body>
    <h1>Payment Success</h1>
    <p>결제가 성공적으로 완료되었습니다. 감사합니다!</p>
    <p>결제 상세 정보:</p>
    <ul>
        <li>상점 거래ID: ${merchant_uid}</li>
        <li>결제 금액: ${paid_amount}원</li>
        <li>카드 승인번호: ${apply_num}</li>
    </ul>
</body>
</html>

<%@ include file="includes/footer.jsp" %>
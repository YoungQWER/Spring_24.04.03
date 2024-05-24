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
        <li>주문자 이름: ${Name}</li>
        <li>주문자 이메일: ${Email}</li>
        <li>주문된 상품: ${productName}</li>
        <li>결제 금액: ${amount}원</li>
        <li>지역번호: ${ShippingPostalCode}</li>
        <li>주문자 주소: ${ShippingAddress}</li>
    </ul>
</body>
</html>

<%@ include file="includes/footer.jsp" %>
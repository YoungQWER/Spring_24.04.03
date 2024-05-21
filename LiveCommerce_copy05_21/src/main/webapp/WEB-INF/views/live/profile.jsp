<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필</title>
</head>
<body>

<h2>프로필</h2>

<c:if test="${empty user}">
    <p>사용자 정보를 불러올 수 없습니다.</p>
</c:if>

<c:if test="${not empty user}">
    <p><strong>사용자 아이디:</strong> ${user.username}</p>
    <p><strong>이메일:</strong> ${user.email}</p>
    <p><strong>배송 주소:</strong> ${user.shippingAddress}</p>
    <p><strong>배송 우편번호:</strong> ${user.shippingPostalCode}</p>
</c:if>

</body>
</html>
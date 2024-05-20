<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>장바구니</title>
</head>
<body>
    <h2>장바구니</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
                <th>총 가격</th>
                <!-- 기타 필요한 컬럼들 -->
            </tr>
        </thead>
        <tbody>
            <!-- 장바구니 정보를 반복해서 표시 -->
            <c:forEach items="${cartDetails}" var="cartItem">
                <tr>
                    <!-- 상품명 -->
                    <td>${cartItem.productName}</td>
                    <!-- 가격 -->
                    <td>${cartItem.price}</td>
                    <!-- 수량 -->
                    <td>${cartItem.quantity}</td>
                    <!-- 총 가격 = 가격 * 수량 -->
                    <td>${cartItem.price * cartItem.quantity}</td>
                    <!-- 기타 필요한 컬럼들 -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 로그인한 사용자의 ID -->
    <p>사용자 ID: ${userID}</p>
</body>
</html>
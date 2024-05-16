<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <script>
        // 수량이 변경될 때 호출되는 함수
        function updatePrice() {
            // 수량과 가격을 가져옴
            var quantity = document.getElementById("quantity").value;
            var price = ${product.price};
            
            // 가격을 계산하여 표시
            var totalPrice = quantity * price;
            document.getElementById("totalPrice").innerText = totalPrice + "원";
        }
    </script>
</head>
<body>
    <h2>주문 페이지</h2>
    <div>
        <h3>상품 정보</h3>
        <p>상품명: ${product.productName}</p>
        <p>가격: ${product.price}원</p>
        <p>설명: ${product.description}</p>
    </div>
    <div>
        <!-- 주문 양식 -->
        <form action="/live/payment" method="post">
            <!-- 상품 정보를 hidden input으로 전달 -->
            <input type="hidden" name="productId" value="${product.productId}">
            
            <!-- 주문 수량 입력 폼 -->
            <label for="quantity">수량:</label>
            <input type="number" id="quantity" name="quantity" min="1" value="1" onchange="updatePrice()">
            
            <!-- 총 가격 표시 -->
            <p>총 가격: <span id="totalPrice">${product.price}원</span></p>
            
            <!-- 결제 버튼 -->
            <input type="submit" value="결제하기">
        </form>
    </div>
</body>
</html>
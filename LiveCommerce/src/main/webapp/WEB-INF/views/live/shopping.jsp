<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
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
<header>
    <!-- 웹사이트 헤더 정보를 여기에 추가하세요 -->
</header>

<main>
    <h1>제품 상세 정보</h1>
    <!-- 제품 상세 정보를 표시하는 코드를 여기에 추가하세요 -->
    <div class="product-details">
        <h2>${product.productName}</h2>
        <p>제품 설명: ${product.description}</p>
        <p>가격: ${product.price}</p>
        <img src="${product.photo}" alt="${product.productName}" width="300">
        
        <!-- 현재 로그인한 사용자 정보 출력 -->
        <h3>현재 로그인한 사용자 정보</h3>
        <p>사용자 ID: ${currentUser}</p>
        
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
</main>

<footer>
    <!-- 웹사이트 푸터 정보를 여기에 추가하세요 -->
</footer>
</body>
</html>
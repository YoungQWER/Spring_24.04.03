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
        
        <p>orderVO : ${orderVO}</p>
        <p>productId : ${productId}</p>
        <p>quantity : ${quantity}</p>
        <p>shippingAddress : ${shippingAddress}</p>
        <p>shippingPostalCode : ${shippingPostalCode}</p>
    </div>
    <div>
        
        
        
    </div>
</body>
</html>
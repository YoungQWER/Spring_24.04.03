<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function updatePrice() {
            var quantity = document.getElementById("quantity").value;
            var price = ${product.price};
            var totalPrice = quantity * price;
            document.getElementById("totalPrice").innerText = totalPrice + "원";
        }

        function requestKakaoPay() {
            $.ajax({
                url: '/live/kakaoPay',
                type: 'post',
                data: {
                    orderId: ${orderId},
                    amount: ${product.price * quantity}
                },
                success: function(response) {
                    window.location.href = response.next_redirect_pc_url;
                },
                error: function(error) {
                    alert('결제 준비 중 오류가 발생했습니다.');
                }
            });
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
        <p>productId : ${productId}</p>
        <p>수량 : ${quantity}</p>
        <p>총 가격: <span id="totalPrice">${product.price * quantity} 원</span></p>
        <p>배송받을 주소 : ${shippingAddress}</p>
        <p>지역번호 : ${shippingPostalCode}</p>
    </div>
    <div>
        <button type="button" onclick="requestKakaoPay()">결제하기</button>
    </div>
</body>
</html>
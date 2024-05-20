<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
    <script>
        $(document).ready(function() {
            // 채팅 기능 추가
            let sock = new SockJS("http://localhost:8181");
            sock.onmessage = onMessage;
            sock.onclose = onClose;
            // 메시지 전송
            function sendMessage() {
                sock.send($("#message").val());
            }
            // 서버로부터 메시지를 받았을 때
            function onMessage(msg) {
                var data = msg.data;
                $("#messageArea").append(data + "<br/>");
            }
            // 서버와 연결을 끊었을 때
            function onClose(evt) {
                $("#messageArea").append("연결 끊김");
            }
            
            // 채팅 전송 버튼 이벤트 처리
            $("#sendBtn").click(function() {
                sendMessage();
                $('#message').val('');
            });
        });
    </script>
</head>
<body>
<header>
    <!-- 웹사이트 헤더 정보를 여기에 추가하세요 -->
</header>

<main>
    
    <!-- 제품 상세 정보를 표시하는 코드를 여기에 추가하세요 -->
    <div class="product-details">
        <h2>${product.productName}</h2>
        <p>제품 설명: ${product.description}</p>
        <p>가격: ${product.price}</p>
        <img src="${product.photo}" alt="${product.productName}" width="300">
        
        <!-- 현재 로그인한 사용자 정보 출력 -->
        <h3>현재 로그인한 사용자 정보</h3>
        <p>사용자 ID: ${currentUser}</p>
        
        <!-- 주문 수량 입력 폼 -->
        <label for="quantity">수량:</label>
        <input type="number" id="quantity" name="quantity" min="1" value="1" onchange="updatePrice()">
        
        <!-- 총 가격 표시 -->
        <p>총 가격: <span id="totalPrice">${product.price}원</span></p>
        
        <!-- 주문 양식 -->
        <form action="/live/order" method="post">
            <!-- 상품 정보를 hidden input으로 전달 -->
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="userID" value="${user.userID}">
            <!-- 수량 필드 -->
            <input type="hidden" name="quantity" id="orderQuantity">
            
            <!-- 결제 버튼 -->
            <input type="submit" value="주문하기" onclick="document.getElementById('orderQuantity').value = document.getElementById('quantity').value;">
        </form>
        
        <!-- 장바구니 추가 폼 -->
        <form action="/cart/add" method="post">
            <!-- 상품 정보를 hidden input으로 전달 -->
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="userID" value="${user.userID}">
            <!-- 수량 필드 -->
            <input type="hidden" name="quantity" id="cartQuantity">
            
            <!-- 장바구니에 추가 버튼 -->
            <input type="submit" value="장바구니 추가" onclick="document.getElementById('cartQuantity').value = document.getElementById('quantity').value;">
        </form>
    </div>
    
    <!-- 채팅 입력 폼 -->
    <input type="text" id="message" />
    <input type="button" id="sendBtn" value="submit"/>
    <div id="messageArea"></div>
</main>

<footer>
    <!-- 웹사이트 푸터 정보를 여기에 추가하세요 -->
</footer>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        /* 간단한 채팅창 스타일링 */
        #chatContainer {
            border: 1px solid #ccc;
            padding: 10px;
            width: 300px;
            height: 400px;
            overflow-y: scroll;
        }
        #messageArea {
            height: 80%;
            overflow-y: scroll;
        }
        #messageInput {
            width: 80%;
        }
        #sendBtn {
            width: 18%;
        }
    </style>
	<script>
	    var sock;
	    var stompClient;
	    var userID = '${currentUser}'; // JSP에서 Spring MVC에서 전달한 사용자 ID 값
	    var username = '${currentUsername}'; // JSP에서 Spring MVC에서 전달한 사용자 이름 값
	    var productId = '${product.productId}'; // JSP에서 Spring MVC에서 전달한 제품 ID 값
	
	    function connect() {
	        sock = new SockJS("/chat"); // WebSocket 연결
	        stompClient = Stomp.over(sock);
	        stompClient.connect({}, function(frame) {
	            console.log('Connected: ' + frame);
	            stompClient.subscribe('/topic/messages/' + productId, function(messageOutput) {
	                showMessage(JSON.parse(messageOutput.body)); // 메시지 표시
	            });
	        });
	    }
	
	    function sendMessage() {
	        var message = document.getElementById("messageInput").value; // 입력된 메시지 가져오기
	        console.log("Sending message: " + message); // 로그 출력
	        stompClient.send("/app/live.chat", {}, JSON.stringify({
	            'userID': userID,
	            'username': username,
	            'productID': productId,
	            'message': message
	        }));
	        showMessage(username, message); // 메시지 표시
	        document.getElementById("messageInput").value = "";
	    }

	
	    function showMessage(username, message) {
	        var messageArea = document.getElementById("messageArea");
	        var messageDiv = document.createElement("div");

	        var messageContent = document.createElement("span");
	        messageContent.textContent = username + ": " + message;
	        messageContent.style.fontWeight = "bold"; // 사용자 이름을 굵게 표시

	        messageDiv.appendChild(messageContent);
	        messageArea.appendChild(messageDiv);

	        // 새로운 메시지가 추가될 때마다 스크롤을 아래로 이동
	        messageArea.scrollTop = messageArea.scrollHeight;
	    }

	
	    function handleKeyPress(event) {
	        if (event.keyCode === 13) { // 엔터 키를 눌렀을 때
	            sendMessage(); // sendMessage() 함수 호출
	        }
	    }
	
	    window.onload = function() {
	        connect(); // 페이지 로드 시 WebSocket 연결
	
	        document.getElementById("messageInput").addEventListener("keypress", handleKeyPress);
	
	        var chatHistory = ${chatHistory};
	        chatHistory.forEach(function(chat) {
	            showMessage(chat);
	        });
	
	        // 스크롤을 항상 아래로 이동
	        var messageArea = document.getElementById("messageArea");
	        messageArea.scrollTop = messageArea.scrollHeight;
	    };
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
        <p>사용자 이름: ${currentUsername}</p>
        <p>제품번호 : ${product.productId}</p>

        <!-- 주문 수량 입력 폼 -->
        <label for="quantity">수량:</label>
        <input type="number" id="quantity" name="quantity" min="1" value="1" onchange="updatePrice()">

        <!-- 총 가격 표시 -->
        <p>총 가격: <span id="totalPrice">${product.price}원</span></p>

		<!-- 주문 양식 -->
		<form action="/live/order" method="post">
		    <!-- 상품 정보를 hidden input으로 전달 -->
		    <input type="hidden" name="productId" value="${product.productId}">
		    <input type="hidden" name="userId" value="${currentUser}">
		    <!-- 수량 필드 -->
		    <label for="quantity">수량:</label>
		    <input type="number" id="quantity" name="quantity" min="1" value="1">
		
		    <!-- 결제 버튼 -->
		    <input type="submit" value="주문하기">
		</form>
		
		<!-- 장바구니 추가 폼 -->
		<form action="/cart/add" method="post">
		    <!-- 상품 정보를 hidden input으로 전달 -->
		    <input type="hidden" name="productId" value="${product.productId}">
		    <input type="hidden" name="userId" value="${currentUser}">
		    <!-- 수량 필드 -->
		    <label for="cartQuantity">수량:</label>
		    <input type="number" id="cartQuantity" name="quantity" min="1" value="1">
		
		    <!-- 장바구니에 추가 버튼 -->
		    <input type="submit" value="장바구니 추가">
		</form>

    </div>

    <!-- 라이브 채팅 영역 -->
    <div id="chatContainer">
        <h3>라이브 채팅</h3>
        <div id="messageArea">
        </div>
        <input type="text" id="messageInput">
        <button id="sendBtn" onclick="sendMessage()">전송</button>
    </div>
</main>

<footer>
    <!-- 웹사이트 푸터 정보를 여기에 추가하세요 -->
</footer>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        main {
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }
        .product-details {
            width: 60%;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .product-details img {
            max-width: 100%;
            height: auto;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .order-form {
            margin-top: 20px;
        }
        .right-container {
            display: flex;
            flex-direction: column;
            width: 35%;
            gap: 20px;
        }
		.product-list {
		    background-color: #fff;
		    padding: 20px;
		    border-radius: 10px;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		    max-height: 300px; /* 스크롤의 최대 높이 지정 */
		    overflow-y: auto; /* 세로 스크롤바 활성화 */
		}

        #chatContainer {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        #messageArea {
            height: 250px;
            overflow-y: scroll;
            margin-bottom: 10px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        #messageInput {
            width: calc(100% - 70px);
            padding: 5px;
            margin-right: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        #sendBtn {
            width: 60px;
            padding: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        #sendBtn:hover {
            background-color: #0056b3;
        }
        .product-item img {
            max-width: 100px;
            max-height: 100px;
        }
        .product-list {
            display: flex;
            flex-wrap: wrap;
            margin: -10px;
        }
        .product-item {
            flex: 0 0 calc(20% - 20px);
            margin: 10px;
        }
        .product-item img {
            max-width: 100%;
            height: auto;
        }
    </style>

    <script>
        var sock;
        var stompClient;
        var userID = '${currentUser}';
        var username = '${currentUsername}';
        var productId = '${product.productId}';

        function connect() {
            sock = new SockJS("/chat");
            stompClient = Stomp.over(sock);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages/' + productId, function(messageOutput) {
                    showMessage(JSON.parse(messageOutput.body));
                });
            });
        }

        function sendMessage() {
            var message = document.getElementById("messageInput").value;
            console.log("Sending message: " + message);
            stompClient.send("/app/live.chat", {}, JSON.stringify({
                'userID': userID,
                'username': username,
                'productID': productId,
                'message': message
            }));
            showMessage(username, message);
            document.getElementById("messageInput").value = "";
        }

        function showMessage(username, message) {
            var messageArea = document.getElementById("messageArea");
            var messageDiv = document.createElement("div");

            var messageContent = document.createElement("span");
            messageContent.textContent = username + ": " + message;
            messageContent.style.fontWeight = "bold";

            messageDiv.appendChild(messageContent);
            messageArea.appendChild(messageDiv);

            messageArea.scrollTop = messageArea.scrollHeight;
        }

        function handleKeyPress(event) {
            if (event.keyCode === 13) {
                sendMessage();
            }
        }

        function updateAmount() {
            var quantity = document.getElementById("quantity").value;
            var price = ${product.price};
            var totalAmount = quantity * price;
            document.getElementById("totalPrice").textContent = totalAmount + "원";
            document.getElementById("amount").value = totalAmount;
        }

        window.onload = function() {
            connect();
            document.getElementById("messageInput").addEventListener("keypress", handleKeyPress);
            document.getElementById("quantity").addEventListener("input", updateAmount);

            var chatHistory = ${chatHistory};
            chatHistory.forEach(function(chat) {
                showMessage(chat.username, chat.message);
            });

            var messageArea = document.getElementById("messageArea");
            messageArea.scrollTop = messageArea.scrollHeight;

            updateAmount();
        };
    </script>
</head>
<body>
<header>
    <!-- 웹사이트 헤더 정보를 여기에 추가하세요 -->
</header>

<main>
    <div class="product-details">w	
        <h2>${product.productName}</h2>
        <p>제품 설명: ${product.description}</p>
        <p>가격: ${product.price}원</p>
        <img src="${product.photo}" alt="${product.productName}" width="300">

        <h3>현재 로그인한 사용자 정보</h3>	
        <p>사용자 ID: ${currentUser}</p>
        <p>사용자 이름: ${currentUsername}</p>
        <p>제품번호: ${product.productId}</p>

        <p>총 가격: <span id="totalPrice">${product.price}원</span></p>

        <form id="kakaoPayForm" action="/live/kakaoPay" method="post">
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="userId" value="${currentUser}">
            <label for="quantity">수량:</label>
            <input type="number" id="quantity" name="quantity" min="1" value="1" oninput="updateAmount()">
            <input type="hidden" id="amount" name="amount" value="${product.price}">
            <input type="hidden" name="buyerName" value="${currentUsername}">
            <input type="hidden" name="productName" value="${product.productName}">
            <button id="requestPaymentBtn" type="submit">결제하기</button>
        </form>
    </div>

    <div class="right-container">
        <div class="product-list">
            <c:forEach items="${caregoryproducts}" var="relatedProduct">
                <div class="product-item">
                    <img src="${relatedProduct.photo}" alt="${relatedProduct.productName}">
                    <div>
                        <a href="/product?id=${relatedProduct.productId}">${relatedProduct.productName}</a>
                        <p>가격: ${relatedProduct.price}원</p>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="chatContainer">
            <h3>라이브 채팅</h3>
            <div id="messageArea"></div>
            <input type="text" id="messageInput">
            <button id="sendBtn" onclick="sendMessage()">전송</button>
        </div>
    </div>
</main>

</body>
</html>

<%@ include file="../includes/footer.jsp" %>
	
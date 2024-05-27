<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        /* 전체 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* 헤더 스타일 */
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        /* 메인 콘텐츠 스타일 */
        main {
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }

        /* 제품 상세 정보 영역 스타일 */
        .product-details {
            width: 60%;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* 제품 이미지 스타일 */
        .product-details img {
            max-width: 100%;
            height: auto;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        /* 주문 폼 스타일 */
        .order-form {
            margin-top: 20px;
        }

        /* 우측 영역 스타일 */
        .right-container {
            display: flex;
            flex-direction: column;
            width: 35%;
            gap: 20px;
        }

        /* 상품 목록 영역 스타일 */
      .product-list {
          background-color: #fff;
          padding: 20px;
          border-radius: 10px;
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
          max-height: 300px; /* 스크롤의 최대 높이 지정 */
          overflow-y: auto; /* 세로 스크롤바 활성화 */
      }

        /* 채팅 영역 스타일 */
        #chatContainer {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* 메시지 영역 스타일 */
        #messageArea {
            height: 250px;
            overflow-y: scroll;
            margin-bottom: 10px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* 입력 필드 및 버튼 스타일 */
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
        max-width: 100px; /* 이미지의 최대 너비를 100px로 지정 */
        max-height: 100px; /* 이미지의 최대 높이를 100px로 지정 */
    }
     .product-list {
        display: flex; /* Flexbox 레이아웃 사용 */
        flex-wrap: nowrap; /* 가로 방향으로 넘치는 경우 줄 바꿈 방지 */
    }

    .product-item {
        flex: 0 0 auto; /* Flex 아이템 크기를 자동으로 유지 */
        margin-right: 20px; /* 각 상품 항목 사이의 간격 조절 */
    }
     .product-list {
        display: flex;
        flex-wrap: wrap; /* 요소가 가로로 넘칠 경우 다음 줄로 넘어감 */
        margin: -10px; /* 요소 간 간격 조정 */
    }

    .product-item {
        flex: 0 0 calc(20% - 20px); /* 한 줄에 5개의 요소가 보이도록 설정 */
        margin: 10px; /* 각 요소의 간격 조정 */
    }

    .product-item img {
        max-width: 100%;
        height: auto;
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

<%--         <!-- 현재 로그인한 사용자 정보 출력 -->
        <h3>현재 로그인한 사용자 정보</h3>
        <p>사용자 ID: ${currentUser}</p>
        <p>사용자 이름: ${currentUsername}</p>
        <p>제품번호 : ${product.productId}</p> --%>

        <!-- 총 가격 표시 -->
        <p>총 가격: <span id="totalPrice">${product.price}원</span></p>

        <form method="post">
            <!-- 상품 정보를 hidden input으로 전달 -->
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="userId" value="${currentUser}">
            <!-- 수량 필드 -->
            <label for="quantity">수량:</label>
            <input type="number" id="quantity" name="quantity" min="1" value="1">
            <!-- 장바구니에 추가 버튼 -->
            <input type="submit" formaction="/cart/add" value="장바구니 추가">
            <!-- 주문하기 버튼 -->
            <input type="submit" formaction="/live/order" value="주문하기">
        </form>
    </div>

    <!-- 우측 영역 -->
    <div class="right-container">
        <!-- 상품 목록 -->
        <h3>관련 상품 목록</h3>
       <div class="product-list">
    <c:forEach items="${categoryproducts}" var="relatedProduct">
        <div class="product-item">
            <img src="${relatedProduct.photo}" alt="${relatedProduct.productName}">
            <div>
                <a href="/live/product?id=${relatedProduct.productId}">${relatedProduct.productName}</a>
                <p>가격: ${relatedProduct.price}원</p>
            </div>
        </div>
    </c:forEach>
</div>
        <!-- 라이브 채팅 영역 -->
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
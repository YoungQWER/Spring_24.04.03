<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ include file="includes/header.jsp" %>

<html>
<body>

    <title>실시간 채팅</title>
    <!-- 필요한 CSS 및 JavaScript 라이브러리 로드 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<div class="container">
    <h1>실시간 채팅</h1>
    <!-- 채팅 내용을 표시할 div -->
    <div id="chatContent" style="height: 300px; overflow-y: scroll;"></div>
    <!-- 채팅 메시지 입력 폼 -->
    <form id="chatForm">
        <input type="text" id="message" class="form-control" placeholder="메시지 입력">
        <button type="submit" class="btn btn-primary mt-2">전송</button>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        // WebSocket 연결
        var socket = new WebSocket("ws://localhost:8080/chat");

        // WebSocket 연결 시 동작
        socket.onopen = function (event) {
            console.log("WebSocket 연결 성공");
        };

        // WebSocket 메시지 수신 시 동작
        socket.onmessage = function (event) {
            var message = JSON.parse(event.data);
            $("#chatContent").append("<p><strong>" + message.username + "</strong>: " + message.message + "</p>");
        };

        // 채팅 메시지 전송 폼 제출 시 동작
        $("#chatForm").submit(function (event) {
            event.preventDefault(); // 폼 제출 기본 동작 막기

            // 입력된 메시지 가져오기
            var message = $("#message").val();

            // WebSocket을 통해 서버로 메시지 전송
            socket.send(JSON.stringify({message: message}));

            // 입력 폼 초기화
            $("#message").val("");
        });
    });
</script>

</body>
</html>

<%@ include file="includes/footer.jsp" %>
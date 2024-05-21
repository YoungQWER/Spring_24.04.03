$(document).ready(function() {
    let sock;
    let username = $("#currentUsername").val(); // 현재 사용자의 이름을 가져옵니다.
    let reconnectInterval = 5000; // 5초 후에 재연결 시도

    // 페이지 로드 시 자동으로 접속
    connectToChat();

    function connectToChat() {
        sock = new SockJS("/chat"); // 수정된 부분: 엔드포인트 경로 수정
        sock.onopen = function() {
            // 사용자 이름을 서버에 전송
            sock.send(JSON.stringify({type: "USERNAME", content: username}));
            $("#chatContainer").show();
        };
        sock.onmessage = onMessage;
        sock.onclose = function() {
            onClose();
            setTimeout(connectToChat, reconnectInterval); // 5초 후에 재연결 시도
        };
    }

    function sendMessage() {
        var message = $("#message").val(); // 입력된 메시지 가져오기
        // 메시지를 JSON 형태로 전송
        sock.send(JSON.stringify({type: "MESSAGE", content: message}));
        $('#message').val(''); // 메시지 입력 필드 비우기
    }

    function onMessage(event) {
        var message = JSON.parse(event.data);
        if (message.type === "MESSAGE") {
            // 받은 메시지가 MESSAGE 타입인 경우에만 메시지를 추가
            $("#messageArea").append(message.content + "<br/>"); // 수정된 부분: 사용자 이름 제거
        }
    }

    function onClose() {
        $("#messageArea").append("연결 끊김<br/>");
    }

    $("#sendBtn").click(function() {
        sendMessage();
    });
});

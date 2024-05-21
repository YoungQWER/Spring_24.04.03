package org.zerock.handler;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class EchoHandlerTest {

    private ChatHandler echoHandler;
    private WebSocketSession mockSession;

    @Before
    public void setUp() {
        echoHandler = new ChatHandler();
        mockSession = mock(WebSocketSession.class);
    }

    @Test
    public void testAfterConnectionEstablished() throws Exception {
        echoHandler.afterConnectionEstablished(mockSession);
        // 특정 동작이 발생했는지 확인하는 코드를 작성합니다.
    }

    @Test
    public void testHandleTextMessage() throws Exception {
        echoHandler.afterConnectionEstablished(mockSession);

        TextMessage message = new TextMessage("Test Message");
        echoHandler.handleTextMessage(mockSession, message);

        // 특정 동작이 발생했는지 확인하는 코드를 작성합니다.
    }

    @Test
    public void testAfterConnectionClosed() throws Exception {
        echoHandler.afterConnectionEstablished(mockSession);
        echoHandler.afterConnectionClosed(mockSession, CloseStatus.NORMAL);

        // 특정 동작이 발생했는지 확인하는 코드를 작성합니다.
    }
}

package com.example.shatranj_backend.shatranj.websocket;

import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.*;
import org.springframework.stereotype.Component;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // For now, just echo back
        session.sendMessage(new TextMessage("Received: " + message.getPayload()));
    }
}
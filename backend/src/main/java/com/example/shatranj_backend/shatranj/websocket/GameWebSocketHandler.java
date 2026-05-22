package com.example.shatranj_backend.shatranj.websocket;

import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler {

    // Keep track of sessions per match
    private Map<String, WebSocketSession[]> matchSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String matchId = session.getUri().getPath().split("/")[3]; // /ws/match/{matchId}
        matchSessions.computeIfAbsent(matchId, k -> new WebSocketSession[2]);

        WebSocketSession[] sessions = matchSessions.get(matchId);
        if (sessions[0] == null) sessions[0] = session;
        else sessions[1] = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Broadcast move to other player
        String matchId = session.getUri().getPath().split("/")[3];
        WebSocketSession[] sessions = matchSessions.get(matchId);
        for (WebSocketSession s : sessions) {
            if (s != null && !s.getId().equals(session.getId())) {
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Cleanup sessions
        matchSessions.forEach((matchId, sessions) -> {
            if (sessions[0] == session) sessions[0] = null;
            else if (sessions[1] == session) sessions[1] = null;
        });
    }
}
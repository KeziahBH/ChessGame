package com.example.shatranj_backend.shatranj.config;

import com.example.shatranj_backend.shatranj.websocket.ChatWebSocketHandler;
import com.example.shatranj_backend.shatranj.websocket.GameWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private GameWebSocketHandler gameWebSocketHandler;

    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket for moves
        registry.addHandler(gameWebSocketHandler, "/ws/match/{matchId}")
                .setAllowedOrigins("*"); // adjust CORS for frontend

        // WebSocket for chat
        registry.addHandler(chatWebSocketHandler, "/ws/chat/{matchId}")
                .setAllowedOrigins("*");
    }
}
// src/components/Chat/Chat.js
import React from 'react';
import '../components/component-styles/Chat.css';

const Chat = () => {
  return (
    <div className="chat-container">
      <h3>Chat</h3>
      <div className="chat-messages">
        <p className="placeholder">Chat will appear here (WebSocket integration later)</p>
      </div>
      <input
        type="text"
        placeholder="Type your message..."
        className="chat-input"
        disabled
      />
    </div>
  );
};

export default Chat;
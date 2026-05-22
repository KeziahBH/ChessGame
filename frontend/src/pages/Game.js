// src/pages/Game.js
import React, { useState } from 'react';
import ChessBoard from '../components/ChessBoard';
import MoveHistory from '../components/MoveHistory';
import Chat from '../components/Chat';
import '../pages/pages-styles/Game.css';

const Game = () => {
  const [moves, setMoves] = useState([]);

  const handleMove = (move) => {
    setMoves((prevMoves) => [...prevMoves, move]);
  };

  return (
    <div className="game-container">
      <div className="board-container">
        <ChessBoard onMove={handleMove} />
      </div>
      <div className="right-panel">
        <MoveHistory moves={moves} />
        <Chat />
      </div>
    </div>
  );
};

export default Game;
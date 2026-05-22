// src/components/ChessBoard/ChessBoard.js
import React, { useState, useEffect, useRef } from 'react';
import { Chessboard } from 'react-chessboard';
import { Chess } from 'chess.js';
import '../components/component-styles/ChessBoard.css';

const ChessBoard = ({ onMove }) => {
  const [game, setGame] = useState(new Chess());
  const boardWrapper = useRef(null);
  const [boardWidth, setBoardWidth] = useState(400);

  useEffect(() => {
    const handleResize = () => {
      if (boardWrapper.current) {
        const containerWidth = boardWrapper.current.offsetWidth;
        setBoardWidth(Math.min(containerWidth, 600));
      }
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  const onDrop = (sourceSquare, targetSquare) => {
    const move = game.move({
      from: sourceSquare,
      to: targetSquare,
      promotion: 'q',
    });
    if (move === null) return false;

    setGame(new Chess(game.fen()));

    // Send move to parent for move history
    if (onMove) onMove(move.san);

    return true;
  };

  return (
    <div ref={boardWrapper} className="chessboard-container">
      <Chessboard
        position={game.fen()}
        onPieceDrop={onDrop}
        boardWidth={boardWidth}
      />
    </div>
  );
};

export default ChessBoard;
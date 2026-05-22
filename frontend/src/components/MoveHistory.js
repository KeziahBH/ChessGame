// src/components/MoveHistory/MoveHistory.js
import React, { useEffect, useRef } from 'react';
import '../components/component-styles/MoveHistory.css';

const MoveHistory = ({ moves }) => {
  const containerRef = useRef(null);

  // Auto-scroll to the bottom when a new move is added
  useEffect(() => {
    if (containerRef.current) {
      containerRef.current.scrollTop = containerRef.current.scrollHeight;
    }
  }, [moves]);

  return (
    <div className="move-history-container">
      <h3>Move History</h3>
      <div className="moves-list" ref={containerRef}>
        {moves.length === 0 && <p>No moves yet</p>}
        {moves.map((move, index) => (
          <div key={index} className="move-item">
            {index + 1}. {move}
          </div>
        ))}
      </div>
    </div>
  );
};

export default MoveHistory;
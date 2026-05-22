import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import GameInfo from "../components/GameInfo";

function HomePage() {
  // console.log(Response);
  return (
    <div className="app-container">
      <div className="main-container">
        <Header />
        <div className="game-info-container">
          <GameInfo />
        </div>
      </div>
    </div>
  );
}

export default HomePage;

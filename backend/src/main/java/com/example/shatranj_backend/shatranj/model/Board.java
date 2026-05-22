package com.example.shatranj_backend.shatranj.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private String[][] grid; // 8x8 chessboard

    public Board() {
        grid = new String[8][8];
        setupInitialBoard();
    }

    private void setupInitialBoard() {
        // Simplified: use FEN or simple letters for pieces
        grid[0] = new String[]{"r","n","b","q","k","b","n","r"}; // black pieces
        grid[1] = new String[]{"p","p","p","p","p","p","p","p"};
        for (int i = 2; i <= 5; i++) {
            grid[i] = new String[]{"","","","","","","",""};
        }
        grid[6] = new String[]{"P","P","P","P","P","P","P","P"}; // white pieces
        grid[7] = new String[]{"R","N","B","Q","K","B","N","R"};
    }
}
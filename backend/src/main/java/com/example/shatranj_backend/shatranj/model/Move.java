package com.example.shatranj_backend.shatranj.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    private String fromSquare; // e.g., "e2"
    private String toSquare;   // e.g., "e4"
    private String piece;      // e.g., "P" for white pawn
    private String capturedPiece; // e.g., "p" if black pawn captured, null if none
    private long timestamp;    // when the move happened

    public Move(String from, String to, String piece, String capturedPiece) {
        this.fromSquare = from;
        this.toSquare = to;
        this.piece = piece;
        this.capturedPiece = capturedPiece;
        this.timestamp = System.currentTimeMillis();
    }
}

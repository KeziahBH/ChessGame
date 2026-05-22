package com.example.shatranj_backend.shatranj.factory;

import com.example.shatranj_backend.shatranj.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class GameManager {

    private final List<Match> matches = new ArrayList<>();

    public Match createMatch(User playerWhite, User playerBlack, String matchType) {
        Match newMatch;

        // Create appropriate match type
        switch (matchType.toUpperCase()) {
            case "BLITZ":
                newMatch = new BlitzMatch();
                break;
            case "RAPID":
                newMatch = new RapidMatch();
                break;
            case "CLASSICAL":
                newMatch = new ClassicalMatch();
                break;
            default:
                throw new IllegalArgumentException("Unknown match type: " + matchType);
        }

        // Assign players
        newMatch.setPlayerWhite(playerWhite);
        newMatch.setPlayerBlack(playerBlack);

        // Generate a unique match ID
        newMatch.setMid(System.currentTimeMillis()); // or UUID.randomUUID().getMostSignificantBits() for unique long

        // Initialize board
        Board board = new Board();
        newMatch.setBoard(board);

        // Initialize moves list
        newMatch.setMovesList(new ArrayList<>());

        // Can later set startMatch ResponseEntity if needed
        // newMatch.setStartMatch(ResponseEntity.ok("Match started"));

        // Save to active matches
        matches.add(newMatch);

        return newMatch;
    }
    public Match findMatchForUser(User user) {
        for(Match match : matches) {
            if((match.getPlayerWhite() != null && match.getPlayerWhite().getUsername().equals(user.getUsername())) ||
                    (match.getPlayerBlack() != null && match.getPlayerBlack().getUsername().equals(user.getUsername()))) {
                return match;
            }
        }
        return null; // no match found for this user
    }
}
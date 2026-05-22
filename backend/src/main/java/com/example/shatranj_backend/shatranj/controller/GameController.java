package com.example.shatranj_backend.shatranj.controller;

import com.example.shatranj_backend.shatranj.model.Match;
import com.example.shatranj_backend.shatranj.service.GameService;
import com.example.shatranj_backend.shatranj.service.MatchMakingService;
import com.example.shatranj_backend.shatranj.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final MatchMakingService matchMakingService;

    @PostMapping("/api/v1/match")
    public ResponseEntity<?> createMatch(@RequestBody Map<String, String> payload,
                                         @CookieValue(value="jwtToken", required=false) String jwtToken) {
//        System.out.println("Received JWT from cookie: " + jwtToken);
        String username = payload.get("username");
        String matchType = payload.get("matchType");
        System.out.println(matchType);
        return gameService.createMatch(username, matchType);
    }
    // New endpoint to check if a match is ready for the given user
    @GetMapping("/api/v1/match/check-match")
    public ResponseEntity<?> checkMatch(@RequestParam String username, @RequestParam String matchType) {
        return matchMakingService.checkMatch(username, matchType);
    }

}

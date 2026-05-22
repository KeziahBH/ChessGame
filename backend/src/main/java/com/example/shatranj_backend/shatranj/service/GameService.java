package com.example.shatranj_backend.shatranj.service;

import com.example.shatranj_backend.shatranj.factory.GameManager;
import com.example.shatranj_backend.shatranj.model.User;
import com.example.shatranj_backend.shatranj.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class GameService {
    MatchMakingService matchMakingService;
    UserRepo userRepo;
    GameManager gameManager;

    synchronized
    public ResponseEntity<?> createMatch(String username, String matchType) {
        User userFromDB = userRepo.getUserByUsername(username);
        System.out.println("userFromDB = " + userFromDB);
        if (userFromDB == null) {
            return ResponseEntity.notFound().build();
        }
        return matchMakingService.createMatch(userFromDB, matchType);
    }
}

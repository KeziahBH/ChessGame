package com.example.shatranj_backend.shatranj.service;
import com.example.shatranj_backend.shatranj.factory.GameManager;
import com.example.shatranj_backend.shatranj.model.Match;
import com.example.shatranj_backend.shatranj.model.User;
import com.example.shatranj_backend.shatranj.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.*;
import java.util.concurrent.*;

@Service
@AllArgsConstructor
public class MatchMakingService {

    private final GameManager gameManager;
    private final UserRepo userRepo;

    // Map key = matchType_ratingRange, value = queue of waiting users
    private final Map<String, Queue<User>> matchQueues = new ConcurrentHashMap<>();

    // Timeout executor for 90s waiting
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    public ResponseEntity<?> createMatch(User user, String matchType) {
        String key = matchType.toUpperCase() + "_" + getRatingRange(user.getRating());
        Queue<User> queue = matchQueues.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());

        synchronized (queue) {
            User opponent = queue.poll();
            if (opponent != null) {
                Match match = gameManager.createMatch(opponent, user, matchType);
                return ResponseEntity.ok(match);
            } else {
                queue.add(user);
                scheduler.schedule(() -> queue.remove(user), 90, TimeUnit.SECONDS);
                return ResponseEntity.ok("Waiting for opponent...");
            }
        }
    }

    // New method to check if a match is ready
    public ResponseEntity<?> checkMatch(String username, String matchType) {
        User user = userRepo.getUserByUsername(username);
        if (user == null) return ResponseEntity.notFound().build();

        String key = matchType.toUpperCase() + "_" + getRatingRange(user.getRating());
        Queue<User> queue = matchQueues.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());

        synchronized (queue) {
            // Check if this user has already been matched by someone else
            Match existingMatch = gameManager.findMatchForUser(user);
            if (existingMatch != null) {
                return ResponseEntity.ok(existingMatch);
            } else {
                return ResponseEntity.ok(Map.of("waiting", true));
            }
        }
    }

    // Helper: determine rating range
    private String getRatingRange(int rating) {
        if (rating <= 1200) return "1000-1200";
        else if (rating <= 1400) return "1201-1400";
        else if (rating <= 1600) return "1401-1600";
        else if (rating <= 1800) return "1601-1800";
        else if (rating <= 2000) return "1801-2000";
        else if (rating <= 2200) return "2001-2200";
        else if (rating <= 2400) return "2201-2400";
        else if (rating <= 2600) return "2401-2600";
        else if (rating <= 2800) return "2601-2800";
        else return "2801-3000";
    }
}

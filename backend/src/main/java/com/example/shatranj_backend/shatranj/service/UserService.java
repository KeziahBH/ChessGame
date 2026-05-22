package com.example.shatranj_backend.shatranj.service;

import com.example.shatranj_backend.shatranj.model.DTO.LoginUserDTO;
import com.example.shatranj_backend.shatranj.model.User;
import com.example.shatranj_backend.shatranj.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseEntity<?> handleLogin(LoginUserDTO loginUser) {
        // check whether this user exists or not in db
        User userFromDB = userRepo.getUserByUsername(loginUser.getUsername());
        String userPassword = passwordEncoder.encode(loginUser.getPassword());
        if (userFromDB == null || !passwordEncoder.matches(loginUser.getPassword(), userFromDB.getPassword())) {
            return ResponseEntity.status(400).body("Invalid username or password");
        }

        // 2. Generate JWT
        String token = jwtService.generateToken(userFromDB.getUsername());

        // Store JWT in HttpOnly cookie
        org.springframework.http.ResponseCookie cookie = org.springframework.http.ResponseCookie
                .from("jwtToken", token)
                .httpOnly(true)
                .secure(false) // set true in production with HTTPS
                .path("/")
                .maxAge(2 * 60) // 2 minutes
                .sameSite("None")
                .build();

        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("username", userFromDB.getUsername()));
    }

    public ResponseEntity<?> handleSignUp(User signupUser) {
        signupUser.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        User u = userRepo.save(signupUser);
        if(u == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("User Signed Up successfully");
    }

}

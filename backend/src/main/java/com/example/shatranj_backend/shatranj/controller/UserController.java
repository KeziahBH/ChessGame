package com.example.shatranj_backend.shatranj.controller;

import com.example.shatranj_backend.shatranj.model.DTO.LoginUserDTO;
import com.example.shatranj_backend.shatranj.model.User;
import com.example.shatranj_backend.shatranj.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping("/api/v1/auth/login")
    ResponseEntity<?> handleLogin(@RequestBody LoginUserDTO loginUser){
        return userService.handleLogin(loginUser);
    }

    @PostMapping("/api/v1/auth/signup")
    ResponseEntity<?> handleSignUp(@RequestBody User signupUser){
        return userService.handleSignUp(signupUser);
    }

    // checking the api endpoints
    @GetMapping("/")
    public ResponseEntity<String> simpleGetReq() {
        System.out.println("Hello World");
        return ResponseEntity.ok("Hello World");
    }


}

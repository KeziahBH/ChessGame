package com.example.shatranj_backend.shatranj.model.DTO;

import lombok.Data;

@Data
public class LoginUserDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
}

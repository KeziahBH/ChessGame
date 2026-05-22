package com.example.shatranj_backend.shatranj.repo;


import com.example.shatranj_backend.shatranj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User getUserByUsername(String username);
}

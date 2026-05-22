package com.example.shatranj_backend.shatranj.service;

import com.example.shatranj_backend.shatranj.model.User;
import com.example.shatranj_backend.shatranj.model.UserPrincipal;
import com.example.shatranj_backend.shatranj.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);
        return new UserPrincipal(user);
    }
}

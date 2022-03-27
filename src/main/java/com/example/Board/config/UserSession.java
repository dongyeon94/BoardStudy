package com.example.Board.config;

import com.example.Board.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserSession extends User {

    private Users users;

    public UserSession(Users users) {
        super(users.getEmail(), users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.users = users;
    }
}

package com.example.Board.service;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Permit;
import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public StatusCode joinNewNomalUser(Users user) {
        if(userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
            return StatusCode.DUPLICATED_USER;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPermit(Permit.USER);
        userRepository.save(user);
        return StatusCode.SUCCESS;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Users users = userRepository.findByEmailOrNickname(loginId, loginId).orElse(null);
        return new UserSession(users);
    }
}

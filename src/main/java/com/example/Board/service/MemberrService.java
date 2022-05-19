package com.example.Board.service;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Permit;
import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberrService {
    private final UserRepository userRepository;
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

    public Optional<Users> loginMember(String loginId) throws UsernameNotFoundException {
        return userRepository.findByEmailOrNickname(loginId, loginId);
    }

    public Users authenticateByEmailAndPassword(String email, String password) {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email));

        if(!passwordEncoder.matches(password, users.getPassword())) {
            throw new BadCredentialsException("Password not matched");
        }

        return users;
    }
}

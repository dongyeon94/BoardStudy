package com.example.Board.service;

import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public StatusCode joinNewNomalUser(Users user) {
        if(userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
            return StatusCode.DUPLICATED_EMAIL;
        }
        userRepository.save(user);
        return StatusCode.SUCCESS;
    }
}

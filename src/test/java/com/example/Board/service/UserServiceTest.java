package com.example.Board.service;

import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void joinUserTest() {
        Users users = new Users();
        users.setEmail("test@naver.com");
        users.setNickname("test");
        users.setPassword("qwer1234");
        StatusCode code = userService.joinNewNomalUser(users);

        assertEquals(StatusCode.SUCCESS, code);
    }
}

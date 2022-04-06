package com.example.Board.service;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Permit;
import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class BoardrServiceTest {


    @Autowired
    private BoardService boardService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeTransaction
    void before() {
        Users users = new Users();
        users.setEmail("test@naver.com");
        users.setNickname("test");
        users.setPassword(passwordEncoder.encode("qwer1234"));
        users.setPermit(Permit.USER);
        userRepository.save(users);
    }

    @AfterTransaction
    void after() {
        userRepository.deleteAll();
    }

    @DisplayName("게시판 생성 테스트")
    @Test
    @WithMockUser
    @WithUserDetails(value = "test@naver.com")
    void createBoardTest() {
        Users users = new Users();
        users.setEmail("test@naver.com");
        users.setPassword("qwer1234");
        UserSession userSession = new UserSession(users);
        StatusCode code = boardService.createBoard("제목이다", "제목은 곧 내용이다.", userSession);

        assertEquals(StatusCode.SUCCESS, code);
    }
}

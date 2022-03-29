package com.example.Board.controller;

import com.example.Board.domain.Permit;
import com.example.Board.domain.Users;
import com.example.Board.service.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void before() {
        Users users = new Users();
        users.setEmail("test@naver.com");
        users.setNickname("test");
        users.setPassword(passwordEncoder.encode("qwer1234"));
        users.setPermit(Permit.USER);
        userRepository.save(users);
    }

    @AfterEach
    void after() {
        userRepository.deleteAll();
    }


    @DisplayName("유저 가입 테스트")
    @Test
    void signUpTest() throws Exception {
        mockMvc.perform(post("/user/signup")
            .param("email","tests@naver.com")
            .param("nickname","tests")
            .param("password","qwer1234")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }


    @DisplayName("이메일 로그인 테스트")
    @Test
    void loginWithEmail() throws Exception{
        mockMvc.perform(post("/login")
            .param("loginId","test@naver.com")
            .param("password","qwer1234")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername("test@naver.com"));
    }

    @DisplayName("이메일 로그인 실패")
    @Test
    void loginFailWithEmail() throws Exception{
        mockMvc.perform(post("/login")
            .param("loginId","test@naver.com")
            .param("password","qwer12345")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/login?error=true"));
    }

    @DisplayName("닉네임 로그인 테스트")
    @Test
    void loginWithNickName() throws Exception{
        mockMvc.perform(post("/login")
            .param("loginId","test")
            .param("password","qwer1234")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername("test@naver.com"));
    }

    @DisplayName("닉네임 로그인 테스트")
    @Test
    void loginFailWithNickName() throws Exception{
        mockMvc.perform(post("/login")
            .param("loginId","test")
            .param("password","qwer12345")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/login?error=true"));
    }
}
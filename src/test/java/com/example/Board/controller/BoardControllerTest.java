package com.example.Board.controller;

import com.example.Board.domain.Permit;
import com.example.Board.domain.Users;
import com.example.Board.service.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;
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

    @DisplayName("게시판 메인 뷰어")
    @Test
    @WithMockUser
    void boardViewer() throws Exception {
        mockMvc.perform(get("/board/"))
            .andExpect(view().name("board/boardviewer"))
            .andExpect(status().isOk());
    }

    @DisplayName("게시판 작성 뷰어")
    @Test
    @WithMockUser
    void boardWriter() throws Exception {
        mockMvc.perform(get("/board/write"))
            .andExpect(view().name("board/boardwriter"))
            .andExpect(status().isOk());
    }

    @DisplayName("게시판 작성 확인")
    @Test
    @WithUserDetails(value = "test@naver.com")
    void boardWriterConfirm() throws Exception {
        mockMvc.perform(post("/board/write")
            .param("title","테스트 제목")
            .param("content","테스트 내용")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/board/"));
    }

    @DisplayName("세션 없음")
    @Test
    void SessionFail() throws Exception {
        mockMvc.perform(get("/board/"))
            .andExpect(unauthenticated())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("http://localhost/user/login"))
            .andDo(print());
    }
}

package com.example.Board.controller;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Users;
import com.example.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final String BOARD_URL_PREFIX = "board/";
    private final String BOARD_URL_REDIRECT = "redirect:/board/";
    private final BoardService boardService;

    @GetMapping("/")
    public String boardMain() {
        return BOARD_URL_PREFIX + "boardviewer";
    }

    @GetMapping("/write")
    public String boardWriter() {
        return BOARD_URL_PREFIX + "boardwriter";
    }

    @PostMapping("/write")
    public String boardWriteConfirm(String title, String content, @AuthenticationPrincipal UserSession users) {
        boardService.createBoard(title, content, users);
        return BOARD_URL_REDIRECT;
    }

}

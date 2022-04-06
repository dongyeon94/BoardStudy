package com.example.Board.controller;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Boards;
import com.example.Board.domain.Users;
import com.example.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final String BOARD_URL_PREFIX = "board/";
    private final String BOARD_URL_REDIRECT = "redirect:/board/";
    private final BoardService boardService;

    @GetMapping("/")
    public String boardMain(Model model) {
        List<Boards> boards = boardService.allBoards();
        model.addAttribute("boards", boards);
        return BOARD_URL_PREFIX + "boardviewer";
    }

    @GetMapping("/detail/{id}")
    public String boardDetail(Model model, @PathVariable("id") Long id) {
        Boards board = boardService.boardDetails(id);
        model.addAttribute("boards", board);
        return BOARD_URL_PREFIX + "boarddetail";
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

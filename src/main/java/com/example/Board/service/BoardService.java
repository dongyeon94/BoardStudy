package com.example.Board.service;

import com.example.Board.config.UserSession;
import com.example.Board.domain.Boards;
import com.example.Board.domain.StatusCode;
import com.example.Board.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public StatusCode createBoard(String title, String content, UserSession userSession){
        Users users = userRepository.findByEmail(userSession.getUsername()).orElse(null);

        if(users == null) {
            return StatusCode.AUTH_ERROR;
        }

        Boards boards = new Boards();
        boards.setMemberId(users);
        boards.setCreateDate(LocalDate.now());
        boards.setTitle(title);
        boards.setContent(content);
        boards.setView(0);
        boardRepository.save(boards);

        return StatusCode.SUCCESS;
    }

    public StatusCode updateBoard() {
        return StatusCode.SUCCESS;
    }

    public List<Boards> allBoards() {
        return boardRepository.findAll();
    }

    public Boards boardDetails(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
}

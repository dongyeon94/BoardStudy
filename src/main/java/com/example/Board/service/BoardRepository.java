package com.example.Board.service;

import com.example.Board.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Boards, Long> {
}

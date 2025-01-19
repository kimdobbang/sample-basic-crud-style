package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import org.springframework.stereotype.Service;


@Service
public interface BoardCreateUseCase{
    Board create(Board board); // 선언만
}

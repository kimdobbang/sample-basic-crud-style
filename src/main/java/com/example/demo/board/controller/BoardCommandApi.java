package com.example.demo.board.controller;

import com.example.demo.board.controller.dto.BoardCommandDtos.BoardCreateRequest;
import com.example.demo.board.controller.dto.BoardCommandDtos.BoardCreateResponse;
import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.types.BoardStatus;
import com.example.demo.board.service.BoardCreateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController // bean 등록
@RequiredArgsConstructor // final 또는 non-null인것 생성자 생성
@RequestMapping("/boards")
@Slf4j
// 컨트롤러에서 하는일: 유스케이스(서비스)에 넘겨주기
public class BoardCommandApi {

    private final BoardCreateUseCase boardCreateUseCase;

// @RequiredArgsConstructor 애노테이션으로 대체
//    public BoardCommandApi(BoardCreateUseCase boardCreateUseCase) {
//        this.boardCreateUseCase = boardCreateUseCase;
//    }

    @PostMapping
    public BoardCreateResponse create(@RequestBody @Valid BoardCreateRequest dto) {
        Instant now = Instant.now();
        Board board = Board.builder()
                .title(dto.title())
                .content(dto.content())
                .status(BoardStatus.ACTIVE)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // useCase 사용하기
//        Board savedEntity = boardCreateUseCase.create(board);
        var savedEntity = boardCreateUseCase.create(board);
        log.info(savedEntity.toString());

        // 반환
        return BoardCreateResponse.builder()
                .board(savedEntity)
                .build();
    }

}

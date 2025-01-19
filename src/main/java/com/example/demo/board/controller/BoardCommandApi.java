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
@RequiredArgsConstructor // final 또는 non-null인것만 생성자 만들어줌
@RequestMapping("/boards")
@Slf4j
// 컨트롤러에서 하는일: 유스케이스(서비스)에 넘겨주기
public class BoardCommandApi {

    private final BoardCreateUseCase boardCreateUseCase; // useCase << 사용이유: 더 추상화 계층이 높음
    // 여기 코드는 단 하나도 바꾸지 않고, 나중에 service를 교체할 수 있도록 하기 위함. (의존성 역전 원칙 DIP)
    // class를 바로 사용하면, 나중에 새로운 bean으로 갈아끼우려 할 때 여기 와서 이곳의 코드도 수정 필요
    // interface를 사용하면 -> 빈 갈아끼우는건 spring이 알아서 하니까 여기서 우리가 수정할 필요 x

//// @RequiredArgsConstructor 애노테이션으로 대체
//    public BoardCommandApi(BoardCreateUseCase boardCreateUseCase /*빈 주입되는 자리*/) {
//        this.boardCreateUseCase = boardCreateUseCase; // 주입된 빈을 필드에 옮겨 담음
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

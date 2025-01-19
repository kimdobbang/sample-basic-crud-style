package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardCommandService implements BoardCreateUseCase {

    private final BoardRepository boardRepository;

    // 생성자
    public BoardCommandService (BoardRepository injectedBean/*파라미터에 빈이 주입됨*/){
        // 주입받은 빈 옮겨담기
        this.boardRepository = injectedBean;
    }


    // cmd N(윈도우: ctrl + I) 또는 ALT + 엔터
    @Override
    public Board create(Board board) {
        var savedEntity = boardRepository.save(board);       //자바에서 var는 지역변수에서 잠깐 사용하는 정도(그리고 타입 생각안나면 잠깐 써도 됨. 이클립스는 인식 잘 안될 수 있다.)
        // id가 있는 엔티티 = repository.save(id가 없는 엔티티)
        return savedEntity;
    }
}

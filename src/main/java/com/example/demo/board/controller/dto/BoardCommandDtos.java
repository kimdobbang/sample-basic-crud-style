package com.example.demo.board.controller.dto;

import com.example.demo.board.domain.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

// Dots <-s 붙으면 그거 관련된 서비스 같은것들
public final class BoardCommandDtos { // final로 상속 못하게

    private BoardCommandDtos() {}// private 로 인스턴스 생성 못하게

    // requeset, response dto 생성
    // 참고: record -> 한번 객체 만들어지면 수정불가. 게터세터로 못바꿈. 한번 쓰고 말 불변객체(생성자 같아보이는데 생성자는 아니고,, 생성자 쓰는 방식과는 유사한듯)

    @Builder
    public record BoardCreateRequest(
            @NotBlank(message = "제목을 입력하십시오.") // null, empty "", blank " "
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            @Size(max = 50, message = "제목은 최대 50글자입니다.")
            String title,

            @NotBlank(message = "본문을 입력하십시오.")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content
    ) {}

    @Builder
    public record BoardCreateResponse(
            Board board
    ) {}

}

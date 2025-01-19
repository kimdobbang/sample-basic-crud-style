package com.example.demo.board.domain;

import com.example.demo.board.domain.types.BoardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING) // 열거상수 네임 그대로 사용하겠다
    private BoardStatus status;
    private Instant createdAt; // snake_case -> CamelCase
    private Instant updatedAt;



}


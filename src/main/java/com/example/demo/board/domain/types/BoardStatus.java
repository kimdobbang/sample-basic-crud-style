package com.example.demo.board.domain.types;

// 선택지가 있는 구조에 사용하는 데이터 타입
// 계절별, 컬러별 등
// ex) unum Season {SPRING, SUMMER, AUTUMN, WINTER}

public enum BoardStatus {
    // 대기
    PENDING,

    // 활성
    ACTIVE,

    // 제한됨
    SUSPENDED,

    // 삭제
    REMOVED

    // 서비스에 휴면 개념 사라짐
}

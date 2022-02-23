package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체를 다 바꿀 수 있는 생성자
@ToString

// Board.java
public class Board {

    // 글번호, 제목, 내용, 작성자, 조회수
    // 1. 변수설정 (ERD 다이어그램 참고해서)
    private long no = 0L;
    private String title = null;
    private String content = null;
    private String writer = null;
    private int hit = 0;

}

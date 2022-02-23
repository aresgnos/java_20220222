package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// 파일명 : Book.java
public class Book {

    // 1. 변수
    private long code = 0L; // 코드 : 숫자형
    private String title = null; // 책제목 : 문자열형
    private int price = 0; // 책가격 : 숫자형
    private String writer = null; // 저자 : 문자열형

}

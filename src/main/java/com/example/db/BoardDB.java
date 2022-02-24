package com.example.db;

import java.util.List;

import com.example.vo.Board;

// 설계
public interface BoardDB {

    // int = 숫자로, 했다 안했다 알랴줌
    // 게시판 글쓰기
    public int insertBoard(Board board);

    // 게시판 삭제
    public int deleteBoard(Board board);

    // 게시판 수정
    public int updateBoard(Board board);

    // 게시판 1개 조회
    public Board selectOneBoard(Board board);

    // 게시판 전체 조회
    // ArrayList = List
    public List<Board> selectListBoard();
}

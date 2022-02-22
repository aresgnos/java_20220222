package com.example.view;

// 같은 위치에 board import
import com.example.vo.Board;

// 출력하는 곳
// jsp, vue, react로 전환되는 영역
public class Print {

    // 1. 변수 만들기
    // 변수로 board를 받아온다.
    private Board board = null;

    // 2. getter/ setter 만들기
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    // 4. 메소드
    // 실제 업무 = 출력 = 게시판 정보가 오면 글번호, 제목을 출력
    public void printAction() {
        System.out.println("===================");
        System.out.println(this.board.getNo());
        System.out.println(this.board.getTitle());
        System.out.println("===================");
    }

}

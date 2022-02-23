package com.example;

import com.example.db.BoardDB;
// import com.example.db.BoardDB;
// import com.example.db.BoardDB;
import com.example.vo.Board;

// 프로그램 시작 위치
public class App {
    public static void main(String[] args) {
        BoardDB obj = new BoardDB();

        Board board = new Board();

        // 추가
        // board.setNo(152);
        // board.setTitle("수요일");
        // board.setContent("내일은 목요일");
        // board.setWriter("송세라");
        // board.setHit(0);
        // int ret = obj.insertData(board);
        // if (ret == 1) {
        // System.out.println("추가성공");
        // } else {
        // System.out.println("추가실패");
        // }

        // 삭제
        // board.setNo(151);
        // int result = obj.deleteData(board);
        // System.out.println(result);

        // 수정
        // board.setNo(150);
        // board.setTitle("수정구슬");
        // board.setContent("수정이 되게 해주세요");
        // int result = obj.updateData(board);
        // System.out.println(result);

        // 1개 조회
        // board.setNo(150);
        // Board board1 = obj.selectOneData(board);

        // if (board1 != null) {
        // System.out.println(board1.toString());
        // }

    }
}

/*
 * 객체 생성
 * MemberDB obj = new MemberDB();
 * 
 * Member member = new Member();
 * 
 * 추가하기
 * member.setId("one"); // 기본키이기 때문에 바꿔야 추가가 가능
 * member.setAge(18);
 * member.setName("학생");
 * member.setRegdate("2022");
 * member.setRole("Customer");
 * int ret = obj.insertData(member);
 * if (ret == 1) {
 * System.out.println("추가성공");
 * } else {
 * System.out.println("추가실패");
 * }
 * 
 * 삭제
 * 삭제를 위한 아이디 전달
 * member.setId("hungry");
 * 삭제하기
 * int result = obj.deleteData(member);
 * 결과 확인
 * System.out.println(result);
 * 
 * 수정
 * member.setId("result");
 * member.setAge(881);
 * member.setName("나이제배불러1");
 * int ret = obj.updateData(member);
 * System.out.println(ret);
 * 
 * 1개 조회
 * member.setId("one");
 * 
 * Member member1 = obj.selectOneData(member);
 * // 중요한 문법!!
 * if (member1 != null) {
 * System.out.println(member1.toString());
 * }
 * 
 * 전체 조회
 * ArrayList<Member> list = obj.selectListData();
 * for (Member tmp : list) {
 * System.out.println(tmp.toString());
 * }
 */

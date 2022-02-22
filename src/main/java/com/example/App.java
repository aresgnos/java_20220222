package com.example;

// 클래스가 App.java의 입장에서 찾을 수 있게 위치를 설정해서 가져옴
// 같은 위치에 있게 import
import com.example.vo.Board;
import com.example.view.Print;

public class App {
    public static void main(String[] args) {

        // 1. 게시판 데이터를 추가함
        // 출력은 Print.java에서
        Board obj = new Board();
        obj.setNo(1);
        obj.setTitle("제목1");
        obj.setWriter("작성자2");
        obj.setHit(100);
        obj.setContent("내용1");

        // 여기서 사용하지 마세요.
        // System.out.println( obj.toString() );

        // 2. 프린트하기
        Print obj1 = new Print();
        // 준비물 전달 단계
        obj1.setBoard(obj);
        // 3. 실제 프린트 액션 수행
        obj1.printAction();
    }
}

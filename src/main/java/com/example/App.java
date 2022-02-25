package com.example;

import java.util.List;

import com.example.db.ItemDB;
import com.example.db.ItemDBImpl;
import com.example.vo.Item;

// 프로그램 시작 위치
public class App {
    public static void main(String[] args) {
        ItemDB obj = new ItemDBImpl();

        // 아이템 추가
        // 빈 것으로 만들어서 넣은 것
        // 생성자를 이용해서 넣을수도 있다.
        // = Item item = new Item(0L, 1000L, 2L, "휴지");
        // Item item = new Item();
        // item.setName("방구");
        // item.setPrice(20L);
        // item.setQuantity(1L);
        // int ret = obj.inserItem(item);
        // System.out.println(ret);

        // 삭제
        // throws Exception으로 오류 처리를 위임하였기 때문에 try, catch를 사용
        // try {
        // int ret = obj.deleteItem(7L);
        // System.out.println(ret);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // 한개 조회 Map<String, Object>
        // 컬렉션 => 모음, 수집

        // List ex) array => ["aaaa", "bbbb", "cccc"]
        // 순차적인 것에 용이, 따라서 순차적으로 출력
        // List<String> list1 = new ArrayList<>();

        // Map ex) json => {"id":"aaa", "name":"bbb", "age":13}
        // 키를 가지고 넣음, 따라서 필요한 것 꺼낼 때 용이
        // Map<String, String> map1 = new HashMap<>();

        // Map<String, Object> map = obj.selectOneMapItem(5L);
        // System.out.println(map.get("ID"));
        // System.out.println(map.get("NAME"));
        // System.out.println(map.get("PRICE"));
        // System.out.println(map.get("QUANTITY"));

        // 한개 조회 Item
        Item item = obj.selectOneItem(5L);
        System.out.println(item.getNo());
        System.out.println(item.getName());
        System.out.println(item.getPrice());
        System.out.println(item.getQuantity());
        // 한방에 나오게
        System.out.println(item.toString());

        // 수정
        // Item item = new Item();
        // item.setNo(1L);
        // item.setName("용기");
        // item.setPrice(3000L);
        // item.setQuantity(3L);
        // int ret = obj.updateItem(item);
        // System.out.println(ret);

        // 전체 조회
        List<Item> list = obj.selectListItem();
        for (Item tmp : list) {
            System.out.println(tmp.toString());
        }

        // 페이지 단위 조회
        // List<Item> list = obj.selectListPageItem(1);
        // for (Item tmp : list) {
        // System.out.println(tmp.toString());
        // }
    }
}

/*
 * // Member
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
 * 
 * BoardDB obj = new BoardDB();
 * 
 * Board board = new Board();
 * 
 * 추가
 * board.setNo(152);
 * board.setTitle("수요일");
 * board.setContent("내일은 목요일");
 * board.setWriter("송세라");
 * board.setHit(0);
 * int ret = obj.insertData(board);
 * if (ret == 1) {
 * System.out.println("추가성공");
 * } else {
 * System.out.println("추가실패");
 * }
 * 
 * 삭제
 * board.setNo(151);
 * int result = obj.deleteData(board);
 * System.out.println(result);
 * 
 * 수정
 * board.setNo(150);
 * board.setTitle("수정구슬");
 * board.setContent("수정이 되게 해주세요");
 * int result = obj.updateData(board);
 * System.out.println(result);
 * 
 * 1개 조회
 * board.setNo(150);
 * Board board1 = obj.selectOneData(board);
 * 
 * if (board1 != null) {
 * System.out.println(board1.toString());
 * }
 * 
 * 전체 조회
 * 
 */

/*
 * // 부모가 일을 마무리하지 못했기 때문에 부모 객체가 만들어지지 않는다.
 * // Parent obj1 = new Parent();
 * // obj1.printNum();
 * 
 * // 전달시에 제일 많이 쓰는 방식
 * // printchild는 쓸 수 없지
 * Parent obj2 = new Child1();
 * obj2.printNum();
 * obj2.printNum1();
 * 
 * // Child1 obj3 = new Child1();
 * // obj3.printNum();
 * // obj3.printChild1();
 * 
 */

/*
 * // board
 * // 클래스명 객체명 = new 생성자();
 * // BoardDB obj = new BoardDBImpl();
 * 
 * // 게시판 글쓰기
 * // 생성자를 통해서 넣기
 * // Board board = new Board(3L, "삭제", "삭제해도돼", "이승기", 18);
 * // 수행
 * // int ret = obj.insertBoard(board);
 * // System.out.println(ret);
 * 
 * // 게시판 삭제
 * // Board board = new Board();
 * // board.setNo(3L);
 * // int ret = obj.deleteBoard(board);
 * // System.out.println(ret);
 * 
 * // 게시판 수정
 * // Board board = new Board(2L, "수정", "수정이 돼야해", "나지", 0);
 * // int ret = obj.updateBoard(board);
 * // System.out.println(ret);
 * 
 * // 게시판 1개 조회
 * // Board board = new Board();
 * // board.setNo(1L);
 * // Board board1 = obj.selectOneBoard(board);
 * // System.out.println(board1.toString());
 * 
 * // 전체 조회
 * // List<Board> list = obj.selectListBoard();
 * // for (Board tmp : list) {
 * // System.out.println(tmp.toString());
 * // }
 */

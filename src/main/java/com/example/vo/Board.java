package com.example.vo;

// Board.java
public class Board {

    // 글번호, 제목, 내용, 작성자, 조회수
    // 1. 변수설정 (ERD 다이어그램 참고해서)
    private long no = 0L;
    private String title = null;
    private String content = null;
    private String writer = null;
    private int hit = 0;

    // 2. getter / setter 만들기 (오른쪽버튼 -> Sourse action)
    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    // 3. toString 만들기
    @Override
    public String toString() {
        return "Board [content=" + content + ", hit=" + hit + ", no=" + no + ", title=" + title + ", writer=" + writer
                + "]";
    }

    // 4. 메소드(함수)

}

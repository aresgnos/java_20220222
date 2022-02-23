package com.example.basic;

// 제너릭
// 파일명 : Exam1.java
public class Exam1<E> {
    // private int num = 0; // 숫자형
    private E name = null; // 타입이 없음(클래스명 뒤에 붙은 E가 와서)

    // name을 변경하기
    public void setData(E na) {
        this.name = na;
    }

    // name 값 가져가기
    public E getData() {
        return this.name;
    }
}

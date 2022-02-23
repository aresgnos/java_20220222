package com.example.basic;

// public : 공용(o)
// private : 개인(x)
// ** 한개의 객체를 만들기

// 클래스
public class Exam2 {

    // 객체를 직접 1개 만들기
    private static Exam2 obj = new Exam2();

    // 생성자를 막고
    // 생성자 private 외부에서 생성불가
    public Exam2() {

    }

    // 메소드 = 원래는 객체가 생성되고 호출되는 것.
    // static이 있으면 객체를 생성하지 않고 호출할 수 있음.
    // static을 만들 땐 객체에도 붙여줘야함.
    // 외부에서는 이거만 쓰는 것이다. 객체를 한번만 만드는 것.
    public static Exam2 create() {
        return obj;
    }
}

package com.example.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Parent {
    // 클래스의 공통변수
    private int num = 0;

    // 클래스명과 일치 생성자, 여러개 만들 수 있다. (오브로딩)
    public Parent() {

    }

    // 생성자
    public Parent(int num) {
        this.num = num;
    }

    // getter / setter

    // 메소드 (리턴값은 없고 상단 num을 출력)
    public void printNum() {
        System.out.println(this.num);
    }

    // 이 메소드는 기능구현X. 자식은 반드시 가져가서 기능을 구현해야함.
    // 부모쪽은 설계용
    // abstract : 추상, 이런게 하나라도 있다면 상단 클래스 앞에 명시해야함.
    public abstract void printNum1();
}

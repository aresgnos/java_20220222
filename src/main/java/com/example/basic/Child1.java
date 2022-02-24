package com.example.basic;

// Parent 기능을 확장하겠다.
// Parent는 라이브러리이고 Child1을 만드는데 기존의 기능 + 새로운 기능을 추가
public class Child1 extends Parent implements Parent1 {

    public Child1() {
        super();
        // public Parent() => 이러한 생성자 호출
    }

    public Child1(int num) {
        // 부모의 생성자를 호출
        // public Parent(int num) => 이러한 생성자를 호출
        super(num);
    }

    @Override // 부모의 기능을 재구현(부모의 구현부 + 자식의 구현부)
    // 부모가 정의했던 메소드를 유지해야함 (바꿀 수 없다)
    public void printNum() {
        super.printNum();
        // super => 부모의 메소드 호출
        System.out.println("child1에서 출력");
    }

    // 부모와 상관없는 메소드
    public void printChild1() {
        System.out.println("printChild1");
    }

    @Override
    public void printNum1() {

        System.out.println("printChild1-printNum11");

    }

    @Override
    public void printA() {

    }

    @Override
    public void printB() {

    }

}

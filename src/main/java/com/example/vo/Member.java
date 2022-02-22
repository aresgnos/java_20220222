// main/java/com/example/vo
package com.example.vo;

// 파일명 : Member.java, 파일명과 클래스명이 같아야한다.(대소문자까지)

// = const member = {_id:'a@a.com', name:'a', role:'Customer', regdate:'2022'}
public class Member {
    // **변수, 기본값 설정, 보관할 수 있는 고정적인 값
    // 1. 변수를 만들고
    private String id = null;
    private String name = null;
    private String role = null;
    private int age = 999;
    private String regdate = null;

    // **메소드(함수) = 외부에서 데이터를 바꾸거나(넣거나) 가져갈 수 있게 함
    // 2. getter/setter를 만들고

    // 가져가는 것
    public String getId() {
        return this.id;
    }

    // 바꿀 수 있는 것, 받아와야 바꿀 수 있음, 괄호 안은 받아오는 것
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String b) {
        this.name = b;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String c) {
        this.role = c;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int d) {
        this.age = d;
    }

    public String getRegdate() {
        return this.regdate;
    }

    public void setRegdate(String a) {
        this.regdate = a;
    }

    // 3. toString 만들기
    // = 마우스 오른쪽 버튼 -> Source action -> generate toString -> 만들고 싶은 항목 선택
    @Override
    public String toString() {
        return "Member [age=" + age + ", id=" + id + ", name=" + name + ", regdate=" + regdate + ", role=" + role + "]";
    }

}

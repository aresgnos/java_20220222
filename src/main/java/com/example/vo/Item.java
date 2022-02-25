package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

public class Item {

    // 물품번호, 물품명, 가격, 수량
    // Print1.java에서 네가지 출력
    private long no = 0L;
    private String name = null;
    private long price = 0L;
    private long quantity = 0L;

    // getter / setter
    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    // toString
    @Override
    public String toString() {
        return "Item [name=" + name + ", no=" + no + ", price=" + price + ", quantity=" + quantity + "]";
    }

}

package com.example.view;

import com.example.vo.Item;

public class Print1 {

    // 변수 만들기
    private Item item = null;
    // private Item[] items = null;

    // getter / setter
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // public Item[] getItems() {
    // return items;
    // }

    // public void setItems(Item[] items) {
    // this.items = items;
    // }

    public void printAction() {
        // System.out.println(this.item.getNo());
        // System.out.println(this.item.getName());
        // System.out.println(this.item.getPrice());
        // System.out.println(this.item.getQuantity());
        System.out.println(this.item);
    }

    // public void printAction() {
    // for (int i = 0; i < this.items.length; i++) {
    // System.out.println("=================");
    // System.out.println(this.items[i].getNo());
    // System.out.println(this.items[i].getName());
    // System.out.println("=================");
    // }
    // }
}

package com.example.db;

import java.util.List;
import java.util.Map;

import com.example.vo.Item;

public interface ItemDB {

    // 시퀀스를 이용해서 물품 등록
    public int inserItem(Item item);

    // 물품 삭제
    public int deleteItem(Item item);

    // 변경
    public int updateItem(Item item);

    // 조회 1개
    public Map<String, Object> selectOneMapItem(long code);

    public Item selectOneItem(long code);

    // 조회 전체 물품 코드별 정렬
    public List<Item> selectListItem();

    // 조회 페이지 단위로
    public List<Item> selectListPageItem(int page);

}

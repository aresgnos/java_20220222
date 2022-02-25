package com.example.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.vo.Item;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

public class ItemDBImpl implements ItemDB {
    private final String url = "mongodb://id216:pw216@1.234.5.158:37017/db216";
    private MongoCollection<Document> collection = null; // item2
    private MongoCollection<Document> seqCollection = null; // sequence

    // DB접속
    public ItemDBImpl() {

        MongoClient client = MongoClients.create(this.url);
        MongoDatabase db = client.getDatabase("db216");
        this.collection = db.getCollection("item2");
        this.seqCollection = db.getCollection("sequence");
    }

    @Override
    public int inserItem(Item item) {
        try {
            // 시퀀스에서 받아오고 1을 증가시키는 부분
            Bson filter = Filters.eq("_id", "SEQ_ITEM2_NO");
            Bson update = Updates.inc("seq", 1);
            Document doc = this.seqCollection.findOneAndUpdate(filter, update);
            long seq = doc.getLong("seq");
            System.out.println(seq);

            Document doc1 = new Document();
            doc1.append("_id", seq); // 자동
            doc1.append("name", item.getName());
            doc1.append("price", item.getPrice());
            doc1.append("quantity", item.getQuantity());

            InsertOneResult result = this.collection.insertOne(doc1);

            if (result.getInsertedId().asInt64().getValue() == seq) {
                return 1;
            }

            return 0;

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1; // 오류
        }
    }

    @Override
    public int updateItem(Item item) {
        try {
            // 수정할 조건
            Bson filter = Filters.eq("_id", item.getNo());

            // 수정할 항목 3개 => combine
            Bson bson1 = Updates.set("name", item.getName());
            Bson bson2 = Updates.set("price", item.getPrice());
            Bson bson3 = Updates.set("quantity", item.getQuantity());

            Bson update = Updates.combine(bson1, bson2, bson3);

            UpdateResult result = this.collection.updateOne(filter, update);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1; // 오류
        }
    }

    @Override
    public int deleteItem(long code) throws Exception {
        // Filters.eq = static 메소드(객체를 만들 필요가 없다, .찍고 나오는건 대부분)
        Bson filter = Filters.eq("_id", code);
        DeleteResult result = this.collection.deleteOne(filter);

        if (result.getDeletedCount() == 1L) {
            return 1;
        }

        return 0;
    }

    @Override
    public Map<String, Object> selectOneMapItem(long code) {
        try {
            Bson filter = Filters.eq("_id", code);

            Map<String, Object> map = new HashMap<>();
            // map.put("_id", 1L); = 추가할 때

            FindIterable<Document> rows = this.collection.find(filter);

            for (Document tmp : rows) { // 1회반복
                // map 키(변수)를 마음대로 해서 추가함.
                map.put("ID", tmp.getLong("_id"));
                map.put("NAME", tmp.getString("name"));
                map.put("PRICE", tmp.getLong("price"));
                map.put("QUANTITY", tmp.getLong("quantity"));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }

    @Override
    public Item selectOneItem(long code) {
        try {

            Bson filter = Filters.eq("_id", code);

            Item item = new Item();
            FindIterable<Document> rows = this.collection.find(filter);
            for (Document tmp : rows) { // 1회반복
                // map 키(변수)를 마음대로 해서 추가함.
                item.setNo(tmp.getLong("_id"));
                item.setName(tmp.getString("name"));
                item.setPrice(tmp.getLong("price"));
                item.setQuantity(tmp.getLong("quantity"));
            }
            return item;

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }

    @Override
    public List<Item> selectListItem() {
        try {
            Bson sort = Filters.eq("_id", 1);

            // projection을 활용하여 일부 데이터만 가져오기
            // exclude(제외), include(포함)
            Bson projection = Projections.exclude("name");
            FindIterable<Document> rows = this.collection.find().projection(projection).sort(sort);

            List<Item> list = new ArrayList<Item>();
            for (Document tmp : rows) {
                Item item = new Item(
                        tmp.getLong("_id"),
                        null,
                        tmp.getLong("price"),
                        tmp.getLong("quantity"));

                list.add(item);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }

    @Override
    public List<Item> selectListPageItem(int page) {
        try {
            // 오름차순 = 1, 내림차순 = -1
            Bson sort = Filters.eq("_id", 1);

            // 1페이지 = skip 0, 10
            // 2페이지 = skip 10, 10
            // 3페이지 = skip 20, 10
            int skip = (page - 1) * 10;
            int limit = 10;
            FindIterable<Document> rows = this.collection.find()
                    .sort(sort).skip(skip).limit(limit);

            List<Item> list = new ArrayList<Item>();
            for (Document tmp : rows) {
                Item item = new Item(
                        tmp.getLong("_id"),
                        tmp.getString("name"),
                        tmp.getLong("price"),
                        tmp.getLong("quantity"));

                list.add(item);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }

}

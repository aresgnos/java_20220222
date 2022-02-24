package com.example.db;

import java.util.List;
import java.util.Map;

import com.example.vo.Item;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
            doc1.append("_id", seq);
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
    public int deleteItem(Item item) {
        try {
            Bson bson = Filters.eq("_id", item.getNo());

            DeleteResult result = this.collection.deleteOne(bson);
            if (result.getDeletedCount() == 1L) {
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
            Bson bson = Filters.eq("_id", item.getNo());

            Bson bson1 = Updates.set("name", item.getName());
            Bson bson2 = Updates.set("price", item.getPrice());
            Bson bson3 = Updates.set("quantity", item.getQuantity());

            Bson bson4 = Updates.combine(bson1, bson2, bson3);

            UpdateResult result = this.collection.updateOne(bson, bson4);
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
    public Map<String, Object> selectOneMapItem(long code) {
        try {

            return null;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }

    }

    @Override
    public Item selectOneItem(long code) {
        try {
            Bson bson = Filters.eq("_id", code);
            FindIterable<Document> docs =this.collection.find(bson);

            for (Document tmp : docs){
                return new Item(
                    tmp.getLong(key)
                )
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }

    }

    @Override
    public List<Item> selectListItem() {

        return null;
    }

    @Override
    public List<Item> selectListPageItem(int page) {

        return null;
    }

}

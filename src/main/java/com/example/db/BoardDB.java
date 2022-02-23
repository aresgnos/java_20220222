package com.example.db;

import java.util.ArrayList;

import com.example.vo.Board;
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

public class BoardDB {

    private MongoCollection<Document> collection = null;

    public BoardDB() {

        // db연결하기
        String url = "mongodb://id216:pw216@1.234.5.158:37017/db216";
        MongoClient client = MongoClients.create(url);
        MongoDatabase db = client.getDatabase("db216");

        // board2 컬렉션 선택
        this.collection = db.getCollection("board1");

        // 선택한 컬렉션을 collection 변수에 넣기
    }

    // 메소드 (추가)
    public int insertData(Board board) {
        try {
            Document doc = new Document();
            doc.append("_id", board.getNo());
            doc.append("title", board.getTitle());
            doc.append("content", board.getContent());
            doc.append("writer", board.getWriter());
            doc.append("hit", board.getHit());

            InsertOneResult result = this.collection.insertOne(doc);

            System.out.println("결과 : " + result);

            // asInt64() => long
            // asInt32() => int
            // asString() => String
            if (result.getInsertedId().asInt64().getValue() == board.getNo()) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1;
        }
    }

    // 메소드 (삭제)
    public int deleteData(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());
            DeleteResult result = this.collection.deleteOne(bson);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1;
        }

    }

    // 메소드 (수정)
    public int updateData(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());

            Bson bson1 = Updates.set("title", board.getTitle());
            Bson bson2 = Updates.set("content", board.getContent());
            Bson bosn3 = Updates.combine(bson1, bson2);
            UpdateResult result = this.collection.updateOne(bson, bosn3);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1;
        }
    }

    // 메소드 (1개 조회)
    public Board selectOneData(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());
            FindIterable<Document> docs = this.collection.find(bson);

            Board sendBoard = new Board();
            for (Document tmp : docs) {
                sendBoard.setNo(tmp.getInteger("_id"));
                sendBoard.setTitle(tmp.getString("title"));
                sendBoard.setContent(tmp.getString("content"));
                sendBoard.setWriter(tmp.getString("writer"));
                sendBoard.setHit(tmp.getInteger("hit"));

                // System.out.println(tmp.getInteger("_id"));
                // System.out.println(tmp);
            }
            return sendBoard;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null;
        }
    }

    // 메소드 (전체 조회)
    public ArrayList<Board> selectListData() {
        try {

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null;
        }
    }
}

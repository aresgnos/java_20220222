package com.example.db;

import java.util.ArrayList;
import java.util.List;

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

// 파일명 : BoardDBImpl.java
public class BoardDBImpl implements BoardDB {
    // final = 변경할 수 없는 상수로 만듦
    private final String url = "mongodb://id216:pw216@1.234.5.158:37017/db216";
    private MongoCollection<Document> collection = null;

    // 생성자에서 외부에서 객체 생성 시점에 DB 연결하고 컬렉션도 선택
    public BoardDBImpl() {

        MongoClient client = MongoClients.create(this.url);
        MongoDatabase db = client.getDatabase("db216");
        this.collection = db.getCollection("board2");

    }

    @Override
    public int insertBoard(Board board) {
        try {
            // 2. Document
            Document doc = new Document();
            doc.append("_id", board.getNo());
            doc.append("title", board.getTitle());
            doc.append("content", board.getContent());
            doc.append("writer", board.getWriter());
            doc.append("hit", board.getHit());

            // 1. 컬렉션에 데이터를 추가하기(Document가 필요!!)
            // result에 추가했던 _id의 값이 리턴됨
            InsertOneResult result = this.collection.insertOne(doc);

            // String aa="aa"; => if(aa.equals("aa")){
            // long aa=1L; => if(aa == 2){

            if (result.getInsertedId().asInt64().getValue() == board.getNo()) {
                return 1; // 성공
            }
            return 0; // 실패

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1; // 오류
        }
    }

    @Override
    public int deleteBoard(Board board) {
        try {
            // 2. bson
            Bson bson = Filters.eq("_id", board.getNo());

            // 1. 삭제(Bosn)
            DeleteResult result = this.collection.deleteOne(bson);
            if (result.getDeletedCount() == 1L) {
                return 1; // 성공
            }
            return 0; // 실패

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1; // 오류
        }
    }

    @Override
    public int updateBoard(Board board) {
        try {
            // Bson(조건)
            Bson bson = Filters.eq("_id", board.getNo());

            // Bson(변경값)
            Bson bson1 = Updates.set("title", board.getTitle());
            Bson bson2 = Updates.set("content", board.getContent());
            Bson bson3 = Updates.set("writer", board.getWriter());

            // Bosn을 합침
            Bson bson4 = Updates.combine(bson1, bson2, bson3);

            // Bson(조건), Bson(변경값)
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
    public Board selectOneBoard(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());

            // List<String> <= "aaa","bbb","ccc"
            // = List<Document>
            FindIterable<Document> rows = this.collection.find(bson);

            // Board 타입으로 보내야함.
            // 생성자가 있기 때문에 안에서 바로 객체 만들고 리턴시킴.
            for (Document tmp : rows) {
                return new Board(
                        tmp.getLong("_id"),
                        tmp.getString("title"),
                        tmp.getString("content"),
                        tmp.getString("writer"),
                        tmp.getInteger("hit"));
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }

    @Override
    public List<Board> selectListBoard() {
        try {
            // 전체 조회
            // List<Board>, FindIterable<Document> 서로 타입이 다르기 때문에
            // 반복문을 돌려서 board 타입으로 바꿔준다.
            FindIterable<Document> rows = this.collection.find();

            // Board를 n개 보관할 수 있는 list변수
            // = 가변길이 배열 = 안에 들어갈 수 있는 길이를 자유롭게, 배열 = [{},{},{}]
            // 이 시점엔 list ===> null(데이터가 없음)
            List<Board> list = new ArrayList<Board>();

            // tmp에서 다섯가지 항목을 꺼내서 board 타입, list의 형태로
            for (Document tmp : rows) {
                // 여러개를 보관할 때는 객체를 안에 만듦
                Board board = new Board(
                        tmp.getLong("_id"),
                        tmp.getString("title"),
                        tmp.getString("content"),
                        tmp.getString("writer"),
                        tmp.getInteger("hit"));

                list.add(board);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null; // 오류
        }
    }
}

/*
 * 
 * private MongoCollection<Document> collection = null;
 * 
 * public BoardDBImpl() {
 * 
 * // db연결하기
 * String url = "mongodb://id216:pw216@1.234.5.158:37017/db216";
 * MongoClient client = MongoClients.create(url);
 * MongoDatabase db = client.getDatabase("db216");
 * 
 * // board2 컬렉션 선택
 * this.collection = db.getCollection("board1");
 * 
 * // 선택한 컬렉션을 collection 변수에 넣기
 * }
 * 
 * // 메소드 (추가)
 * public int insertData(Board board) {
 * try {
 * Document doc = new Document();
 * doc.append("_id", board.getNo());
 * doc.append("title", board.getTitle());
 * doc.append("content", board.getContent());
 * doc.append("writer", board.getWriter());
 * doc.append("hit", board.getHit());
 * 
 * InsertOneResult result = this.collection.insertOne(doc);
 * 
 * System.out.println("결과 : " + result);
 * 
 * // asInt64() => long
 * // asInt32() => int
 * // asString() => String
 * if (result.getInsertedId().asInt64().getValue() == board.getNo()) {
 * return 1;
 * }
 * return 0;
 * 
 * } catch (Exception e) {
 * e.printStackTrace(); // 오류출력
 * return -1;
 * }
 * }
 * 
 * // 메소드 (삭제)
 * public int deleteData(Board board) {
 * try {
 * Bson bson = Filters.eq("_id", board.getNo());
 * DeleteResult result = this.collection.deleteOne(bson);
 * if (result.getDeletedCount() == 1L) {
 * return 1;
 * }
 * return 0;
 * } catch (Exception e) {
 * e.printStackTrace(); // 오류출력
 * return -1;
 * }
 * 
 * }
 * 
 * // 메소드 (수정)
 * public int updateData(Board board) {
 * try {
 * Bson bson = Filters.eq("_id", board.getNo());
 * 
 * Bson bson1 = Updates.set("title", board.getTitle());
 * Bson bson2 = Updates.set("content", board.getContent());
 * Bson bosn3 = Updates.combine(bson1, bson2);
 * UpdateResult result = this.collection.updateOne(bson, bosn3);
 * if (result.getModifiedCount() == 1L) {
 * return 1;
 * }
 * return 0;
 * } catch (Exception e) {
 * e.printStackTrace(); // 오류출력
 * return -1;
 * }
 * }
 * 
 * // 메소드 (1개 조회)
 * public Board selectOneData(Board board) {
 * try {
 * Bson bson = Filters.eq("_id", board.getNo());
 * FindIterable<Document> docs = this.collection.find(bson);
 * 
 * Board sendBoard = new Board();
 * for (Document tmp : docs) {
 * sendBoard.setNo(tmp.getInteger("_id"));
 * sendBoard.setTitle(tmp.getString("title"));
 * sendBoard.setContent(tmp.getString("content"));
 * sendBoard.setWriter(tmp.getString("writer"));
 * sendBoard.setHit(tmp.getInteger("hit"));
 * 
 * // System.out.println(tmp.getInteger("_id"));
 * // System.out.println(tmp);
 * }
 * return sendBoard;
 * } catch (Exception e) {
 * e.printStackTrace(); // 오류출력
 * return null;
 * }
 * }
 * 
 * // 메소드 (전체 조회)
 * public ArrayList<Board> selectListData() {
 * try {
 * FindIterable<Document> docs = this.collection.find().sort(Filters.eq("_id",
 * 1));
 * 
 * ArrayList<Board> list = new ArrayList<Board>();
 * for (Document tmp : docs) {
 * Board sendBoard = new Board();
 * sendBoard.setNo(tmp.getInteger("_id"));
 * sendBoard.setTitle(tmp.getString("title"));
 * sendBoard.setContent(tmp.getString("content"));
 * sendBoard.setWriter(tmp.getString("writer"));
 * sendBoard.setHit(tmp.getInteger("hit"));
 * 
 * list.add(sendBoard);
 * }
 * return list;
 * 
 * } catch (Exception e) {
 * e.printStackTrace(); // 오류출력
 * return null;
 * }
 * }
 * }
 */
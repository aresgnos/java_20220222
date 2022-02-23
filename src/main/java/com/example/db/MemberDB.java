package com.example.db;

import java.util.ArrayList;

import com.example.vo.Member;
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

// 파일명 : MemberDB.java
public class MemberDB {

    // 컬렉션을 꺼냄
    private MongoCollection<Document> collection = null;

    // 생성자
    // DB 연결시킴
    public MemberDB() {

        String url = "mongodb://id216:pw216@1.234.5.158:37017/db216";

        // 객체 한개만 만들기, 접속은 객체를 n개 생성 불가
        // 1. 접속하기 = static으로 되어있음
        MongoClient client = MongoClients.create(url);

        // 2. db연결
        MongoDatabase db = client.getDatabase("db216");

        // 3. 컬렉션 선택 (member2), 상단의 컬렉션을 받아옴
        this.collection = db.getCollection("member2");
    }

    // 메소드 (추가)
    // get~ 정보가져오기
    // set~ 정보변경
    public int insertData(Member member) {
        try {
            Document doc = new Document();
            doc.append("_id", member.getId());
            doc.append("name", member.getName());
            doc.append("regdate", member.getRegdate());
            doc.append("role", member.getRole());
            doc.append("age", member.getAge());

            InsertOneResult result = this.collection.insertOne(doc);

            // 결과 : AcknowledgedInsertOneResult{insertedId=BsonString{value='result'}}
            System.out.println("결과 : " + result);

            if (result.getInsertedId()
                    .asString()
                    .getValue()
                    .equals(member.getId())) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1;
        }
    }

    // 메소드 (삭제)
    public int deleteData(Member member) {
        try {
            // 조건 만들기
            Bson bson = Filters.eq("_id", member.getId());
            // 삭제하기
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
    public int updateData(Member member) {
        try {
            // 수정하고자하는 아이디 조건
            Bson bson = Filters.eq("_id", member.getId());

            // 변경하고자 하는 내용
            // 두개만 만들 수 있어서 두개를 만들고 섞음
            Bson bson1 = Updates.set("name", member.getName());
            Bson bson2 = Updates.set("age", member.getAge());
            Bson bson3 = Updates.combine(bson1, bson2);

            UpdateResult result = this.collection.updateOne(bson, bson3);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return -1;
        }
    }

    // 1개 조회
    // 클래스가 Member, 숫자가 아니기 때문에 return 값은 null
    public Member selectOneData(Member member) {
        try {
            // Document 타입을 -> Member 타입으로 변경
            // 조건
            Bson bson = Filters.eq("_id", member.getId());
            FindIterable<Document> docs = this.collection.find(bson);
            // Member로 주기위해 빈 것 만들기
            Member sendMember = new Member();
            for (Document tmp : docs) { // 1회반복
                sendMember.setId(tmp.getString("_id"));
                sendMember.setName(tmp.getString("name"));
                sendMember.setAge(tmp.getInteger("age"));
                sendMember.setRole(tmp.getString("role"));
                sendMember.setRegdate(tmp.getString("regdate"));

                // System.out.println(tmp.getString("_id"));
                // System.out.println(tmp);
            }
            return sendMember;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null;
        }
    }

    // 전체조회, 여러개 조회 (_id 기준 오름차순으로 정렬)
    // ArrayList = 가변길이배열, 객체를 n개 담을 수 있다, 합쳐서 간다.
    // ArrayList<Member> = member가 n개 담겨서 간다.
    public ArrayList<Member> selectListData() {
        try {
            FindIterable<Document> docs = this.collection
                    .find().sort(Filters.eq("_id", 1)); // 오름차순

            // 클래스명<E> list = new 클래스명<E>();
            ArrayList<Member> list = new ArrayList<Member>();
            for (Document tmp : docs) {
                Member sendMember = new Member();
                sendMember.setId(tmp.getString("_id"));
                sendMember.setName(tmp.getString("name"));
                sendMember.setAge(tmp.getInteger("age"));
                sendMember.setRole(tmp.getString("role"));
                sendMember.setRegdate(tmp.getString("regdate"));

                list.add(sendMember); // 4번 수행
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace(); // 오류출력
            return null;
        }
    }

}

// MemberDB obj = new MemberDB();
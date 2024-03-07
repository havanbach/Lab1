package com.example.lab1;

import java.util.HashMap;

public class Todo {
    private String id,title,content;

    public Todo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // Hàm sử lý dữ liệu trong firebase
    public HashMap<String,Object> convertHansh(){
        HashMap<String,Object> word = new HashMap<>();
        word.put("id",id);
        word.put("title",title);
        word.put("content",content);
        return word;
    }
}

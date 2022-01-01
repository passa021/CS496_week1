package com.example.cs496_week1;

public class Todolist {
    int id;
    String context;
    int did;
    public void Setting(int id, String context, int did){
        this.id = id;
        this.context=context;
        this.did = did;
    }
    public String getContext(){
        return this.context;
    }
    public int getDid(){
        return this.did;
    }
}

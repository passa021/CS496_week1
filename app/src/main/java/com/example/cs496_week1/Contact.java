package com.example.cs496_week1;

class Contact {
    int id;
    String phone_number;
    String first_name;
    String last_name;
    public void Setting(int id, String phone_number,String first_name, String last_name){
        this.id = id;
        this.phone_number = phone_number;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public String getPhone_number(){
        return this.phone_number;
    }
    public String getName(){
        return this.first_name+" "+this.last_name;
    }
}

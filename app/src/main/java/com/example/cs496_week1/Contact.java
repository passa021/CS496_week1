package com.example.cs496_week1;

class Contact {
    String phone_number;
    String first_name;
    String last_name;
    public void Setting(String phone_number,String first_name, String last_name){
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

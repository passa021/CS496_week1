package com.example.cs496_week1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DB_VERSion = 1;
    private static final String DB_NAME = "contact.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Contact(id INTEGER PRIMARY KEY AUTOINCREMENT, phone_number TEXT NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        onCreate(db);
    }

    public void InsertContact(String phone_number, String first_name, String last_name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Contact(phone_number, first_name, last_name) VALUES('"+phone_number+"', '"+first_name+"','"+last_name+"');");
    }

    // UPDATE
    /*public void  UpdateContact(String phone_number, String first_name, String last_name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Contact SET phone_number = '"+phone_number+"',first_name = '"+first_name+"', last_name = '"+last_name+  "'WHERE phone_number ='"+phone_number+"'");

    }
     */
    // DELETE
    public void DeleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Contact WHERE id = '"+id +"'");
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Contact ORDER BY first_name", null);
        if (cursor.getCount()!=0){
            while(cursor.moveToNext()){
               String phone_number = cursor.getString(1);
               String first_name = cursor.getString(2);
               String last_name = cursor.getString(3);
               Contact contact = new Contact();
               contact.Setting(phone_number,first_name,last_name);
               contacts.add(contact);

            }
        }
        return contacts;
    }



}



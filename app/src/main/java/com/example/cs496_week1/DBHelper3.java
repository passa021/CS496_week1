package com.example.cs496_week1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class DBHelper3 extends SQLiteOpenHelper implements Serializable {
    private static final int DB_VERSion = 1;
    private static final String DB_NAME = "todolist.db";
    private static final long serialVersionUID = 1L;

    public DBHelper3(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Todo(id INTEGER PRIMARY KEY AUTOINCREMENT, context TEXT NOT NULL, did INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        onCreate(db);
    }

    public int InsertTodo(String context, int did ){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Todo(context, did) VALUES('"+context+"', '"+did+"');");
        //Log.v("hi","hi");
        Cursor cursor = db.rawQuery("SELECT * FROM Todo WHERE context = '"+context +"'", null);
        int id=-1;
        if (cursor.moveToFirst()){
            id = cursor.getInt(0);
        }
        Log.v("id",""+id);
        return id;
    }

    // UPDATE
    public void  UpdateContact(String context, int did){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Todo SET context = '"+context+"',did = '"+did+"'WHERE context ='"+context+"'");

    }
    // DELETE
    public void DeleteTodo(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Todo WHERE id = '"+id +"'");
    }

    public ArrayList<Todolist> getTododid(){
        ArrayList<Todolist> todolists= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Todo ORDER BY id", null);
        if (cursor.getCount()!=0){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
               String context = cursor.getString(1);
               int did = cursor.getInt(2);
                if (did==1){
                    Todolist todolist = new Todolist();
                    todolist.Setting(id, context,did);
                    todolists.add(todolist);
                }

            }
        }
        return todolists;
    }
    public ArrayList<Todolist> getTododidnot(){
        ArrayList<Todolist> todolists= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Todo ORDER BY id DESC", null);
        if (cursor.getCount()!=0){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String context = cursor.getString(1);
                int did = cursor.getInt(2);
                if (did==0){
                    Todolist todolist = new Todolist();
                    todolist.Setting(id, context,did);
                    todolists.add(todolist);
                }

            }
        }
        return todolists;
    }



}



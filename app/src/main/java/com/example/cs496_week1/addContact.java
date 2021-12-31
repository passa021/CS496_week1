package com.example.cs496_week1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        EditText t_phone_number = (EditText) findViewById(R.id.phone_number);
        EditText t_first_name = (EditText) findViewById(R.id.first_name);
        EditText t_last_name = (EditText) findViewById(R.id.last_name);

        //DBHelper mDBHelper = (DBHelper) getIntent().getSerializableExtra("dbHelper");
        findViewById(R.id.save).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        String phone_number = t_phone_number.getText().toString();
                        String first_name = t_first_name.getText().toString();
                        String last_name = t_last_name.getText().toString();
                        Log.v("check1",phone_number+first_name+last_name);
                        //mDBHelper.InsertContact(phone_number,first_name,last_name);
                        MainActivity ma = (MainActivity) MainActivity.activity;
                        DBHelper mDBHelper = new DBHelper(ma);
                        //ma.onCreate();
                        mDBHelper.InsertContact(phone_number,first_name,last_name);
                        ma.finish();
                        //ma.recreate();
                        //ma.startActivity(getIntent());
                        Intent new_intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(new_intent);

                        //finish();
                        //addContact.super.onBackPressed();
                    }
                }
        );
        findViewById(R.id.cancel).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        //addContact.super.onBackPressed();
                        finish();
                    }
                }
        );



   }

}
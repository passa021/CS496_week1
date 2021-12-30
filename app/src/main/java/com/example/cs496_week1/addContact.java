package com.example.cs496_week1;

import android.os.Bundle;
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
        String phone_number = t_phone_number.getText().toString();
        String first_name = t_first_name.getText().toString();
        String last_name =
   }
}
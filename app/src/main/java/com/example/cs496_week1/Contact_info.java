package com.example.cs496_week1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        TextView name = (TextView) findViewById(R.id.name);
        TextView phone_number = (TextView) findViewById(R.id.phone_number);

        Intent intent = getIntent();
        String naame = intent.getExtras().getString("name");
        String phonee_number = intent.getExtras().getString("phone_number");
        name.setText(naame);
        phone_number.setText(phonee_number);

        findViewById(R.id.back_button1).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Contact_info.super.onBackPressed();

                    }
                }
        );

    }
}
package com.example.cs496_week1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Contact_info extends AppCompatActivity {
    Boolean editing = false;
    String f_name = null;
    String l_name = null;
    String p_number = null;
    int id = -1;
    MainActivity ma = (MainActivity) MainActivity.activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        //Boolean editing = false;


        TextView name = (TextView) findViewById(R.id.name);
        TextView phone_number = (TextView) findViewById(R.id.phone_number);
        TextView editing_t = (TextView) findViewById(R.id.editing_text);

        EditText start =(EditText) findViewById(R.id.start_name_edit_text);
        EditText last = (EditText) findViewById(R.id.last_name_edit_text);
        EditText phone_ed = (EditText) findViewById(R.id.phone_edit);


        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        f_name = intent.getExtras().getString("first_name");
        l_name = intent.getExtras().getString("last_name");
        String naame = f_name+" "+l_name;
        p_number = intent.getExtras().getString("phone_number");
        name.setText(naame);
        phone_number.setText(p_number);

        findViewById(R.id.back_button1).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        ma.finish();
                        Intent new_intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(new_intent);
                        //Contact_info.super.onBackPressed();

                    }
                }
        );
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                if (editing){
                    editing = false;
                    name.setVisibility(View.VISIBLE);
                    phone_number.setVisibility(View.VISIBLE);
                    editing_t.setVisibility(View.INVISIBLE);

                    f_name = start.getText().toString();
                    l_name = last.getText().toString();
                    p_number = phone_ed.getText().toString();
                    name.setText(f_name+" "+l_name);
                    phone_number.setText(p_number);

                    DBHelper mDBHelper = new DBHelper(ma);
                    mDBHelper.UpdateContact(p_number,f_name,l_name,id);

                    start.setVisibility(View.INVISIBLE);
                    last.setVisibility(View.INVISIBLE);
                    phone_ed.setVisibility(View.INVISIBLE);


                }else{
                    editing = true;
                    name.setVisibility(View.INVISIBLE);
                    phone_number.setVisibility(View.INVISIBLE);
                    editing_t.setVisibility(View.VISIBLE);


                    start.setVisibility(View.VISIBLE);
                    last.setVisibility(View.VISIBLE);
                    phone_ed.setVisibility(View.VISIBLE);

                    start.setText(f_name);
                    last.setText(l_name);
                    phone_ed.setText(p_number);
                }

            }
        });


    }
}
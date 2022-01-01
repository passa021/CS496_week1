package com.example.cs496_week1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1# newInstance} factory method to
 * create an instance of this fragment.
 */



public class Fragment1 extends Fragment {
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.v("start","hihi Frag1");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        ArrayList<Contact> list = new ArrayList<Contact>();
        DBHelper mDBHelper = new DBHelper(getActivity().getApplicationContext());


        //DBHelper mDBHelper = new DBHelper(getActivity().getApplicationContext());
        //mDBHelper.InsertContact("01000000000","Park","Doyun");
        list = mDBHelper.getContacts();

        rootView.findViewById(R.id.add_button).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //mDBHelper.InsertContact("01000000000","Park","Doyun");
                        Intent intent = new Intent(v.getContext(), addContact.class);
                        //intent.putExtra("dbHelper",mDBHelper);
                        startActivity(intent);

                        //Log.v("click", "okay");

                    }
                }
        );


        SimpleTextAdapter adapter = new SimpleTextAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        return rootView;
    }
}
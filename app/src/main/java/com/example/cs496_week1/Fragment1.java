package com.example.cs496_week1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        ArrayList<Contact> list= new ArrayList<Contact>();
        /*try {
            Reader reader = new FileReader("../../../../../data/phone_number");
            Log.v("reader", "success");
            JSONObject jsonObj = new JSONObject();



            for (int i= 0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String phone_number = (String) jsonObject.get("phone number");
                list.add(phone_number);
            }
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }*/

        /*String jsondata = "[{\"phone_number\":\"01065634353\",\"first_name\":\"Ashton\",\"last_name\":\"Bleackly\"},\n" +
                "{\"phone_number\":\"01029973634\",\"first_name\":\"Evered\",\"last_name\":\"Bundock\"},\n" +
                "{\"phone_number\":\"01059910020\",\"first_name\":\"Roger\",\"last_name\":\"Braime\"},\n" +
                "{\"phone_number\":\"01050243554\",\"first_name\":\"Carilyn\",\"last_name\":\"Wilgar\"},\n" +
                "{\"phone_number\":\"01051735958\",\"first_name\":\"Marcel\",\"last_name\":\"Prattin\"},\n" +
                "{\"phone_number\":\"01063952052\",\"first_name\":\"Jud\",\"last_name\":\"Anten\"}]";

         */
        String file = "phone_number.json";
        String jsondata = null;
        try {
            jsondata = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error",file);
        }

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error","hihi2");
        }
        for (int i= 0;i<jsonArray.length();i++){
            JSONObject jsonObject= null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("error","hihi3");
            }
            Contact contact = new Contact();
            try {


                String phone_number = (String) jsonObject.get("phone_number");
                String first_name = (String) jsonObject.get("first_name");
                String last_name = (String) jsonObject.get("last_name");

                contact.Setting(phone_number,first_name,last_name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list.add(contact);
        }


        SimpleTextAdapter adapter = new SimpleTextAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
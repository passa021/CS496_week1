package com.example.cs496_week1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contact:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment1()).commit();

                    return true;
                    //break;
                case R.id.navigation_gallery:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment2()).commit();

                    return true;
                    //break;
                case R.id.navigation_frag3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment3()).commit();

                    //break;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment1()).commit();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}

package com.example.cs496_week1;

import android.app.ActionBar;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static MainActivity activity;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //TextView menu_text = (TextView)findViewById(R.id.toolbartext);
            switch (item.getItemId()) {
                case R.id.navigation_contact:
                    if (getSupportFragmentManager().findFragmentByTag("Contact") != null) {
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("Contact")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment1(), "Contact").commit();
                    }
                    //보이는거 처리
                    if(getSupportFragmentManager().findFragmentByTag("Gallery") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("Gallery")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("TODO") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("TODO")).commit();
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment1()).commit();

                    //menu_text.setText("Contact");
                    return true;
                    //break;
                case R.id.navigation_gallery:
                    if (getSupportFragmentManager().findFragmentByTag("Gallery") != null){
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("Gallery")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment2(), "Gallery").commit();
                    }

                    if(getSupportFragmentManager().findFragmentByTag("Contact") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("Contact")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("TODO") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("TODO")).commit();
                    }

                    //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment2()).commit();
                    //Toolbar toolbar = findViewById(R.id.toolbar);
                    //toolbar.inflateMenu(R.menu.gallery_menu);

                    //menu_text.setText("Gallery");
                    return true;
                    //break;
                case R.id.navigation_TODO:
                    if (getSupportFragmentManager().findFragmentByTag("TODO") != null) {
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("TODO")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment3(), "TODO").commit();
                    }
                    //보이는거 처리
                    if(getSupportFragmentManager().findFragmentByTag("Gallery") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("Gallery")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("Contact") !=null){
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("Contact")).commit();
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment3()).commit();
                    //menu_text.setText("frag3");
                    //break;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment2(), "Gallery");
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment3(), "TODO");
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment1(), "Contact").commit();
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        Toast.makeText(getApplicationContext(), "swipe를 통해 목록을 지울 수 있습니다.", Toast.LENGTH_SHORT).show();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}

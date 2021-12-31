package com.example.cs496_week1;

import android.app.ActionBar;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //TextView menu_text = (TextView)findViewById(R.id.toolbartext);
            switch (item.getItemId()) {
                case R.id.navigation_contact:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment1()).commit();

                    //menu_text.setText("Contact");
                    return true;
                    //break;
                case R.id.navigation_gallery:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment2()).commit();
                    //Toolbar toolbar = findViewById(R.id.toolbar);
                    //toolbar.inflateMenu(R.menu.gallery_menu);

                    //menu_text.setText("Gallery");
                    return true;
                    //break;
                case R.id.navigation_frag3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Fragment3()).commit();
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
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Fragment1()).commit();
        //getSupportActionBar().setDisplayShowTitleEnabled(false);



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

package com.example.cs496_week1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity {
    Activity act = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        setImage(imageView);

        findViewById(R.id.back_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        ImageActivity.super.onBackPressed();

                    }
                }
        );
    }

    private void setImage(ImageView imageView) {
        Intent receivedIntent = getIntent();

        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }

}

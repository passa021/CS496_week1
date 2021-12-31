package com.example.cs496_week1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class ImageClickListener implements OnClickListener {

    Context context;
    Uri data;
    //int imageID;

    /*public ImageClickListener(Context context, int imageID) {
        this.context = context;
        this.imageID = imageID;
    }*/

    public ImageClickListener(Context context, Uri data) {
        this.context = context;
        this.data = data ;
    }

    public void onClick(View v){
        Intent intent = new Intent(context, ImageActivity.class);
        //intent.putExtra("image ID", imageID);
        intent.putExtra("imageURI", data);
        context.startActivity(intent);
    }
}

package com.example.cs496_week1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    Context context = null;
    //File file = null;

    //int[] imageIDs = null;
    ArrayList<Uri> mData = null;

    /*public ImageAdapter(Context context, int[] imageIDs, File file){
        this.context = context;
        this.imageIDs = imageIDs;
    }*/

    public ImageAdapter(ArrayList<Uri> list, Context context){
        this.context = context;
        this.mData = list;
    }

    /*public ImageAdapterFile(Context context, File file){
        this.context = context;
        this.file = file;
    }*/

    public int getCount() {
        return (null != mData) ? mData.size() : 0;
    }

    public Object getItem(int position) {
        return (null != mData) ? mData.get(position) : 0;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;

        if(null != convertView){
            imageView = (ImageView) convertView;
        }
        else {
            //BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inSampleSize = 128;
            //Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[position],options);
            //bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);

            //imageView = convertView.findViewById(R.id.gridViewImages);
            imageView = new ImageView(context);
            //Glide.with(context).load(imageIDs[position]).override(100,100).centerCrop().into(imageView);
            Glide.with(context).load(mData.get(position)).override(300,300).centerCrop().into(imageView);
            imageView.setAdjustViewBounds(true);
            //imageView.setImageBitmap(bmp);


            //ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs[position]);
            ImageClickListener imageViewClickListener = new ImageClickListener(context, mData.get(position));
            imageView.setOnClickListener(imageViewClickListener);
        }

        return imageView;
    }
}



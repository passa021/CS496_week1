package com.example.cs496_week1;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    private static final String TAG = "MultiImageActivity";
    ArrayList<Uri> uriList = new ArrayList<>(); //이미지 uri 담음
    GridView gridViewImages;
    ImageAdapter imageAdapter;

    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.gallery_import:
                    //Toast.makeText(getActivity(),"mafee", Toast.LENGTH_SHORT).show();

                    PermissionListener permissionListener = new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {
                            //Toast.makeText(getActivity(), "권한 허가", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPermissionDenied(ArrayList<String> arrayList) {
                            Toast.makeText(getActivity(), "권한 거부", Toast.LENGTH_SHORT).show();
                        }
                    };
                    TedPermission.with(getActivity()).setPermissionListener(permissionListener).setRationaleMessage(getResources().getString(R.string.permission_2)).setDeniedMessage(getResources().getString(R.string.permission_1)).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA).check();

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,PICK_FROM_ALBUM);


                    return true;
                case R.id.gallery_delete:
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_2,container, false);


        /*int[] imageIDs = new int[]{
                R.drawable.image_01,
                R.drawable.image_02,
                R.drawable.image_03,
                R.drawable.image_04,
                R.drawable.image_05,
                R.drawable.image_06,
                R.drawable.image_07,
                R.drawable.image_08,
                R.drawable.image_09,
                R.drawable.image_10,
                R.drawable.image_11,
                R.drawable.image_12,
                R.drawable.image_13,
                R.drawable.image_14,
                R.drawable.image_15,
                R.drawable.image_16,
                R.drawable.image_17,
                R.drawable.image_18,
                R.drawable.image_19,
                R.drawable.image_20,

        };*/

        gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
        //GridView gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
        //ImageAdapter imageAdapter = new ImageAdapter(getActivity(), imageIDs, null);
        //gridViewImages.setAdapter(imageAdapter);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        TextView textView = (TextView) rootView.findViewById(R.id.toolbartext);
        textView.setText("Gallery");

        toolbar.inflateMenu(R.menu.gallery_menu);
        toolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);

        setHasOptionsMenu(true);
        //onCreateOptionsMenu(gallery_menu, getActivity().getMenuInflater());

        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_FROM_ALBUM){
            if(data==null){
                Toast.makeText(getActivity(),"이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                if (data.getClipData() == null) {//이미지 하나선
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList.add(imageUri);

                    imageAdapter = new ImageAdapter(uriList, getActivity());
                    gridViewImages.setAdapter(imageAdapter);

                } else {
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    if (clipData.getItemCount() > 10) {
                        Toast.makeText(getActivity(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "multiple choice");

                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri imageUri = clipData.getItemAt(i).getUri();
                            try {
                                uriList.add(imageUri);
                            } catch (Exception e) {
                                Log.e(TAG, "File select error", e);
                            }
                        }
                        imageAdapter = new ImageAdapter(uriList, getActivity());
                        gridViewImages.setAdapter(imageAdapter);

                    }
                }
            }
        }

        /*if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();

            Cursor cursor = null;

            try{
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getActivity().getContentResolver().query(photoUri,proj,null,null,null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));
            } finally {
                if (cursor !=null){
                    cursor.close();
                }
            }

            //ImageAdapter imageAdapter = new ImageAdapter(getActivity(),null,tempFile);


            //이미지 set

        }*/
    }
    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.gallery_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.gallery_import:
                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //Toast.makeText(getActivity(), "권한 허가", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> arrayList) {
                        Toast.makeText(getActivity(), "권한 거부", Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(getActivity()).setPermissionListener(permissionListener).setRationaleMessage(getResources().getString(R.string.permission_2)).setDeniedMessage(getResources().getString(R.string.permission_1)).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA).check();

                Toast.makeText(getActivity(),"mafee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                //intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_FROM_ALBUM);
                return super.onOptionsItemSelected(item);


            case R.id.gallery_delete:
                Toast.makeText(getActivity(),"mafeㄹㄷㄹe", Toast.LENGTH_SHORT).show();

                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }*/





}
package com.example.cs496_week1;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    private static final String TAG = "MultiImageActivity";
    private SharedPreferences preferences;
    private String PREFERENCE_LOG_FILE = "uri";
    private JSONArray array = new JSONArray();
    private SharedPreferences.Editor editor;
    ArrayList<Uri> uriList = new ArrayList<>(); //이미지 uri 담음
    GridView gridViewImages;
    ImageAdapter imageAdapter;

    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.gallery_import:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_FROM_ALBUM);
                    return true;

                case R.id.gallery_camera:
                    Toast.makeText(getActivity(),"camera", Toast.LENGTH_SHORT).show();
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    camera_intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    camera_intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    camera_intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                    startActivityForResult(camera_intent, PICK_FROM_CAMERA);
                    break;
            }
        return true;}
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }
            @Override
            public void onPermissionDenied(ArrayList<String> arrayList) {
                Toast.makeText(getActivity(), "권한 거부", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getActivity()).setPermissionListener(permissionListener).setRationaleMessage(getResources().getString(R.string.permission_2)).setDeniedMessage(getResources().getString(R.string.permission_1)).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).check();

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_2, container, false);
        gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
        gridViewImages.setSaveEnabled(true);
        preferences = getActivity().getSharedPreferences("Uri", MODE_PRIVATE);
        getPreferences();
        imageAdapter = new ImageAdapter(uriList,getActivity());
        gridViewImages.setAdapter(imageAdapter);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        TextView textView = (TextView) rootView.findViewById(R.id.toolbartext);
        textView.setText("Gallery");
        toolbar.inflateMenu(R.menu.gallery_menu);
        toolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
        setHasOptionsMenu(true);
        return rootView;
    }

    private void getPreferences(){
        String json = preferences.getString(PREFERENCE_LOG_FILE,null);
        if(json !=null){
            try{
                JSONArray array = new JSONArray(json);
                //uriList.clear();
                for(int i = 0 ; i <array.length(); i++){
                    String uri = array.optString(i);
                    uriList.add(Uri.parse(uri));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        } else {
        }
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editor = preferences.edit();

        if (requestCode == PICK_FROM_ALBUM) {
            if (data == null) {
                Toast.makeText(getActivity().getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
            } else {
                if (data.getClipData() == null) {//이미지 하나선
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList.add(imageUri);
                    array.put(imageUri);
                    editor.putString(PREFERENCE_LOG_FILE, array.toString());
                    editor.commit();
                    imageAdapter = new ImageAdapter(uriList, getActivity());
                    gridViewImages.setAdapter(imageAdapter);

                } else {
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    if (clipData.getItemCount() > 10) {
                        Toast.makeText(getActivity().getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "multiple choice");

                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri imageUri = clipData.getItemAt(i).getUri();
                            try {
                                uriList.add(imageUri);
                                array.put(imageUri);
                                editor.putString(PREFERENCE_LOG_FILE, array.toString());
                                editor.commit();
                            } catch (Exception e) {
                                Log.e(TAG, "File select error", e);
                            }
                        }
                        imageAdapter = new ImageAdapter(uriList, getActivity());
                        gridViewImages.setAdapter(imageAdapter);

                    }
                }
            }
        } else{
            if (data == null){
                Toast.makeText(getActivity().getApplicationContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }else {
                Bundle extras = data.getExtras();
                Uri photoURI = getImageUri(getActivity(), (Bitmap) extras.get("data"));

                uriList.add(photoURI);
                array.put(photoURI);

                editor.putString(PREFERENCE_LOG_FILE, array.toString());
                editor.commit();

                imageAdapter = new ImageAdapter(uriList, getActivity());
                gridViewImages.setAdapter(imageAdapter);
            }
        }
    }
}

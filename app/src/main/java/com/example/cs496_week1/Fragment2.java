package com.example.cs496_week1;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    String imageFilePath;
    private File tempFile;
    private static final String TAG = "MultiImageActivity";
    ArrayList<Uri> uriList = new ArrayList<>(); //????????? uri ??????
    GridView gridViewImages;
    ImageAdapter imageAdapter;


    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.gallery_import:
                    //Toast.makeText(getActivity(),"mafee", Toast.LENGTH_SHORT).show();


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


                    // @@@@@@@@@@@@@@@ added @@@@@@@@@@@@

                    /*if(camera_intent.resolveActivity(getActivity().getPackageManager()) != null){
                        File photoFile = null;
                        try{
                            photoFile = createImageFile();
                        }catch(IOException ex){
                            //Toast.makeText(getActivity(),"camedsawra", Toast.LENGTH_SHORT).show();
                        }

                        if (photoFile != null){
                            Uri photoURI = FileProvider.getUriForFile(getActivity().getApplicationContext(),"com.example.cs496_week1.fileprovider",photoFile);
                            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(camera_intent, PICK_FROM_CAMERA);
                        }
                    }*/

                    ////////////////////////////////

                    /*if(camera_intent.resolveActivity(getActivity().getPackageManager())!=null){
                        File photoFile = null;
                        try{
                            photoFile = createImageFile();

                        }catch(IOException ex){

                        }

                        if(photoFile!=null){
                            photoURI = FileProvider.getUriForFile(Objects.requireNonNull(requireActivity()),BuildConfig.APPLICATION_ID+".provider",photoFile);
                        }
                    }*/

                    //File photoFile = null;
                    //camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    /*File photoFile = null;
                    try{
                        photoFile = createImageFile();
                    } catch (IOException ex){

                    }
                    if (photoFile != null) {
                        Uri photoURI = Uri.fromFile(photoFile);
                        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }*/
                    startActivityForResult(camera_intent, PICK_FROM_CAMERA);



                    /*if (camera_intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        File photoFile = null;

                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                        }
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(getActivity(), "com.gilbomi.fileprovider", photoFile);
                            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            //setResult(9001, camera_intent);
                            startActivityForResult(camera_intent, PICK_FROM_CAMERA);
                            //launcher.lauch(camera_intent);
                            //getActivity().setResult(9001, camera_intent);
                            //launcher.launch(camera_intent);
                        }
                    }*/
                    //startActivityForResult(camera_intent, PICK_FROM_CAMERA);
                    break;
            }
        return true;}
    };

    private SharedPreferences preferences;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                //Toast.makeText(getActivity(), "?????? ??????", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> arrayList) {
                Toast.makeText(getActivity(), "?????? ??????", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getActivity()).setPermissionListener(permissionListener).setRationaleMessage(getResources().getString(R.string.permission_2)).setDeniedMessage(getResources().getString(R.string.permission_1)).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).check();


        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_2, container, false);

        gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
        gridViewImages.setSaveEnabled(true);
        //GridView gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
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
        //onCreateOptionsMenu(gallery_menu, getActivity().getMenuInflater());

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
        }else {

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //File storageDir = new File(Environment.getExternalStorageDirectory() + "/test/");
        if (!storageDir.exists()) storageDir.mkdir();

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    private String PREFERENCE_LOG_FILE = "uri";
    private JSONArray array = new JSONArray();
    private SharedPreferences.Editor editor;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editor = preferences.edit();

        if (requestCode == PICK_FROM_ALBUM) {
            if (data == null) {
                Toast.makeText(getActivity().getApplicationContext(), "???????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show();
            } else {
                if (data.getClipData() == null) {//????????? ?????????
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList.add(imageUri);

                    /*for(int i = 0; i<uriList.size(); i++){
                        array.put(uriList.get(i));
                    }*/
                    array.put(imageUri);

                    editor.putString(PREFERENCE_LOG_FILE, array.toString());
                    editor.commit();
                    imageAdapter = new ImageAdapter(uriList, getActivity());
                    gridViewImages.setAdapter(imageAdapter);

                } else {
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    if (clipData.getItemCount() > 10) {
                        Toast.makeText(getActivity().getApplicationContext(), "????????? 10????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "multiple choice");

                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri imageUri = clipData.getItemAt(i).getUri();
                            try {
                                uriList.add(imageUri);
                                //JSONArray array = new JSONArray();
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
                //Toast.makeText(getActivity(),"after on activity", Toast.LENGTH_SHORT).show();
                //Uri photoURI = data.getData();
                Bundle extras = data.getExtras();

                Uri photoURI = getImageUri(getActivity(), (Bitmap) extras.get("data"));

                uriList.add(photoURI);
                array.put(photoURI);

                //JSONArray array = new JSONArray();
                /*for(int i = 0; i<uriList.size(); i++){
                    array.put(uriList.get(i));
                }*/

                editor.putString(PREFERENCE_LOG_FILE, array.toString());
                editor.commit();

                imageAdapter = new ImageAdapter(uriList, getActivity());
                gridViewImages.setAdapter(imageAdapter);
            }

            /*if (resultCode == RESULT_OK) {
                File file = new File(mCurrentPhotoPath);
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),Uri.fromFile(file));

                Uri photoURI = Uri.fromFile(file);
                uriList.add(photoURI);

                imageAdapter = new ImageAdapter(uriList, getActivity());
                gridViewImages.setAdapter(imageAdapter);
            }*/
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


            //????????? set

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
                        //Toast.makeText(getActivity(), "?????? ??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> arrayList) {
                        Toast.makeText(getActivity(), "?????? ??????", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(),"mafe?????????e", Toast.LENGTH_SHORT).show();

                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }*/


}

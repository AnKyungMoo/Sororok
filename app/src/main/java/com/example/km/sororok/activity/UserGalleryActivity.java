package com.example.km.sororok.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.km.sororok.R;
import com.example.km.sororok.adapter.GalleryAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class UserGalleryActivity extends AppCompatActivity {

    private static TextView totalImageCount;
    private static GridView gridGallery;
    private static Button backButton;
    private static GalleryAdapter galleryAdapter;
    private static ArrayList<String> galleryImageUrls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_user_gallery);

        //Intent intent = getIntent();

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            initComponent();
            fetchGalleryImages();
            setUpGridView();
        }

    }

    private void fetchGalleryImages() {

        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date
        Cursor imgCursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");

        galleryImageUrls = new ArrayList<>();//Init array

        for (int i = 0; i < imgCursor.getCount(); i++) {
            imgCursor.moveToPosition(i);
            int dataColumnIndex = imgCursor.getColumnIndex(MediaStore.Images.Media.DATA);//get column index
             galleryImageUrls.add(imgCursor.getString(dataColumnIndex));//get Image from column index
             System.out.println("Array path : " + galleryImageUrls.get(i));
        }
        totalImageCount.setText("전체보기("+galleryImageUrls.size()+")");
    }

    private void setUpGridView() {
        galleryAdapter = new GalleryAdapter(UserGalleryActivity.this, galleryImageUrls);
        gridGallery.setAdapter(galleryAdapter);
        gridGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Intent intent;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Uri uri = Uri.parse(galleryAdapter.getItem(i).toString());
               // File file = new File(galleryAdapter.getItem(i).toString());

                if(i==0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(intent, 200);
                    }

                }else{
                    cropImage(uri);
                    /*Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.putExtra("photo_path", galleryAdapter.getItem(i).toString());
                    setResult(RESULT_OK, intent);
                    finish();*/
                }
            }
        });
    }

    public void cropImage(Uri uri){
        try{
            Intent CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            CropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            CropIntent.setDataAndType(uri, "image/*");
            Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 2);

        }catch (ActivityNotFoundException e){
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 200 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageB = (Bitmap)extras.get("data");
            Uri tempUri = getImageUri(getApplicationContext(), imageB);
            File finalFile = new File(getRealPathFromURI(tempUri));
            sendFirstPhotoPath(finalFile);
        } else if(requestCode == 2){
            Log.i("왔니","ㅔ");
        }
    }

    public void sendFirstPhotoPath(File filePath){
        Intent intent = new Intent();
        intent.putExtra("photo_path", filePath.getPath());
        setResult(RESULT_OK,intent);
        finish();
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Sororok", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void initComponent(){
        gridGallery = (GridView)findViewById(R.id.grid_user_gallery);
        totalImageCount = (TextView)findViewById(R.id.txt_total_image_count);
        backButton = (Button)findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //버전 확인
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    100);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                100);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
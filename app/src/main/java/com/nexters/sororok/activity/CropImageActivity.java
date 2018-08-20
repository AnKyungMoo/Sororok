package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nexters.sororok.R;
import com.naver.android.helloyako.imagecrop.view.ImageCropView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CropImageActivity extends AppCompatActivity{
    private Button cropBtn, ratioBtn1, ratioBtn2, ratioBtn3, ratioBtn4, ratioBtn5, cancelBtn;
    private ImageCropView imageCropView;
    private View.OnClickListener listener;
    private String crop_path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        Intent intent = getIntent();
        crop_path = intent.getExtras().getString("crop_image_path");

        initComponent();
        setImageCropView();
        buttonListener();

    }

    public void actionButton(Button button){
        switch (button.getId()){
            case R.id.btn_crop_save:
                if (!imageCropView.isChangingScale()) {
                    Bitmap b = imageCropView.getCroppedImage();
                    if (b != null) {
                        cropImageIntent(bitmapConvertToFile(b).toString());
                    } else {
                    }
                }
                break;
            case R.id.btn_crop_cancel:
                finish();
                break;
            case R.id.btn_ratio1:
                if (isPossibleCrop(4, 3))
                    imageCropView.setAspectRatio(4, 3);
                break;
            case R.id.btn_ratio2:
                if (isPossibleCrop(1, 1))
                    imageCropView.setAspectRatio(1, 1);
                break;
            case R.id.btn_ratio3:
                if (isPossibleCrop(3, 4))
                    imageCropView.setAspectRatio(3, 4);
                break;
            case R.id.btn_ratio4:
                if (isPossibleCrop(2, 3))
                    imageCropView.setAspectRatio(2, 3);
                break;
            case R.id.btn_ratio5:
                if (isPossibleCrop(9, 16))
                    imageCropView.setAspectRatio(9, 16);
                break;
        }
    }

    public void cropImageIntent(String crop_result){
        Intent intent = new Intent();
        intent.putExtra("crop_result",crop_result);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void initComponent(){
        cropBtn = (Button)findViewById(R.id.btn_crop_save);
        cancelBtn = (Button)findViewById(R.id.btn_crop_cancel);
        ratioBtn1 = (Button)findViewById(R.id.btn_ratio1);
        ratioBtn2 = (Button)findViewById(R.id.btn_ratio2);
        ratioBtn3 = (Button)findViewById(R.id.btn_ratio3);
        ratioBtn4 = (Button)findViewById(R.id.btn_ratio4);
        ratioBtn5 = (Button)findViewById(R.id.btn_ratio5);
        imageCropView = (ImageCropView)findViewById(R.id.layout_image_crop);
    }

    public void setImageCropView(){
        imageCropView.setGridInnerMode(ImageCropView.GRID_ON);
        imageCropView.setGridOuterMode(ImageCropView.GRID_ON);
        imageCropView.setImageFilePath(crop_path);
        imageCropView.setAspectRatio(1, 1);
    }

    public void buttonListener(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionButton((Button)view);
            }
        };

        cropBtn.setOnClickListener(listener);
        cancelBtn.setOnClickListener(listener);
        ratioBtn1.setOnClickListener(listener);
        ratioBtn2.setOnClickListener(listener);
        ratioBtn3.setOnClickListener(listener);
        ratioBtn4.setOnClickListener(listener);
        ratioBtn5.setOnClickListener(listener);
    }


    public File bitmapConvertToFile(Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        File bitmapFile = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory("image_crop_sample"), "");
            if (!file.exists()) {
                file.mkdir();
            }

            bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");
            fileOutputStream = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            MediaScannerConnection.scanFile(this, new String[]{bitmapFile.getAbsolutePath()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public  void onMediaScannerConnected() {

                }

                @Override
                public  void onScanCompleted(String path, Uri uri) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public  void run() {
                            //Toast.makeText(getApplicationContext(), "file saved", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }

        return bitmapFile;
    }

    private  boolean isPossibleCrop(int widthRatio, int heightRatio) {
        Bitmap bitmap = imageCropView.getViewBitmap();
        if (bitmap == null) {
            return false;
        }
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        if (bitmapWidth < widthRatio && bitmapHeight < heightRatio) {
            return false;
        } else {
            return true;
        }
    }
}

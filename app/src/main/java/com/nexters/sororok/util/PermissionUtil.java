package com.nexters.sororok.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * PermissionUtil 클래스
 * 권한 획득에 관련된 메소드들이 있는 클래스
 */
public class PermissionUtil {

    public static final int REQUEST_PERMISSION = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE};

    public static boolean checkPermission(Activity activity, String permission){
      int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
      if (permissionResult== PackageManager.PERMISSION_GRANTED) return true;
      else return false;
    }


    public static void requestExternalPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_PERMISSION);

    }


    public static boolean verifyPermission(int[] grantresults){
        if(grantresults.length<1){
            return false;
        }
        for(int result:grantresults){
            if(result!=PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }


}

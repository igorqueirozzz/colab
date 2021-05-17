package br.com.app.colab.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionCheck {

    public static boolean check(String[] permissions, Activity context, int requestCode){
        if(Build.VERSION.SDK_INT >= 23){
            List<String> permissionList = new ArrayList<>();
            for(String permission : permissions) {
                boolean isGranted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
                if (!isGranted){
                    permissionList.add(permission);
                }
            }

            if(permissionList.isEmpty()){
                return true;
            }else {
                String[] permissionToRequest = new String[permissionList.size()];
                permissionList.toArray(permissionToRequest);
                ActivityCompat.requestPermissions(context, permissionToRequest, requestCode);
            }

        }
        return true;
    }
}

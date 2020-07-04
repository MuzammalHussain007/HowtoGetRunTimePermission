package com.example.howtogetruntimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button mButton;

    private int SotragePermissionCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMultiplePermission();
            }
        });
    }

    private void getMultiplePermission() {
        List<String> permissionList = new ArrayList<>();
        int External_Read_Storage = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int Camera_Permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        int Location_Permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int Bluetooth = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH);
        if (External_Read_Storage == PackageManager.PERMISSION_DENIED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (Camera_Permission == PackageManager.PERMISSION_DENIED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (Location_Permission == PackageManager.PERMISSION_DENIED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (Bluetooth == PackageManager.PERMISSION_DENIED) {
            permissionList.add(Manifest.permission.BLUETOOTH);
        }
        if (permissionList.size() > 0) {
            String[] permissionArray = new String[permissionList.size()];

            for (int i = 0; i < permissionArray.length; i++) {
                permissionArray[i] = permissionList.get(i);
            }
            ActivityCompat.requestPermissions(MainActivity.this, permissionArray, SotragePermissionCode);

        } else {
            Toast.makeText(getApplicationContext(), " All permission Granted", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==SotragePermissionCode)
        {
            for (int i=0 ; i<permissions.length;i++)
            {
                if (permissions.length > 0 && grantResults[i]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext() , permissions[i]+"SuccessFully",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void connection() {

        mButton = (Button) findViewById(R.id.uploadImage);
    }
}
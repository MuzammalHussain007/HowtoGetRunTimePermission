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


public class MainActivity extends AppCompatActivity {
    private Button mButton;

    private int SotragePermissionCode=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             askStoragePermission();

            }
        });
    }

    private void askStoragePermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
        ==PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"Permission is all ready Granted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this
        ,Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Permission Require")
                    .setMessage("Purmission Require for This Purpose")
                    .setPositiveButton("Granted", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},SotragePermissionCode);

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this
                    ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},SotragePermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==SotragePermissionCode)
        {
            if (grantResults.length>= 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permission not Granted",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void connection() {

        mButton = (Button) findViewById(R.id.uploadImage);
    }
}
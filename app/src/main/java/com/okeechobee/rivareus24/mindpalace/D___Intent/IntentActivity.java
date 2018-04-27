package com.okeechobee.rivareus24.mindpalace.D___Intent;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.R;

// https://developer.android.com/guide/components/intents-common.html
public class IntentActivity extends AppCompatActivity {

    private Button btnOpenGoogle;
    private Button btnOpenChooser;
    private Button btnOpenCamera;
    private TextView tvText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        Intent i = getIntent();
        String s = i.getStringExtra(Intent.EXTRA_TEXT);

        tvText = (TextView) findViewById(R.id.tvText);
        tvText.setText(s);

        btnOpenGoogle = (Button) findViewById(R.id.btnOpenGoogle);
        btnOpenGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.google.com");
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnOpenChooser = (Button) findViewById(R.id.btnOpenChooser);
        btnOpenChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.google.com");
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                Intent chooser = Intent.createChooser(intent, "Titolo chooser");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });

        btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCamera(v);
            }
        });


    }

    // PERMISSION A RUNTIME
    @TargetApi(Build.VERSION_CODES.M)
    public void showCamera(View view){
        if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED){
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else{
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                Toast.makeText(IntentActivity.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            } else {
                Toast.makeText(IntentActivity.this, "Permission not granted", Toast.LENGTH_LONG).show();
            }
        } else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

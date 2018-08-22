package com.example.shubham7stark.speechtotext;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAndroid, btnCloud, btnLivai, btnMediaAcc, btnMicAcc, btnInternetAcc;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 102;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnAndroid = (Button)findViewById(R.id.button_android_speech);
        btnCloud = (Button)findViewById(R.id.button_google_cloud_speech);
        btnLivai = (Button)findViewById(R.id.button_livai_speech);

        btnMediaAcc = (Button)findViewById(R.id.button_media_access);
        btnMicAcc = (Button)findViewById(R.id.button_mic_access);
        btnInternetAcc = (Button)findViewById(R.id.button_internet_access);

        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AndroidSpeechToTextActivity.class));
            }
        });

        btnCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GoogleCloudSpeechToTextActivity.class));
            }
        });

        btnLivai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LivAISpeechToTextActivity.class));
            }
        });

        btnMediaAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMediaPermission();
            }
        });

        btnMicAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMicPermission();
            }
        });

        btnInternetAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetPermission();
            }
        });

    }

    private void checkMicPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.RECORD_AUDIO)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.RECORD_AUDIO},
                            MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                }
            } else {
                Toast.makeText(this, "Thanks! You have already granted permission!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkMediaPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            } else {
                Toast.makeText(this, "Thanks! You have already granted permission!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkInternetPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.INTERNET)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.INTERNET},
                            MY_PERMISSIONS_REQUEST_INTERNET);
                }
            } else {
                Toast.makeText(this, "Thanks! You have already granted permission!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}

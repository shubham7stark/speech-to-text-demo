package com.example.shubham7stark.speechtotext;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;

import ai.liv.s2tlibrary.Speech2TextIntent;
import ai.liv.s2tlibrary.model.CanonicalData;
import ai.liv.s2tlibrary.model.S2TError;
import ai.liv.s2tlibrary.model.Transcription;

public class LivAISpeechToTextActivity extends AppCompatActivity {
    EditText editText;
    ToggleButton toggleButton;

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 102;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 103;

    private static final String TAG = "Liv.AI SpeechToText";
    boolean isRecording = false;

    Speech2TextIntent.Speech2TextIntentCallback callbackFromS2T;
    Speech2TextIntent s2TIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liv_aispeech_to_text);
        checkPermission();
        //Google-Translator API Handling
        editText = (EditText) findViewById(R.id.livai_editText);
        toggleButton = (ToggleButton) findViewById(R.id.livai_button);

        callbackFromS2T = new Speech2TextIntent.Speech2TextIntentCallback() {
            @Override
            public void onTranscriptionReceived(ArrayList<Transcription> transcriptions, CanonicalData Data) {
                //Write Code to handle the list of transcriptions in streaming mode, received with their confidence scores
                if (transcriptions.size() > 0) {
                    for(Transcription transcription : transcriptions){
                        Log.d(TAG, "Transcription:"+transcription.getText()+", English Transcription:"+transcription.getEnglishText()+", Confidence:"+transcription.getConfidence());
                        editText.setText("Full: "+transcription.getText());
                    }
                }
            }

            @Override
            public void onPartialTranscriptionReceived(ArrayList<Transcription> transcriptions) {
                //Write Code to handle the list of transcriptions in non streaming mode, received with their confidence scores
                if (transcriptions.size() > 0) {
                    for(Transcription transcription : transcriptions){
                        Log.d(TAG, "Partial Transcription:"+transcription.getText()+", English Transcription:"+transcription.getEnglishText()+", Confidence:"+transcription.getConfidence());
                        editText.setText("Streaming: "+transcription.getText());
                    }
                }
            }

            @Override
            public void onError(S2TError error) {
                //Write code to handle each error from the error codes by comparing error.errorCode to constants in Error class
                if(error.errorCode == S2TError.ERROR_NO_USER_ID){
                    Log.d(TAG, "No user id,"+error.message);
                }
                toggleButton.setChecked(false);
            }


            @Override
            public void onRecordingEnd() {
                //Called when recording is stopped and SDK is awaiting a result
                Log.d(TAG,"onRecordingEnd");
                toggleButton.setChecked(false);
            }

            @Override
            public void onAmplitudeChanged(double amplitude) {
                //Callback Every 60ms with average amplitude for the duration, for animation drawing.
                Log.d(TAG,"onAmplitudeChanged"+amplitude);
            }

            @Override
            public void onTransactionEnd() {
                //Called when service is stopped
                //ripple.setVisibility(View.GONE);
                isRecording = false;
                toggleButton.setChecked(false);

            }
        };

        s2TIntent = new Speech2TextIntent.Speech2TextIntentBuilder(this, callbackFromS2T)
                .setLanguage(Speech2TextIntent.LANGUAGE_HINDI)
                .setView(Speech2TextIntent.NO_VIEW)
                .setStreaming(true)
                .build();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    s2TIntent.setLanguage(Speech2TextIntent.LANGUAGE_HINDI);
                    s2TIntent.startService();
                } else {
                    //s2TIntent.stopService();
                }
            }

        });

    }


    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.RECORD_AUDIO)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.RECORD_AUDIO},
                            MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                }
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.INTERNET)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.INTERNET},
                            MY_PERMISSIONS_REQUEST_INTERNET);
                }
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            }

        }
    }


}

package com.zfm.audiorecoder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import utils.AudioRecorderHelper;

public class MainActivity extends AppCompatActivity {
    AudioRecorderHelper audioRecorderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioRecorderHelper = new AudioRecorderHelper(this);

        findViewById(R.id.start_record).setOnClickListener(v -> requestRecordAudioPermission());
        findViewById(R.id.stop_record).setOnClickListener(v -> audioRecorderHelper.stopRecording());
        findViewById(R.id.play_audio).setOnClickListener(v -> audioRecorderHelper.playRecording());

    }

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, you can start recording
                    audioRecorderHelper.startRecording();
                } else {
                    // Permission denied, show a message or disable the record button
                }
                break;
        }
    }

    private void requestRecordAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        } else {
            // Permission already granted, you can start recording
            audioRecorderHelper.startRecording();
        }
    }
}
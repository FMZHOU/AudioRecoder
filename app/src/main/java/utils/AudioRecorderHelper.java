package utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

public class AudioRecorderHelper {
    private static final String TAG = "AudioRecorderHelper";


    private Context context;
    private String filePath;
    private MediaRecorder mediaRecorder;
    public AudioRecorderHelper(Context context) {
        this.context = context;
        // Set the output file path
        String packageName = context.getApplicationContext().getPackageName();
        filePath = "/data/data/" + packageName + "/audio_record.3gp";
    }

    public void startRecording() {
        Log.d(TAG, "startRecording: exec");
        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(filePath);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        Log.d(TAG, "stopRecording: exec");
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
        }
    }

    public void playRecording() {
        Log.d(TAG, "playRecording: exec");
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            Log.d(TAG, "playRecording: " + e);
        }
    }

}

package com.example.automotivedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int MICROPHONE_PERMISSION_CODE=200;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    Button button1,button2,button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

       if(isMicrophonePresent()){
           getMicrophonePermission();
       }

       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   mediaRecorder=new MediaRecorder();
                   mediaRecorder.setAudioSource((MediaRecorder.AudioSource.MIC));
                   mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                   mediaRecorder.setOutputFile(getRecordeingFile());
                   mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                   mediaRecorder.prepare();
                   mediaRecorder.start();
                   Toast.makeText(MainActivity.this, "Recording is start", Toast.LENGTH_SHORT).show();
                   //Toast.makeText(this, "Recording is start", Toast.LENGTH_SHORT).show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mediaRecorder.stop();
               mediaRecorder.release();
               mediaRecorder=null;
               Toast.makeText(MainActivity.this, "Recording is stop", Toast.LENGTH_SHORT).show();

               //Toast.makeText(this, "Recording is Stop", Toast.LENGTH_SHORT).show();
           }
       });
       button3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   mediaPlayer=new MediaPlayer();
                   mediaPlayer.setDataSource(getRecordeingFile());
                   mediaPlayer.prepare();
                   mediaPlayer.start();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });

    }
    private boolean isMicrophonePresent(){
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return  true;
        }
        else{
            return false;
        }
    }
    private void getMicrophonePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE=200);
        }
    }
    private String getRecordeingFile(){
        ContextWrapper contextWrapper=new ContextWrapper(getApplicationContext());
        File musicDirectory=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file=new File(musicDirectory,"testRecordingFile" + ".mp3");
        return  file.getPath();
    }
}
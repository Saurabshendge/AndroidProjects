package com.example.musicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    Button play,pause,stop;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        stop=findViewById(R.id.stop);
        player=new MediaPlayer();




        try {
            File file=new File(Environment.getExternalStorageDirectory(),"/downloads//home/androidteam/Downloads/Main Khiladi - Selfiee.mp3");
            player.setDataSource(file.getPath());
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Inside onstart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "inside onpause", Toast.LENGTH_SHORT).show();
    }
    public void play(View view){
        player.start();
    }
    public void pause(View view){
        player.pause();
    }
    public void stop(View view) throws IOException {
        player.stop();
    }
}
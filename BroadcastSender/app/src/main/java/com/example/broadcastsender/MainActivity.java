package com.example.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBroadcastSendBtnClicked(View v){
        Intent i = new Intent();
        getIntent().setAction("com.codingpursuits.myBroadcastMessage");
        getIntent().setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(getIntent());
    }
}
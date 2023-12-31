package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("com.codingpursuits.myBroadcastMessage");
        MyBroadcastReceiver objRec = new MyBroadcastReceiver();
        registerReceiver(objRec, intentFilter);
    }

}
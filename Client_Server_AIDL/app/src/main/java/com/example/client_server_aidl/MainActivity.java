package com.example.client_server_aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Messenger mService = null;
    boolean mBound = false;
    TextView fetchDataTextView;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        fetchDataTextView = (TextView) findViewById(R.id.textView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDataTextView.setVisibility(View.VISIBLE);
                onButtonClick(v);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    public void onButtonClick(View v) {
        if (mBound) {

            Message msg = Message.obtain(null, LocalService.FETCH_DATA_FROM_API, 0, 0);
            msg.replyTo = replyMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
}
private ServiceConnection mConnection=new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_SHORT).show();
        mService = new Messenger(service);
        mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        mBound = false;
    }

    };
    Messenger replyMessenger = new Messenger(new HandlerReplyMsg());
    // handler for message from service

    class HandlerReplyMsg extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String recdMessage = msg.obj.toString(); //msg received from service
            Toast.makeText(MainActivity.this, "Response Fetched", Toast.LENGTH_LONG).show();
            fetchDataTextView.setText(String.valueOf(recdMessage));
        }
    }
}
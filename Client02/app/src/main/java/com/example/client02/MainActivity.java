package com.example.client02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.server02.aidlServer;



public class MainActivity extends AppCompatActivity {
    private EditText num1,num2;
    TextView text3;
    Button btn1;
    aidlServer server;


    private ServiceConnection serviceConnection=new ServiceConnection() {
        private String Tag;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            server=aidlServer.Stub.asInterface(service);
            Log.d(Tag, "Service Connected");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=findViewById(R.id.text1);
        num2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        btn1=findViewById(R.id.btn1);

        Intent intent=new Intent("aidlServer");
        intent.setPackage("com.example.server02");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double a = Double.parseDouble(num1.getText().toString());
                    double b = Double.parseDouble(num2.getText().toString());
                    double answer = server.addNumber(a,b);
                    Log.i("msg",answer+" ");
                    text3.setText(answer+" ");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
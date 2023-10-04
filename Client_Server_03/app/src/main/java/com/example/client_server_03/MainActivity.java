package com.example.client_server_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Sample Activity", "Inside OnServiceConnected callback");
            IService remoteService = IService.Stub.asInterface(service);
            try {
                remoteService.registerCallBack(mCallback);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            try {
                Log.d("Sample Activity", "Before calling fromActivity in Activity");
                remoteService.fromActivity();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AIDL service", "Oncreate");
        startActivity();
        boolean ret = getApplicationContext().bindService(new Intent(MainActivity.this,MsgService.class),
                serviceConnection, Context.BIND_AUTO_CREATE);
        Log.d("Sample Activity", "After bind call " + ret);
    }
    public void startActivity(){
        Log.d("AIDL service", "startActivity");
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.example.client_server_03", "com.example.client_server_03.IServiceCallBack");
        startActivity(intent);
    }
    private IServiceCallBack mCallback = new IServiceCallBack.Stub() {
        @Override
        public void fromService() throws RemoteException {
            Log.d("Sample Activity", "Callback from Service");
            someMethodInActivity();
        }
        public void someMethodInActivity(){
            Log.d("Sample Activity", "someMethodInActivity");
        }
    };
}
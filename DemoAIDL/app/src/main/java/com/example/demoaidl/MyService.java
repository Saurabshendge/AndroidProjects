package com.example.demoaidl;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private static final String TAG = "AIDL Color Service";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    private final IDemoAidlInterface.Stub binder = new IDemoAidlInterface.Stub() {
        @Override
        public int getColors(){
            Random rdn = new Random();
            int colors = Color.argb(255, rdn.nextInt(256),rdn.nextInt(250),rdn.nextInt(200));
            //Toast.makeText(MyService.this, "Color : ", LENGTH_SHORT).show();
            Log.d(TAG, "getColor: "+ colors);
            return colors;
        }
    };
}
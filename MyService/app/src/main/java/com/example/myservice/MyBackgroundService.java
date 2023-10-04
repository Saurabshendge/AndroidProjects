package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.Provider;

public class MyBackgroundService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            Log.e("services", "services is running");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
        return super.onStartCommand();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

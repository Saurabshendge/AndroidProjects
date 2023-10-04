package com.example.server02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AdditionService extends Service {
    private final aidlServer.Stub mBinder=new aidlServer.Stub() {
        @Override
        public double addNumber(double a, double b) throws RemoteException {
            return a+b;
        }


    };
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }
}
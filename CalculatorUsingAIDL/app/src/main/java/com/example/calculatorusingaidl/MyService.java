package com.example.calculatorusingaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {


        return mBinder;
    }
    IMyAidlInterface.Stub mBinder=new IMyAidlInterface.Stub() {
        @Override
        public int miltipleTwoValuesTogether(int x, int y) throws RemoteException {
            return x*y;
        }

        @Override
        public int substractionTwoValuesTogether(int x, int y) throws RemoteException {
            return x-y;
        }

        @Override
        public int additionTwoValuesTogether(int x, int y) throws RemoteException {
            return x+y;
        }

        @Override
        public int divisionTwoValuesTogether(int x, int y) throws RemoteException {
            return x/y;
        }
    };
}
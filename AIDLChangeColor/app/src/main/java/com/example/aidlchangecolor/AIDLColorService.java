package com.example.aidlchangecolor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.Random;

public class AIDLColorService extends Service {
    public AIDLColorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return binder;
    }
    private  final IAIDLColourInterface.Stub binder=new IAIDLColourInterface.Stub() {
        @Override
        public int getColor() throws RemoteException {
            Random rnd=new Random();
            int color= Color.argb(255,rnd.nextInt(255),rnd.nextInt(256),rnd.nextInt(255));
            Log.d(TAG,"getColor : "+color);
            return color;
        }
    };
}
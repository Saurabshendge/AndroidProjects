package com.example.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartThread(View view){
        /*ExThread thread = new ExThread(10);
        thread.start();*/
        ExRunnable runnable = new ExRunnable(10);
        new Thread(runnable).start();
        //runnable.run();
    }

    public void StopThread(View view){

    }

    /*class ExThread extends Thread {
        int seconds;

        ExThread(int seconds){
            this.seconds=seconds;
        }
        @Override
        public void run() {
            for(int i=0;i<seconds;i++){
                Log.d(TAG, "StartThread: "+ i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    class ExRunnable implements Runnable{
        int seconds;
        ExRunnable(int seconds){
            this.seconds=seconds;
        }
        @Override
        public void run() {
            for(int i=0;i<seconds;i++){
                Log.d(TAG, "StartThread: "+ i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
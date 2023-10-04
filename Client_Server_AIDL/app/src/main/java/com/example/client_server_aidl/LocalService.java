package com.example.client_server_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;

import java.security.Provider;

public class LocalService extends Service {
    Messenger replyMessanger;
     static final int FETCH_DATA_FROM_API = 1;
    public static String responseData;
    //private Messenger mMessenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_LONG).show();
        return mMessenger.getBinder();
        
    }
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FETCH_DATA_FROM_API:
                    //  Bundle bundle = msg.getData();
                    Toast.makeText(LocalService.this,"Request Recieved",Toast.LENGTH_SHORT).show();
                    replyMessanger = msg.replyTo;
                    //getDataFromAPI();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    final Messenger mMessenger = new Messenger(new IncomingHandler());
//    public void getDataFromAPI() {
//
//        String url = "http://api.androidhive.info/contacts/";
//        StringRequest sr = null;
//        sr = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response != null) {
//                            responseData = response.toString();
//                            if (replyMessanger != null)
//                                try {
//                                    Message message = new Message();
//                                    message.obj = responseData;
//                                    replyMessanger.send(message);//replying / sending msg to activity
//                                } catch (RemoteException e) {
//                                    e.printStackTrace();
//                                }
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("volleyerror", error.toString());
//
//            }
//        });
//        VolleySingleton.getInstance(this).getRequestQueue().add(sr);
//    }

}

package com.example.client_server_03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class MsgService extends Service {
    final RemoteCallbackList<IServiceCallBack> mCallbacks = new RemoteCallbackList<IServiceCallBack>();
    public MsgService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return  mBinder;
    }
    private final IService.Stub mBinder = new IService.Stub() {
        @Override
        public void fromActivity() throws RemoteException {
            Log.d("AIDL service", "fromActivity method called from Activity");
            fromActivityProcess();
        }
        @Override
        public void registerCallBack(IServiceCallBack cb) throws RemoteException {
            if(cb!=null){
                Log.d("AIDL service", "registerCallBack registering");
                mCallbacks.register(cb);
            }
        }
        public void fromActivityProcess(){
            Log.d("AIDL service", "fromActivity1");
            Log.d("AIDL service", "Now to call someMethodInActivity");
            try {
                // this is very important - if u miss it u ll end in exception
                int N = mCallbacks.beginBroadcast();
                Log.d("AIDL service", "mCallBacks N value = " + N);
                // now for time being we will consider only one activity is bound to the service, so hardcode 0
                mCallbacks.getBroadcastItem(0).fromService();
                mCallbacks.finishBroadcast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    };

}
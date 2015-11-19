package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceMain extends Service {

	private static String TAG = "ServiceMain";

    @Override
    public void onCreate() {
        Log.e(TAG, "ServiceMain onCreate()");
        super.onCreate();
    }
     
    
    @Override
    public void onStart(Intent intent, int startId) 
    {
        super.onStart(intent, startId);
        Toast.makeText(this, "ServiceMain onStart()", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ServiceMain onStart()");

    }
    
    
    @Override
    public void onDestroy() 
    {
        Toast.makeText(this, "ServiceMain onDestroy()", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ServiceMain onDestroy()");
        super.onDestroy();
    }
    
    
    //��������ͨ��bindService ����֪ͨ��Serviceʱ�÷���������
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "ServiceMain onBind()", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ServiceMain onBind()");
        return null;
    }
    
    
    //��������ͨ��unbindService����֪ͨ��Serviceʱ�÷���������
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "ServiceMain onUnbind()", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ServiceMain onUnbind()");
        return super.onUnbind(intent);
    }
     
}
package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceMain extends Service
{
	WorkLoop _loop;
	UdpHelper _udp;
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "ServiceMain start...", Toast.LENGTH_SHORT).show();
        
        _loop = new WorkLoop("QAZWSX");
        _loop.start();
        
        _udp = new UdpHelper(new Handler(_loop.getLooper(),_loop));
		new Thread(_udp).start();
        
        return START_STICKY;
    }

	@Override
	public void onDestroy()
	{
		_loop.getLooper().quit();
		_udp.IsThreadDisable = false;
		
		super.onDestroy();
		Toast.makeText(this, "ServiceMain stop!", Toast.LENGTH_SHORT).show();
		
	}

	// 其他对象通过bindService 方法通知该Service时该方法被调用
	@Override
	public IBinder onBind(Intent intent)
	{
		Toast.makeText(this, "ServiceMain onBind()", Toast.LENGTH_SHORT).show();
		Log.e("XUE", "ServiceMain onBind()");
		return null;
	}

	// 其它对象通过unbindService方法通知该Service时该方法被调用
	@Override
	public boolean onUnbind(Intent intent)
	{
		Toast.makeText(this, "ServiceMain onUnbind()", Toast.LENGTH_SHORT)
				.show();
		Log.e("XUE", "ServiceMain onUnbind()");
		return super.onUnbind(intent);
	}

}
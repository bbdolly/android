package com.example.myservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static String TAG = "MainActivity";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();
		Log.e(TAG, "MainActivity");

        Button btnStart = (Button)findViewById(R.id.button00);
        Button btnStop = (Button)findViewById(R.id.Button01);
         
        //定义点击监听器
        OnClickListener ocl = new OnClickListener() {
             
			@Override
			public void onClick(View v) 
			{
                //显示指定  intent所指的对象是个   service
				
                Intent intent = new Intent(MainActivity.this,ServiceMain.class);
                switch(v.getId()){
                case R.id.button00:
                    //开始服务
                    startService(intent);
                    break;
                case R.id.Button01:
                    //停止服务
                    stopService(intent);
                    break;
                }
            }

        };
         
         //绑定点击监听
        btnStart.setOnClickListener(ocl);
        btnStop.setOnClickListener(ocl);
	}


	
    //定义服务链接对象
    final ServiceConnection conn = new ServiceConnection() {
         
        @Override
        public void onServiceDisconnected(ComponentName name) 
        {
            Log.e(TAG, "ServiceMain onSeviceDisconnected");
        }
         
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) 
        {
            Log.e(TAG, "ServiceMain onServiceConnected");
        }
    };
}

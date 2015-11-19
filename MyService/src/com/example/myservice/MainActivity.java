package com.example.myservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.ComponentName;
import android.content.Context;
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
        Button btnBind = (Button)findViewById(R.id.Button02);
        Button btnUnbind = (Button)findViewById(R.id.Button03);
         
        //������������
        OnClickListener ocl = new OnClickListener() {
             
			@Override
			public void onClick(View v) 
			{
                //��ʾָ��  intent��ָ�Ķ����Ǹ�   service
				
                Intent intent = new Intent(MainActivity.this,ServiceMain.class);
                switch(v.getId()){
                case R.id.button00:
                    //��ʼ����
                    startService(intent);
                    break;
                case R.id.Button01:
                    //ֹͣ����
                    stopService(intent);
                    break;
                case R.id.Button02:
                    //�󶨷���
                    bindService(intent, conn, Context.BIND_AUTO_CREATE);
                    break;
                case R.id.Button03:
                    //������
                    unbindService(conn);
                    break;
                }
            }

        };
         
         //�󶨵������
        btnStart.setOnClickListener(ocl);
        btnStop.setOnClickListener(ocl);
        btnBind.setOnClickListener(ocl);
        btnUnbind.setOnClickListener(ocl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    //����������Ӷ���
    final ServiceConnection conn = new ServiceConnection() {
         
        @Override
        public void onServiceDisconnected(ComponentName name) 
        {
			Toast.makeText(MainActivity.this,"ServiceMain onSeviceDisconnected", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "ServiceMain onSeviceDisconnected");
        }
         
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) 
        {
            Toast.makeText(MainActivity.this, "ServiceMain onServiceConnected",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "ServiceMain onServiceConnected");
        }
    };
}

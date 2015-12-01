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
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
	protected void onResume() 
    {
    	super.onResume();
        EditText output = (EditText)findViewById(R.id.editText1);
		if (ServiceMain.isRun) 
		{
			output.setText("Runing...");
		} else 
		{
			output.setText("stop!");
		}
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        Button btnStart = (Button)findViewById(R.id.button00);
        Button btnStop = (Button)findViewById(R.id.Button01);
		
        OnClickListener ocl = new OnClickListener() {
             
			@Override
			public void onClick(View v) 
			{
                Intent intent = new Intent(MainActivity.this,ServiceMain.class);
                switch(v.getId()){
                case R.id.button00:
                    startService(intent);
                    break;
                case R.id.Button01:
                    stopService(intent);
                    break;
                }
            }
        };
         
         //°ó¶¨µã»÷¼àÌý
        btnStart.setOnClickListener(ocl);
        btnStop.setOnClickListener(ocl);
	}
}

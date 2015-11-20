package com.example.myservice;

import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class WorkLoop extends HandlerThread implements Callback
{
	public WorkLoop(String name)
	{
		super(name);
	}

	@Override
	public boolean handleMessage(Message msg)
	{
		String josn = (String)msg.obj;
		return false;
	}

}

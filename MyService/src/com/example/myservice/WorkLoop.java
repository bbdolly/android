package com.example.myservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;
import android.view.KeyEvent;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WorkLoop extends HandlerThread implements Callback
{
	public WorkLoop(String name)
	{
		super(name);
	}

	@Override
	public boolean handleMessage(Message msg)
	{
		String josn = (String) msg.obj;
		Log.e("XUE", "handleMessage:"+josn);
		
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher(josn);
		while(matcher.find())
		{		
			try
			{
				Log.e("XUE", "LINE"+matcher.group());
				JSONObject jsonobj = new JSONObject(matcher.group());   
			    String func = jsonobj.getString("func");   			    
			    switch(func)
			    {
				    case "down":
				    {
				    	int x = jsonobj.getInt("x");
				    	int y = jsonobj.getInt("y");
				    	long now = SystemClock.uptimeMillis();
				    	injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_DOWN, x, y, 0));
				    	break;
				    }
				    case "move":
				    {
				    	int x = jsonobj.getInt("x");
				    	int y = jsonobj.getInt("y");
				    	long now = SystemClock.uptimeMillis();
				    	injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_MOVE, x, y, 0));
				    	break;
				    }
				    case "up":
				    {
				    	int x = jsonobj.getInt("x");
				    	int y = jsonobj.getInt("y");
				    	long now = SystemClock.uptimeMillis();
				    	injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_UP, x, y, 0));
				    	break;
				    }
			    }
			}
			catch(JSONException e)
			{
				Log.e("XUE", e.getMessage());
			}
		}
		return false;
	}

	private void sendSwipe(float x1, float y1, float x2, float y2)
	{
		final int NUM_STEPS = 10;
		long now = SystemClock.uptimeMillis();
		injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_DOWN, x1, y1, 0));
		for (int i = 0; i < NUM_STEPS; i++)
		{
			float alpha = (float) i / NUM_STEPS;
			injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_MOVE, lerp(x1, x2, alpha), lerp(y1, y2, alpha), 0));
		}
		injectPointerEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_UP, x2, y2, 0));
	}

	private static final float lerp(float a, float b, float alpha)
	{
		return (b - a) * alpha + a;
	}

	private void injectKeyEvent(KeyEvent event)
	{
		InputManager.getInstance().injectInputEvent(event, InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH);
	}

	private void injectPointerEvent(MotionEvent event)
	{
		event.setSource(InputDevice.SOURCE_TOUCHSCREEN);
		InputManager.getInstance().injectInputEvent(event, InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH);
	}

}

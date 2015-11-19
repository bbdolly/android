package com.example.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MyView extends SurfaceView implements SurfaceHolder.Callback {
	
	private SurfaceHolder holder;
	private MyThread myThread;
	
	public MyView(Context context) {
		super(context);
	}
	public MyView(Context context, AttributeSet attrs) {
		super(context,attrs);
		holder = this.getHolder();
		holder.addCallback(this);
		myThread = new MyThread(holder);// 创建一个绘图线程
	}
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		myThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	
	class MyThread extends Thread {
	private SurfaceHolder holder;
	public boolean isRun;
	
	public MyThread(SurfaceHolder holder) {
		this.holder = holder;
		isRun = true;
	}
	
	@Override
	public void run() {
		int count = 0;
		while (isRun) {
			Canvas c = null;
			try {
				synchronized (holder) {
					c = holder.lockCanvas();// 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
					c.drawColor(Color.WHITE);// 设置画布背景颜色
					Paint p = new Paint(); // 创建画笔
					p.setColor(Color.BLACK);
					c.drawText("这是第" + (count++) + "秒", 20, 20, p);
					Thread.sleep(3000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (c != null) {
					holder.unlockCanvasAndPost(c);
				}
			}
		}
	}
	
	}
}


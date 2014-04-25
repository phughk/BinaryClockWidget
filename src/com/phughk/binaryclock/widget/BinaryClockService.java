package com.phughk.binaryclock.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;
import android.widget.Toast;

public class BinaryClockService extends Service
{

	Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			super.handleMessage(msg);
//			Toast.makeText(BinaryClockService.this, "update", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(BinaryClockService.this, BinaryClockAppWidgetProvider.class);
			intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
			// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
			// since it seems the onUpdate() is only fired on that:
			AppWidgetManager appMan = AppWidgetManager.getInstance(BinaryClockService.this);
			ComponentName provider = new ComponentName(BinaryClockService.this, BinaryClockAppWidgetProvider.class);
			int[] ids = appMan.getAppWidgetIds(provider);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
			sendBroadcast(intent);

//			RemoteViews rv = new RemoteViews(getPackageName(), com.phughk.binaryclock.R.layout.binary_clock_widget_layout);
//			appMan.updateAppWidget(provider, rv);
		};
	};
	
	Thread thread = new Thread(new Runnable()
	{
		public void run()
		{
			while (thread.isInterrupted()==false)
			{
				try
				{
					Thread.sleep(200);
					handler.sendEmptyMessage(0);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
			}
		}
	});
	
	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		thread.start();
		Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO Auto-generated method stub
		Toast.makeText(this, "Service onStartCommand", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.interrupt();
		Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

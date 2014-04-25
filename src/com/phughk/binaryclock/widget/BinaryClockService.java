package com.phughk.binaryclock.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class BinaryClockService extends Service
{

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
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
		Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

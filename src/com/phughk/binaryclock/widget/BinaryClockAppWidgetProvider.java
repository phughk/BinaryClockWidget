package com.phughk.binaryclock.widget;

import android.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceView;
import android.webkit.WebView.FindListener;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BinaryClockAppWidgetProvider extends AppWidgetProvider
{
	// private SimpleDateFormat formatter = new
	// SimpleDateFormat("dd MMM yyyy  hh:mm:ss a");
	String strWidgetText = "";
    Bitmap bmp = Bitmap.createBitmap(400, 300, Bitmap.Config.RGB_565);

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		// TODO Auto-generated method stub
		// super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "onDeleted()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDisabled(Context context)
	{
		// TODO Auto-generated method stub
		// super.onDisabled(context);
		Toast.makeText(context, "onDisabled()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onEnabled(Context context)
	{
		// TODO Auto-generated method stub
		// super.onEnabled(context);
		Toast.makeText(context, "onEnabled()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
        //Do drawing
        Canvas c = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
//        paint.setStyle(Style.FILL);
//        c.drawText("works", 100.0f, 100.0f, paint);
//        c.drawPaint(paint);
        
        c.drawRect(0, 0, 400, 300, paint);
        paint.setColor(Color.BLUE);
        c.drawRect(10, 10, 390, 290, paint);
        
		for (int appWidgetID: appWidgetIds)
		{
			RemoteViews updateViews = new RemoteViews(context.getPackageName(), com.phughk.binaryclock.R.layout.binary_clock_widget_layout);
	        updateViews.setTextViewText(com.phughk.binaryclock.R.id.title, "it works");
	        updateViews.setImageViewBitmap(com.phughk.binaryclock.R.id.clockImage, bmp);
	        
	        appWidgetManager.updateAppWidget(appWidgetID, updateViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Toast.makeText(context, "onUpdate()", Toast.LENGTH_LONG).show();

	}
}

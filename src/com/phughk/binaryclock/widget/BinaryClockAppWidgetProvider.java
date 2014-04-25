package com.phughk.binaryclock.widget;

import java.util.Calendar;

import android.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;
import android.webkit.WebView.FindListener;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BinaryClockAppWidgetProvider extends AppWidgetProvider
{
	static Rect r = new Rect(0, 0, 400, 300);
    static Bitmap bmp = Bitmap.createBitmap(r.right-r.left, r.bottom-r.top, Bitmap.Config.ARGB_8888);
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		// super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "onDeleted()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDisabled(Context context)
	{
		// super.onDisabled(context);
		Toast.makeText(context, "onDisabled()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onEnabled(Context context)
	{
		// super.onEnabled(context);
		Toast.makeText(context, "onEnabled()", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
        //Do drawing
        Canvas c = new Canvas(bmp);
        drawClock(c);
        
		for (int appWidgetID: appWidgetIds)
		{
			RemoteViews updateViews = new RemoteViews(context.getPackageName(), com.phughk.binaryclock.R.layout.binary_clock_widget_layout);
	        updateViews.setImageViewBitmap(com.phughk.binaryclock.R.id.clockImage, bmp);
	        
	        appWidgetManager.updateAppWidget(appWidgetID, updateViews);
		}

	}
	
	private static boolean getBit(int d, int b)
	{
		while (b>0)
		{
			d=d/2;
			b--;
		}
		return (d%2==1?true:false);
	}
	
	public static void drawClock(Canvas c)
	{
        Paint paint = new Paint();
        boolean clock[][] = new boolean[6][4]; // 6x4 clock, bcd clock
        
        // get time
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        
        int[] fields = {hours, minutes, seconds};
        
        // Convert to binary
        for (int x=0; x<6; x++)
        {
        	for (int y=3; y>=0; y--)
        	{
        		int field = fields[x/2];
        		int val = (x%2==0 ? field/10 : field%10); // get the respective decimal position to convert to binary
        		clock[x][y]=getBit(val, 3-y);
            }
        }
        
        // draw binary table
		int width = (r.right-r.left)/6;
		int height = (r.bottom-r.top)/4;
        for (int x=0; x<6; x++)
        {
        	for (int y=0; y<4; y++)
        	{
        		// get tile rect
        		Rect tile = new Rect(x*width+1, y*height+1, (x+1)*width-1, (y+1)*height-1); // The +/-1 is for borders
        		if (clock[x][y])
        		{
        			paint.setColor(Color.CYAN);
        		}
        		else
        		{
        			paint.setColor(Color.TRANSPARENT);
        		}
        		c.drawRect(tile, paint);
        	}
        }
	}
}

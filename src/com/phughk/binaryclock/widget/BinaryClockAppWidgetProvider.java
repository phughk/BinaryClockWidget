package com.phughk.binaryclock.widget;

import java.util.Calendar;

import android.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.SurfaceView;
import android.webkit.WebView.FindListener;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BinaryClockAppWidgetProvider extends AppWidgetProvider
{
	static Rect r = new Rect(0, 0, 400, 300); // This defines the size of the image being drawn.
											  // I figured it doesn't really matter what size it is since it is resized anyway
											  // Tile calculations reference this value
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		// super.onDeleted(context, appWidgetIds);
//		Toast.makeText(context, "onDeleted()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisabled(Context context)
	{
		// super.onDisabled(context);
		Intent i = new Intent(context, BinaryClockService.class);
		context.stopService(i);
//		Toast.makeText(context, "onDisabled()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onEnabled(Context context)
	{
		// super.onEnabled(context);
		Intent i = new Intent(context, BinaryClockService.class);
		context.startService(i);
//		Toast.makeText(context, "onEnabled()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
        //Do drawing
	    Bitmap bmp = Bitmap.createBitmap(r.right-r.left, r.bottom-r.top, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        drawClock(c);
        
		for (int appWidgetID: appWidgetIds)
		{
			RemoteViews updateViews = new RemoteViews(context.getPackageName(), com.phughk.binaryclock.R.layout.binary_clock_widget_layout);
	        updateViews.setImageViewBitmap(com.phughk.binaryclock.R.id.clockImage, bmp);
	        
	        appWidgetManager.updateAppWidget(appWidgetID, updateViews);
		}

	}
	
	/**
	 * Get the specific bit of a integer
	 * @param d The integer that we want to read
	 * @param b The index of the bit that we want
	 * @return true if bit was set, otherwise false
	 */
	private static boolean getBit(int d, int b)
	{
		while (b>0)
		{
			d=d/2;
			b--;
		}
		return (d%2==1?true:false);
	}
	
	/**
	 * Gets the time, makes a 6x4 boolean matrix and draws it
	 * @param c Context of the bitmap you want to draw on
	 */
	public static void drawClock(Canvas c)
	{
        Paint paint = new Paint();
        boolean clock[][] = new boolean[6][4]; // 6x4 clock, bcd clock
        
        // get time
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        
        int[] fields = {hours, minutes, seconds}; // The values are put into an array to make the calculations below work more dynamically (see: int field)
        
        // Convert to binary
        for (int x=0; x<6; x++)
        {
        	for (int y=3; y>=0; y--)
        	{
        		int field = fields[x/2]; // Which int are we working on
        		int val = (x%2==0 ? field/10 : field%10); // get the respective decimal position to convert to binary
        		clock[x][y]=getBit(val, 3-y); // Get the bit of the selected decimal
            }
        }
        
        // calculate tile size(6x4)
		int width = (r.right-r.left)/6;
		int height = (r.bottom-r.top)/4;

		// Draw border, 4 lines
		paint.setColor(Color.BLACK);
		c.drawLine(r.left, r.top, r.right-1, r.top, paint);
		c.drawLine(r.left, r.top, r.left, r.bottom-1, paint);
		c.drawLine(r.right-1, r.top, r.right-1, r.bottom-1, paint);
		c.drawLine(r.left, r.bottom-1, r.right-1, r.bottom-1, paint);
		
		// draw clock
        for (int x=0; x<6; x++)
        {
        	for (int y=0; y<4; y++)
        	{
        		// get tile to currently draw
        		Rect tile = new Rect(x*width+1, y*height+1, (x+1)*width-1, (y+1)*height-1); // The +/-1 is for borders
        		if (clock[x][y]) // Decide to draw or not
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

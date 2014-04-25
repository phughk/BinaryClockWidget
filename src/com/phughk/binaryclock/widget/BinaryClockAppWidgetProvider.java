package com.phughk.binaryclock.widget;
import android.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;


public class BinaryClockAppWidgetProvider extends AppWidgetProvider
{
	/**
	 * This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute
	 * in the AppWidgetProviderInfo (see Adding the AppWidgetProviderInfo Metadata above).
	 * This method is also called when the user adds the App Widget, so it should perform the essential setup,
	 * such as define event handlers for Views and start a temporary Service, if necessary.
	 * However, if you have declared a configuration Activity, this method is not called
	 * when the user adds the App Widget, but is called for the subsequent updates.
	 * It is the responsibility of the configuration Activity to perform the first update
	 * when configuration is done. (See Creating an App Widget Configuration Activity below.)
	 */
	/*
	public void onUpdate(Context c, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		for (int i=0; i<appWidgetIds.length; i++)
		{
			//TODO Do update here
			RemoteViews views = new RemoteViews(c.getPackageName(), com.phughk.binaryclock.R.layout.binary_clock_widget_layout);
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
			
		}
	}
	*/
}

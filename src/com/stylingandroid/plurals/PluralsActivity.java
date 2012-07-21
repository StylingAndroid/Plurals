package com.stylingandroid.plurals;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PluralsActivity extends Activity
{
	private static final int[] sItems = { 0, 1, 5 };
	private static final int[] sAges = { 1, 18, 23 };

	private Resources mResources;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.plurals );

		mResources = getResources();

		TextView tv1 = ((TextView) findViewById( R.id.textView1 ));
		TextView tv2 = ((TextView) findViewById( R.id.textView2 ));
		TextView tv3 = ((TextView) findViewById( R.id.textView3 ));
		TextView tv4 = ((TextView) findViewById( R.id.textView4 ));

		tv1.setText( plural1(sItems) );
		tv2.setText( plural2( R.plurals.items, sItems ) );
		tv3.setText( plural2( R.plurals.age, sAges) );
		
		DisplayMetrics dm = mResources.getDisplayMetrics();
		Configuration conf = mResources.getConfiguration();
		Locale oldLocale = conf.locale;
		conf.locale = new Locale( "ru" );
		mResources.updateConfiguration( conf, dm );
		tv4.setText( plural2( R.plurals.age, sAges) );
		conf.locale = oldLocale;
		mResources.updateConfiguration( conf, dm );
	}

	private String plural1(int[] values)
	{
		StringBuilder sb = new StringBuilder();
		for (int value : values)
		{
			sb.append( String.format( value == 1 ? "%d item " : "%d items ",
					value ) );
		}
		return sb.toString();
	}

	private String plural2( int resource, int[] values )
	{
		StringBuilder sb = new StringBuilder();
		for (int value : values)
		{
			sb.append( mResources.getQuantityString( resource, value, value ) )
					.append( " " );
		}
		return sb.toString();
	}
}

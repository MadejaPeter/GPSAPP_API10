package com.madeja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.madeja.gpslib.GPSLib;

public class MainActivity extends MapActivity {
    /** Called when the activity is first created. */
    LinearLayout linearLayout;
    protected MapView mapView;
    protected Activity activity;
    boolean zapnuteGPS;
    GPSLib gps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        zapnuteGPS = false;
        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (gps==null) {
					gps = new GPSLib(getApplicationContext());
				} 
				if (!zapnuteGPS) {
					gps.zapniGPS();
					gps.zapniKompas(activity, mapView);
					zapnuteGPS = true;
					button.setText("Vypni Tracking");
					button.invalidate();
				} else {
					gps.vypniGPS();
					zapnuteGPS = false;
					button.setText("Zapni Tracking");
					button.invalidate();
				}
			}
		});
    }

    @Override
    protected boolean isRouteDisplayed()
    {

        return false;
    }
}
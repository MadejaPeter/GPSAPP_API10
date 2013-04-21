package com.madeja;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
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
        gps = new GPSLib(getApplicationContext());
        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			//ClickButton Tracking
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
        
        Location loc = gps.getCurrentLocation();
        final MapController controller = mapView.getController();
        
        mapView.getController().animateTo(new GeoPoint((int)(loc.getLatitude() * 1E6), (int)(loc.getLongitude() * 1E6)));
        Log.i("I", "Location" + loc.toString());
        mapView.getController().setZoom(15);
        
        final Button searchButton = (Button)findViewById(R.id.searchbutton);
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    // TODO Auto-generated method stub
			    if (v == searchButton) {
			        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
			        try {
			        	EditText mapSearchBox = (EditText) findViewById(R.id.search);
			        	
			            List<Address> addresses = geo.getFromLocationName(mapSearchBox.getText().toString(), 5);
			            if (addresses.size() > 0) {
			                GeoPoint p = new GeoPoint((int) (addresses.get(0)
			                        .getLatitude() * 1E6), (int) (addresses.get(0)
			                        .getLongitude() * 1E6));

			                controller.animateTo(p);
			                controller.setZoom(12);
//			                MapOverlay mapOverlay = new MapOverlay();
//			                List<Overlay> lisOverlays = mapView.getOverlays();
//			                lisOverlays.clear();
//			                lisOverlays.add(mapOverlay);

			            } else {
			                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
			                adb.setTitle("Google Map");
			                adb.setMessage("please provide proper place");
			                adb.setPositiveButton("Close", null);
			                adb.show();
			            }
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }
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
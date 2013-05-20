package com.madeja.gpslib;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class GPSLib {
	
	protected Context context;
	protected boolean oznamujLokaciu;
	
	public GPSLib(Context context) {
		this.context = context;
	}
	
	public void zapniKompas(Activity aktivita, MapView mapa) {
		 MyLocationOverlay overlaySKompasom=new MyLocationOverlay(aktivita, mapa);
		 overlaySKompasom.enableCompass();
		 overlaySKompasom.enableMyLocation();
		 mapa.getOverlays().add(overlaySKompasom);
	}
	
	public void zapniGPS() {
		LocationManager mlocManager = 
				(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener(); 
		mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		oznamujLokaciu = true;
	}
	
	public void vypniGPS() {
		oznamujLokaciu = false;
	}

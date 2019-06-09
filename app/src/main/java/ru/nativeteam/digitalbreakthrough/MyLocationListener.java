package ru.nativeteam.digitalbreakthrough;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import static androidx.core.content.ContextCompat.checkSelfPermission;

class MyLocationListener implements LocationListener {
	
	static Location imHere; // здесь будет всегда доступна самая последняя информация о местоположении пользователя.
	
	public static void SetUpLocationListener( Context context ) // это нужно запустить в самом начале работы программы
	{
		LocationManager locationManager = ( LocationManager )
				context.getSystemService( Context.LOCATION_SERVICE );
		
		LocationListener locationListener = new MyLocationListener( );
		
		if ( checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
			// TODO: Consider calling
			//    Activity#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for Activity#requestPermissions for more details.
			return;
		}
		if ( locationManager != null ) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER ,
					5000 ,
					10 ,
					locationListener ); // здесь можно указать другие более подходящие вам параметры
		}
		
		if ( locationManager != null ) {
			imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
	}
	
	private static int checkSelfPermission( String accessFineLocation ) {
		return 0;
	}
	
	@Override
	public void onLocationChanged(Location loc) {
		imHere = loc;
	}
	@Override
	public void onProviderDisabled(String provider) {}
	@Override
	public void onProviderEnabled(String provider) {}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}
}

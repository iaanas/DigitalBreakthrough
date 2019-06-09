package ru.nativeteam.digitalbreakthrough;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends AppCompatActivity {
	
	TextView startOfDate;
	
	TextView longitude;
	TextView lathitude;
	
	Location location;
	
	@BindView(R.id.location_result)
	TextView txtLocationResult;
	
	@BindView(R.id.updated_on)
	TextView txtUpdatedOn;
	
	@BindView(R.id.btn_start_location_updates)
	Button btnStartUpdates;
	
	@BindView(R.id.btn_stop_location_updates)
	Button btnStopUpdates;
	
	// location last updated time
	private String mLastUpdateTime;
	
	// location updates interval - 10sec
	private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
	
	// fastest updates interval - 5 sec
	// location updates will be received if another app is requesting the locations
	// than your app can handle
	private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
	
	private static final int REQUEST_CHECK_SETTINGS = 100;
	
	
	// bunch of location related apis
	private FusedLocationProviderClient mFusedLocationClient;
	private SettingsClient mSettingsClient;
	private LocationRequest mLocationRequest;
	private LocationSettingsRequest mLocationSettingsRequest;
	private LocationCallback mLocationCallback;
	private Location mCurrentLocation;
	
	// boolean flag to toggle the ui
	private Boolean mRequestingLocationUpdates;
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_events );
		
		startOfDate = (TextView ) findViewById( R.id.startOfdate );
		
		onLocationChanged( location  );
		
		
		Date currentDate = new Date(  );
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		String dateText = dateFormat.format(currentDate);
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		String timeText = timeFormat.format(currentDate);
		
		startOfDate.setText( dateText + " " + timeText );
		
		LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE);
		
		if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// Проверка наличия разрешений
			// Если нет разрешения на использование соответсвующих разркешений выполняем какие-то действия
			return;
		}
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,listener);
		
		ButterKnife.bind(this);
		
		// initialize the necessary libraries
		init();
		
		// restore the values from saved instance state
		restoreValuesFromBundle(savedInstanceState);
		
		
	}
	
	private LocationListener listener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
	};
	
	public void onLocationChanged(Location location) {
		if (location!=null) {
			lathitude.setText(String.valueOf(location.getLatitude()));
			longitude.setText(String.valueOf(location.getLongitude()));
		}
		else{
			lathitude.setText("Sorry, location");
			longitude.setText("unavailable");
		}
	}
	
	private void init() {
		mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
		mSettingsClient = LocationServices.getSettingsClient(this);
		
		mLocationCallback = new LocationCallback() {
			@Override
			public void onLocationResult( LocationResult locationResult) {
				super.onLocationResult(locationResult);
				// location is received
				mCurrentLocation = locationResult.getLastLocation();
				mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
				
				updateLocationUI();
			}
		};
		
		mRequestingLocationUpdates = false;
		
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
		mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		
		LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
		builder.addLocationRequest(mLocationRequest);
		mLocationSettingsRequest = builder.build();
	}
	
	/**
	 * Restoring values from saved instance state
	 */
	private void restoreValuesFromBundle(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey("is_requesting_updates")) {
				mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
			}
			
			if (savedInstanceState.containsKey("last_known_location")) {
				mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
			}
			
			if (savedInstanceState.containsKey("last_updated_on")) {
				mLastUpdateTime = savedInstanceState.getString("last_updated_on");
			}
		}
		
		updateLocationUI();
	}
	
	
	/**
	 * Update the UI displaying the location data
	 * and toggling the buttons
	 */
	private void updateLocationUI() {
		if (mCurrentLocation != null) {
			txtLocationResult.setText(
					"Lat: " + mCurrentLocation.getLatitude() + ", " +
							"Lng: " + mCurrentLocation.getLongitude()
			);
			
			// giving a blink animation on TextView
			txtLocationResult.setAlpha(0);
			txtLocationResult.animate().alpha(1).setDuration(300);
			
			// location last updated time
			txtUpdatedOn.setText("Last updated on: " + mLastUpdateTime);
		}
		
		toggleButtons();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
		outState.putParcelable("last_known_location", mCurrentLocation);
		outState.putString("last_updated_on", mLastUpdateTime);
		
	}
	
	private void toggleButtons() {
		if (mRequestingLocationUpdates) {
			btnStartUpdates.setEnabled(false);
			btnStopUpdates.setEnabled(true);
		} else {
			btnStartUpdates.setEnabled(true);
			btnStopUpdates.setEnabled(false);
		}
	}
	
	/**
	 * Starting location updates
	 * Check whether location settings are satisfied and then
	 * location updates will be requested
	 */
	private void startLocationUpdates() {
		mSettingsClient
				.checkLocationSettings(mLocationSettingsRequest)
				.addOnSuccessListener(this, new OnSuccessListener < LocationSettingsResponse >() {
					@SuppressLint("MissingPermission")
					@Override
					public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//						Log.i(TAG, "All location settings are satisfied.");
						
						Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();
						
						//noinspection MissingPermission
						mFusedLocationClient.requestLocationUpdates(mLocationRequest,
								mLocationCallback, Looper.myLooper());
						
						updateLocationUI();
					}
				})
				.addOnFailureListener(this, new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						int statusCode = (( ApiException ) e).getStatusCode();
						switch (statusCode) {
							case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//								Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
//										"location settings ");
								try {
									// Show the dialog by calling startResolutionForResult(), and check the
									// result in onActivityResult().
									ResolvableApiException rae = (ResolvableApiException) e;
									rae.startResolutionForResult(EventsActivity.this, REQUEST_CHECK_SETTINGS);
								} catch ( IntentSender.SendIntentException sie) {
//									Log.i(TAG, "PendingIntent unable to execute request.");
								}
								break;
							case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
								String errorMessage = "Location settings are inadequate, and cannot be " +
										"fixed here. Fix in Settings.";
//								Log.e(TAG, errorMessage);
								
								Toast.makeText(EventsActivity.this, errorMessage, Toast.LENGTH_LONG).show();
						}
						
						updateLocationUI();
					}
				});
	}
	
	@OnClick(R.id.btn_start_location_updates)
	public void startLocationButtonClick() {
		// Requesting ACCESS_FINE_LOCATION using Dexter library
		Dexter.withActivity(this)
				.withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
				.withListener(new PermissionListener() {
					@Override
					public void onPermissionGranted( PermissionGrantedResponse response) {
						mRequestingLocationUpdates = true;
						startLocationUpdates();
					}
					
					@Override
					public void onPermissionDenied( PermissionDeniedResponse response) {
						if (response.isPermanentlyDenied()) {
							// open device settings when the permission is
							// denied permanently
							openSettings();
						}
					}
					
					@Override
					public void onPermissionRationaleShouldBeShown( com.karumi.dexter.listener.PermissionRequest permission , PermissionToken token ) {
						token.continuePermissionRequest();
					}
					
				}).check();
	}
	
	@OnClick(R.id.btn_stop_location_updates)
	public void stopLocationButtonClick() {
		mRequestingLocationUpdates = false;
		stopLocationUpdates();
	}
	
	public void stopLocationUpdates() {
		// Removing location updates
		mFusedLocationClient
				.removeLocationUpdates(mLocationCallback)
				.addOnCompleteListener(this, new OnCompleteListener <Void>() {
					@Override
					public void onComplete(@NonNull Task <Void> task) {
						Toast.makeText(getApplicationContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();
						toggleButtons();
					}
				});
	}
	
	@OnClick(R.id.btn_get_last_location)
	public void showLastKnownLocation() {
		if (mCurrentLocation != null) {
			Toast.makeText(getApplicationContext(), "Lat: " + mCurrentLocation.getLatitude()
					+ ", Lng: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "Last known location is not available!", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			// Check for the integer request code originally supplied to startResolutionForResult().
			case REQUEST_CHECK_SETTINGS:
				switch (resultCode) {
					case Activity.RESULT_OK:
//						Log.e(TAG, "User agreed to make required location settings changes.");
						// Nothing to do. startLocationupdates() gets called in onResume again.
						break;
					case Activity.RESULT_CANCELED:
//						Log.e(TAG, "User chose not to make required location settings changes.");
						mRequestingLocationUpdates = false;
						break;
				}
				break;
		}
	}
	
	private void openSettings() {
		Intent intent = new Intent();
		intent.setAction(
				Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		Uri uri = Uri.fromParts("package",
				BuildConfig.APPLICATION_ID, null);
		intent.setData(uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// Resuming location updates depending on button state and
		// allowed permissions
		if (mRequestingLocationUpdates && checkPermissions()) {
			startLocationUpdates();
		}
		
		updateLocationUI();
	}
	
	private boolean checkPermissions() {
		int permissionState = ActivityCompat.checkSelfPermission(this,
				Manifest.permission.ACCESS_FINE_LOCATION);
		return permissionState == PackageManager.PERMISSION_GRANTED;
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if (mRequestingLocationUpdates) {
			// pausing location updates
			stopLocationUpdates();
		}
	}
	
}

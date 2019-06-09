package ru.nativeteam.digitalbreakthrough;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class EventsActivity extends AppCompatActivity {
	
	TextView startOfDate;
	Button gps;
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_events );
		
		startOfDate = (TextView ) findViewById( R.id.startOfdate );
		gps = (Button ) findViewById( R.id.btn_start_location_updates);
		
		
		Date currentDate = new Date(  );
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		String dateText = dateFormat.format(currentDate);
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		String timeText = timeFormat.format(currentDate);
		
		startOfDate.setText( dateText + " " + timeText );
		
		
		
//		LocationManager locationManager = (LocationManager)
//				getSystemService( Context.LOCATION_SERVICE);
		
		
		gps.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				Intent intent = new Intent( EventsActivity.this, GetCurrentLocation.class );
				startActivity( intent );
				
			}
		} );
		
	}
	
}

package ru.nativeteam.digitalbreakthrough;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class EventsActivity extends AppCompatActivity {
	
	TextView startOfDate;
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_events );
		
		startOfDate = (TextView ) findViewById( R.id.startOfdate );
		
		
		Date currentDate = new Date(  );
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		String dateText = dateFormat.format(currentDate);
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		String timeText = timeFormat.format(currentDate);
		
		startOfDate.setText( dateText + " " + timeText );
		
		
		
	}
	
}

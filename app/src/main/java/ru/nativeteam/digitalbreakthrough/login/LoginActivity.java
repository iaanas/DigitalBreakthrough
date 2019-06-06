package ru.nativeteam.digitalbreakthrough.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.nativeteam.digitalbreakthrough.HomeActivity;
import ru.nativeteam.digitalbreakthrough.MainActivity;
import ru.nativeteam.digitalbreakthrough.R;

public class LoginActivity extends AppCompatActivity {
	
	Button signIn;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_login );
		
		signIn = findViewById( R.id.btnSignIn );
		
		signIn.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Intent intent = new Intent( LoginActivity.this, HomeActivity.class );
				startActivity( intent );
			}
		} );
		
	}
	
	
}

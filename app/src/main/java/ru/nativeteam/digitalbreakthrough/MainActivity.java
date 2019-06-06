package ru.nativeteam.digitalbreakthrough;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import ru.nativeteam.digitalbreakthrough.menu.ItemOneFragment;
import ru.nativeteam.digitalbreakthrough.menu.ItemThreeFragment;
import ru.nativeteam.digitalbreakthrough.menu.ItemTwoFragment;


public class MainActivity extends AppCompatActivity {
	
	private DrawerLayout dl;
	private ActionBarDrawerToggle t;
	private NavigationView nv;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
		
		dl = (DrawerLayout)findViewById(R.id.nv);
		dl.addDrawerListener(t);
		t.syncState();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		nv = (NavigationView)findViewById(R.id.nv);
		nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				int id = item.getItemId();
				switch(id)
				{
					case R.id.account:
						Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();break;
					case R.id.settings:
						Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
					case R.id.mycart:
						Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();break;
					default:
						return true;
				}
				
				
				return true;
				
			}
		});
		
		
		
		BottomNavigationView bottomNavigationView = (BottomNavigationView)
				findViewById(R.id.navigation);
		
		bottomNavigationView.setOnNavigationItemSelectedListener
				(new BottomNavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item) {
						Fragment selectedFragment = null;
						switch (item.getItemId()) {
							case R.id.action_item1:
								selectedFragment = ItemOneFragment.newInstance();
								break;
							case R.id.action_item2:
								selectedFragment = ItemTwoFragment.newInstance();
								break;
							case R.id.action_item3:
								selectedFragment = ItemThreeFragment.newInstance();
								break;
						}
						FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
						transaction.replace(R.id.frame_layout, selectedFragment);
						transaction.commit();
						return true;
					}
				});
		
		//Manually displaying the first fragment - one time only
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
		transaction.commit();
		
	}
}

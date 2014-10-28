package com.example.seproject;

//import com.mkyong.android.R;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Red_Button extends Activity implements LocationListener{
	
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	LocationManager locationManager;
	LocationListener locationListener;
	Context context;
	TextView txtLat;
	String lat;
	String provider;
	String latitude,longitude; 
	boolean gps_enabled,network_enabled;
	LocationListener listener;
	String coordinate;
	
//	public void sendCoordinate(double latitude, double longitude){
//		coordinate = "Latitude = " + latitude + ", Longitude = " + longitude;
//		System.out.println(coordinate);
//		
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_red__button);
		buttonSend = (Button) findViewById(R.id.helpButton);
		
		

		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

//				String phoneNo = textPhoneNo.getText().toString();
//				String sms = textSMS.getText().toString();
				listener = new Red_Button();
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.red__button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		String coordinate = "Latitude = " + latitude + ", Longitude = " + longitude;
		try {
			
			SmsManager smsManager = SmsManager.getDefault();
			System.out.println(coordinate);
			smsManager.sendTextMessage("+18178233972", null, "Help!!!" + coordinate, null, null);
			
			Toast.makeText(getApplicationContext(), "SMS Sent!",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d("Latitude","status");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","enable");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","disable");
	}

}

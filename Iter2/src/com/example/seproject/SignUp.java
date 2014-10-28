package com.example.seproject;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
	Button next,signin;
	private EditText utaid, username, emailid, emename, emenum;
	private ProgressDialog pDialog;
	int flag=0;
	JSONParser jsonParser = new JSONParser();
	private static String url = "http://129.107.239.57/test/register.php";
	private static final String TAG_SUCCESS = "success"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());*/
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	
	//Go To Login.java	
		next=(Button)findViewById(R.id.button1);	
        next.setOnClickListener(new View.OnClickListener() 
        {		
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Password.class);
				startActivity(i);
				
			}
		}); 
    // Close Login.java
        
     //Get all data and log in 
    	next=(Button)findViewById(R.id.button1);	
    	utaid=(EditText)findViewById(R.id.editText4);
    	username=(EditText)findViewById(R.id.editText3);
    	emailid=(EditText)findViewById(R.id.editText5);
    	emename=(EditText)findViewById(R.id.editText2);
    	emenum=(EditText)findViewById(R.id.editText1);
    	
    	
        next.setOnClickListener(new View.OnClickListener() 
        {			
        	
			@Override
			public void onClick(View view) {
				
		//Check all fields		
//				if(utaid.length()==0)
//				{
//					Toast.makeText(SignUp.this,"Please Enter the UTAID", Toast.LENGTH_LONG).show();
//					return;
//				}
//				 if(username.length()<0)
//				{				
//					Toast.makeText(SignUp.this,"Please Enter hte username", Toast.LENGTH_LONG).show();
//					return;
//				}
//				 if(emailid.length()<0)
//					{				
//						Toast.makeText(SignUp.this,"Please Enter the email id", Toast.LENGTH_LONG).show();
//						return;
//					} 
//				 if(emename.length()<0)
//					{				
//						Toast.makeText(SignUp.this,"Please Enter the emergency contact person name", Toast.LENGTH_LONG).show();
//						return;
//					}
//				 if(emenum.length()<0)
//					{				
//						Toast.makeText(SignUp.this,"Please Enter the emergency contact person number", Toast.LENGTH_LONG).show();
//						return;
//					}
				 //check connectivity		
				 if(!isOnline(SignUp.this))
				{					
					Toast.makeText(SignUp.this,"No network connection", Toast.LENGTH_LONG).show();
					return;	
				}
		
		//from login.java		
					new loginAccess().execute();
			}
   
		//code to check online details
		private boolean isOnline(Context mContext) {
		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting())
	   	  {
			return true;
     		}
		    return false;
       	}
      //Close code that check online details		
	  }); 
        //Close log in 
    }


class loginAccess extends AsyncTask<String, String, String> {

	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(SignUp.this);
		pDialog.setMessage("Sign in...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	@Override
	protected String doInBackground(String... arg0) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String s_utaid = utaid.getText().toString();
		String s_username=username.getText().toString();
		String s_emailid=emailid.getText().toString();
		String s_emename=emename.getText().toString();
		String s_emenum=emenum.getText().toString();
		
		params.add(new BasicNameValuePair("utaid", s_utaid));
		params.add(new BasicNameValuePair("username", s_username));
		params.add(new BasicNameValuePair("emailid", s_emailid));
		params.add(new BasicNameValuePair("emename", s_emename));
		params.add(new BasicNameValuePair("emenum", s_emenum));
		
		
		JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
		System.out.println(json);
		Log.d("Create Response", json.toString());
		
		
		try {
			int success = json.getInt(TAG_SUCCESS);
			if (success == 1) 
			 {
			  flag=0;	
			  Intent i = new Intent(getApplicationContext(),Password.class);
//			  i.putExtra("username",username1);
//			  i.putExtra("password",pwd);
			  startActivity(i);
			  finish();
			 }
			 else
			 {
				// failed to Sign in
				flag=1;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected void onPostExecute(String file_url) {
//		pDialog.dismiss();
//		if(flag==1)
//			Toast.makeText(register.this,"Please Enter Correct informations", Toast.LENGTH_LONG).show();
//		
	}
	
  }
	
}
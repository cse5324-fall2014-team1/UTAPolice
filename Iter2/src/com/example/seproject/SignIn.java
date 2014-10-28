package com.example.seproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//import server.maverickeventhub.R;
//import server.maverickeventhub.Home;
//import server.maverickeventhub.Login;
//import server.maverickeventhub.R;
//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.graphics.Typeface;
import android.os.Bundle;
//import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends Activity {

	HttpResponse response;
	public static final String PREFS_NAME = "LoginPrefs";
	ProgressDialog dialog = null;
	EditText edittext_name = null;
	EditText edittext_password = null;
	Button button_login = null;
	HttpPost httppost;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	TextView textview_password = null;
	BufferedReader bufferedReader = null;
	CheckBox c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		//Parse.initialize(this, "VvqWXdLdxMvKvO958vOw4Ito4zDhRKr0COXLapYb", "fXxrVNIGcnyxgeSFKycEftObTkN4Ewgzxu3qQCRd");

		edittext_name = (EditText) findViewById(R.id.editText1);
		edittext_password = (EditText) findViewById(R.id.editText2);
		//textview_password= (TextView) findViewById(R.id.textView1);
		//TextView textview_login_welcome= (TextView) findViewById(R.id.tv_login_welcome);
		//TextView textview_username= (TextView) findViewById(R.id.tv_login_username);
		//Typeface font = Typeface.createFromAsset(getAssets(), "segoeui_r.ttf");  
		button_login = (Button) findViewById(R.id.button1);
		//c = (CheckBox) findViewById(R.id.checkBox1);
		//Button button_refresh = (Button) findViewById(R.id.btn_login_refresh);
		//Button button_close = (Button) findViewById(R.id.btn_login_close);
		//Button button_forgotpassword = (Button) findViewById(R.id.btn_forgotpassword);
		//Button button_forgotusername = (Button) findViewById(R.id.btn_forgotusername);

		//		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		//
		//		if (settings.getString("logged", "").toString().equals("")) {
		//		}
		//		else
		//		{
		//			Intent intent = new Intent(SignIn.this, Password.class);
		//			//intent.putExtra("username",settings.getString("logged", "").toString());
		//			startActivity(intent);
		//		}

		button_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//				EditText edittext_username = (EditText) findViewById(R.id.editText1);
				//				EditText edittext_password = (EditText) findViewById(R.id.editText2);
				//				String username = edittext_username.getText().toString();
				//				String password = edittext_password.getText().toString();
				//				int count=0;
				//ProgressDialog dialog = null;
				dialog = ProgressDialog.show(SignIn.this, "", 
						"Validating user...", true);
				new Thread(new Runnable() {
					public void run() {
						login();                          
					}
				}).start(); 
   
			}       

		});
	}

	void login(){
		try{            

//			edittext_name = (EditText) findViewById(R.id.editText1);
//			edittext_password = (EditText) findViewById(R.id.editText2);

//			HttpPost httppost;
//			HttpClient httpclient;
//			List<NameValuePair> nameValuePairs;

			httpclient=new DefaultHttpClient();
			httppost= new HttpPost("http://129.107.239.57/test/login_check.php"); // make sure the url is correct.
			//add your data
			nameValuePairs = new ArrayList<NameValuePair>(2);
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("username",edittext_name.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password",edittext_password.getText().toString().trim())); 
			System.out.println(nameValuePairs);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			 //Execute HTTP Post Request
            response=httpclient.execute(httppost);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			//ResponseHandler responseHandler = new BasicResponseHandler();
			final String response1 = httpclient.execute(httppost, responseHandler);
			System.out.println("Response : " + response); 
			runOnUiThread(new Runnable() {
				public void run() {                    
					//dialog.dismiss();
					//textview_password.setText("Response from PHP : " + response);
	                    dialog.dismiss();
				}
			});
			
//			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		       StringBuffer stringBuffer = new StringBuffer("");
//		       String line = "";
//		       String LineSeparator = System.getProperty("line.separator");
//		       while ((line = bufferedReader.readLine()) != null) {
//		        stringBuffer.append(line + LineSeparator); 
//		       }
//
//		      bufferedReader.close();  
//		      return stringBuffer.toString();

			if(response1.equalsIgnoreCase("User Found")){

//								if (c.isChecked()) {
//				
//									SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//									SharedPreferences.Editor editor = settings.edit();
//									editor.putString("logged", edittext_name.getText().toString().trim());
//									editor.putString("log", edittext_password.getText().toString().trim());
//									editor.commit();
//								}


				runOnUiThread(new Runnable() {
					public void run() {


						//
						Toast.makeText(SignIn.this,"Login Success", Toast.LENGTH_SHORT).show();
						
					}
				});
				startActivity(new Intent(SignIn.this, Red_Button.class));
			}else{
				showAlert();
			}
		}catch(Exception e){
			e.printStackTrace();
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public void showAlert(){
        SignIn.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.")  
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                     
                AlertDialog alert = builder.create();
                alert.show();               
            }
        });
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
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
	public void onRestart() {
		super.onRestart();
		startActivity(new Intent(this, Red_Button.class));
	}
	public void onBackPressed()
	{
		finish();
	}
}
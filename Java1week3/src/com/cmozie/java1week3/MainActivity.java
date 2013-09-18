
package com.cmozie.java1week3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmozie.classes.*;
import com.cmozie.libz.FileStuff;
import webConnections.*;


// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	
	public static Context _context;
	LinearLayout _appLayout;
	SearchForm _search;
	
	public static LocationDisplay _locationDetails;
	FavDisplay _favorites;
	TextView _popularZips;
	Boolean _connected = false;
	String _zipcode;
	String _areaCode;
	String _city;
	String _county;
	String _state;
	String _latitude;
	String _longitude;
	String _csa_name;
	String _cbsa_name;
	String _region;
	String _timezone;
	
	HashMap<String, String> _history;
	TextView _showJsonData;
	Button _pop;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_appLayout = new LinearLayout(this);
		_popularZips = new TextView(this);
		_history = getHistory();
		//_search.setClickable(false);
		String _placeholderText1 = getResources().getString(R.string.textFieldText);
		String _searchButnText = getResources().getString(R.string.searchButn);
		
		 _search = new SearchForm(_context,_placeholderText1,_searchButnText);
		Log.i("HISTORY READ", _history.toString());
		//ADD search handler
		
		 //EditText searchField = _search.getField();	 
		 Button searchButton = _search.getButton();
		 searchButton.setClickable(false);
		if (_search.toString().length() <1 ) {
			Log.i("PRESSED","PRESSED");
		}
		
		 searchButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View view) {
				
		
					Log.i("PRESSED","PRESSED!");
					getLookup(_search.getField().getText().toString());
				
				
				
			}
		});
		
		 
		 //webConnection
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
			 
		}else if(!_connected) {
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Connection Required!");
			alert.setMessage("You need to connect to an internet service!");
			alert.setCancelable(false);
			alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

					dialog.cancel();
				}
			});
			alert.show();
			searchButton.setClickable(false);
		}
		 _pop = new Button(this);
		 _pop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					_appLayout.addView(_favorites);
					_pop.setClickable(false);
				}
			});
		//add favorite display
		 _favorites = new FavDisplay(_context);
		 _popularZips.setText("Popular Zipcodes");
		 _pop.setText("Click here for popular zipcodes");
		
		
		
		_locationDetails = new LocationDisplay(_context);
		_appLayout.addView(_search);
		_appLayout.addView(_locationDetails);
		//_appLayout.addView(_popularZips);
		_appLayout.addView(_pop);
		
		//_appLayout.addView(_showJsonData);

		_appLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(_appLayout);

	}
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getLookup(String zipcode){
		String baseURL = "http://zipfeeder.us/zip?";
		String key = "key=EN4GbNMq";
		String qs = "";
		try{
			qs = URLEncoder.encode(zipcode, "UTF-8");
		}catch (Exception e) {
			
			Log.e("Bad URL","Encoding Problem");
			qs = "";
		}
		URL finalURL;
		try{
			finalURL = new URL (baseURL + key + "&zips=" + qs);
			Log.i("URL",finalURL.toString());
			QuoteRequest qr = new QuoteRequest();
			qr.execute(finalURL);
		}catch (MalformedURLException e){
			Log.e("BAD URL", "Malformed URL");
			finalURL = null;
		}
	}
	
	private class QuoteRequest extends AsyncTask<URL, Void, String>{
		@Override
		protected String doInBackground(URL...urls){
			String response = "";
		for (URL url : urls) {
			response = WebStuff.getURLStringResponse(url);
		}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			
			Log.i("URL RESPONSE", result);
			try {
				JSONObject json = new JSONObject(result);
				JSONArray ja = json.getJSONArray("zips");
				
				
				Log.i("results",result);
				Boolean error =false;
				if (error) {
					
					Toast toast = Toast.makeText(_context, "Invalid Zipcode", Toast.LENGTH_SHORT);
					toast.show();
					
				}else {
					//loops through json array 
					for (int i = 0; i < ja.length(); i++) {
						//sets a json object to access object values inside array
						JSONObject one = ja.getJSONObject(i);
						
					//setting my text to the values to the strings of the json data
					_zipcode = one.getString("zip_code");
					_areaCode = one.getString("area_code");
					_city = one.getString("city");
					_state = one.getString("state");
					_county = one.getString("county");
					_csa_name = one.getString("csa_name");
					_cbsa_name = one.getString("cbsa_name");
					_latitude = one.getString("latitude");
					_longitude = one.getString("longitude");
					_region = one.getString("region");
					_timezone = one.getString("time_zone");
						 
					}
					Log.i("one", _areaCode + _city + _state + _county + _csa_name + _cbsa_name + _latitude + _longitude + _region + _timezone);
				
					//sets the values of the text by calling the locationInfo function inside of my Locationdisplay class
					_locationDetails.locationInfo(_areaCode, _city, _county, _state, _latitude, _longitude, _csa_name, _cbsa_name, _region, _timezone);  
				
					Toast toast = Toast.makeText(_context, "Valid Zipcode " + _zipcode , Toast.LENGTH_SHORT);
					toast.show();
					
					_history.put("oneObjectitem", ja.toString());
					
					FileStuff.storeObjectFile(_context, "history", _history, false);
					FileStuff.storeStringFile(_context, "temp", ja.toString(), true);
					
					
					
					}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("JSON","JSON OBJECT EXCEPTION");
			}
		}
		
	}
	@SuppressWarnings("unchecked")
	private HashMap<String, String> getHistory(){
		
		Object stored = FileStuff.readObjectFile(_context, "history", false);
		HashMap<String, String> history;
		if (stored == null) {
			Log.i("HISTORY","NO HISTORY FILE FOUND");
			history = new HashMap<String, String>();
		}	else {
			history = (HashMap<String, String>) stored;
		}
		return history;
		
	}
	
}

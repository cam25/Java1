/*
 * project 			Java1Week3
 * 
 * package			com.cmozie.java1week3
 * 
 * name				cameronmozie
 * 
 * date				Sep 19, 2013
 */

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

	//--public statics
	public static Context _context;
	public static LocationDisplay _locationDetails;
	public static Button searchButton;
	
	
	//layout
	LinearLayout _appLayout;
	TextView _localStorageText;
	TextView _localStorageLabel;
	Button _pop;
	
	//bool
	Boolean _connected = false;
	
	//class declarations
	FavDisplay _favorites;
	SearchForm _search;
	
	//strings
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
	
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_appLayout = new LinearLayout(this);
		_localStorageText = new TextView(this);
		_localStorageLabel = new TextView(this);
		
		//sets _history to the get history call
		_history = getHistory();
		
		//accessing resources
		String _placeholderText1 = getResources().getString(R.string.textFieldText);
		String _searchButnText = getResources().getString(R.string.searchButn);
		
		// _search is the search form
		 _search = new SearchForm(_context,_placeholderText1,_searchButnText);
		 
		 //logs the _history text if inside local storage
		Log.i("HISTORY READ", _history.toString());
	 
		//allows to target the search button in the search form to set onclick event
		searchButton = _search.getButton();
	
		//search button on click listener
		 searchButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View view) {
			
					//if the search button is pressed and the text field length is greater than 1 go ahead and search
					if (searchButton.isPressed() && _search.getField().length() > 1) {
						getLookup(_search.getField().getText().toString());
						searchButton.setEnabled(true);
					}
					
					//empties the search field
				_search.getField().setText("");
				
			}
		});
		
		 
		 //webConnection jar file usage
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
			
			//if no connection
		}else if(!_connected) {
			
			//alert for connection
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
		 
		 //popular zipcodes onclick
		 _pop = new Button(this);
		 _pop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//adds the _favorites/spinner to the view button is clicked
					_appLayout.addView(_favorites);
					
					//sets button to non clickable once clicked once 
					_pop.setClickable(false);
					
					
				}
			});
		 //if the search text field is < 1 or the text field is selected then enable else dont
		 if (_search.getField().length() < 1 || _search.getField().isSelected()) {
			 searchButton.setEnabled(true);
		}else  {
			searchButton.setEnabled(false);
		}
		 
		
		//add favorite display
		 _favorites = new FavDisplay(_context);
		 _pop.setText("Click here for popular zipcodes");
		 
		 //sets text field text to local storage text
		 _localStorageText.setText(_history.toString());
		 _localStorageLabel.setText("THIS IS WHERE LOCAL STORAGE TEXT WILL SHOW IF ANY IS STORED:");
		//sets _locationDetails to a new LocationDisplay object
		_locationDetails = new LocationDisplay(_context);
		
		//adding contents to view
		_appLayout.addView(_search);
		_appLayout.addView(_locationDetails);
		_appLayout.addView(_pop);
		_appLayout.addView(_localStorageLabel);
		_appLayout.addView(_localStorageText);
		
		//sets orientation of UI
		_appLayout.setOrientation(LinearLayout.VERTICAL);
		
		//puts the content on the view
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
	
	//get lookup function that allows me to query the api on on click of the search --see searchButton.OnClick 
	/**
	 * Gets the lookup.
	 *
	 * @param zipcode the zipcode
	 * @return the lookup
	 */
	public void getLookup(String zipcode){
		
		//this is the base url of the api
		String baseURL = "http://zipfeeder.us/zip?";
		
		//key needed to use api
		String key = "key=EN4GbNMq";
		//this empty string accepts an empty string which will be for zipcodes entered
		String qs = "";
		try{
			//allows the empty string to recieve the zipcode string encoded
			qs = URLEncoder.encode(zipcode, "UTF-8");
		}catch (Exception e) {
			
			//if an error in the api show the bad url alert
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Bad URL");
			alert.setMessage("An error occured the in the construction of the URL.Encoding problem.");
			alert.setCancelable(false);
			alert.setPositiveButton("Sorry", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

					dialog.cancel();
				}
			});
			alert.show();
			Log.e("Bad URL","Encoding Problem");
			qs = "";
		}
		
		//creates finalURL as a URL
		URL finalURL;
		try{
			//sets the final url to the base plus the api key with the string parameter needed for search as well as the empty string that recieves a zipcode.
			finalURL = new URL (baseURL + key + "&zips=" + qs);
			
			//logs the final url query
			Log.i("URL",finalURL.toString());
			
			mainZipRequest qr = new mainZipRequest();
			qr.execute(finalURL);
		}catch (MalformedURLException e){
			Log.e("BAD URL", "Malformed URL");
			finalURL = null;
		}
	}
	
	/**
	 * The Class mainZipRequest.
	 */
	private class mainZipRequest extends AsyncTask<URL, Void, String>{
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(URL...urls){
			String response = "";
		for (URL url : urls) {
			response = WebStuff.getURLStringResponse(url);
		}
			return response;
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result){
			
			Log.i("URL RESPONSE", result);
			try {
				
				//creates a json object and json array to access my json objects
				JSONObject json = new JSONObject(result);
				JSONArray ja = json.getJSONArray("zips");
				
				
				Log.i("results",result);
				
			
				 
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
				
					//confims zipcode is valie
					Toast toast = Toast.makeText(_context, "Valid Zipcode " + _zipcode , Toast.LENGTH_SHORT);
					toast.show();
					
					_history.put("Location: ", ja.toString());
					
					//store the location into local storage
					FileStuff.storeObjectFile(_context, "history", _history, false);
					FileStuff.storeStringFile(_context, "temp", ja.toString(), true);
					
					
					
					

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//Alert for any error in entering into textfield if not a zipcode
				AlertDialog.Builder alert = new AlertDialog.Builder(_context);
				alert.setTitle("Error");
				alert.setMessage("There was an error searching for your request. Check connections or make sure zipcode is correct. USA zipcodes only.");
				alert.setCancelable(false);
				alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
					}
				});
				alert.show();
				e.printStackTrace();
				Log.e("JSON","JSON OBJECT EXCEPTION");
			}
		}
	}
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, String> getHistory(){
		
		//creates an object named stored that reads the object that is stored in local storage
		Object stored = FileStuff.readObjectFile(_context, "history", false);
		
		//declares the hashmap history variable
		HashMap<String, String> history;
		
		//if theres an error fire alert
		if (stored == null) {
			Log.i("HISTORY","NO HISTORY FILE FOUND");
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Saved Files");
			alert.setMessage("There are no saved zipcodes in local storage. Once a search is made the zipcode will be saved.");
			alert.setCancelable(false);
			alert.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

					dialog.cancel();
				}
			});
			alert.show();
			history = new HashMap<String, String>();
			
			//else store it into the history
		}	else {
			history = (HashMap<String, String>) stored;
		}
		return history;
		
	}
	
}


package com.cmozie.java1week3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cmozie.classes.*;
import com.cmozie.libz.FileStuff;
import com.cmozie.libz.WebStuff;



// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	
	Context _context;
	LinearLayout _appLayout;
	SearchForm _search;
	LocationDisplay _locationDetails;
	FavDisplay _favorites;
	Boolean _connected = false;
	String oneObjectitem;
	HashMap<String, String> _history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_appLayout = new LinearLayout(this);
		_history = getHistory();
		 _search = new SearchForm(_context,"Enter Zipcode","Search");
		Log.i("HISTORY READ", _history.toString());
		//ADD search handler
		 
		 //EditText searchField = _search.getField();	 
		 Button searchButton = _search.getButton();
		 
		 searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				getLookup(_search.getField().getText().toString());
				
			}
		});
		
		 
		 //Detect net connection
		 _connected = WebStuff.getConnectionStatus(_context);
		 if (_connected) {
			Log.i("Network Connection", WebStuff.getConnectionType(_context));
			 
		}
		 
		//add favorite display
		 _favorites = new FavDisplay(_context);
		 
		 
		
		_locationDetails = new LocationDisplay(_context);
		_appLayout.addView(_search);
		_appLayout.addView(_locationDetails);
		_appLayout.addView(_favorites);

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

	private void getLookup(String zipcode){
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
					
					for (int i = 0; i < ja.length(); i++) {
						JSONObject one = ja.getJSONObject(i);
						
						 oneObjectitem = one.getString("zip_code");
					}
					Log.i("one",oneObjectitem);
					
					
					Toast toast = Toast.makeText(_context, "Valid Zipcode " + oneObjectitem, Toast.LENGTH_SHORT);
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

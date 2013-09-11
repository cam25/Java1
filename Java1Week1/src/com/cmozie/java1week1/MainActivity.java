/*
 * project 			Java1Week1
 * 
 * package			com.cmozie.java1week1
 * 
 * name				cameronmozie
 * 
 * date				Sep 10, 2013
 */
package com.cmozie.java1week1;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.cmozie.jsondata.*;



// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	
	TextView showJsonResults;
	TextView infoBox;
	RadioGroup locationOptions;
	ArrayList<Lookup> stateNames;

	
	//RadioGroup boxes;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	LinearLayout ll = new LinearLayout(this);
	LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	
	ll.setLayoutParams(lp);
	ll.setOrientation(LinearLayout.VERTICAL);
	
	TextView info = new TextView(this);
		
		//accessing strings from resources
		String hintText = getResources().getString(R.string.editHint);
		
		String myText = getResources().getString(R.string.info);
		String ctext = getResources().getString(R.string.cbutton);
		
		//accessing function from formdata class
		LinearLayout entryBox = FormData.singleEntryWithButton(this, hintText, "Search");
		
		Button clearRadio = (Button) new Button(this);
		
		
		
		Button findButton = (Button) entryBox.findViewById(2);
		
		findButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText codes = (EditText) v.getTag();
				Log.i("Button Clicked",codes.getText().toString());

				String text = codes.getText().toString();
				
				Log.i("locationOptions", locationOptions.toString());
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();

				String selected = "";
			
				//condition that allows for usage of radio buttons based on if edit text field is empty it will allow you to select radio button
				//and click go to get results. else if you enter something inside text field
				if (codes.length() == 0) {
					
					Log.i("Test1", codes.toString());
					
					if (selectedRadioID ==1 || selectedRadioID == 2 || selectedRadioID == 3) {
						Log.i("Test2", codes.toString());
						
						RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
						  selected = selectedRadio.getText().toString();
						  
						  showJsonResults.setText(JSON.readJSON(selected));
					}
					
					else {
		
						showJsonResults.setText(JSON.readJSON(text));
					}
				
					}else {
						
					showJsonResults.setText(JSON.readJSON(text));
				}
				
			}
		});
		
		clearRadio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//this condition allows for clearing fields for cleaner UI
				
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();
				
			
				  if (selectedRadioID ==1 || selectedRadioID == 2 || selectedRadioID == 3) {
					  RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
					  selectedRadio.getText().toString();
					  showJsonResults.setText("");
					 
						
					}
				  
				  //clears radio buttons
				  locationOptions.clearCheck();
				  //clears json data 
				  showJsonResults.setText("");
			}
		});
		
		//array of items that are being added to the Lookup interface
		ArrayList<Lookup> stateNames = new ArrayList<Lookup>();
		 stateNames.add(new LookupDetails("WashDC",20001));
		 stateNames.add(new LookupDetails("NewYork",11221));
		 stateNames.add(new LookupDetails("Virginia",22314));
		 
		//creating  
		String[] localNames = new String[stateNames.size()];
		for (int i = 0; i < stateNames.size(); i++) {
			
			Log.i("size",stateNames.toString());
			localNames[i] = stateNames.get(i).getUserLocation();
			
			
		}
		
		locationOptions = FormData.getOptions(this, localNames);		
		showJsonResults = FormData.outputTextView(this);
		showJsonResults.setText("Results will show here");
		info.setText(myText);
		clearRadio.setText(ctext);
		
		//added entry box to view
		
		
		ll.addView(info);
		ll.addView(clearRadio);
		ll.addView(locationOptions);
		
		ll.addView(entryBox);
		ll.addView(showJsonResults);
		
		
		
		setContentView(ll);
		
		
		
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

}

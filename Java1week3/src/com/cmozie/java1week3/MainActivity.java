/*
 * project 			Java1Week1
 * 
 * package			com.cmozie.java1week1
 * 
 * name				cameronmozie
 * 
 * date				Sep 10, 2013
 */
package com.cmozie.java1week3;

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
import com.cmozie.classes.*;



// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	
	TextView showJsonResults;
	TextView infoBox;
	RadioGroup locationOptions;
	ArrayList<Lookup> stateNames;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		//setting of layout
	LinearLayout ll = new LinearLayout(this);
	LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	
	ll.setLayoutParams(lp);
	ll.setOrientation(LinearLayout.VERTICAL);
	
	//info text view for info about app
	TextView info = new TextView(this);
		
		//accessing strings from resources
		String hintText = getResources().getString(R.string.editHint);
		String myText = getResources().getString(R.string.info);
		String ctext = getResources().getString(R.string.cbutton);
		
		
		//accessing function from formdata class and passing in of resource hintext 
		LinearLayout entryBox = FormData.singleEntryWithButton(this, hintText, "Search");
		
		//buttons 
		Button resetBtn = (Button) new Button(this);
		Button findButton = (Button) entryBox.findViewById(2);
		
		findButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//setting of edit text value
				EditText codes = (EditText) v.getTag();

				
				//text is the text inside the edit text field
				String text = codes.getText().toString();
				
				//sets radiogroup to an int 
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();

				String selected = "";
			
				//condition that allows for usage of radio buttons based on if edit text field is empty it will allow you to select radio button
				//and click go to get results.Also if no radio buttons are selected and you enter text in edit text field and search it will output based on text. 
				if (codes.length() == 0) {
			
					
					if (selectedRadioID ==1 || selectedRadioID == 2 || selectedRadioID == 3) {
						
						//grabbing id of radio buttons and setting the the selected radio buttons ids to strings
						RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
						  selected = selectedRadio.getText().toString();
						  
						  //ouputs the data from json based on the radio button thats selected and pressing search
						showJsonResults.setText(JSON.readJSON(selected));
					}
					
					else {
						//outputs the data from json based on a zipcode entered into text field
						
						showJsonResults.setText(JSON.readJSON(text));
						
					}
				
					}else {
						//outputs the data from json based on a zipcode entered into text field	
						showJsonResults.setText(JSON.readJSON(text));
				}
				
			}
		});
		
		//reset button on click to clear data fields
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();
				
			
				  if (selectedRadioID ==1 || selectedRadioID == 2 || selectedRadioID == 3) {
					  RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
					  selectedRadio.getText().toString();
					  
					  //clears json results text view
					  showJsonResults.setText("");

					}
				
				  //clears radio buttons
				  locationOptions.clearCheck();
				  
				  //clears json data 
				  showJsonResults.setText("");
				  
				  //clears the form data edit text field
				  FormData.et.setText("");
				 
				
			}
		});
		
		//array of items that are being added to the Lookup interface
		ArrayList<Lookup> stateNames = new ArrayList<Lookup>();
		 stateNames.add(new LookupDetails("WashDC",20001));
		 stateNames.add(new LookupDetails("NewYork",11221));
		 stateNames.add(new LookupDetails("Virginia",22314));
		 
		//creating  an array for the names of my items in my state names array  and looping through it to set the state names to my radio buttons
		String[] localNames = new String[stateNames.size()];
		for (int i = 0; i < stateNames.size(); i++) {
			
			Log.i("size",stateNames.toString());
			localNames[i] = stateNames.get(i).getUserLocation();
			
			
		}
		
		//sets the data from my form data class and sets the names to my radio buttons by passing localNames
		locationOptions = FormData.getOptions(this, localNames);
		
		//sets my showJsonResults text view to recieve the data from my json data
		showJsonResults = FormData.outputTextView(this);
		
		//setting text to text views
		showJsonResults.setText("JSON results will show here");
		info.setText(myText);
		resetBtn.setText(ctext);
		
		//adding elements to my view
		ll.addView(info);
		ll.addView(resetBtn);
		ll.addView(locationOptions);
		ll.addView(entryBox);
		ll.addView(showJsonResults);
		
		//setting content to view
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

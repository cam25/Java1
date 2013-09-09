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



public class MainActivity extends Activity {
	
	TextView showJsonResults;
	RadioGroup locationOptions;
	ArrayList<Lookup> stateNames;
	String etField = "";
	
	
	//RadioGroup boxes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.e("locationOptions", locationOptions.toString());
		
	LinearLayout ll = new LinearLayout(this);
	LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	
	ll.setLayoutParams(lp);
	ll.setOrientation(LinearLayout.VERTICAL);
		
		//accessing strings from resources
		String hintText = getResources().getString(R.string.editHint);
		
		//String myText = getResources().getString(R.string.button);
		
		//accessing function from formdata class
		LinearLayout entryBox = FormData.singleEntryWithButton(this, hintText, "GO");
		
		//Button et = FormData.singleEntryWithButton(context, hint, buttonText);
		
		//final EditText areaCodeText = (EditText) entryBox.findViewById(1);
		
		Button findButton = (Button) entryBox.findViewById(2);
		
		//Button findButton = new Button(this);
		
		//findButton.setText(myText);
		findButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText codes = (EditText) v.getTag();
				Log.i("Button Clicked",codes.getText().toString());

				String text = codes.getText().toString();
				
				Log.i("locationOptions", locationOptions.toString());
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();
				locationOptions.check(selectedRadioID);
				
				RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
				String selected = selectedRadio.getText().toString();
			
				//condition that allows for usage of radio buttons based on if edit text field is empty it will allow you to select radio button
				//and click go to get results. else if you enter something inside text field
				if (codes.length() == 0) {
					showJsonResults.setText(JSON.readJSON(selected));
				}
				else if (codes.length() > 1) {
					
					showJsonResults.setText(JSON.readJSON(text));
				}else if (codes.length() > 1 && selected.length() == 0) {
					
					showJsonResults.setText(JSON.readJSON(text));
				}
				
				
						
				
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
		
		
		//added entry box to view
		
		
		
		ll.addView(locationOptions);
		
		ll.addView(entryBox);
		ll.addView(showJsonResults);
		
		
		
		setContentView(ll);
		
		
		
	}
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

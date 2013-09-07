package com.cmozie.java1week1;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
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
	//RadioGroup boxes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	LinearLayout ll = new LinearLayout(this);
	LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	
	ll.setLayoutParams(lp);
	ll.setOrientation(LinearLayout.VERTICAL);
		
		//accessing strings from resources
		String hintText = getResources().getString(R.string.editHint);
		
		String myText = getResources().getString(R.string.textviewText);
		
		//accessing function from formdata class
		LinearLayout entryBox = FormData.singleEntryWithButton(this, hintText, "GO");
		
		//EditText areaCodeText = (EditText) entryBox.findViewById(1);
		
		Button findButton = (Button) entryBox.findViewById(2);
		
		findButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//setting radio buttons to capture which was clicked
				int selectedRadioID = locationOptions.getCheckedRadioButtonId();
				RadioButton selectedRadio = (RadioButton) locationOptions.findViewById(selectedRadioID);
				
				
				EditText codes = (EditText) v.getTag();
				//console log for button
				Log.i("Button Clicked",codes.getText().toString());
				//grabs json data on click
				String selected = codes.getText().toString();
				
				showJsonResults.setText(JSON.readJSON(selected));
				
			}
			
		
			
		});
		
		showJsonResults = FormData.outputTextView(this);
		showJsonResults.setText("Results will show here");
		
		//array of items that are being added to the Lookup interface
		ArrayList<Lookup> stateNames = new ArrayList<Lookup>();
		 stateNames.add(new LookupDetails("Washington DC"));
		 stateNames.add(new LookupDetails("New York"));
		 stateNames.add(new LookupDetails("Virginia"));
		 
		//creating  
		String[] localNames = new String[stateNames.size()];
		for (int i = 0; i < stateNames.size(); i++) {
			
			localNames[i] = stateNames.get(i).getUserLocation();
		}
		
		RadioGroup locationOptions = FormData.getOptions(this, localNames);		
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

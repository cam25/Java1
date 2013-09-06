package com.cmozie.java1week1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cmozie.jsondata.*;



public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	LinearLayout ll = new LinearLayout(this);
	
		
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
				
				EditText codes = (EditText) v.getTag();
				//console log for button
				Log.i("Button Clicked",codes.getText().toString());
				
			}
		});
		
		ll.addView(entryBox);
		
		setContentView(ll);
		
		
		
	}
	
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

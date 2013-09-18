package com.cmozie.classes;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class FavDisplay extends LinearLayout {
	
	Button _add;
	Button _remove;
	TextView _popularZips;
	Spinner _favList;
	Context _context;
	LocationDisplay _LocationDisplay; 
	
	ArrayList<String>_stacks = new ArrayList<String>();
 

	public FavDisplay(Context context) {
		super(context);
		_context = context;
		
		LayoutParams lp;
		
		
		
		_favList = new Spinner(context);
		updateFavs();
		
		lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.0f);
		
		_favList.setLayoutParams(lp);
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, _stacks);
		listAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		_favList.setAdapter(listAdapter);
		
		_favList.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent,View v,int pos, long id){
				String selected = parent.getItemAtPosition(pos).toString();
				Log.i("Favorite Selected", selected);
				
				//trying to call this function and pass in the selectedItemAtPosition string to the function to run the api query on the selected zipcode in the spinner.
				JSONQuery.getLookup(selected);
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?>parent){
				Log.i("Aborted", "None Selected");
				
			}
			
			
		});
		
		
		
		
		
		this.addView(_favList);
		
		//this.addView(_add);
		//this.addView(_remove);
		
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
		
	}
	

	private void updateFavs(){
		
		
		_stacks.add("22304");
		_stacks.add("21206");
		_stacks.add("20785");
		
	
		
		
	}

	
}

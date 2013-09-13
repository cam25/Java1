
package com.cmozie.java1week3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.cmozie.classes.*;



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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_appLayout = new LinearLayout(this);
		
		 _search = new SearchForm(_context,"Enter Zipcode","Search");
		
		//ADD search handler
		 
		 //EditText searchField = _search.getField();	 
		 Button searchButton = _search.getButton();
		 
		 searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Log.i("CLICK HANDLER",_search.getField().getText().toString());
				
			}
		});
		
		 
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

}

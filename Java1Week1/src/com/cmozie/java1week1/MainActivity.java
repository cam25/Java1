package com.cmozie.java1week1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cmozie.jsondata.*;



public class MainActivity extends Activity {

	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		//accessing strings from resources
		String hintText = getResources().getString(R.string.editHint);
		
		String myText = getResources().getString(R.string.textviewText);
		
		TextView tv = new TextView(this);
		tv.setText(myText);
		
		ll.addView(tv);
		
		EditText et = new EditText(this);
		//ll.addView(et);
		et.setHint(hintText);
		et.setId(1);
		
		Button b = new Button(this);
		b.setText("Select");
		b.setId(2);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//ll.addView(b);
		
		LinearLayout form = new LinearLayout(this);
		form.setOrientation(LinearLayout.VERTICAL);
		form.setLayoutParams(lp);
		form.addView(et);
		form.addView(b);
		ll.addView(form);
		
		setContentView(ll);
		
	}
	
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

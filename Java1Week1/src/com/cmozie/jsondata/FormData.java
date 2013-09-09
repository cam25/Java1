package com.cmozie.jsondata;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import com.cmozie.jsondata.*;
public class FormData {

	public static LinearLayout singleEntryWithButton(Context context, String hint, String buttonText){
		
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		//setting layout params
		ll.setLayoutParams(lp);
		
		EditText et  = new EditText(context);
		
		//added new layout parameters to let the text field stretch the width of screen without pushing button off it
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		
		//sets hint
		et.setHint(hint);
		
		//sets the layout parameters 
		et.setLayoutParams(lp);
		et.setId(1);
		
		Button b = new Button(context);
		b.setText(buttonText);
		b.setId(2);
		b.setTag(et);
		
		ll.addView(et);
		ll.addView(b);
		
		
		//retuning type of data we need to return
		return ll;
		
	}
	
	public static TextView outputTextView(Context context){
		
		//creates a new text view for results of the json query.
		TextView JSONoutputText = new TextView(context);
		JSONoutputText.setId(3);
		
		return JSONoutputText;
		
	}
	
	public static RadioGroup getOptions(Context context, String[] options){
		
		RadioGroup boxes = new RadioGroup(context);
		
		for (int i = 0; i < options.length; i++) {
			RadioButton rb = new RadioButton(context);
			rb.setText(options[i]);
			rb.setId(i+1);
			
			if (rb.isSelected() == true) {
				
				
			}
			boxes.addView(rb);
			
		}
		
		return boxes;
		
	}

}

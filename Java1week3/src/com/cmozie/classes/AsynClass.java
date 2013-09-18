package com.cmozie.classes;

import java.net.URL;

import webConnections.WebStuff;
import android.os.AsyncTask;

public class AsynClass {
	@SuppressWarnings("unused")
	private class QuoteRequest extends AsyncTask<URL, Void, String>{
		@Override
		protected String doInBackground(URL...urls){
			String response = "";
		for (URL url : urls) {
			response = WebStuff.getURLStringResponse(url);
		}
			return response;
		}
		
		
}

	
}

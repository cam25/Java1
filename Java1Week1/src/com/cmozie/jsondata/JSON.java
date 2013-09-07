package com.cmozie.jsondata;

import org.json.JSONException;
import org.json.JSONObject;



public class JSON {

	public static JSONObject createJson() {

		// creats stocks object
		JSONObject location = new JSONObject();
		try {
			JSONObject query = new JSONObject();

			for (Enums locations : Enums.values()) {

				// creat the stocks object
				JSONObject namesObject = new JSONObject();

				// add
				namesObject.put("areaCode", locations.setAreaCode());
				namesObject.put("state", locations.setState());
				namesObject.put("time", locations.setTime());

				query.put(locations.name().toString(), namesObject);

			}
			// add query to songs
			location.put("query", query);
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return location;
	}
	
	public static String readJSON(String selected){
		
		String result= ""; 
		String areaCode = "";
		String state = "";
		String time = "";
		
		
		
		JSONObject object = createJson();
		
		try {
			areaCode = object.getJSONObject("query").getJSONObject(selected).getString("areaCode");
			state = object.getJSONObject("query").getJSONObject(selected).getString("state");
			time = object.getJSONObject("query").getJSONObject(selected).getString("time");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		
		
		result = "areaCode" + areaCode + "\r\n";
		return result;
		
		
		
		
		
		
	}


}

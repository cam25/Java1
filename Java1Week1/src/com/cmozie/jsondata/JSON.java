package com.cmozie.jsondata;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSON {

	ArrayList<Lookup> stateNames;

	public static JSONObject buildJSON() {

		// create lookup json object
		JSONObject lookupObject = new JSONObject();
		try {
			// creates area_codes object
			JSONObject area_codesObject = new JSONObject();

			// Create lookup objects in area_Codes

			for (Enums lookups : Enums.values()) {

				// create new json Object
				JSONObject areaLookupObject = new JSONObject();

				areaLookupObject.put("area_code", lookups.setAreaCode());
				areaLookupObject.put("state", lookups.setState());
				areaLookupObject.put("timezone", lookups.setTimezone());
				areaLookupObject.put("time", lookups.setTime());

				area_codesObject.put(lookups.name().toString(),
						areaLookupObject);

			}
			// adds area_codes to area_codesObject
			lookupObject.put("area_codes", area_codesObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lookupObject;
		

	}

	public static String readJSON(String selected) {

		String result, areaCode, state, timezone,time;
		
		JSONObject object = buildJSON();
		
		try {
			areaCode = object.getJSONObject("area_codes").getJSONObject(selected).getString("area_code");
			state = object.getJSONObject("area_codes").getJSONObject(selected).getString("state");
			timezone = object.getJSONObject("area_codes").getJSONObject(selected).getString("timezone");
			time = object.getJSONObject("area_codes").getJSONObject(selected).getString("time");
			
			Log.i("areacode", areaCode);
			
			result = "Area Code: " + areaCode + "\r\n"
					+ "State: " + state + "\r\n"
					+ "TimeZone: " + timezone + "\r\n"
					+ "Time: " + time + "\r\n";
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		
		
		
		return result;

	}

}

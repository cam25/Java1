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

				Log.i("value",lookups.setLongitude());
				
				// create new json Object
				JSONObject areaLookupObject = new JSONObject();

				areaLookupObject.put("zipcode", lookups.setZipcode());
				areaLookupObject.put("city", lookups.setCity());
				areaLookupObject.put("county", lookups.setCounty());
				areaLookupObject.put("state", lookups.setState());
				areaLookupObject.put("area_code", lookups.setAreaCodes());
				areaLookupObject.put("latitude", lookups.setLatitude());
				areaLookupObject.put("longitude", lookups.setLongitude());
				areaLookupObject.put("csa_name", lookups.setCsa_name());
				areaLookupObject.put("cbsa_name", lookups.setCbsa_name());
				areaLookupObject.put("region", lookups.setRegion());
				areaLookupObject.put("timezone", lookups.setTimezone());

				area_codesObject.put(lookups.setZipcode().toString(),
						areaLookupObject);
				
				area_codesObject.put(lookups.name().toString(),
						areaLookupObject);
				

			}
			// adds area_codes to area_codesObject
			lookupObject.put("zips", area_codesObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lookupObject;
		

	}

	public static String readJSON(String selected) {

		String result, zipcode, city, county, state, area_code, latitude, longitude, csa_name,cbsa_name, region,timezone;
		
		JSONObject object = buildJSON();
		
		try {
			zipcode = object.getJSONObject("zips").getJSONObject(selected).getString("zipcode");
			city = object.getJSONObject("zips").getJSONObject(selected).getString("city");
			county = object.getJSONObject("zips").getJSONObject(selected).getString("county");
			state = object.getJSONObject("zips").getJSONObject(selected).getString("state");
			area_code = object.getJSONObject("zips").getJSONObject(selected).getString("area_code");
			latitude = object.getJSONObject("zips").getJSONObject(selected).getString("latitude");
			longitude = object.getJSONObject("zips").getJSONObject(selected).getString("longitude");
			csa_name = object.getJSONObject("zips").getJSONObject(selected).getString("csa_name");
			cbsa_name = object.getJSONObject("zips").getJSONObject(selected).getString("cbsa_name");
			region = object.getJSONObject("zips").getJSONObject(selected).getString("region");
			timezone = object.getJSONObject("zips").getJSONObject(selected).getString("timezone");
			
			Log.i("areacode", area_code);
			
			result = "Zipcode : " + zipcode + "\r\n"
					+ "City : " + city + "\r\n"
					+ "County : " + county + "\r\n"
					+ "State: " + state + "\r\n"
					+ "Area Code :" + area_code + "\r\n"
					+ "Latitude :" + latitude + "\r\n"
					+ "Longitude :" + longitude + "\r\n"
					+ "Csa_Name :" + csa_name + "\r\n"
					+ "Cbsa_Name :" + cbsa_name + "\r\n"
					+ " Region :" + region + "\r\n"
					+ "Timezone :" + timezone + "\r\n";
 			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
			
			
			
		}
		
		
		
		return result;

	}

}

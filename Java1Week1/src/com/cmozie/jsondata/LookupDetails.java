package com.cmozie.jsondata;

public class LookupDetails implements Lookup {

	String locationName;
	
	public LookupDetails(String locationName){
		
		setUserLocation(locationName);
	}
	
	@Override
	public boolean setUserLocation(String locationName) {
		this.locationName = locationName;
		return true;
	}

	@Override
	public String getUserLocation() {
		// TODO Auto-generated method stub
		return this.locationName;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

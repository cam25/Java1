package com.cmozie.jsondata;

public class LookupDetails implements Lookup {

	String locationName;
	int zipcode;
	
	public LookupDetails(String locationName, int zipcode){
		
		
		setUserLocation(locationName);
		setZip(zipcode);
	}
	
	public boolean setZip(int zipcode)
	{
		this.zipcode = zipcode;
		return true;
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

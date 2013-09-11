/*
 * project 			Java1Week1
 * 
 * package			com.cmozie.jsondata
 * 
 * name				cameronmozie
 * 
 * date				Sep 10, 2013
 */
package com.cmozie.jsondata;

// TODO: Auto-generated Javadoc
/**
 * The Class LookupDetails.
 */
public class LookupDetails implements Lookup {

	String locationName;
	int zipcode;
	
	/**
	 * Instantiates a new lookup details.
	 *
	 * @param locationName the location name
	 * @param zipcode the zipcode
	 */
	public LookupDetails(String locationName, int zipcode){
		
		
		setUserLocation(locationName);
		setZip(zipcode);
	}
	
	/* (non-Javadoc)
	 * @see com.cmozie.jsondata.Lookup#setZip(int)
	 */
	public boolean setZip(int zipcode)
	{
		this.zipcode = zipcode;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.cmozie.jsondata.Lookup#setUserLocation(java.lang.String)
	 */
	@Override
	public boolean setUserLocation(String locationName) {
		this.locationName = locationName;
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cmozie.jsondata.Lookup#getUserLocation()
	 */
	@Override
	public String getUserLocation() {
		// TODO Auto-generated method stub
		return this.locationName;
		
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

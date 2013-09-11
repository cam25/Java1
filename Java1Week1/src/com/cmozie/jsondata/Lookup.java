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
 * The Interface Lookup.
 */
public interface Lookup {
	
	//set the area code thats being passed in to hold data.
	/**
	 * Sets the user location.
	 *
	 * @param locationName the location name
	 * @return true, if successful
	 */
	public boolean setUserLocation(String locationName);
	
	/**
	 * Sets the zip.
	 *
	 * @param zipcode the zipcode
	 * @return true, if successful
	 */
	public boolean setZip(int zipcode);
	
	/**
	 * Gets the user location.
	 *
	 * @return the user location
	 */
	public String getUserLocation();
	
	

}

/*
 * project 			Java1Week1
 * 
 * package			com.cmozie.jsondata
 * 
 * name				cameronmozie
 * 
 * date				Sep 10, 2013
 */
package com.cmozie.classes;

// TODO: Auto-generated Javadoc
/**
 * The Enum Enums.
 */
public enum Enums {
	
	NewYork("11221","Brooklyn","KINGS","NY","347/718/917","40.692502","-73.927079","New York-Newark-Bridgeport"," NY-NJ-CT-PA ,New York-Northern New Jersey-Long Island NY-NJ-PA", "Northeast","5"),
	WashDC("20001", "Washington","DISTRICT OF COLUMBIA", "DC", "202/703","38.910717","-77.01666","Washington-Baltimore-Northern Virginia, DC-MD-VA-WV","Washington-Arlington-Alexandria DC-VA-MD-WV","South","5"),
	Virginia("22314","Alexandria","ALEXANDRIA CITY","VA", "202/571/703","38.81403","-77.063962","Washington-Baltimore-Northern Virginia, DC-MD-VA-WV","Washington-Arlington-Alexandria DC-VA-MD-WV","South","5");
			

	private final String zipcode;
	
	private final String city;
	
	private final String county;
	
	private final String state;
	
	private final String areaCodes;
	
	private final String latitude;
	
	private final String longitude;
	
	private final String csa_name;
	
	private final String cbsa_name;
	
	private final String region;
	
	private final String timezone;
	
	
	
	/**
	 * Instantiates a new enums.
	 *
	 * @param zipcode the zipcode
	 * @param city the city
	 * @param county the county
	 * @param state the state
	 * @param areaCodes the area codes
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param csa_name the csa_name
	 * @param cbsa_name the cbsa_name
	 * @param region the region
	 * @param timezone the timezone
	 */
	private Enums(String zipcode, String city, String county, String state, String areaCodes, String latitude, String longitude, String csa_name,String cbsa_name,String region, String timezone){
		
		this.zipcode = zipcode;
		this.city = city;
		this.county = county;
		this.state = state;
		this.areaCodes = areaCodes;
		this.latitude = latitude;
		this.longitude = longitude;
		this.csa_name = csa_name;
		this.cbsa_name = cbsa_name;
		this.region = region;
		this.timezone = timezone;
		
	}
	
	/**
	 * Sets the zipcode.
	 *
	 * @return the string
	 */
	public String setZipcode(){
		
		return zipcode;
	}
	
/**
 * Sets the city.
 *
 * @return the string
 */
public String setCity(){
		
		return city;
	}

/**
 * Sets the county.
 *
 * @return the string
 */
public String setCounty(){
	
	return county;
}

/**
 * Sets the state.
 *
 * @return the string
 */
public String setState(){
	
	return state;
}

/**
 * Sets the area codes.
 *
 * @return the string
 */
public String setAreaCodes(){
	
	return areaCodes;
}

/**
 * Sets the latitude.
 *
 * @return the string
 */
public String setLatitude(){
	
	return latitude;
}

/**
 * Sets the longitude.
 *
 * @return the string
 */
public String setLongitude(){
	
	return longitude;
}

/**
 * Sets the csa_name.
 *
 * @return the string
 */
public String setCsa_name(){
	
	return csa_name;
}

/**
 * Sets the cbsa_name.
 *
 * @return the string
 */
public String setCbsa_name(){
	
	return cbsa_name;
}

/**
 * Sets the region.
 *
 * @return the string
 */
public String setRegion(){
	
	return region;
}

/**
 * Sets the timezone.
 *
 * @return the string
 */
public String setTimezone(){
	
	return timezone;
}

}

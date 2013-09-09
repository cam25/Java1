package com.cmozie.jsondata;

import java.util.HashMap;

public enum Enums {
	
	NewYork("11221","Brooklyn","KINGS","NY","347/718/917","40.692502","-73.927079","New York-Newark-Bridgeport, NY-NJ-CT-PA","New York-Northern New Jersey-Long Island NY-NJ-PA", "Northeast","5"),
	WashDC("20001", "Washington","DISTRICT OF COLUMBIA", "DC", "202/703","38.910717","-77.01666","Washington-Baltimore-Northern Virginia, DC-MD-VA-WV","Washington-Arlington-Alexandria DC-VA-MD-WV","South","5"),
	Virginia("22314","Alexandria","Virginia", "202/571/703","38.81403","-77.063962","Washington-Baltimore-Northern Virginia", "DC-MD-VA-WV","Washington-Arlington-Alexandria DC-VA-MD-WV","South","5");
			

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
	
	public String setZipcode(){
		
		return zipcode;
	}
	
public String setCity(){
		
		return city;
	}

public String setCounty(){
	
	return county;
}
public String setState(){
	
	return state;
}
public String setAreaCodes(){
	
	return areaCodes;
}

public String setLatitude(){
	
	return latitude;
}
public String setLongitude(){
	
	return longitude;
}

public String setCsa_name(){
	
	return csa_name;
}

public String setCbsa_name(){
	
	return cbsa_name;
}

public String setRegion(){
	
	return region;
}

public String setTimezone(){
	
	return timezone;
}
/*public static HashMap<Enums, Integer>getValue(int code){
	
	
	HashMap<Enums, Integer> value = new HashMap<Enums, Integer>();
	
	locations[] areaStates = {NEW YORK,WAS HDC,VIRGINIA};
	
	if () {
		
	}
	
	return value;
}*/

}

package com.cmozie.jsondata;

import java.util.HashMap;

public enum Enums {
	
	NewYork(201,"NY"," Eastern","1:00PM"),
	WashDC(202, "DC","Eastern", "1:00PM"),
	Virginia(703,"VA","Eastern", "4:20PM");

	private final int areaCode;
	
	private final String state;
	
	private final String timezone;
	
	private final String time;
	
	
	
	private Enums(int areaCode, String state, String timezone, String time){
		
		this.areaCode = areaCode;
		this.state = state;
		this.timezone = timezone;
		this.time = time;
	}
	
	public int setAreaCode(){
		
		return areaCode;
	}
	
public String setState(){
		
		return state;
	}

public String setTimezone(){
	
	return timezone;
}
public String setTime(){
	
	return time;
}

/*public static HashMap<Enums, Integer>getValue(int code){
	
	
	HashMap<Enums, Integer> value = new HashMap<Enums, Integer>();
	
	locations[] areaStates = {NEW YORK,WAS HDC,VIRGINIA};
	
	if () {
		
	}
	
	return value;
}*/

}

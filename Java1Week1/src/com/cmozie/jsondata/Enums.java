package com.cmozie.jsondata;

public enum Enums {
	
	NewYork(201,"NY"," 11:30PM"),
	WashDC(202, "DC", "1:00PM"),
	Virginia(703,"VA", "4:20PM");

	private final int areaCode;
	
	private final String state;
	
	private final String time;
	
	
	
	private Enums(int areaCode, String state, String time){
		
		this.areaCode = areaCode;
		this.state = state;
		this.time = time;
	}
	
	public int setAreaCode(){
		
		return areaCode;
	}
	
public String setState(){
		
		return state;
	}
public String setTime(){
	
	return time;
}
}

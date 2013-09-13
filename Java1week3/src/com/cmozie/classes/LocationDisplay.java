package com.cmozie.classes;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class LocationDisplay extends GridLayout {
	Context _context;
	TextView _zipcode;
	TextView _city;
	TextView _county;
	TextView _state;
	TextView _area_code;
	TextView _latitude;
	TextView _longitude;
	TextView _csa_name;
	TextView _cbsa_name;
	TextView _region;
	TextView _timezone;
	
	
	public LocationDisplay(Context context){
		super(context);
		
		_context = context;
		this.setColumnCount(2);
		
		
		TextView zipcodeLabel = new TextView(_context);
		zipcodeLabel.setText("Zipcode:");
		_zipcode = new TextView(_context);
		
		TextView cityLabel = new TextView(_context);
		cityLabel.setText("City:");
		_city = new TextView(_context);
		
		TextView countyLabel = new TextView(_context);
		countyLabel.setText("County:");
		_county = new TextView(_context);
		
		TextView stateLabel = new TextView(_context);
		stateLabel.setText("State:");
		_state = new TextView(_context);
		
		TextView areaCodeLabel = new TextView(_context);
		areaCodeLabel.setText("Areacode:");
		_area_code = new TextView(_context);
		
		TextView latitudeLabel = new TextView(_context);
		latitudeLabel.setText("Latitude:");
		_latitude = new TextView(_context);
		
		TextView longitudeLabel = new TextView(_context);
		longitudeLabel.setText("Longitude:");
		_longitude = new TextView(_context);
		
		TextView csaNameLabel = new TextView(_context);
		csaNameLabel.setText("Csa-Name:");
		_csa_name = new TextView(_context);
		
		TextView cbsaNameLabel = new TextView(_context);
		cbsaNameLabel.setText("Cbsa-Name:");
		_cbsa_name = new TextView(_context);
		
		TextView regionLabel = new TextView(_context);
		regionLabel.setText("Region:");
		_region = new TextView(_context);
		
		TextView timeZoneLabel = new TextView(_context);
		timeZoneLabel.setText("Timezone:");
		_timezone = new TextView(_context);
		
		
		this.addView(areaCodeLabel);
		this.addView(_area_code);
		
		this.addView(cityLabel);
		this.addView(_city);
		
		this.addView(countyLabel);
		this.addView(_county);
		
		this.addView(stateLabel);
		this.addView(_state);
	
		
		this.addView(latitudeLabel);
		this.addView(_latitude);
		
		this.addView(longitudeLabel);
		this.addView(_longitude);
		
		this.addView(csaNameLabel);
		this.addView(_csa_name);
		
		this.addView(cbsaNameLabel);
		this.addView(_cbsa_name);
		
		this.addView(regionLabel);
		this.addView(_region);
		
		this.addView(timeZoneLabel);
		this.addView(_timezone);
		
	
	}

}

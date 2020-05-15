package entryFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.Airport;
import location.Location;
import planningEntry.FlightEntry;
import planningEntry.SingleSortedResourceEntryImpl;
import planningEntry.TwoLocationEntryImpl;
import resource.Flight;
import timeslot.Timeslot;

public class FlightEntryFactory implements EntryFactory<FlightEntry> {

	@Override
	public FlightEntry getEntry(String S) {
		// TODO Auto-generated method stub
		
		String str = getMessage("Flight:",S);
		String[] strs = str.split(",");
		String flightNumber = strs[1];
		
		TwoLocationEntryImpl te = new TwoLocationEntryImpl();
		SingleSortedResourceEntryImpl<Flight> se = new SingleSortedResourceEntryImpl<Flight>();
		Location start = new Airport(getMessage("DepartureAirport:",S));
		Location end = new Airport(getMessage("ArrivalAirport:",S));
		te.setLocations(start, end);
		
		String startTime = getMessage("DepatureTime:",S);
		String endTime = getMessage("ArrivalTime:",S);
		Timeslot timeslot = new Timeslot(startTime,endTime);
		if(timeslot.timeSub()>1) {
			System.out.println("起止时间差超过一天！请选择其他文件");
			return null;
		}
		
		String numbering = getMessage("Plane:",S);
		String modelNumber = getMessage("Type:",S);
		int seatsNumber = Integer.parseInt(getMessage("Seats:",S));
		float age = Float.parseFloat(getMessage("Age:",S));
		Flight flight = new Flight(numbering,modelNumber,seatsNumber,age);
		se.setResource(flight);	
		
		return new FlightEntry(flightNumber,timeslot,te,se);
	}

	/**
	 * 
	 * @param front
	 * @param S
	 * @return S中front所在行中在front后面的字符串
	 */
	private String getMessage(String front,String S) {
		Pattern pattern = Pattern.compile("(?<="+front+").+");
		Matcher mc = pattern.matcher(S);
		while(mc.find())
			return mc.group();
		return "";
	}
	
}

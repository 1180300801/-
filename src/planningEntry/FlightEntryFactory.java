package planningEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.Location;
import resource.Flight;
import timeslot.Timeslot;

public class FlightEntryFactory implements EntryFactory<FlightEntry> {

	@Override
	public FlightEntry getEntry(String S) {
		// TODO Auto-generated method stub
		
		TwoLocationEntryImpl te = new TwoLocationEntryImpl();
		SingleSortedResourceEntryImpl<Flight> se = new SingleSortedResourceEntryImpl<Flight>();
		Location start = new Location(getMessage("DepartureAirport:",S));
		Location end = new Location(getMessage("ArrivalAirport:",S));
		te.setLocations(start, end);
		
		String startTime = getMessage("DepatureTime:",S);
		String endTime = getMessage("ArrivalTime:",S);
		Timeslot timeslot = new Timeslot(startTime,endTime);
		if(timeSub(timeslot)>1) {
			System.out.println("起止时间差超过一天！请选择其他文件");
			return null;
		}
		
		String numbering = getMessage("Plane:",S);
		String modelNumber = getMessage("Type:",S);
		int seatsNumber = Integer.parseInt(getMessage("Seats:",S));
		float age = Float.parseFloat(getMessage("Age:",S));
		Flight flight = new Flight(numbering,modelNumber,seatsNumber,age);
		se.setResource(flight);	
		
		return new FlightEntry(timeslot,te,se);
	}

	private String getMessage(String front,String S) {
		Pattern pattern = Pattern.compile("(?<="+front+").+");
		Matcher mc = pattern.matcher(S);
		while(mc.find())
			return mc.group();
		return "";
	}
	
	private int timeSub(Timeslot timeslot) {
		String[] s = timeslot.getStartTime().split(" ");
		String[] s1 = s[0].split("-");
		String[] str = timeslot.getEndTime().split(" ");
		String[] str1 = str[0].split("-");
		int subResult = 100*(Integer.parseInt(s1[0])-Integer.parseInt(str1[0]));		
		subResult += 10*(Integer.parseInt(s1[1])-Integer.parseInt(str1[1]));
		subResult += Integer.parseInt(s1[2])-Integer.parseInt(str1[2]);
		return subResult;
	}
}

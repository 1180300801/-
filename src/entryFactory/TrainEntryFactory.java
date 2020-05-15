package entryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.RailwayStation;
import planningEntry.BlockableEntryImpl;
import planningEntry.MultipleLocationEntryImpl;
import planningEntry.MultipleSortedResourceEntryImpl;
import planningEntry.TrainEntry;
import resource.Carriage;
import timeslot.Timeslot;

public class TrainEntryFactory implements EntryFactory<TrainEntry> {

	@Override
	public TrainEntry getEntry(String S) {
		// TODO Auto-generated method stub
		String str = getMessage("Train:",S);
		String[] strs = str.split(",");
		String trainNumber = strs[1];
		
		MultipleLocationEntryImpl mle = new MultipleLocationEntryImpl();
		MultipleSortedResourceEntryImpl<Carriage> msre = new MultipleSortedResourceEntryImpl<Carriage>();
		List<RailwayStation> locations = new ArrayList<RailwayStation>();
		locations.add(new RailwayStation(getMessage("DepartureStation:",S)));
		String l = getMessage("IntermediateStation:",S);
		String[] inter = l.split(",");
		for(int i = 0;i<inter.length;i++) {
			locations.add(new RailwayStation(inter[i]));
		}
		locations.add(new RailwayStation(getMessage("ArrivalStation:",S)));
		mle.setLocations(locations);
		
		String startTime = getMessage("DepatureTime:",S);
		String endTime = getMessage("ArrivalTime:",S);
		Timeslot timeslot = new Timeslot(startTime,endTime);
		
		
		String numbering = getMessage("Carriage:",S);
		String type = getMessage("Type:",S);
		int seatsNumber = Integer.parseInt(getMessage("PersonnelNumber:",S));
		String factoryYear = getMessage("FactoryYear:",S);
		Carriage carriage = new Carriage(numbering,type,seatsNumber,factoryYear);
		List<Carriage> carriages = new ArrayList<Carriage>();
		carriages.add(carriage);	
		msre.setResources(carriages);
		
		BlockableEntryImpl be = new BlockableEntryImpl();
		
		return new TrainEntry(trainNumber,timeslot,mle,msre,be);
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

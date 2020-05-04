package planningEntryCollection;

import java.io.IOException;

import planningEntry.FlightEntry;

public class Test {

	public static void main(String[] args) throws IOException {
		FlightCollection f = new FlightCollection("src/planningEntryCollection/FlightSchedule_5.txt");
		for(FlightEntry fe:f) {
			System.out.println(fe.getStartAndEndTime().getStartTime());
		}
		
	}
}

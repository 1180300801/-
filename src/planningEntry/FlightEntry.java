package planningEntry;

import entryState.EntryState;
import location.Location;
import resource.Flight;
import timeslot.Timeslot;

public class FlightEntry extends CommonPlanningEntry implements FlightPlanningEntry {

	private TwoLocationEntryImpl te;
	private SingleSortedResourceEntryImpl<Flight> se;
	
	public FlightEntry(Timeslot startAndEndTime,TwoLocationEntryImpl te,SingleSortedResourceEntryImpl<Flight> se) {
		super(startAndEndTime);
		this.te = te;
		this.se = se;
		if(this.se != null)
			this.currentState = EntryState.ALLOCATED;
	}
	
	@Override
	public void setLocations(Location start, Location end) {
		// TODO Auto-generated method stub
		te.setLocations(start, end);
	}

	@Override
	public void setResource(Flight resource) {
		// TODO Auto-generated method stub
		se.setResource(resource);
	}

}

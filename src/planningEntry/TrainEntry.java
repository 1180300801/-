package planningEntry;

import java.util.List;

import entryState.EntryState;
import location.Location;
import resource.Train;
import timeslot.Timeslot;

public class TrainEntry extends CommonPlanningEntry implements TrainplanningEntry {

	private MultipleLocationEntryImpl mle;
	private MultipleSortedResourceEntryImpl<Train> msre;
	private BlockableEntryImpl be;
	
	public TrainEntry(Timeslot startAndEndTime,MultipleLocationEntryImpl mle,MultipleSortedResourceEntryImpl<Train> msre,BlockableEntryImpl be) {
		super(startAndEndTime);
		this.mle = mle;
		this.msre = msre;
		this.be = be;
		if(this.msre != null)
			this.currentState = EntryState.ALLOCATED;
	}
	
	@Override
	public void setLocations(List<Location> locations) {
		// TODO Auto-generated method stub
		mle.setLocations(locations);
	}

	@Override
	public void setResources(List<Train> resources) {
		// TODO Auto-generated method stub
		msre.setResources(resources);
	}

	@Override
	public void block(Timeslot timeslot) {
		// TODO Auto-generated method stub
		be.block(timeslot);
	}

}

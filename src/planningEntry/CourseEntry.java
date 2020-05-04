package planningEntry;

import entryState.EntryState;
import location.Location;
import resource.Teacher;
import timeslot.Timeslot;

public class CourseEntry extends CommonPlanningEntry implements CoursePlanningEntry{

	private SingleLocationEntryImpl se;
	private SingleSortedResourceEntryImpl<Teacher> ssre;
	
	public CourseEntry(Timeslot startAndEndTime,SingleLocationEntryImpl se,SingleSortedResourceEntryImpl<Teacher> ssre) {
		super(startAndEndTime);
		this.se = se;
		this.ssre = ssre;
		if(this.ssre != null)
			this.currentState = EntryState.ALLOCATED;
	}
	
	@Override
	public void setLocations(Location location) {
		// TODO Auto-generated method stub
		se.setLocations(location);
	}

	@Override
	public void setResource(Teacher resource) {
		// TODO Auto-generated method stub
		ssre.setResource(resource);
	}

}

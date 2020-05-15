package planningEntry;

import location.Location;

public class TwoLocationEntryImpl implements TwoLocationEntry {

	private Location start;
	private Location end;
	//AF:计划项的起止地
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	@Override
	public void setLocations(Location start, Location end) {
		// TODO Auto-generated method stub
		if(this.start == null&&this.end == null) {
			this.start = start;
			this.end = end;
		}		
	}

	public Location getStart() {
		return start;
	}

	public Location getEnd() {
		return end;
	}

}

package planningEntry;

import location.Location;

public class SingleLocationEntryImpl implements SingleLocationEntry {

	private Location location;
	
	@Override
	public void setLocations(Location location) {
		// TODO Auto-generated method stub
		if(this.location == null)
		    this.location = location;
	}

	/**
	 * 
	 * @return �ƻ����λ����Ϣ
	 */
	public Location getLocation(){
		return location;
	}
	
	public void resetLocation(Location location) {
		//location.
		this.location = location;
	}
}

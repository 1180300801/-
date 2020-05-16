package planningEntry;

import location.Location;

public class SingleLocationEntryImpl implements SingleLocationEntry {

	private Location location;
	//AF:单个计划项的位置
	//RI:true
	//Safety from rep exposure:所有属性均为私有

	@Override
	public void setLocations(Location location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

	/**
	 * 
	 * @return 计划项的位置信息
	 */
	public Location getLocation(){
		return location;
	}
	
	public void resetLocation(Location location) {
		//location.
		this.location = location;
	}
}

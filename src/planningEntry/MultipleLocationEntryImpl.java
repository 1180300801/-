package planningEntry;

import java.util.List;
import location.Location;

public class MultipleLocationEntryImpl implements MultipleLocationEntry {

	private List<? extends Location> locations;
	//AF:存放计划项的位置
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	@Override
	public void setLocations(List<? extends Location> locations) {
		// TODO Auto-generated method stub
		if(this.locations == null)
		    this.locations = locations;
	}

	/**
	 * 
	 * @return 计划项的位置信息
	 */
	public List<? extends Location> getLocations() {
		return locations;
	}
}

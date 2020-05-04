package planningEntry;

import java.util.List;
import location.Location;

public class MultipleLocationEntryImpl implements MultipleLocationEntry {

	private List<Location> locations;
	
	@Override
	public void setLocations(List<Location> locations) {
		// TODO Auto-generated method stub
		if(this.locations == null)
		    this.locations = locations;
	}

	public List<Location> getLocations() {
		return locations;
	}
}

package planningEntry;

import java.util.List;
import location.Location;

public class MultipleLocationEntryImpl implements MultipleLocationEntry {

	private List<? extends Location> locations;
	//AF:��żƻ����λ��
	//RI:true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	@Override
	public void setLocations(List<? extends Location> locations) {
		// TODO Auto-generated method stub
		if(this.locations == null)
		    this.locations = locations;
	}

	/**
	 * 
	 * @return �ƻ����λ����Ϣ
	 */
	public List<? extends Location> getLocations() {
		return locations;
	}
}

package planningEntry;

import java.util.List;
import location.Location;

public interface MultipleLocationEntry {

	/**
	 * ���ö��λ��
	 * @param locations λ���嵥
	 */
	void setLocations(List<? extends Location> locations);
}

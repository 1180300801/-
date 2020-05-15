package planningEntry;

import java.util.List;
import location.Location;

public interface MultipleLocationEntry {

	/**
	 * 设置多个位置
	 * @param locations 位置清单
	 */
	void setLocations(List<? extends Location> locations);
}

package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;
import resource.Resource;

public class PlanningEntryAPIs {

	private LocationConflictAPI lf;//位置矛盾
	private ResourceExclusiveConflictAPI pec;//资源矛盾
	private PreEntryPerResourceAPI ppr;//前序计划
	
	public PlanningEntryAPIs() {
		lf = new CheckLocationConflict();
		pec = new CheckResourceExclusiveConflict();
		ppr = new FindPreEntryPerResource();
	}
	
	public boolean checkLocationConflict(List<PlanningEntry> entries) {
		return lf.checkLocationConflict(entries);
	}
	
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries) {
		return pec.checkResourceExclusiveConflict(entries);
	}
	
	public PlanningEntry findPreEntryPerResource(Resource r, PlanningEntry e, List<PlanningEntry> entries) {
		return ppr.findPreEntryPerResource(r,e,entries);
	}
}

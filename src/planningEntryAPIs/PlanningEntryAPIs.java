package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;
import resource.Resource;

public class PlanningEntryAPIs {

	private LocationConflictAPI lf;//λ��ì��
	private ResourceExclusiveConflictAPI pec;//��Դì��
	private PreEntryPerResourceAPI ppr;//ǰ��ƻ�
	
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

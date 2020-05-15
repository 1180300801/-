package planningEntryAPIs;

import java.util.List;
import planningEntryCollection.EntryComparator;
import planningEntry.PlanningEntry;

public class FindPreEntryPerResource implements PreEntryPerResourceAPI {

	@Override
	public PlanningEntry findPreEntryPerResource(String r, PlanningEntry e, List<PlanningEntry> entries) {
		// TODO Auto-generated method stub
		PlanningEntry cpe = null;
		EntryComparator comp = new EntryComparator();
		here:
		for(PlanningEntry pe:entries) {
			if(comp.compare(pe,e) == -1) {
				if(pe.getResource().contains(r))
					cpe = pe;
			}
			else
				break here;
		}
		return cpe;
	}

}

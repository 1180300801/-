package planningEntryCollection;

import java.util.Comparator;
import planningEntry.PlanningEntry;

public class EntryComparator implements Comparator<PlanningEntry> {

	@Override
	public int compare(PlanningEntry o1, PlanningEntry o2) {
		// TODO Auto-generated method stub
		int a1,a2,b1,b2;
		String[] s1 = o1.getStartAndEndTime().getStartTime().split(" ");
		a1 = Integer.parseInt(s1[0].replace("-", ""));
		a2 = Integer.parseInt(s1[1].replace(":", ""));
		String[] s2 = o2.getStartAndEndTime().getStartTime().split(" ");
		b1 = Integer.parseInt(s2[0].replace("-", ""));
		b2 = Integer.parseInt(s2[1].replace(":", ""));
		if(a1>b1) 
			return 1;
		else if(a1 == b1) {
			if(a2>b2)
				return 1;
			else if(a2<b2)
				return -1;
		}
		else if(a1<b1)
			return -1;
		return 0;
	}

}

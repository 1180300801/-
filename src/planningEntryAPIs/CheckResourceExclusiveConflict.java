package planningEntryAPIs;

import java.util.List;

import javax.swing.JOptionPane;

import planningEntry.PlanningEntry;
import resource.Resource;
import timeslot.Timeslot;

public class CheckResourceExclusiveConflict implements ResourceExclusiveConflictAPI {

	@Override
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries) {
		for(int i = 0;i<entries.size()-1;i++) {
			for(int j = i+1;j<entries.size();j++) {
				List<Resource> re = entries.get(j).getResource();
				for(Resource s:re) {
					if(entries.get(i).getResource().contains(s)) {
						Timeslot ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getStartTime());
						if(ts.timeSub() == 0) {
							if(ts.timeSubHourAndMin()>0) {
								ts = new Timeslot(entries.get(j).getStartAndEndTime().getStartTime(),entries.get(i).getStartAndEndTime().getEndTime());
								if(ts.timeSubHourAndMin()>0) {
									JOptionPane.showMessageDialog(null, entries.get(i).getStartAndEndTime().getStartTime()+entries.get(i).getEntryName()+"��"+entries.get(j).getStartAndEndTime().getStartTime()+entries.get(j).getEntryName()+"������Դ��ռì�ܣ�"+"\n��ռ����ԴΪ��"+s.getResource());
									return false;
								}
							}
							else {
								ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getEndTime());
								if(ts.timeSubHourAndMin()>0) {
									JOptionPane.showMessageDialog(null, entries.get(i).getStartAndEndTime().getStartTime()+entries.get(i).getEntryName()+"��"+entries.get(j).getStartAndEndTime().getStartTime()+entries.get(j).getEntryName()+"������Դ��ռì�ܣ�"+"\n��ռ����ԴΪ��"+s.getResource());
									return false;
								}
							}	
						}
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, "��������Դ��ռì�ܣ�");
		return true;	
	}

}

package planningEntryAPIs;

import java.util.List;

import javax.swing.JOptionPane;

import planningEntry.PlanningEntry;
import timeslot.Timeslot;

public class CheckLocationConflict implements LocationConflictAPI {

	@Override
	public boolean checkLocationConflict(List<PlanningEntry> entries) {
		for(int i = 0;i<entries.size()-1;i++) {
			for(int j = i+1;j<entries.size();j++) {
				//������ͬʱ��ʱ���Ƿ�ì��
				if(entries.get(i).getLocation().equals(entries.get(j).getLocation())) {
					//�Ƚ�i��j�Ŀ�ʼʱ��
					Timeslot ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getStartTime());
					if(ts.timeSubHourAndMin()>0) {
						//j�Ŀ�ʼʱ������iʱ���Ƚ�i�Ľ���ʱ����j�Ŀ�ʼʱ�䣬�Դ˱��j�Ƿ���i����ʱ��ì��
						ts = new Timeslot(entries.get(j).getStartAndEndTime().getStartTime(),entries.get(i).getStartAndEndTime().getEndTime());
						if(ts.timeSubHourAndMin()>0) {
							JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"��"+entries.get(j).getEntryName()+"����λ����ռì�ܣ�");
							return false;
						}
					}
					else {
						//i�Ŀ�ʼʱ������jʱ���Ƚ�j�Ľ���ʱ����i�Ŀ�ʼʱ�䣬�Դ˱��j�Ƿ���i����ʱ��ì��
						ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getEndTime());
						if(ts.timeSubHourAndMin()>0){
							JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"��"+entries.get(j).getEntryName()+"����λ����ռì�ܣ�");
							return false;
						}
					}					
				}
			}
		}
		return true;	
	}

}

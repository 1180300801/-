package planningEntryAPIs;

import java.util.List;

import javax.swing.JOptionPane;

import planningEntry.PlanningEntry;
import timeslot.Timeslot;

public class AnotherCheckLocationConflict implements LocationConflictAPI {

	@Override
	public boolean checkLocationConflict(List<PlanningEntry> entries) {
		// TODO Auto-generated method stub
		for(int i = 0;i<entries.size()-1;i++) {
			for(int j = i+1;j<entries.size();j++) {
				//�Ƚ�i��j�Ŀ�ʼʱ��
				Timeslot ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getStartTime());
				//j�Ŀ�ʼʱ������iʱ���Ƚ�i�Ľ���ʱ����j�Ŀ�ʼʱ�䣬�Դ˱��j�Ƿ���i����ʱ��ì�� 
				Timeslot ts1 = new Timeslot(entries.get(j).getStartAndEndTime().getStartTime(),entries.get(i).getStartAndEndTime().getEndTime());
				//i�Ŀ�ʼʱ������jʱ���Ƚ�j�Ľ���ʱ����i�Ŀ�ʼʱ�䣬�Դ˱��j�Ƿ���i����ʱ��ì��
				Timeslot ts2 = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getEndTime());
				//ʱ��ì��ʱ�������Ƿ���ͬ
				if((ts.timeSub()>0&ts1.timeSubHourAndMin()>0)|(ts.timeSub()<=0&ts2.timeSubHourAndMin()>0)) {
					if(entries.get(i).getLocation().equals(entries.get(j).getLocation())){
						JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"��"+entries.get(j).getEntryName()+"����λ����ռì�ܣ�");
						return false;
					}
				}
			}
		}
		return true;	
	}

}

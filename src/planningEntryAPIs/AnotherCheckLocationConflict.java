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
				//比较i，j的开始时间
				Timeslot ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getStartTime());
				//j的开始时间晚于i时，比较i的结束时间与j的开始时间，以此辨别j是否与i存在时间矛盾 
				Timeslot ts1 = new Timeslot(entries.get(j).getStartAndEndTime().getStartTime(),entries.get(i).getStartAndEndTime().getEndTime());
				//i的开始时间晚于j时，比较j的结束时间与i的开始时间，以此辨别j是否与i存在时间矛盾
				Timeslot ts2 = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getEndTime());
				//时间矛盾时看地名是否相同
				if((ts.timeSub()>0&ts1.timeSubHourAndMin()>0)|(ts.timeSub()<=0&ts2.timeSubHourAndMin()>0)) {
					if(entries.get(i).getLocation().equals(entries.get(j).getLocation())){
						JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"与"+entries.get(j).getEntryName()+"存在位置抢占矛盾！");
						return false;
					}
				}
			}
		}
		return true;	
	}

}

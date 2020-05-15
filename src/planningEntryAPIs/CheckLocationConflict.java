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
				//地名相同时看时间是否矛盾
				if(entries.get(i).getLocation().equals(entries.get(j).getLocation())) {
					//比较i，j的开始时间
					Timeslot ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getStartTime());
					if(ts.timeSubHourAndMin()>0) {
						//j的开始时间晚于i时，比较i的结束时间与j的开始时间，以此辨别j是否与i存在时间矛盾
						ts = new Timeslot(entries.get(j).getStartAndEndTime().getStartTime(),entries.get(i).getStartAndEndTime().getEndTime());
						if(ts.timeSubHourAndMin()>0) {
							JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"与"+entries.get(j).getEntryName()+"存在位置抢占矛盾！");
							return false;
						}
					}
					else {
						//i的开始时间晚于j时，比较j的结束时间与i的开始时间，以此辨别j是否与i存在时间矛盾
						ts = new Timeslot(entries.get(i).getStartAndEndTime().getStartTime(),entries.get(j).getStartAndEndTime().getEndTime());
						if(ts.timeSubHourAndMin()>0){
							JOptionPane.showMessageDialog(null, entries.get(i).getEntryName()+"与"+entries.get(j).getEntryName()+"存在位置抢占矛盾！");
							return false;
						}
					}					
				}
			}
		}
		return true;	
	}

}

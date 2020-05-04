package planningEntry;

import java.util.ArrayList;
import java.util.List;
import timeslot.Timeslot;

public class BlockableEntryImpl implements BlockableEntry {

	private List<Timeslot> time = new ArrayList<Timeslot>();//存放暂停时间对
	//AF:一个包含暂停时间对的集合
	//RI:true
	
	public List<Timeslot> getTime() {
		return time;
	}
		
	@Override
	public void block(Timeslot timeslot) {
		// TODO Auto-generated method stub
		time.add(timeslot);
	}

}

package planningEntry;

import java.util.ArrayList;
import java.util.List;
import timeslot.Timeslot;

public class BlockableEntryImpl implements BlockableEntry {

	private List<Timeslot> time = new ArrayList<Timeslot>();//�����ͣʱ���
	//AF:һ��������ͣʱ��Եļ���
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

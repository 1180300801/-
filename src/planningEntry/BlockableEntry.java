package planningEntry;

import timeslot.Timeslot;

public interface BlockableEntry {

	/**
	 * 任务阻塞时，存放阻塞的时长
	 * @param timeslot 阻塞的时长
	 */
	void block(Timeslot timeslot);
}

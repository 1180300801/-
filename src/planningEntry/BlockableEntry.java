package planningEntry;

import timeslot.Timeslot;

public interface BlockableEntry {

	/**
	 * ��������ʱ�����������ʱ��
	 * @param timeslot ������ʱ��
	 */
	void block(Timeslot timeslot);
}

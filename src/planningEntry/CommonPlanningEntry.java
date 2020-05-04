package planningEntry;

import entryState.EntryState;
import timeslot.Timeslot;

public class CommonPlanningEntry {

	EntryState currentState;
	
	private Timeslot startAndEndTime;//��ʼʱ��
	//AF:����һ���ƻ������ʼʱ��
	//RI:��ʼʱ��ͽ���ʱ����� yyyy-MM-dd HH:mm���﷨����
	
	public CommonPlanningEntry(Timeslot startAndEndTime) {
		currentState = EntryState.WAITING;
		this.startAndEndTime = startAndEndTime;
	}

	/**
	 * 
	 * @return �ƻ������ʼʱ��
	 */
	public Timeslot getStartAndEndTime() {
		return startAndEndTime;
	}

	/**
	 * ����
	 * @throws Exception
	 */
	public void Start() throws Exception {
		if(currentState == EntryState.ALLOCATED)
		    currentState = EntryState.RUNNING;
		else
			throw new Exception("δ������Դ����");
	}
	
	/**
	 * ����
	 * @throws Exception
	 */
	public void End() throws Exception {
		if(currentState == EntryState.RUNNING)
		    currentState = EntryState.ENDED;
		else
			throw new Exception("δ��������");
	}
	
	/**
	 * ȡ��
	 * @throws Exception
	 */
	public void Cancell() throws Exception {
		if(currentState == EntryState.WAITING||currentState == EntryState.ALLOCATED)
		    currentState = EntryState.CANCELLED;
		else
			throw new Exception("��ǰ״̬��֧��ȡ������");
	}
	
	/**
	 * ���ص�ǰ״̬
	 * @return
	 */
	public EntryState getState() {
		return currentState;
	}
	
}
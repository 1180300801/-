package planningEntry;

import java.util.List;

import entryState.Context;
import entryState.StateWaiting;
import timeslot.Timeslot;

public class CommonPlanningEntry implements PlanningEntry {

	private Context currentState = new Context(StateWaiting.instance);
	private Timeslot startAndEndTime;//��ʼʱ��
	//AF:����һ���ƻ������ʼʱ��
	//RI:��ʼʱ��ͽ���ʱ����� yyyy-MM-dd HH:mm���﷨����
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	public CommonPlanningEntry(Timeslot startAndEndTime) {
		setStartAndEndTime(startAndEndTime);
	}

	/**
	 * 
	 * @return �ƻ������ʼʱ��
	 */
	public Timeslot getStartAndEndTime() {
		return startAndEndTime;
	}

	/**
	 * ״̬ת��
	 * @param c ת������
	 * @return ת���ɹ�����true
	 */
	public boolean setCurrentState(String c) {
		currentState.move(c);
		return true;
	}
	
	@Override
	public boolean Start() {
		if(currentState.getState().toString().equals("Allocated")) {
			currentState.move("r");
			return true;
		}
		    
		else
			System.out.println("δ������Դ����");
		return false;
	}
	
	@Override
	public boolean End() {
		if(currentState.getState().toString().equals("Running")) {
			currentState.move("e");
			return true;
		}
		    
		else
			System.out.println("δ��������");
		return false;
	}
	
	@Override
	public boolean Cancell() {
        String s = currentState.getState().toString();
		if(s.equals("Waiting")||s.equals("Allocated")) {
			currentState.move("c");
			return true;
		}
			
		else
			System.out.println("��ǰ״̬��֧��ȡ������");
		return false;
	}
	
	@Override
	public String getState() {
		return currentState.getState().toString();
	}

	@Override
	public void setStartAndEndTime(Timeslot startAndEndTime) {
		// TODO Auto-generated method stub
		this.startAndEndTime = startAndEndTime;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEntryName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
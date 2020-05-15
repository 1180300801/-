package planningEntry;

import java.util.List;

import entryState.Context;
import entryState.StateWaiting;
import timeslot.Timeslot;

public class CommonPlanningEntry implements PlanningEntry {

	private Context currentState = new Context(StateWaiting.instance);
	private Timeslot startAndEndTime;//开始时间
	//AF:代表一个计划项的起始时间
	//RI:起始时间和结束时间符合 yyyy-MM-dd HH:mm的语法规则
	//Safety from rep exposure:所有属性均为私有
	
	public CommonPlanningEntry(Timeslot startAndEndTime) {
		setStartAndEndTime(startAndEndTime);
	}

	/**
	 * 
	 * @return 计划项的起始时间
	 */
	public Timeslot getStartAndEndTime() {
		return startAndEndTime;
	}

	/**
	 * 状态转换
	 * @param c 转换参数
	 * @return 转换成功返回true
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
			System.out.println("未分配资源！！");
		return false;
	}
	
	@Override
	public boolean End() {
		if(currentState.getState().toString().equals("Running")) {
			currentState.move("e");
			return true;
		}
		    
		else
			System.out.println("未启动！！");
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
			System.out.println("当前状态不支持取消！！");
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
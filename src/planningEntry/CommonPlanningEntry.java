package planningEntry;

import entryState.EntryState;
import timeslot.Timeslot;

public class CommonPlanningEntry {

	EntryState currentState;
	
	private Timeslot startAndEndTime;//开始时间
	//AF:代表一个计划项的起始时间
	//RI:起始时间和结束时间符合 yyyy-MM-dd HH:mm的语法规则
	
	public CommonPlanningEntry(Timeslot startAndEndTime) {
		currentState = EntryState.WAITING;
		this.startAndEndTime = startAndEndTime;
	}

	/**
	 * 
	 * @return 计划项的起始时间
	 */
	public Timeslot getStartAndEndTime() {
		return startAndEndTime;
	}

	/**
	 * 启动
	 * @throws Exception
	 */
	public void Start() throws Exception {
		if(currentState == EntryState.ALLOCATED)
		    currentState = EntryState.RUNNING;
		else
			throw new Exception("未分配资源！！");
	}
	
	/**
	 * 结束
	 * @throws Exception
	 */
	public void End() throws Exception {
		if(currentState == EntryState.RUNNING)
		    currentState = EntryState.ENDED;
		else
			throw new Exception("未启动！！");
	}
	
	/**
	 * 取消
	 * @throws Exception
	 */
	public void Cancell() throws Exception {
		if(currentState == EntryState.WAITING||currentState == EntryState.ALLOCATED)
		    currentState = EntryState.CANCELLED;
		else
			throw new Exception("当前状态不支持取消！！");
	}
	
	/**
	 * 返回当前状态
	 * @return
	 */
	public EntryState getState() {
		return currentState;
	}
	
}
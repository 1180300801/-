package planningEntry;

import java.util.List;
import resource.Resource;
import timeslot.Timeslot;

public interface PlanningEntry {

	/**
	 * 启动
	 */
	public boolean Start();
	
	/**
	 * 结束
	 */
	public boolean End();
	
	/**
	 * 取消
	 */
	public boolean Cancell();
	
	/**
	 * 返回当前状态
	 * @return
	 */
	public String getState();
	
	/**
	 * 
	 * @return 位置
	 */
	public String getLocation();
	
	/**
	 * 
	 * @return 资源列表，表中存放资源的唯一标识
	 */
	public List<Resource> getResource();
	
	/**
	 * 设置起止时间
	 * @param startAndEndTime 起止时间
	 */
	public void setStartAndEndTime(Timeslot startAndEndTime);
	
	/**
	 * 返回起止时间
	 * @return 起止时间
	 */
	public Timeslot getStartAndEndTime();
	
	/**
	 * 返回计划项的名称
	 * @return 计划项的名称
	 */
	public String getEntryName();
	
}

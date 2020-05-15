package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface LocationConflictAPI {

	/**
	 * 检测一组计划项之间是否存在位置独占冲突：如果两个计划项在同一时
 	 *间点上占用了不可共享的位置，那么就存在了位置冲突。例如：某次《软件构造》
	 *课的时间是本日 8:00-10:00，而某次《计算机系统》课是在本日 9:00-11:00，
	 *二者均在 D01 教室，而“教室”这种位置是不可共享的，那么在 9:00-10:00 这
	 *个时间段里就存在着位置冲突。但是对机场、车站等可共享位置来说，则不会存
	 *在位置冲突（假设不考虑位置的容量问题）
	 * @param entries 计划项集
	 * @return 冲突返回false，没有冲突返回true
	 */
	public boolean checkLocationConflict(List<PlanningEntry> entries);
}

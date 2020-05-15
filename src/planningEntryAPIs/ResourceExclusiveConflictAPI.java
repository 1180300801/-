package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface ResourceExclusiveConflictAPI {

	/**
	 * 检测一组计划项之间是否存在资源独占冲突：如果两个计划项在同一时
	 *间点上占用了同样的资源，那么就存在了资源冲突，例如同一个教师、同一个车
	 *厢、同一架飞机。对 ROM、学习资料等不可区分个体的资源，无需考虑资源独占
	 *冲突。
	 * @param entries 计划项集
	 * @return 冲突返回false，不冲突返回true
	 */
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries);
}

package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface PreEntryPerResourceAPI {

	/**
	 * 提取面向特定资源的前序计划项：针对某个资源 r 和使用 r 的某个计划
	 *项 e，从一组计划项中找出 e 的前序 f，f 也使用资源 r，f 的执行时间在 e 之
	 *前，且在 e 和 f 之间不存在使用资源 r 的其他计划项。若不存在这样的计划项
	 *f，则返回 null。如果存在多个这样的 f，返回其中任意一个即可。对 ROM、学
	 *习资料等不可区分个体的资源，该 API 不适用。
	 * @param r 特定资源
	 * @param e 特定计划项
	 * @param entries 计划项集
	 * @return 针对某个资源 r和使用 r的某个计划项 e，从一组计划项中找出 e的前序 f，f也使用资源 r，f的执行时间在 e之前，
	 * 且在 e和 f之间不存在使用资源 r的其他计划项。若不存在这样的计划项f，则返回 null。
	 */
	public PlanningEntry findPreEntryPerResource(String r,PlanningEntry e, List<PlanningEntry> entries);
}

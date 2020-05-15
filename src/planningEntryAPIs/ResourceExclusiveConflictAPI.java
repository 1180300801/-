package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface ResourceExclusiveConflictAPI {

	/**
	 * ���һ��ƻ���֮���Ƿ������Դ��ռ��ͻ����������ƻ�����ͬһʱ
	 *�����ռ����ͬ������Դ����ô�ʹ�������Դ��ͻ������ͬһ����ʦ��ͬһ����
	 *�ᡢͬһ�ܷɻ����� ROM��ѧϰ���ϵȲ������ָ������Դ�����迼����Դ��ռ
	 *��ͻ��
	 * @param entries �ƻ��
	 * @return ��ͻ����false������ͻ����true
	 */
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries);
}

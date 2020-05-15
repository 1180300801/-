package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface PreEntryPerResourceAPI {

	/**
	 * ��ȡ�����ض���Դ��ǰ��ƻ�����ĳ����Դ r ��ʹ�� r ��ĳ���ƻ�
	 *�� e����һ��ƻ������ҳ� e ��ǰ�� f��f Ҳʹ����Դ r��f ��ִ��ʱ���� e ֮
	 *ǰ������ e �� f ֮�䲻����ʹ����Դ r �������ƻ���������������ļƻ���
	 *f���򷵻� null��������ڶ�������� f��������������һ�����ɡ��� ROM��ѧ
	 *ϰ���ϵȲ������ָ������Դ���� API �����á�
	 * @param r �ض���Դ
	 * @param e �ض��ƻ���
	 * @param entries �ƻ��
	 * @return ���ĳ����Դ r��ʹ�� r��ĳ���ƻ��� e����һ��ƻ������ҳ� e��ǰ�� f��fҲʹ����Դ r��f��ִ��ʱ���� e֮ǰ��
	 * ���� e�� f֮�䲻����ʹ����Դ r�������ƻ���������������ļƻ���f���򷵻� null��
	 */
	public PlanningEntry findPreEntryPerResource(String r,PlanningEntry e, List<PlanningEntry> entries);
}

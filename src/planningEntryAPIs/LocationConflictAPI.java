package planningEntryAPIs;

import java.util.List;

import planningEntry.PlanningEntry;

public interface LocationConflictAPI {

	/**
	 * ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ����������ƻ�����ͬһʱ
 	 *�����ռ���˲��ɹ����λ�ã���ô�ʹ�����λ�ó�ͻ�����磺ĳ�Ρ�������졷
	 *�ε�ʱ���Ǳ��� 8:00-10:00����ĳ�Ρ������ϵͳ�������ڱ��� 9:00-11:00��
	 *���߾��� D01 ���ң��������ҡ�����λ���ǲ��ɹ���ģ���ô�� 9:00-10:00 ��
	 *��ʱ�����ʹ�����λ�ó�ͻ�����ǶԻ�������վ�ȿɹ���λ����˵���򲻻��
	 *��λ�ó�ͻ�����費����λ�õ��������⣩
	 * @param entries �ƻ��
	 * @return ��ͻ����false��û�г�ͻ����true
	 */
	public boolean checkLocationConflict(List<PlanningEntry> entries);
}

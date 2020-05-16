package planningEntry;

import java.util.List;
import resource.Resource;
import timeslot.Timeslot;

public interface PlanningEntry {

	/**
	 * ����
	 */
	public boolean Start();
	
	/**
	 * ����
	 */
	public boolean End();
	
	/**
	 * ȡ��
	 */
	public boolean Cancell();
	
	/**
	 * ���ص�ǰ״̬
	 * @return
	 */
	public String getState();
	
	/**
	 * 
	 * @return λ��
	 */
	public String getLocation();
	
	/**
	 * 
	 * @return ��Դ�б����д����Դ��Ψһ��ʶ
	 */
	public List<Resource> getResource();
	
	/**
	 * ������ֹʱ��
	 * @param startAndEndTime ��ֹʱ��
	 */
	public void setStartAndEndTime(Timeslot startAndEndTime);
	
	/**
	 * ������ֹʱ��
	 * @return ��ֹʱ��
	 */
	public Timeslot getStartAndEndTime();
	
	/**
	 * ���ؼƻ��������
	 * @return �ƻ��������
	 */
	public String getEntryName();
	
}

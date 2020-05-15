package planningEntryCollection;

import location.Location;

public interface Collection {

	/**
	 * 
	 * @return �ƻ���мƻ���ĸ���
	 */
	int size();
	
	/**
	 * 
	 * @return ��ȡ�ƻ������Ϣ��
	 */
	public Object[][] getMessage();
	
	/**
	 * 
	 * @param location
	 * @return ����ƻ���еĿ���λ��
	 */
	boolean addLocations(Location location);
	
	/**
	 * 
	 * @param location
	 * @return ɾ���ƻ���еĿ���λ��
	 */
	boolean deleteLocations(Location location);
	
}

package planningEntry;

public interface BlockableEntry {

	/**
	 * ��������ʱ�����������ʼ��ʱ��
	 * @param time ������ʼ��ʱ��
	 */
	boolean block(String time);
}

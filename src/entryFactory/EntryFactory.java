package entryFactory;

public interface EntryFactory<L> {

	/**
	 * ����ƻ���Ĺ�������������������ض���ʽ�İ���һ���ƻ���������Ϣ���ַ���������һ���µļƻ������
	 * @param S �����ض���ʽ�İ���һ���ƻ���������Ϣ���ַ���
	 * @return ���ɵļƻ���
	 */
	public L getEntry(String S);
}

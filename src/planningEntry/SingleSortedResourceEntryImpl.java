package planningEntry;

public class SingleSortedResourceEntryImpl<L> implements SingleSortedResourceEntry<L> {

	private L resource;//��Դ������
	//AF:һ���ƻ������Դ
	//RI:���ͱ�����Lƥ��
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	@Override
	public void setResource(L resource) {
		// TODO Auto-generated method stub
		if(this.resource == null)
		    this.resource = resource;
	}
	
	/**
	 * @return �ƻ������Դ
	 */
	public L getResource() {
		return resource;
	}
}

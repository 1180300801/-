package planningEntry;

import java.util.List;

public class MultipleSortedResourceEntryImpl<L> implements MultipleSortedResourceEntry<L> {

	private List<L> respources;//��Դ������
	//AF:һ���ƻ������Դ
	//RI:���ͱ�����Lƥ��
	
	@Override
	public void setResources(List<L> resources) {
		// TODO Auto-generated method stub
		if(resources == null)
		    this.respources = resources;
	}

	/**
	 * @return �ƻ������Դ
	 */
	public List<L> getResources() {
		return respources;
	}

}

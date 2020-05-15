package planningEntry;

import java.util.List;

public class MultipleSortedResourceEntryImpl<L> implements MultipleSortedResourceEntry<L> {

	private List<L> resources;//��Դ������
	//AF:һ���ƻ������Դ
	//RI:���ͱ�����Lƥ��
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	@Override
	public void setResources(List<L> resources) {
		// TODO Auto-generated method stub
		if(resources != null)
		    this.resources = resources;
	}

	/**
	 * @return �ƻ������Դ
	 */
	public List<L> getResources() {
		return resources;
	}

	@Override
	public void addResources(L resource) {
		// TODO Auto-generated method stub
		resources.add(resource);
	}

	@Override
	public String toString() {
		String s = "";
		int size = resources.size();
		for(int i = 0;i<size;i++) {
			if(i<size-1)
			    s = s+resources.get(i).toString()+",";
			s += resources.get(i);
		}
		return s;
	}
}

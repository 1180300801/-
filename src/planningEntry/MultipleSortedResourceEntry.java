package planningEntry;

import java.util.List;

public interface MultipleSortedResourceEntry<L>{

	/**
	 * ���ö����Դ
	 * @param resources ��Դ�嵥
	 *
	 */
	void setResources(List<L> resources);
	
	/**
	 * ������Դ
	 * @param resource
	 */
	void addResources(L resource);
}

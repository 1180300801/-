package planningEntry;

import java.util.List;

public interface MultipleSortedResourceEntry<L>{

	/**
	 * 设置多个资源
	 * @param resources 资源清单
	 *
	 */
	void setResources(List<L> resources);
	
	/**
	 * 新增资源
	 * @param resource
	 */
	void addResources(L resource);
}

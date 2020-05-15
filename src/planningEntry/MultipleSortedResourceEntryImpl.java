package planningEntry;

import java.util.List;

public class MultipleSortedResourceEntryImpl<L> implements MultipleSortedResourceEntry<L> {

	private List<L> resources;//资源，泛型
	//AF:一个计划项的资源
	//RI:类型必须与L匹配
	//Safety from rep exposure:所有属性均为私有
	@Override
	public void setResources(List<L> resources) {
		// TODO Auto-generated method stub
		if(resources != null)
		    this.resources = resources;
	}

	/**
	 * @return 计划项的资源
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

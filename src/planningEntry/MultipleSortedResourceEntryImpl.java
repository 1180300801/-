package planningEntry;

import java.util.List;

public class MultipleSortedResourceEntryImpl<L> implements MultipleSortedResourceEntry<L> {

	private List<L> respources;//资源，泛型
	//AF:一个计划项的资源
	//RI:类型必须与L匹配
	
	@Override
	public void setResources(List<L> resources) {
		// TODO Auto-generated method stub
		if(resources == null)
		    this.respources = resources;
	}

	/**
	 * @return 计划项的资源
	 */
	public List<L> getResources() {
		return respources;
	}

}

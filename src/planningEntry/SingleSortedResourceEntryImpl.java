package planningEntry;

public class SingleSortedResourceEntryImpl<L> implements SingleSortedResourceEntry<L> {

	private L resource;//资源，泛型
	//AF:一个计划项的资源
	//RI:类型必须与L匹配
	//Safety from rep exposure:所有属性均为私有
	@Override
	public void setResource(L resource) {
		// TODO Auto-generated method stub
		if(this.resource == null)
		    this.resource = resource;
	}
	
	/**
	 * @return 计划项的资源
	 */
	public L getResource() {
		return resource;
	}
}

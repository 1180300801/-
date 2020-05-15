package planningEntryCollection;

import location.Location;

public interface Collection {

	/**
	 * 
	 * @return 计划项集中计划项的个数
	 */
	int size();
	
	/**
	 * 
	 * @return 获取计划项集的信息表
	 */
	public Object[][] getMessage();
	
	/**
	 * 
	 * @param location
	 * @return 增添计划项集中的可用位置
	 */
	boolean addLocations(Location location);
	
	/**
	 * 
	 * @param location
	 * @return 删除计划项集中的可用位置
	 */
	boolean deleteLocations(Location location);
	
}

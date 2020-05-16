package planningEntry;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import location.Location;
import resource.Carriage;
import resource.Resource;
import timeslot.Timeslot;

public class TrainEntry extends CommonPlanningEntry implements TrainplanningEntry {

	private String trainNumber;//计划项名称
	private MultipleLocationEntryImpl mle;//设置多个位置
	private MultipleSortedResourceEntryImpl<Carriage> msre;//设置多个资源
	private BlockableEntryImpl be;//中停
	private int blockNum;//记录block次数，不能超过中间站次数
	//AF:一个拥有列车名称名称，起止时间，起止及中间站，行驶列车的高铁计划
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 构造器
	 * @param trainNumber 列车号
	 * @param startAndEndTime 起止时间对
	 * @param mle 多个位置设置器
	 * @param msre 多个资源设置器
	 * @param be 阻塞器
	 */
	public TrainEntry(String trainNumber,Timeslot startAndEndTime,MultipleLocationEntryImpl mle,MultipleSortedResourceEntryImpl<Carriage> msre,BlockableEntryImpl be) {
		super(startAndEndTime);
		this.trainNumber = trainNumber;
		this.mle = mle;
		this.msre = msre;
		this.be = be;
		blockNum = 0;
		if(this.msre != null)
			setCurrentState("a");
	}
	
	@Override
	public void setLocations(List<? extends Location> locations) {
		// TODO Auto-generated method stub
		mle.setLocations(locations);
	}

	@Override
	public void setResources(List<Carriage> resources) {
		// TODO Auto-generated method stub
		msre.setResources(resources);
	}

	/**
	 * 新增资源
	 */
	public void addResources(Carriage carriage) {
		msre.addResources(carriage);
	}
	
	@Override
	public boolean block(String timeslot) {
		// TODO Auto-generated method stub
		if(blockNum<(mle.getLocations().size()-2)) {
			blockNum++;
			be.block(timeslot);
			return setCurrentState("b");
		}
		else
			JOptionPane.showMessageDialog(null, "中间站已走完，不可再中途停止！"+(msre.getResources().size()-2));
		return false;
	}
	
	@Override
	public boolean Cancell() {
		if(msre.getResources() != null) {
			JOptionPane.showMessageDialog(null, "车厢已分配，不可取消！");
			return false;
		}
		else {
			super.Cancell();
			return true;
		}
	}
	
	/**
	 * 
	 * @return 多个位置设置器
	 */
	public MultipleLocationEntryImpl getMle() {
		return mle;
	}
	
	/**
	 * 
	 * @return 多个资源设置器
	 */
	public MultipleSortedResourceEntryImpl<Carriage> getMsre() {
		return msre;
	}
	
	public BlockableEntryImpl getBe() {
		return be;
	}

	/**
	 * 
	 * @return 阻塞器
	 */
	public String getTrainNumber() {
		return trainNumber;
	}
	
	@Override
	public List<Resource> getResource(){
		List<Resource> resources = new ArrayList<Resource>();
		for(Carriage carriage:msre.getResources()) {
			resources.add(carriage);
		}
		return resources;
	}
	
	/**
	 * 获取计划项的资源
	 * @return 计划项的资源
	 */
	public List<Carriage> getCarriages(){
		return msre.getResources();
	}
	
	@Override
	public String getEntryName() {
		return trainNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof TrainEntry))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Flight，然后比较他们的唯一标识是否相等
		TrainEntry trainEntry = (TrainEntry)obj;
		if(this.trainNumber.equals(trainEntry.trainNumber)&this.getStartAndEndTime().getStartTime().equals(trainEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//在String类型中已经重写了hashCode方法，这里直接调用就可以了
		return this.trainNumber.hashCode();
	}
}

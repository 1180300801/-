package planningEntry;

import java.util.ArrayList;
import java.util.List;

import location.Airport;
import location.Location;
import resource.Flight;
import resource.Resource;
import timeslot.Timeslot;

public class FlightEntry extends CommonPlanningEntry implements FlightPlanningEntry {

	private String Flightnumber;
	private TwoLocationEntryImpl te;
	private SingleSortedResourceEntryImpl<Flight> se;
	private Airport interAirport;
	private BlockableEntryImpl be;//中停
	//AF:一个拥有航班号，起止时间，起止地点，使用飞机的航班
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 构造器
	 * @param Flightnumber 航班号
	 * @param startAndEndTime 起止时间对
	 * @param te 两个位置设置器
	 * @param se 单个资源设置器
	 */
	public FlightEntry(String Flightnumber,Timeslot startAndEndTime,TwoLocationEntryImpl te,SingleSortedResourceEntryImpl<Flight> se) {
		super(startAndEndTime);
		this.Flightnumber = Flightnumber;
		this.te = te;
		this.se = se;
		this.be = new BlockableEntryImpl();
		interAirport = new Airport("havaNo");
		if(this.se != null)
			setCurrentState("a");
	}
	
	@Override
	public void setLocations(Location start, Location end) {
		// TODO Auto-generated method stub
		te.setLocations(start, end);
	}

	@Override
	public void setResource(Flight resource) {
		// TODO Auto-generated method stub
		se.setResource(resource);
	}

	/**
	 * 
	 * @return 计划项使用的飞机
	 */
	public Flight getFlight(){
		return se.getResource();
	}
	
	@Override
	public List<Resource> getResource(){
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(se.getResource());
		return resources;
	}
	
	/**
	 * 
	 * @return 两个位置设置器
	 */
	public TwoLocationEntryImpl getTe() {
		return te;
	}
	
	/**
	 * 
	 * @return 单个资源设置器
	 */
	public SingleSortedResourceEntryImpl<Flight> getSe() {
		return se;
	}

	/**
	 * 
	 * @return 航班号
	 */
	public String getFlightnumber() {
		return Flightnumber;
	}
	
	@Override
	public String getEntryName() {
		return Flightnumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof FlightEntry))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Flight，然后比较他们的唯一标识是否相等
		FlightEntry flightEntry = (FlightEntry)obj;
		if(this.Flightnumber.equals(flightEntry.Flightnumber)&this.getStartAndEndTime().getStartTime().equals(flightEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//在String类型中已经重写了hashCode方法，这里直接调用就可以了
		return this.Flightnumber.hashCode();
	}

	@Override
	public boolean block(String time) {
		// TODO Auto-generated method stub
		if(interAirport.equals(new Airport("havaNo"))|be.getBlockNum()>0)
			return false;
		else {
			be.block(time);
			return setCurrentState("b");
		}
	}

	public Airport getInterAirport() {
		return interAirport;
	}

	public void setInterAirport(Airport interAirport) {
		this.interAirport = interAirport;
	}
}

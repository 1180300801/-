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
	private BlockableEntryImpl be;//��ͣ
	//AF:һ��ӵ�к���ţ���ֹʱ�䣬��ֹ�ص㣬ʹ�÷ɻ��ĺ���
	//RI:true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * ������
	 * @param Flightnumber �����
	 * @param startAndEndTime ��ֹʱ���
	 * @param te ����λ��������
	 * @param se ������Դ������
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
	 * @return �ƻ���ʹ�õķɻ�
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
	 * @return ����λ��������
	 */
	public TwoLocationEntryImpl getTe() {
		return te;
	}
	
	/**
	 * 
	 * @return ������Դ������
	 */
	public SingleSortedResourceEntryImpl<Flight> getSe() {
		return se;
	}

	/**
	 * 
	 * @return �����
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
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof FlightEntry))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪFlight��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		FlightEntry flightEntry = (FlightEntry)obj;
		if(this.Flightnumber.equals(flightEntry.Flightnumber)&this.getStartAndEndTime().getStartTime().equals(flightEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//��String�������Ѿ���д��hashCode����������ֱ�ӵ��þͿ�����
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

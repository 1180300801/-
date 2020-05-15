package resource;

/*
 * Immutable
 * 飞机类
 * @author Administrator
 *
 */
public class Flight implements Resource{

	private String numbering; //飞机编号
	private String modelNumber; //机型
	private int seatsNumber; //座位数
	private double age; //机龄
	
	//AF:代表一架具有特定编号，机型，座位数和机龄的客机
	//RI：numbering和modelNumber非空，seatsNumber和age大于0
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * checkRep
	 */
	public void checkRep() {
		assert !numbering.equals(null);
		assert !modelNumber.equals(null);
		assert seatsNumber>0;
		assert age>0;
	}
	
	/*
	 * 构造器
	 */
	public Flight(String numbering,String modelNumber,int seatsNumber,double d) {
		this.numbering = numbering;
		this.modelNumber = modelNumber;
		this.seatsNumber = seatsNumber;
		this.age = d;
		checkRep();
	}
	
	/*
	 * @return 飞机编号
	 */
	public String getNumbering() {
		return numbering;
	}

	/*
	 * @return 机型
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/*
	 * @return 座位数
	 */
	public int getSeatsNumber() {
		return seatsNumber;
	}

	/*
	 * @return 机龄
	 */
	public double getAge() {
		return age;
	}

	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return numbering;
	}
	
	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof Flight))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Flight，然后比较他们的唯一标识是否相等
		Flight flight = (Flight)obj;
		if(this.numbering.equals(flight.numbering))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//在String类型中已经重写了hashCode方法，这里直接调用就可以了
		return this.numbering.hashCode();
	}
}

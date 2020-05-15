package location;

public class ClassRoom implements Location {

	private String LocationName;//名称
	private String Longitude;//经度,格式为X:mm.m,例如N:35.6表示北纬35.6度
	private String Dimensions;//维度,格式为X:mm.m,例如E:33.2表示东经33.2度	
	private boolean Shareble;//是否可共享
	//AF:一个特定经纬度的位置
	//RI:Location非空，Longitude和Dimensions合理且格式为X:mm.m
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 构造器
	 * @param LocationName
	 */
	public ClassRoom(String LocationName) {
		this.LocationName = LocationName;
		this.Shareble = false;
	}

	/**
	 * 
	 * @return 可共享返回true
	 */
	public boolean isShareble() {
		return Shareble;
	}
	
	/**
	 * @return 位置名称
	 */
	public String getLocationName() {
		return LocationName;
	}


	/**
     * 
     * @return 经度
     */
	public String getLongitude() {
		return Longitude;
	}


	 /**
     * 
     * @return 维度
     */
	public String getDimensions() {
		return Dimensions;
	}

	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof ClassRoom))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Carriage，然后比较他们的唯一标识是否相等
		ClassRoom classroom = (ClassRoom)obj;
		if(this.LocationName.equals(classroom.LocationName))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		return this.LocationName.hashCode();
	}
}

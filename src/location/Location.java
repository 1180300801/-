package location;

/**
 * Immutable
 * @author Administrator
 *
 */
public class Location {

	private String Location;//名称
	private String Longitude;//经度,格式为X:mm.m,例如N:35.6表示北纬35.6度
	private String Dimensions;//维度,格式为X:mm.m,例如E:33.2表示东经33.2度
	//AF:一个特定经纬度的位置
	//RI:Location非空，Longitude和Dimensions合理且格式为X:mm.m
	
	public Location(String Location) {
		this.Location = Location;
	}


	public String getLocation() {
		return Location;
	}


	public String getLongitude() {
		return Longitude;
	}


	public String getDimensions() {
		return Dimensions;
	}
}

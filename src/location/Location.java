package location;

/**
 * Immutable
 * @author Administrator
 *
 */
public class Location {

	private String Location;//����
	private String Longitude;//����,��ʽΪX:mm.m,����N:35.6��ʾ��γ35.6��
	private String Dimensions;//ά��,��ʽΪX:mm.m,����E:33.2��ʾ����33.2��
	//AF:һ���ض���γ�ȵ�λ��
	//RI:Location�ǿգ�Longitude��Dimensions�����Ҹ�ʽΪX:mm.m
	
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

package location;

public class ClassRoom implements Location {

	private String LocationName;//����
	private String Longitude;//����,��ʽΪX:mm.m,����N:35.6��ʾ��γ35.6��
	private String Dimensions;//ά��,��ʽΪX:mm.m,����E:33.2��ʾ����33.2��	
	private boolean Shareble;//�Ƿ�ɹ���
	//AF:һ���ض���γ�ȵ�λ��
	//RI:Location�ǿգ�Longitude��Dimensions�����Ҹ�ʽΪX:mm.m
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * ������
	 * @param LocationName
	 */
	public ClassRoom(String LocationName) {
		this.LocationName = LocationName;
		this.Shareble = false;
	}

	/**
	 * 
	 * @return �ɹ�����true
	 */
	public boolean isShareble() {
		return Shareble;
	}
	
	/**
	 * @return λ������
	 */
	public String getLocationName() {
		return LocationName;
	}


	/**
     * 
     * @return ����
     */
	public String getLongitude() {
		return Longitude;
	}


	 /**
     * 
     * @return ά��
     */
	public String getDimensions() {
		return Dimensions;
	}

	@Override
	public boolean equals(Object obj) {
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof ClassRoom))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪCarriage��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
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

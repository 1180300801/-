package resource;

/*
 * Immutable
 * �ɻ���
 * @author Administrator
 *
 */
public class Flight implements Resource{

	private String numbering; //�ɻ����
	private String modelNumber; //����
	private int seatsNumber; //��λ��
	private double age; //����
	
	//AF:����һ�ܾ����ض���ţ����ͣ���λ���ͻ���Ŀͻ�
	//RI��numbering��modelNumber�ǿգ�seatsNumber��age����0
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
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
	 * ������
	 */
	public Flight(String numbering,String modelNumber,int seatsNumber,double d) {
		this.numbering = numbering;
		this.modelNumber = modelNumber;
		this.seatsNumber = seatsNumber;
		this.age = d;
		checkRep();
	}
	
	/*
	 * @return �ɻ����
	 */
	public String getNumbering() {
		return numbering;
	}

	/*
	 * @return ����
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/*
	 * @return ��λ��
	 */
	public int getSeatsNumber() {
		return seatsNumber;
	}

	/*
	 * @return ����
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
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof Flight))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪFlight��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		Flight flight = (Flight)obj;
		if(this.numbering.equals(flight.numbering))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//��String�������Ѿ���д��hashCode����������ֱ�ӵ��þͿ�����
		return this.numbering.hashCode();
	}
}

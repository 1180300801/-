package resource;

/*
 * Immutable
 * �ɻ���
 * @author Administrator
 *
 */
public class Flight{

	private String numbering; //�ɻ����
	private String modelNumber; //����
	private int seatsNumber; //��λ��
	private float age; //����
	
	//AF:����һ�ܾ����ض���ţ����ͣ���λ���ͻ���Ŀͻ�
	//RI��numbering��modelNumber�ǿգ�seatsNumber��age�Ǹ�
	
	/*
	 * ������
	 */
	public Flight(String numbering,String modelNumber,int seatsNumber,float age) {
		this.numbering = numbering;
		this.modelNumber = modelNumber;
		this.seatsNumber = seatsNumber;
		this.age = age;
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
	public float getAge() {
		return age;
	}
}

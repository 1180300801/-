package resource;

/*
 * Immutable
 * ������
 * @author Administrator
 *
 */
public class Train{

	private String numbering; //������
	private String Type; //����
	private int personnelNumber; //��Ա��
	private String FactoryYear; //�������
	
	//AF:����һ�������ض���ţ����ͣ���Ա���ͳ�����ŵĳ���
	//RI��numbering��Type��FactoryYear�ǿգ�personnelNumber�Ǹ�
	
	/*
	 * ������
	 */
	public Train(String numbering,String Type,int personnelNumber,String FactoryYear) {
		this.numbering = numbering;
		this.Type = Type;
		this.personnelNumber = personnelNumber;
		this.FactoryYear = FactoryYear;
	}

	/**
	 * 
	 * @return ����
	 */
	public String getNumbering() {
		return numbering;
	}

	/**
	 * 
	 * @return ������
	 */
	public String getType() {
		return Type;
	}

	/**
	 * 
	 * @return ��Ա��
	 */
	public int getPersonnelNumber() {
		return personnelNumber;
	}

	/**
	 * 
	 * @return �������
	 */
	public String getFactoryYear() {
		return FactoryYear;
	}
	
}

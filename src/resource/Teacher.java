package resource;

/*
 * Immutable
 * ��ʦ��
 * @author Administrator
 *
 */
public class Teacher{

	private String IDNumber; //���֤��
	private String name; //����
	private String sex;  //�Ա�
	private String title; //ְ��
	
	//AF:����һλ�����ض����֤�ţ��������Ա��ְ�ƵĽ�ʦ
	//RI��numbering��modelNumber�ǿգ�seatsNumber��age�Ǹ�
		
	/*
	 * ������
	 */
	public Teacher(String IDNumber,String name,String sex,String title) {
		this.IDNumber = IDNumber;
		this.name = name;
		this.sex = sex;
		this.title = title;
	}

	/**
	 * 
	 * @return ���֤��
	 */
	public String getIDNumber() {
		return IDNumber;
	}

	/**
	 * 
	 * @return ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return �Ա�
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 
	 * @return ְ��
	 */
	public String getTitle() {
		return title;
	}
}

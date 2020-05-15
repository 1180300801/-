package resource;

/*
 * Immutable
 * ��ʦ��
 * @author Administrator
 *
 */
public class Teacher implements Resource{

	private String IDNumber; //���֤��
	private String name; //����
	private String sex;  //�Ա�
	private String title; //ְ��
	
	//AF:����һλ�����ض����֤�ţ��������Ա��ְ�ƵĽ�ʦ
	//RI��numbering,modelNumber,sex��title�ǿ�
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * checkRep
	 */
	public void checkRep() {
		assert !IDNumber.equals(null);
		assert !name.equals(null);
		assert !sex.equals(null);
		assert !title.equals(null);
	}
	
	/*
	 * ������
	 */
	public Teacher(String IDNumber,String name,String sex,String title) {
		this.IDNumber = IDNumber;
		this.name = name;
		this.sex = sex;
		this.title = title;
		checkRep();
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

	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return IDNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof Teacher))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪTeacher��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		Teacher teacher = (Teacher)obj;
		if(this.IDNumber.equals(teacher.IDNumber))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//��String�������Ѿ���д��hashCode����������ֱ�ӵ��þͿ�����
		return this.IDNumber.hashCode();
	}
}

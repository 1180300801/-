package resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Immutable
 * ������
 * @author Administrator
 *
 */
public class Carriage implements Resource{

	private String numbering; //������
	private String Type; //����
	private int personnelNumber; //��Ա��
	private String FactoryYear; //�������
	
	//AF:����һ�������ض���ţ����ͣ���Ա���ͳ�����ŵĳ���
	//RI��numbering��Type��FactoryYear�ǿգ�personnelNumber>0,�������2000-2020��֮��
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * checkRep
	 */
	public void checkRep() {
		assert !numbering.equals(null);
		assert !Type.equals(null);
		assert !FactoryYear.equals(null);
		Pattern p = Pattern.compile("(20[01][0-9])|(2020)");
		Matcher mc = p.matcher(FactoryYear);
		assert mc.find();
		assert personnelNumber>0;
	}
	
	/*
	 * ������
	 */
	public Carriage(String numbering,String Type,int personnelNumber,String FactoryYear) {
		this.numbering = numbering;
		this.Type = Type;
		this.personnelNumber = personnelNumber;
		this.FactoryYear = FactoryYear;
		checkRep();
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
	
	@Override
	public String toString() {
		return numbering;
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
		if(obj == null|!(obj instanceof Carriage))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪCarriage��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		Carriage carriage = (Carriage)obj;
		if(this.numbering.equals(carriage.numbering))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		return this.numbering.hashCode();
	}
}

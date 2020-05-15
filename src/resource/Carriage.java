package resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Immutable
 * 车厢类
 * @author Administrator
 *
 */
public class Carriage implements Resource{

	private String numbering; //车厢编号
	private String Type; //类型
	private int personnelNumber; //定员数
	private String FactoryYear; //出厂年号
	
	//AF:代表一个具有特定编号，类型，定员数和出厂年号的车厢
	//RI：numbering，Type和FactoryYear非空，personnelNumber>0,且年号在2000-2020年之间
	//Safety from rep exposure:所有属性均为私有
	
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
	 * 构造器
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
	 * @return 类型
	 */
	public String getNumbering() {
		return numbering;
	}

	/**
	 * 
	 * @return 车厢编号
	 */
	public String getType() {
		return Type;
	}

	/**
	 * 
	 * @return 定员数
	 */
	public int getPersonnelNumber() {
		return personnelNumber;
	}

	/**
	 * 
	 * @return 出厂年号
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
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof Carriage))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Carriage，然后比较他们的唯一标识是否相等
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

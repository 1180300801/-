package resource;

/*
 * Immutable
 * 车厢类
 * @author Administrator
 *
 */
public class Train{

	private String numbering; //车厢编号
	private String Type; //类型
	private int personnelNumber; //定员数
	private String FactoryYear; //出厂年号
	
	//AF:代表一个具有特定编号，类型，定员数和出厂年号的车厢
	//RI：numbering，Type和FactoryYear非空，personnelNumber非负
	
	/*
	 * 构造器
	 */
	public Train(String numbering,String Type,int personnelNumber,String FactoryYear) {
		this.numbering = numbering;
		this.Type = Type;
		this.personnelNumber = personnelNumber;
		this.FactoryYear = FactoryYear;
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
	
}

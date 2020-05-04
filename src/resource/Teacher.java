package resource;

/*
 * Immutable
 * 教师类
 * @author Administrator
 *
 */
public class Teacher{

	private String IDNumber; //身份证号
	private String name; //姓名
	private String sex;  //性别
	private String title; //职称
	
	//AF:代表一位具有特定身份证号，姓名，性别和职称的教师
	//RI：numbering和modelNumber非空，seatsNumber和age非负
		
	/*
	 * 构造器
	 */
	public Teacher(String IDNumber,String name,String sex,String title) {
		this.IDNumber = IDNumber;
		this.name = name;
		this.sex = sex;
		this.title = title;
	}

	/**
	 * 
	 * @return 身份证号
	 */
	public String getIDNumber() {
		return IDNumber;
	}

	/**
	 * 
	 * @return 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 
	 * @return 职称
	 */
	public String getTitle() {
		return title;
	}
}

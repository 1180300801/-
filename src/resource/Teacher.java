package resource;

/*
 * Immutable
 * 教师类
 * @author Administrator
 *
 */
public class Teacher implements Resource{

	private String IDNumber; //身份证号
	private String name; //姓名
	private String sex;  //性别
	private String title; //职称
	
	//AF:代表一位具有特定身份证号，姓名，性别和职称的教师
	//RI：numbering,modelNumber,sex和title非空
	//Safety from rep exposure:所有属性均为私有
	
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
	 * 构造器
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

	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return IDNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof Teacher))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Teacher，然后比较他们的唯一标识是否相等
		Teacher teacher = (Teacher)obj;
		if(this.IDNumber.equals(teacher.IDNumber))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//在String类型中已经重写了hashCode方法，这里直接调用就可以了
		return this.IDNumber.hashCode();
	}
}

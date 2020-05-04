package resource;

/*
 * Immutable
 * 飞机类
 * @author Administrator
 *
 */
public class Flight{

	private String numbering; //飞机编号
	private String modelNumber; //机型
	private int seatsNumber; //座位数
	private float age; //机龄
	
	//AF:代表一架具有特定编号，机型，座位数和机龄的客机
	//RI：numbering和modelNumber非空，seatsNumber和age非负
	
	/*
	 * 构造器
	 */
	public Flight(String numbering,String modelNumber,int seatsNumber,float age) {
		this.numbering = numbering;
		this.modelNumber = modelNumber;
		this.seatsNumber = seatsNumber;
		this.age = age;
	}
	
	/*
	 * @return 飞机编号
	 */
	public String getNumbering() {
		return numbering;
	}

	/*
	 * @return 机型
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/*
	 * @return 座位数
	 */
	public int getSeatsNumber() {
		return seatsNumber;
	}

	/*
	 * @return 机龄
	 */
	public float getAge() {
		return age;
	}
}

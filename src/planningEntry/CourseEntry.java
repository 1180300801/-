package planningEntry;

import java.util.ArrayList;
import java.util.List;

import location.Location;
import resource.Resource;
import resource.Teacher;
import timeslot.Timeslot;

public class CourseEntry extends CommonPlanningEntry implements CoursePlanningEntry{

	private String className;
	private SingleLocationEntryImpl se;
	private SingleSortedResourceEntryImpl<Teacher> ssre;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	//AF:一个拥有课程名称，起止时间，发生地点，上课教室的课程
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 构造器
	 * @param className 课程名称
	 * @param startAndEndTime 起止时间
	 * @param se 单个位置设置器
	 * @param ssre 单个资源设置器
	 */
	public CourseEntry(String className,Timeslot startAndEndTime,SingleLocationEntryImpl se,SingleSortedResourceEntryImpl<Teacher> ssre) {
		super(startAndEndTime);
		this.className = className;
		this.se = se;
		this.ssre = ssre;
		if(this.ssre != null) {
			setCurrentState("a");
			teachers.add(ssre.getResource());
		}			
	}
	
	@Override
	public void setLocations(Location location) {
		// TODO Auto-generated method stub
		se.setLocations(location);
	}

	@Override
	public void setResource(Teacher resource) {
		// TODO Auto-generated method stub
		ssre.setResource(resource);
	}

	@Override
	public List<Resource> getResource(){
		List<Resource> teachers = new ArrayList<Resource>();
		teachers.add(ssre.getResource());
		return teachers;
	}
	
	/**
	 * 
	 * @return 单个位置设置器
	 */
	public SingleLocationEntryImpl getSe() {
		return se;
	}
	
	/**
	 * 更换上课教师
	 */
	public boolean changeTeacher() {
		for(int i = 0;i<teachers.size()-1;i++) {
			if(teachers.get(i).equals(ssre.getResource())) {
				ssre.setResource(teachers.get(i+1));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return 单个资源设置器
	 */
	public SingleSortedResourceEntryImpl<Teacher> getSsre() {
		return ssre;
	}

	/**
	 * 
	 * @return 课程名称
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * 
	 * @return 教师
	 */
	public Teacher getTeacher(){
		Teacher teacher = ssre.getResource();
		return teacher;
	}
	
	/**
	 * 添加教师
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}
	
	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return se.getLocation().getLocationName();
	}
	
	@Override
	public String getEntryName() {
		// TODO Auto-generated method stub
		return className;
	}
	
	@Override
	public boolean equals(Object obj) {
		//提高效率
		if(this == obj)
			return true;
		//提高健壮性，判断obj是否为null或者是否是Teacher类的一个对象
		if(obj == null|!(obj instanceof CourseEntry))
			return false;
		//经过前两步判断没有得出结果，到此将obj转型为Flight，然后比较他们的唯一标识是否相等
		CourseEntry courseEntry = (CourseEntry)obj;
		if(this.className.equals(courseEntry.className)&this.getStartAndEndTime().getStartTime().equals(courseEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//在String类型中已经重写了hashCode方法，这里直接调用就可以了
		return this.className.hashCode();
	}
}

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
	//AF:һ��ӵ�пγ����ƣ���ֹʱ�䣬�����ص㣬�Ͽν��ҵĿγ�
	//RI:true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * ������
	 * @param className �γ�����
	 * @param startAndEndTime ��ֹʱ��
	 * @param se ����λ��������
	 * @param ssre ������Դ������
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
	 * @return ����λ��������
	 */
	public SingleLocationEntryImpl getSe() {
		return se;
	}
	
	/**
	 * �����Ͽν�ʦ
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
	 * @return ������Դ������
	 */
	public SingleSortedResourceEntryImpl<Teacher> getSsre() {
		return ssre;
	}

	/**
	 * 
	 * @return �γ�����
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * 
	 * @return ��ʦ
	 */
	public Teacher getTeacher(){
		Teacher teacher = ssre.getResource();
		return teacher;
	}
	
	/**
	 * ��ӽ�ʦ
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
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof CourseEntry))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪFlight��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		CourseEntry courseEntry = (CourseEntry)obj;
		if(this.className.equals(courseEntry.className)&this.getStartAndEndTime().getStartTime().equals(courseEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//��String�������Ѿ���д��hashCode����������ֱ�ӵ��þͿ�����
		return this.className.hashCode();
	}
}

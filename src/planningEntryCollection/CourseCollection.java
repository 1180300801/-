package planningEntryCollection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import entryFactory.CourseEntryFactory;
import location.Location;
import planningEntry.CourseEntry;
import resource.Teacher;

public class CourseCollection implements Iterable<CourseEntry>,Collection{

	private List<CourseEntry> courseCollection = new ArrayList<CourseEntry>();
	private Set<Location> classRooms  = new HashSet<Location>();
	private Set<Teacher> teachers = new HashSet<Teacher>();
	//AF:一个存储课程，可用位置和资源的计划项集
	//RI：true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 构造器
	 * @param fileName 存储计划项的文件
	 * @throws IOException
	 */
	public CourseCollection(String fileName) throws IOException {
		//工厂方法
    	CourseEntryFactory cef = new CourseEntryFactory();
    	String S = "";
    	File f = new File(fileName);
    	BufferedReader br = new BufferedReader(new FileReader(f));	
    	String s = "";
    	int i = 0;
    	here:
    	while((s = br.readLine()) != null) {
    		//忽略空行
    		if(!s.equals("")) {
    			S += s;
    			S += "\n";
    			i++;
    		}
    		//文件中每12行代表一格计划项
			if(i == 12) {
				//判断格式是否符合要求
    			if(check(S)){
    				//使用工厂方法构造计划项
    				CourseEntry courseEntry = cef.getEntry(S);
    				if(courseEntry == null)
    					break here;
    				
    				teachers.add(courseEntry.getSsre().getResource());
    				classRooms.add(courseEntry.getSe().getLocation());
    				courseCollection.add(courseEntry);
    				S = "";
    				i = 0;
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "格式不匹配，请选择其它文件");
    				System.out.println(S);
    				break here;
    			}
    		}
    	}    
    	br.close();
    	//按计划项开始时间从小到大排序
    	EntryComparator comp = new EntryComparator();
    	Collections.sort(courseCollection,comp);
    }
	
	/**
	 * 新增计划项
	 * @param courseEntry 计划项
	 */
	public void addEntry(CourseEntry courseEntry) {
		courseCollection.add(courseEntry);
		EntryComparator comp = new EntryComparator();
    	Collections.sort(courseCollection,comp);
	}
	
	@Override
	public int size() {
		return courseCollection.size();
	}
	
	
	/**
	 * 检查输入的航班信息字符串是否符合要求
	 * @param S
	 * @return 合格时返回true，否则返回false
	 */
	 private boolean check(String S){
		//检查时间格式是否正确
	    String str = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|)))";
	   	Pattern pattern1 = Pattern.compile(str);
	   	//检查总体格式是否正确
	   	Pattern pattern2 = Pattern.compile("((Class:)(2020-[01][0-9]-[0123][0-9]),([\u4e00-\u9fa5]+)\n\\{\n(ClassRoom):([\u4e00-\u9fa5][\u4e00-\u9fa5][0-9]+)\n(DepatureTime):(2020-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(2020-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Teacher:)(\\d{3})\n\\{\n(Name:)([\u4e00-\u9fa5]+)\n(Sex:)([\u4e00-\u9fa5])\n(Title:)([\u4e00-\u9fa5]+)\n\\}\n\\}\n)");
	   	Matcher mc = pattern1.matcher(S);
	   	if(mc.find()) {
    		mc = pattern2.matcher(S);
	    	if(mc.find())
	    		return true;
	   	}
	   	return false;
	}
	
	@Override
	public Object[][] getMessage() {
		Object[][] s = new String[courseCollection.size()][4];
		int i = 0;
		for(CourseEntry ce:courseCollection) {
			s[i][0] = ce.getClassName();
			s[i][1] = ce.getStartAndEndTime().getStartTime()+" to "+ce.getStartAndEndTime().getEndTime();
			s[i][2] = ce.getSe().getLocation().getLocationName();
			s[i][3] = ce.getState().toString();
			i++;
		}
		return s;
	}	
	
	/**
	 * 增加资源
	 * @param teacher
	 * @return
	 */
	 public boolean addResources(Teacher teacher) {
		 teachers.add(teacher);
		 return true;
	 }
	 
	 /**
	 * 删除资源
	 * @param teacher
	 * @return
	 */
	 public boolean deleteResources(Teacher teacher) {
		 teachers.remove(teacher);
		 Iterator<CourseEntry> it = courseCollection.iterator();
		 while(it.hasNext()) {
			 CourseEntry fe = it.next();
			 if(fe.getSsre().getResource().getIDNumber().equals(teacher.getIDNumber()))
					 it.remove();
		 }
		 return true;
	 }
	 
	 @Override
	 public boolean addLocations(Location airport) {
		 classRooms.add(airport);
		 return true;
	 }
	 
	 @Override
	 public boolean deleteLocations(Location classRoom) {
		 classRooms.remove(classRoom);
		 Iterator<CourseEntry> it = courseCollection.iterator();
		 while(it.hasNext()) {
			 CourseEntry fe = it.next();
			 if(fe.getSe().getLocation().getLocationName().equals(classRoom.getLocationName()))
				 it.remove();
		 }
		 return true;
	 }
	
	 /**
	  * 返回计划项集
	  * @return 计划项集
	  */
	 public List<CourseEntry> getCourseEntry(){
		 return courseCollection;
	 }
	 
	 /**
	  * 返回可用资源集
	  * @return 可用资源集
	  */
     public Set<Teacher> getResources(){
    	 return teachers;
     }
	
     /**
	  * 返回可用位置集
	  * @return 可用位置集
	  */
     public Set<Location> getLocations(){
     	 return classRooms;
     }
     /**
      * 返回迭代器
      */
	 public Iterator<CourseEntry> iterator(){	 
		  return new Itr();
     }
	 
	 /**
	  * 迭代器
	  * @author Administrator
	  *
	  */
	 public class Itr implements Iterator<CourseEntry>{
		
		 Iterator<CourseEntry> it;
		
		 public Itr() {
			 it = courseCollection.iterator();
		 }
		
		 @Override
		 public boolean hasNext() {
			 // TODO Auto-generated method stub
			 return it.hasNext();
		 }

		 @Override
		 public CourseEntry next() {
			 // TODO Auto-generated method stub
			 return it.next();
		 }
		
	 }

}

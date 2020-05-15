package entryFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.Airport;
import location.Location;
import planningEntry.CourseEntry;
import planningEntry.SingleLocationEntryImpl;
import planningEntry.SingleSortedResourceEntryImpl;
import resource.Teacher;
import timeslot.Timeslot;

public class CourseEntryFactory implements EntryFactory<CourseEntry> {

	@Override
	public CourseEntry getEntry(String S) {
		// TODO Auto-generated method stub
		
		String str = getMessage("Class:",S);
		String[] strs = str.split(",");
		String course = strs[1];
		
		SingleLocationEntryImpl sle = new SingleLocationEntryImpl();
		SingleSortedResourceEntryImpl<Teacher> se = new SingleSortedResourceEntryImpl<Teacher>();
		Location classRoom = new Airport(getMessage("ClassRoom:",S));
		sle.setLocations( classRoom);
		
		String startTime = getMessage("DepatureTime:",S);
		String endTime = getMessage("ArrivalTime:",S);
		Timeslot timeslot = new Timeslot(startTime,endTime);
		if(timeslot.timeSub()>1) {
			System.out.println("起止时间差超过一天！请选择其他文件");
			return null;
		}
		
		String IDNumber = getMessage("Teacher:",S);
		String name = getMessage("Name:",S);
		String sex = getMessage("Sex:",S);
		String title = getMessage("Title:",S);
		Teacher teacher = new Teacher(IDNumber,name,sex,title);
		se.setResource(teacher);	
		
		return new CourseEntry(course,timeslot,sle,se);
	}

	/**
	 * 
	 * @param front
	 * @param S
	 * @return S中front所在行中在front后面的字符串
	 */
	private String getMessage(String front,String S) {
		Pattern pattern = Pattern.compile("(?<="+front+").+");
		Matcher mc = pattern.matcher(S);
		while(mc.find())
			return mc.group();
		return "";
	}
	
}

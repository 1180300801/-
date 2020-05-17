package entryFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

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
		//判断格式是否符合要求
		if(check(S)) {
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
		
		else {
			JOptionPane.showMessageDialog(null, "格式不匹配，请选择其它文件");
			System.out.println(S);
			return null;
		}
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
}

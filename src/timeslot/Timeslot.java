package timeslot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Immutable
 * 起止时间对
 * @author Administrator
 *
 */
public class Timeslot {

    private String start; //起始时间
    private String end; //结束时间
    
    //AF:表示一个带有起始时间和结束时间的timeslot
    //RI:起始时间和结束时间符合 yyyy-MM-dd HH:mm的语法规则

    //checkRep
    /**
     * 
     * @return 如果输入的起止时间符合yyyy-MM-dd HH:mm的语法规则返回true，否则返回false
     */
    public boolean checkRep(String start,String end) {
    	Pattern pattern=Pattern.compile("((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|)))");		
		Matcher mc=pattern.matcher(start); 		
		if(!mc.matches())
		{
			System.out.println("起始时间输入不符合要求");
			return false;
		}	
		
		mc=pattern.matcher(end);
		if(!mc.matches())
		{
			System.out.println("终止时间输入不符合要求");
			return false;
		}	
		return true;
    }
    
    /*
     * 构造器
     */
	public Timeslot(String start,String end) {
		if(!checkRep(start,end))
			System.out.println("起止时间输入不符合要求");
		else {
			this.start = start;
			this.end = end;
		}		
	}
	
	public String getStartTime() {
		return start;
	}
	
	public String getEndTime() {
		return end;
	}
	
	@Override
	public String toString() {
		return start+","+end;
	}
}

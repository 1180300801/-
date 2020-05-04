package timeslot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Immutable
 * ��ֹʱ���
 * @author Administrator
 *
 */
public class Timeslot {

    private String start; //��ʼʱ��
    private String end; //����ʱ��
    
    //AF:��ʾһ��������ʼʱ��ͽ���ʱ���timeslot
    //RI:��ʼʱ��ͽ���ʱ����� yyyy-MM-dd HH:mm���﷨����

    //checkRep
    /**
     * 
     * @return ����������ֹʱ�����yyyy-MM-dd HH:mm���﷨���򷵻�true�����򷵻�false
     */
    public boolean checkRep(String start,String end) {
    	Pattern pattern=Pattern.compile("((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|)))");		
		Matcher mc=pattern.matcher(start); 		
		if(!mc.matches())
		{
			System.out.println("��ʼʱ�����벻����Ҫ��");
			return false;
		}	
		
		mc=pattern.matcher(end);
		if(!mc.matches())
		{
			System.out.println("��ֹʱ�����벻����Ҫ��");
			return false;
		}	
		return true;
    }
    
    /*
     * ������
     */
	public Timeslot(String start,String end) {
		if(!checkRep(start,end))
			System.out.println("��ֹʱ�����벻����Ҫ��");
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

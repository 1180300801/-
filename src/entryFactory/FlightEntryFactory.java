package entryFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import location.Airport;
import location.Location;
import planningEntry.FlightEntry;
import planningEntry.SingleSortedResourceEntryImpl;
import planningEntry.TwoLocationEntryImpl;
import resource.Flight;
import timeslot.Timeslot;

public class FlightEntryFactory implements EntryFactory<FlightEntry> {

	@Override
	public FlightEntry getEntry(String S) {
		// TODO Auto-generated method stub
		//�жϸ�ʽ�Ƿ����Ҫ��
		if(check(S)) {
			String str = getMessage("Flight:",S);
			String[] strs = str.split(",");
			String flightNumber = strs[1];
			String date = strs[0];
			
			TwoLocationEntryImpl te = new TwoLocationEntryImpl();
			SingleSortedResourceEntryImpl<Flight> se = new SingleSortedResourceEntryImpl<Flight>();
			Location start = new Airport(getMessage("DepartureAirport:",S));
			Location end = new Airport(getMessage("ArrivalAirport:",S));
			te.setLocations(start, end);
			
			String startTime = getMessage("DepatureTime:",S);
			String endTime = getMessage("ArrivalTime:",S);
			Timeslot timeslot = new Timeslot(startTime,endTime);
			Pattern pattern = Pattern.compile(date);
			Matcher mc = pattern.matcher(startTime);
			if(!mc.find()) {
				JOptionPane.showMessageDialog(null, "���ʱ���е����ڱ������һ�е����ڲ�һ��");
				return null;
			}
			if(timeslot.timeSub()>1|timeslot.timeSub()<0) {
				JOptionPane.showMessageDialog(null, "��ֹʱ����һ��򵽴�ʱ�����ڿ�ʼʱ�䣡��ѡ�������ļ�");
				return null;
			}
			
			String numbering = getMessage("Plane:",S);
			String modelNumber = getMessage("Type:",S);
			int seatsNumber = Integer.parseInt(getMessage("Seats:",S));
			float age = Float.parseFloat(getMessage("Age:",S));
			Flight flight = new Flight(numbering,modelNumber,seatsNumber,age);
			se.setResource(flight);	
			
			return new FlightEntry(flightNumber,timeslot,te,se);
		}		
		else {
			JOptionPane.showMessageDialog(null, "��ʽ��ƥ�䣬��ѡ�������ļ�");
			return null;
		}
	}

	/**
	 * 
	 * @param front
	 * @param S
	 * @return S��front����������front������ַ���
	 */
	private String getMessage(String front,String S) {
		Pattern pattern = Pattern.compile("(?<="+front+").+");
		Matcher mc = pattern.matcher(S);
		while(mc.find())
			return mc.group();
		return "";
	}
	
	/**
	 * �������ĺ�����Ϣ�ַ����Ƿ����Ҫ��
	 * @param S
	 * @return �ϸ�ʱ����true�����򷵻�false
	 */
	 private boolean check(String S){
		//���ʱ���ʽ�Ƿ���ȷ
	    String str = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|)))";
	   	Pattern pattern1 = Pattern.compile(str);
	   	//��������ʽ�Ƿ���ȷ
	   	Pattern pattern2 = Pattern.compile("((Flight):(20[012][0-9]-[01][0-9]-[0123][0-9]),([A-Z][A-Z]((\\d{2})|(\\d{3})|(\\d{4})))\n\\{\n(DepartureAirport):([a-zA-z]+)\n(ArrivalAirport):([a-zA-z]+)\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Plane):([BN]\\d{4})\n\\{\n(Type):([A-Za-z0-9]+)\n(Seats:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(Age):(([0-9]|([1-2][0-9])|(30))(\\.[0-9])?)\n\\}\n\\}\n)");
	   	Matcher mc = pattern1.matcher(S);
	   	if(mc.find()) {
    		mc = pattern2.matcher(S);
	    	if(mc.find())
	    		return true;
	   	}
	   	return false;
	}
}

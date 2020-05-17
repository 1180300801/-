package entryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import location.RailwayStation;
import planningEntry.BlockableEntryImpl;
import planningEntry.MultipleLocationEntryImpl;
import planningEntry.MultipleSortedResourceEntryImpl;
import planningEntry.TrainEntry;
import resource.Carriage;
import timeslot.Timeslot;

public class TrainEntryFactory implements EntryFactory<TrainEntry> {

	@Override
	public TrainEntry getEntry(String S) {
		// TODO Auto-generated method stub
		//�жϸ�ʽ�Ƿ����Ҫ��
		if(check(S)) {
			String str = getMessage("Train:",S);
			String[] strs = str.split(",");
			String trainNumber = strs[1];
			String date = strs[0];
			
			MultipleLocationEntryImpl mle = new MultipleLocationEntryImpl();
			MultipleSortedResourceEntryImpl<Carriage> msre = new MultipleSortedResourceEntryImpl<Carriage>();
			List<RailwayStation> locations = new ArrayList<RailwayStation>();
			locations.add(new RailwayStation(getMessage("DepartureStation:",S)));
			String l = getMessage("IntermediateStation:",S);
			String[] inter = l.split(",");
			for(int i = 0;i<inter.length;i++) {
				locations.add(new RailwayStation(inter[i]));
			}
			locations.add(new RailwayStation(getMessage("ArrivalStation:",S)));
			mle.setLocations(locations);
			
			String startTime = getMessage("DepatureTime:",S);
			String endTime = getMessage("ArrivalTime:",S);
			Timeslot timeslot = new Timeslot(startTime,endTime);
			Pattern pattern = Pattern.compile(date);
			Matcher mc = pattern.matcher(startTime);
			if(!mc.find()) {
				JOptionPane.showMessageDialog(null, "����ʱ���е����ڱ������һ�е�����һ��");
				return null;
			}			
			
			String numbering = getMessage("Carriage:",S);
			String type = getMessage("Type:",S);
			int seatsNumber = Integer.parseInt(getMessage("PersonnelNumber:",S));
			String factoryYear = getMessage("FactoryYear:",S);
			Carriage carriage = new Carriage(numbering,type,seatsNumber,factoryYear);
			List<Carriage> carriages = new ArrayList<Carriage>();
			carriages.add(carriage);	
			msre.setResources(carriages);
			
			BlockableEntryImpl be = new BlockableEntryImpl();
			
			return new TrainEntry(trainNumber,timeslot,mle,msre,be);
		}
		
		else {
			JOptionPane.showMessageDialog(null, "��ʽ��ƥ�䣬��ѡ�������ļ�");
			System.out.println(S);
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
	   	Pattern pattern2 = Pattern.compile("((Train):(20[012][0-9]-[01][0-9]-[0123][0-9]),(G((\\d{2})|(\\d{3})|(\\d{4})))\n\\{\n(DepartureStation):([a-zA-z]+)\n(IntermediateStation:([A-Za-z]+\\,)+[A-Za-z]+)\n(ArrivalStation):([a-zA-z]+)\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Carriage):([A-Z]\\d{4})\n\\{\n(Type):([A-H])\n(PersonnelNumber:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(FactoryYear:)(20[12][0-9])\n\\}\n\\}\n)");
	   	Matcher mc = pattern1.matcher(S);
	   	if(mc.find()) {
    		mc = pattern2.matcher(S);
	    	if(mc.find())
	    		return true;
	   	}
	   	return false;
	}
}

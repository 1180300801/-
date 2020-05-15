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

import entryFactory.FlightEntryFactory;
import location.Location;
import planningEntry.FlightEntry;
import resource.Flight;

public class FlightCollection implements Iterable<FlightEntry>,Collection{

	private List<FlightEntry> flightCollection = new ArrayList<FlightEntry>();
	private Set<Location> airports  = new HashSet<Location>();
	private Set<Flight> flights = new HashSet<Flight>();
	//AF:һ���洢����ƻ�������λ�ú���Դ�ļƻ��
	//RI��true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	* ������
	* @param fileName �洢�ƻ�����ļ�
	* @throws IOException
	*/
	public FlightCollection(String fileName) throws IOException {
		//��������
    	FlightEntryFactory cef = new FlightEntryFactory();
    	String S = "";
    	File f = new File(fileName);
    	BufferedReader br = new BufferedReader(new FileReader(f));	
    	String s = "";
    	int i = 0;
    	here:
    	while((s = br.readLine()) != null) {
    		//���Կ���
    		if(!s.equals("")) {
    			S += s;
    			S += "\n";
    			i++;
    		}
    		//�ļ���ÿ13�д���һ��ƻ���
			if(i == 13) {
				//�жϸ�ʽ�Ƿ����Ҫ��
    			if(check(S)){
    				//ʹ�ù�����������ƻ���
    				FlightEntry flightEntry = cef.getEntry(S);
    				if(flightEntry == null)
    					break here;
    				//����Ƿ����ͬ���������࣬���ߺ������ͬʱ��ֹʱ�估�ص㲻��ͬ
    				for(FlightEntry fe:flightCollection) {
    					if(!checkFE(flightEntry,fe)) {
    						 break here;
    					}
    				}
    				
    				flights.add(flightEntry.getSe().getResource());
    				airports.add(flightEntry.getTe().getStart());
    				airports.add(flightEntry.getTe().getEnd());
    				flightCollection.add(flightEntry);
    				S = "";
    				i = 0;
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "��ʽ��ƥ�䣬��ѡ�������ļ�");
    				break here;
    			}
    		}
    	}    
    	br.close();
    	//���ƻ��ʼʱ���С��������
    	EntryComparator comp = new EntryComparator();
    	Collections.sort(flightCollection,comp);
    }
	
	/**
	 * �����ƻ���
	 * @param courseEntry �ƻ���
	 */
	public void addEntry(FlightEntry flightEntry) {
		flightCollection.add(flightEntry);
		EntryComparator comp = new EntryComparator();
    	Collections.sort(flightCollection,comp);
	}
	
	@Override
	public int size() {
		return flightCollection.size();
	}
	
	/**
	 * ������˺����Ƿ���ͬ�����ߺ������ͬʱ��ֹʱ�估�ص㲻��ͬ
	 * @param flightEntry
	 * @param fe
	 * @return
	 */
	private boolean checkFE(FlightEntry flightEntry,FlightEntry fe) {
		String s1 = flightEntry.getFlightnumber();
		//Ϊ���ж�����CA01��CA001��CA0001��ȵ�����������п�ʼ����Ϊ0�ĺ���Ÿı�ɿ�ʼֻ��һ��0����ʽ����CA01���������ͣ�Ȼ������������������ַ�����������CA001,CA0001������
		if(String.valueOf(s1.charAt(2)).equals("0"))
		    s1 = flightEntry.getFlightnumber().replaceFirst("[0]+", "0");
		String s2 = String.valueOf(s1.charAt(0))+String.valueOf(s1.charAt(1))+"0"+s1.substring(2);
		String s3 = String.valueOf(s1.charAt(0))+String.valueOf(s1.charAt(1))+"00"+s1.substring(2);
		
		//�жϺ�����Ƿ���ͬ
		if(fe.getFlightnumber().equals(s1)|fe.getFlightnumber().equals(s2)|fe.getFlightnumber().equals(s3)) {
			//�������ͬʱ���Ƚ��Ƿ���ͬһ�죬���ڣ����ͻ
			Pattern pattern = Pattern.compile("//d{4}-//d{2}-//d{2}");
			Matcher mc = pattern.matcher(flightEntry.getStartAndEndTime().getStartTime());
			if(mc.find()) {
				Matcher mc1 = pattern.matcher(fe.getStartAndEndTime().getStartTime());
				if(mc1.find()) {
					if(mc.group().equals(mc1.group())) {
						JOptionPane.showMessageDialog(null, "�ļ��д���һ���������ƻ�");
						return false;
					}   										
				}
			} 
			//�������ͬʱ���Ƚ���ֹ���Ƿ���ͬ��������ͬ�����ͻ
			if(!fe.getTe().getStart().getLocationName().equals(flightEntry.getTe().getStart().getLocationName())|!fe.getTe().getEnd().getLocationName().equals(flightEntry.getTe().getEnd().getLocationName())) {
				JOptionPane.showMessageDialog(null, "�ļ��д��ں������ͬʱ��ֹ�ص㲻��ͬ�ļƻ���");
			}
			//�������ͬ�Ҳ���ͬһ��ʱ���Ƚ���ֹʱ�䣨ʱ�֣��Ƿ���ͬ��������ͬ�����ͻ
			String str1 = (flightEntry.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str2 = (fe.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str3 = (flightEntry.getStartAndEndTime().getEndTime()).split(" ")[1];
			String str4 = (fe.getStartAndEndTime().getEndTime()).split(" ")[1];
			if(!str1.equals(str2)|!str3.equals(str4)|!flightEntry.getTe().getStart().getLocationName().equals(fe.getTe().getStart().getLocationName())|!flightEntry.getTe().getEnd().getLocationName().equals(fe.getTe().getEnd().getLocationName())) {
				JOptionPane.showMessageDialog(null, "�ļ��д��ں������ͬʱ��ֹʱ�䲻��ͬ�ļƻ���");
				return false;
			} 							
		}
		return true;
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
	
	 @Override
	public Object[][] getMessage() {
		Object[][] s = new String[flightCollection.size()][4];
		int i = 0;
		for(FlightEntry fe:flightCollection) {
			s[i][0] = fe.getFlightnumber();
			s[i][1] = fe.getStartAndEndTime().getStartTime()+" to "+fe.getStartAndEndTime().getEndTime();
			s[i][2] = fe.getTe().getStart().getLocationName()+"-"+fe.getTe().getEnd().getLocationName();
			s[i][3] = fe.getState().toString();
			i++;
		}
		return s;
	}	
	
	 /**
	 * ������Դ
	 * @param teacher
	 * @return
	 */
	 public boolean addResources(Flight flight) {
		 flights.add(flight);
		 return true;
	 }
	 
	 /**
	* ɾ����Դ,ͬʱɾ��ʹ�ø���Դ�ĺ���
	* @param flight Ҫɾ������Դ
	* @return
	*/
	 public boolean deleteResources(Flight flight) {
		 if(flight == null)
			 return false;
		 if(flights.remove(flight))
			 return true;
		 Iterator<FlightEntry> it = flightCollection.iterator();
		 while(it.hasNext()) {
			 FlightEntry fe = it.next();
			 if(fe.getSe().getResource().getNumbering().equals(flight.getNumbering()))
				 it.remove();
					 
		 }
		 return false;
	 }
	 
	 @Override
	 public boolean addLocations(Location airport) {
		 airports.add(airport);
		 return true;
	 }
	 
	 @Override
	 public boolean deleteLocations(Location airport) {
		 if(airport == null)
			 return false;
		 if(airports.remove(airport))
			 return true;
		 Iterator<FlightEntry> it = flightCollection.iterator();
		 while(it.hasNext()) {
			 FlightEntry fe = it.next();
			 if(fe.getTe().getStart().toString().equals(airport.toString())|fe.getTe().getEnd().toString().equals(airport.toString()))
				 it.remove();
		 }
		 return false;
	 }
	
	 /**
	  * ���ؼƻ��
	  * @return �ƻ��
	  */
	 public List<FlightEntry> getFlightEntry(){
		 return flightCollection;
	 }
	 
	 /**
	  * ���ؿ�����Դ��
	  * @return ������Դ��
	  */
     public Set<Flight> getResources(){
    	 return flights;
     }
     /**
	  * ���ؿ���λ�ü�
	  * @return ����λ�ü�
	  */
     public Set<Location> getLocations(){
     	 return airports;
     }
    
     /**
      * ���ص�����
      */
	 public Iterator<FlightEntry> iterator(){	 
		  return new Itr();
     }
	 
	 /**
	  * ������
	  * @author Administrator
	  *
	  */
	 public class Itr implements Iterator<FlightEntry>{
		
		 Iterator<FlightEntry> it;
		
		 public Itr() {
			 it = flightCollection.iterator();
		 }
		
		 @Override
		 public boolean hasNext() {
			 // TODO Auto-generated method stub
			 return it.hasNext();
		 }

		 @Override
		 public FlightEntry next() {
			 // TODO Auto-generated method stub
			 return it.next();
		 }
		
	 }

}
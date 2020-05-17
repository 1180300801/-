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

	private List<FlightEntry> flightCollection = new ArrayList<FlightEntry>();//存放所有航班计划
	private Set<Location> airports  = new HashSet<Location>();//存放所有航班计划中出现的机场
	private Set<Flight> flights = new HashSet<Flight>();//存放所有航班计划中使用到的飞机
	//AF:一个存储航班计划，可用位置和资源的计划项集
	//RI：true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	* 构造器
	* @param fileName 存储计划项的文件
	* @throws IOException
	*/
	public FlightCollection(String fileName) throws IOException {
		//工厂方法
    	FlightEntryFactory cef = new FlightEntryFactory();
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
    		//文件中每13行代表一格计划项
			if(i == 13) {
				//使用工厂方法构造计划项
				FlightEntry flightEntry = cef.getEntry(S);
				if(flightEntry == null)
					break here;
				//检查是否存相同的两个航班，或者航班号相同时起止时间及地点不相同
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
    	}    
    	br.close();
    	//按计划项开始时间从小到大排序
    	EntryComparator comp = new EntryComparator();
    	Collections.sort(flightCollection,comp);
    }
	
	/**
	 * 新增计划项
	 * @param courseEntry 计划项
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
	 * 检查两趟航班是否相同，或者航班号相同时起止时间及地点不相同
	 * @param flightEntry
	 * @param fe
	 * @return
	 */
	private boolean checkFE(FlightEntry flightEntry,FlightEntry fe) {
		String s1 = flightEntry.getFlightnumber();
		//为了判断类似CA01，CA001，CA0001相等的情况，将所有开始数字为0的航班号改变成开始只有一个0的形式，即CA01这样的类型，然后再扩充出另外两个字符串，即类似CA001,CA0001这样的
		if(String.valueOf(s1.charAt(2)).equals("0"))
		    s1 = flightEntry.getFlightnumber().replaceFirst("[0]+", "0");
		String s2 = String.valueOf(s1.charAt(0))+String.valueOf(s1.charAt(1))+"0"+s1.substring(2);
		String s3 = String.valueOf(s1.charAt(0))+String.valueOf(s1.charAt(1))+"00"+s1.substring(2);
		
		//判断航班号是否相同
		if(fe.getFlightnumber().equals(s1)|fe.getFlightnumber().equals(s2)|fe.getFlightnumber().equals(s3)) {
			//航班号相同时，比较是否在同一天，若在，则冲突
			Pattern pattern = Pattern.compile("//d{4}-//d{2}-//d{2}");
			Matcher mc = pattern.matcher(flightEntry.getStartAndEndTime().getStartTime());
			if(mc.find()) {
				Matcher mc1 = pattern.matcher(fe.getStartAndEndTime().getStartTime());
				if(mc1.find()) {
					if(mc.group().equals(mc1.group())) {
						JOptionPane.showMessageDialog(null, "文件中存在一样的两个计划");
						return false;
					}   										
				}
			} 
			//航班号相同时，比较起止地是否相同，若不相同，则冲突
			if(!fe.getTe().getStart().getLocationName().equals(flightEntry.getTe().getStart().getLocationName())|!fe.getTe().getEnd().getLocationName().equals(flightEntry.getTe().getEnd().getLocationName())) {
				JOptionPane.showMessageDialog(null, "文件中存在航班号相同时起止地点不相同的计划项");
			}
			//航班号相同且不在同一天时，比较起止时间（时分）是否相同，若不相同，则冲突
			String str1 = (flightEntry.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str2 = (fe.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str3 = (flightEntry.getStartAndEndTime().getEndTime()).split(" ")[1];
			String str4 = (fe.getStartAndEndTime().getEndTime()).split(" ")[1];
			if(!str1.equals(str2)|!str3.equals(str4)|!flightEntry.getTe().getStart().getLocationName().equals(fe.getTe().getStart().getLocationName())|!flightEntry.getTe().getEnd().getLocationName().equals(fe.getTe().getEnd().getLocationName())) {
				JOptionPane.showMessageDialog(null, "文件中存在航班号相同时起止时间不相同的计划项");
				return false;
			} 							
		}
		return true;
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
	 * 增加资源
	 * @param teacher
	 * @return
	 */
	 public boolean addResources(Flight flight) {
		 flights.add(flight);
		 return true;
	 }
	 
	 /**
	* 删除资源,同时删除使用该资源的航班
	* @param flight 要删除的资源
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
	  * 返回计划项集
	  * @return 计划项集
	  */
	 public List<FlightEntry> getFlightEntry(){
		 return flightCollection;
	 }
	 
	 /**
	  * 返回可用资源集
	  * @return 可用资源集
	  */
     public Set<Flight> getResources(){
    	 return flights;
     }
     /**
	  * 返回可用位置集
	  * @return 可用位置集
	  */
     public Set<Location> getLocations(){
     	 return airports;
     }
    
     /**
      * 返回迭代器
      */
	 public Iterator<FlightEntry> iterator(){	 
		  return new Itr();
     }
	 
	 /**
	  * 迭代器
	  * @author Administrator
	  *
	  */
	 class Itr implements Iterator<FlightEntry>{
		
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
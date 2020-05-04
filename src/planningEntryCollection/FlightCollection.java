package planningEntryCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import planningEntry.EntryComparator;
import planningEntry.FlightEntry;
import planningEntry.FlightEntryFactory;

public class FlightCollection implements Iterable<FlightEntry>{

	private List<FlightEntry> flightCollection = new ArrayList<FlightEntry>();
	
	public FlightCollection(String fileName) throws IOException {
    	FlightEntryFactory cef = new FlightEntryFactory();
    	String S = "";
    	File f = new File(fileName);
    	BufferedReader br = new BufferedReader(new FileReader(f));	
    	String s = "";
    	int i = 0;
    	here:
    	while((s = br.readLine()) != null) {
    		S += s;
			S += "\n";
			i++;
			if(i == 13) {
    			if(check(S)){
    				FlightEntry flightEntry = cef.getEntry(S);
    				if(flightEntry == null)
    					break here;
    				flightCollection.add(flightEntry);
    				S = "";
    				i = 0;
    			}
    			else {
    				System.out.println("格式不匹配，请选择其它文件");
    				break here;
    			}
    		}
    	}    	
    	br.close();
    	EntryComparator comp = new EntryComparator();
    	Collections.sort(flightCollection,comp);
    }
	
	public void addEntry(FlightEntry flightEntry) {
		flightCollection.add(flightEntry);
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
	   	Pattern pattern2 = Pattern.compile("((Flight):(20[012][0-9]-[01][0-9]-[0123][0-9]),([A-Z][A-Z]((\\d{2})|(\\d{3})|(\\d{4})))\n\\{\n(DepartureAirport):([a-zA-z]+)\n(ArrivalAirport):([a-zA-z]+)\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Plane):([BN]\\d{4})\n\\{\n(Type):([A-Za-z0-9]+)\n(Seats:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(Age):(([0-9]|([1-2][0-9])|(30))\\.?[0-9]?)\n\\}\n\\}\n)");
	   	Matcher mc = pattern1.matcher(S);
	   	if(mc.find()) {
    		mc = pattern2.matcher(S);
	    	if(mc.find())
	    		return true;
	   	}
	   	return false;
	}
	 
	 public Iterator<FlightEntry> iterator(){	 
		 return new Itr();
	 }
	 
	private class Itr implements Iterator<FlightEntry>{
		
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

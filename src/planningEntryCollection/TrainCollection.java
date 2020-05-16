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

import entryFactory.TrainEntryFactory;
import location.Location;
import planningEntry.TrainEntry;
import resource.Carriage;

public class TrainCollection implements Iterable<TrainEntry>,Collection{

	private List<TrainEntry> trainCollection = new ArrayList<TrainEntry>();
	private Set<Location> stations  = new HashSet<Location>();
	private Set<Carriage> carriages = new HashSet<Carriage>();
	//AF:一个存储高铁计划项，可用位置和资源的计划项集
	//RI：true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	* 构造器
	* @param fileName 存储计划项的文件
	* @throws IOException
	*/
	public TrainCollection(String fileName) throws IOException {
		//工厂方法
		TrainEntryFactory tef = new TrainEntryFactory();
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
    		//文件中每14行代表一格计划项
			if(i == 14) {
				//判断格式是否符合要求
    			if(check(S)){
    				//使用工厂方法构造计划项
    				TrainEntry trainEntry = tef.getEntry(S);
    				if(trainEntry == null)
    					break here;
    				//检查是否存相同的两个航班，或者列车号相同时起止时间及地点不相同
    				for(TrainEntry te:trainCollection) {
    					if(!checkFE(trainEntry,te)) {
    						break here;
    					}
    				}
    				carriages.add(trainEntry.getMsre().getResources().get(0));
    				for(int j = 0;j<trainEntry.getMle().getLocations().size();j++) {
    					stations.add(trainEntry.getMle().getLocations().get(j));
    				}
    				trainCollection.add(trainEntry);
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
    	Collections.sort(trainCollection,comp);
    }
	
	/**
	 * 新增计划项
	 * @param courseEntry 计划项
	 */
	public void addEntry(TrainEntry trainEntry) {
		trainCollection.add(trainEntry);
		EntryComparator comp = new EntryComparator();
    	Collections.sort(trainCollection,comp);
	}
	
	@Override
	public int size() {
		return trainCollection.size();
	}
	
	/**
	 * 检查两趟航班是否相同，或者航班号相同时起止时间及地点不相同
	 * @param flightEntry
	 * @param fe
	 * @return
	 */
	private boolean checkFE(TrainEntry trainEntry,TrainEntry te) {
		String s1 = trainEntry.getTrainNumber();
		//为了判断类似G01，G001，G0001相等的情况，将所有开始数字为0的列车号改变成开始只有一个0的形式，即G01这样的类型，然后再扩充出另外两个字符串，即类似G001,G0001这样的
		if(String.valueOf(s1.charAt(1)).equals("0"))
		    s1 = trainEntry.getTrainNumber().replaceFirst("[0]+", "0");
		String s2 = String.valueOf(s1.charAt(0))+"0"+s1.substring(1);
		String s3 = String.valueOf(s1.charAt(0))+"00"+s1.substring(1);
		
		//判断列车号是否相同
		if(te.getTrainNumber().equals(s1)|te.getTrainNumber().equals(s2)|te.getTrainNumber().equals(s3)) {
			//列车号相同时，比较是否在同一天，若在，则冲突
			Pattern pattern = Pattern.compile("//d{4}-//d{2}-//d{2}");
			Matcher mc = pattern.matcher(trainEntry.getStartAndEndTime().getStartTime());
			if(mc.find()) {
				Matcher mc1 = pattern.matcher(te.getStartAndEndTime().getStartTime());
				if(mc1.find()) {
					if(mc.group().equals(mc1.group())) {
						JOptionPane.showMessageDialog(null, "文件中存在一样的两个计划");
						return false;
					}   										
				}
			} 
			//L列车号相同且不在同一天时，比较起止时间（时分）是否相同，若不相同，则冲突
			String str1 = (trainEntry.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str2 = (te.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str3 = (trainEntry.getStartAndEndTime().getEndTime()).split(" ")[1];
			String str4 = (te.getStartAndEndTime().getEndTime()).split(" ")[1];
			if((!str1.equals(str2)|!str3.equals(str4))&te.getMle().getLocations().get(0).getLocationName().equals(trainEntry.getMle().getLocations().get(0).getLocationName())) {
				JOptionPane.showMessageDialog(null, "文件中存在列车号相同时起止时间不相同的计划项");
				System.out.println(te.getTrainNumber()+":"+str2+" "+str4+te.getMle().getLocations().get(0));
				return false;
			} 							
		}
		return true;
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
	   	Pattern pattern2 = Pattern.compile("((Train):(20[012][0-9]-[01][0-9]-[0123][0-9]),(G((\\d{2})|(\\d{3})|(\\d{4})))\n\\{\n(DepartureStation):([a-zA-z]+)\n(IntermediateStation:([A-Za-z]+\\,)+[A-Za-z]+)\n(ArrivalStation):([a-zA-z]+)\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Carriage):([A-Z]\\d{4})\n\\{\n(Type):([A-H])\n(PersonnelNumber:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(FactoryYear:)(20[12][0-9])\n\\}\n\\}\n)");
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
		Object[][] s = new String[trainCollection.size()][4];
		int i = 0;
		for(TrainEntry te:trainCollection) {
			s[i][0] = te.getTrainNumber();
			s[i][1] = te.getStartAndEndTime().getStartTime()+" to "+te.getStartAndEndTime().getEndTime();
			s[i][2] = te.getMle().getLocations().get(0)+"-"+te.getMle().getLocations().get(te.getMle().getLocations().size()-1);
			s[i][3] = te.getState().toString();
			i++;
		}
		return s;
	}	
	
    /**
	* 增加资源
	* @param teacher
    * @return
    */
	 public boolean addResources(Carriage carriage) {
		 carriages.add(carriage);
		 return true;
	 }
	 
	 /**
	* 删除资源
	* @param teacher
	* @return
	*/
	 public boolean deleteResources(Carriage carriage) {
		 carriages.remove(carriage);
		 Iterator<TrainEntry> it = trainCollection.iterator();
		 while(it.hasNext()) {
			 TrainEntry fe = it.next();
			 if(fe.getMsre().getResources().get(0).getNumbering().equals(carriage.getNumbering()))
					 it.remove();
		 }
		 return true;
	 }
	 
	 @Override
	 public boolean addLocations(Location station) {
		 stations.add(station);
		 return true;
	 }
	 
	 @Override
	 public boolean deleteLocations(Location station) {
		 stations.remove(station);
		 Iterator<TrainEntry> it = trainCollection.iterator();
		 while(it.hasNext()) {
			TrainEntry fe = it.next();
			 if(fe.getMle().getLocations().contains(station))
				 it.remove();
		 }
		 return true;
	 }
	
	 /**
	  * 返回计划项集
	  * @return 计划项集
	  */
	 public List<TrainEntry> getTrainEntry(){
		 return trainCollection;
	 }
	 
	 /**
	  * 返回可用资源集
	  * @return 可用资源集
	  */
     public Set<Carriage> getResources(){
    	 return carriages;
     }
	
     /**
	  * 返回可用位置集
	  * @return 可用位置集
	  */
     public Set<Location> getLocations(){
     	 return stations;
     }
    
     /**
      * 返回迭代器
      */
	 public Iterator<TrainEntry> iterator(){	 
		  return new Itr();
     }
	 
	 /**
	  * 迭代器
	  * @author Administrator
	  *
	  */
	 private class Itr implements Iterator<TrainEntry>{
		
		 Iterator<TrainEntry> it;
		
		 public Itr() {
			 it = trainCollection.iterator();
		 }
		
		 @Override
		 public boolean hasNext() {
			 // TODO Auto-generated method stub
			 return it.hasNext();
		 }

		 @Override
		 public TrainEntry next() {
			 // TODO Auto-generated method stub
			 return it.next();
		 }
		
	 }

}

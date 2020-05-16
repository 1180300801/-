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
	//AF:һ���洢�����ƻ������λ�ú���Դ�ļƻ��
	//RI��true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	* ������
	* @param fileName �洢�ƻ�����ļ�
	* @throws IOException
	*/
	public TrainCollection(String fileName) throws IOException {
		//��������
		TrainEntryFactory tef = new TrainEntryFactory();
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
    		//�ļ���ÿ14�д���һ��ƻ���
			if(i == 14) {
				//�жϸ�ʽ�Ƿ����Ҫ��
    			if(check(S)){
    				//ʹ�ù�����������ƻ���
    				TrainEntry trainEntry = tef.getEntry(S);
    				if(trainEntry == null)
    					break here;
    				//����Ƿ����ͬ���������࣬�����г�����ͬʱ��ֹʱ�估�ص㲻��ͬ
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
    				JOptionPane.showMessageDialog(null, "��ʽ��ƥ�䣬��ѡ�������ļ�");
    				System.out.println(S);
    				break here;
    			}
    		}
    	}    	
    	br.close();
    	//���ƻ��ʼʱ���С��������
    	EntryComparator comp = new EntryComparator();
    	Collections.sort(trainCollection,comp);
    }
	
	/**
	 * �����ƻ���
	 * @param courseEntry �ƻ���
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
	 * ������˺����Ƿ���ͬ�����ߺ������ͬʱ��ֹʱ�估�ص㲻��ͬ
	 * @param flightEntry
	 * @param fe
	 * @return
	 */
	private boolean checkFE(TrainEntry trainEntry,TrainEntry te) {
		String s1 = trainEntry.getTrainNumber();
		//Ϊ���ж�����G01��G001��G0001��ȵ�����������п�ʼ����Ϊ0���г��Ÿı�ɿ�ʼֻ��һ��0����ʽ����G01���������ͣ�Ȼ������������������ַ�����������G001,G0001������
		if(String.valueOf(s1.charAt(1)).equals("0"))
		    s1 = trainEntry.getTrainNumber().replaceFirst("[0]+", "0");
		String s2 = String.valueOf(s1.charAt(0))+"0"+s1.substring(1);
		String s3 = String.valueOf(s1.charAt(0))+"00"+s1.substring(1);
		
		//�ж��г����Ƿ���ͬ
		if(te.getTrainNumber().equals(s1)|te.getTrainNumber().equals(s2)|te.getTrainNumber().equals(s3)) {
			//�г�����ͬʱ���Ƚ��Ƿ���ͬһ�죬���ڣ����ͻ
			Pattern pattern = Pattern.compile("//d{4}-//d{2}-//d{2}");
			Matcher mc = pattern.matcher(trainEntry.getStartAndEndTime().getStartTime());
			if(mc.find()) {
				Matcher mc1 = pattern.matcher(te.getStartAndEndTime().getStartTime());
				if(mc1.find()) {
					if(mc.group().equals(mc1.group())) {
						JOptionPane.showMessageDialog(null, "�ļ��д���һ���������ƻ�");
						return false;
					}   										
				}
			} 
			//L�г�����ͬ�Ҳ���ͬһ��ʱ���Ƚ���ֹʱ�䣨ʱ�֣��Ƿ���ͬ��������ͬ�����ͻ
			String str1 = (trainEntry.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str2 = (te.getStartAndEndTime().getStartTime()).split(" ")[1];
			String str3 = (trainEntry.getStartAndEndTime().getEndTime()).split(" ")[1];
			String str4 = (te.getStartAndEndTime().getEndTime()).split(" ")[1];
			if((!str1.equals(str2)|!str3.equals(str4))&te.getMle().getLocations().get(0).getLocationName().equals(trainEntry.getMle().getLocations().get(0).getLocationName())) {
				JOptionPane.showMessageDialog(null, "�ļ��д����г�����ͬʱ��ֹʱ�䲻��ͬ�ļƻ���");
				System.out.println(te.getTrainNumber()+":"+str2+" "+str4+te.getMle().getLocations().get(0));
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
	* ������Դ
	* @param teacher
    * @return
    */
	 public boolean addResources(Carriage carriage) {
		 carriages.add(carriage);
		 return true;
	 }
	 
	 /**
	* ɾ����Դ
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
	  * ���ؼƻ��
	  * @return �ƻ��
	  */
	 public List<TrainEntry> getTrainEntry(){
		 return trainCollection;
	 }
	 
	 /**
	  * ���ؿ�����Դ��
	  * @return ������Դ��
	  */
     public Set<Carriage> getResources(){
    	 return carriages;
     }
	
     /**
	  * ���ؿ���λ�ü�
	  * @return ����λ�ü�
	  */
     public Set<Location> getLocations(){
     	 return stations;
     }
    
     /**
      * ���ص�����
      */
	 public Iterator<TrainEntry> iterator(){	 
		  return new Itr();
     }
	 
	 /**
	  * ������
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

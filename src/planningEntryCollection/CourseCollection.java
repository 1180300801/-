package planningEntryCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import planningEntry.CourseEntry;
import planningEntry.CourseEntryFactory;

public class CourseCollection {

    private List<CourseEntry> courseCollection = new ArrayList<CourseEntry>();
    
    public CourseCollection(String fileName) throws IOException {
    	CourseEntryFactory cef = new CourseEntryFactory();
    	String S = "";
    	File f = new File(fileName);
    	BufferedReader br = new BufferedReader(new FileReader(f));
    	for(int i = 0;i<13;i++) {
    		try {
    			S += br.readLine();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		S += "\n"; 
    		if(i == 12) {
    			if(check(S)){
    				courseCollection.add(cef.getEntry(S));
    				i = 0;
    			}
    			else {
    				System.out.println("格式不匹配，请选择其它文件");
    				br.close();
    			}
    		}
    	}
    	br.close();
    }
    
    private boolean check(String S){
    	String str = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
    	Pattern pattern1 = Pattern.compile(str);
    	Pattern pattern2 = Pattern.compile("((Flight):(20[012][0-9]-[01][0-9]-[0123][0-9]),([A-Z][A-Z]((\\d{2})|(\\d{3})|(\\d{4})))\n\\{\n(DepartureAirport):([a-zA-z]+)\n(ArrivalAirport):([a-zA-z]+)\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Plane):([A-Z]\\d{4})\n\\{\n(Type):([A-Z]\\d{3})\n(Seats:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(Age):(([0-9]|([12][0-9])|(30))\\.[0-9])\n\\}\n\\}\n)");
    	Matcher mc = pattern1.matcher(S);
    	if(mc.find()) {
    		mc = pattern2.matcher(S);
    		if(mc.find())
    			return true;
    	}
    	return false;
    }
}
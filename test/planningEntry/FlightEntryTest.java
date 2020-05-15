package planningEntry;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import entryFactory.FlightEntryFactory;

public class FlightEntryTest {

	/**
	 * 测试equals方法
	 * 测试策略：
	 * obj与this指向同一个对象，obj与this不指向同一个对象
	 * obj与this相同， obj与this不相同
	 * obj为空，obj非空
	 * obj与this类型相同，obj与this类型不相同
	 * @throws IOException 
	 */
	@Test
	public void testFlightEntry() throws IOException {
		File f = new File("test/txt/test_3.txt");
    	BufferedReader br = new BufferedReader(new FileReader(f));	
    	String S = "",s = "";
    	while((s = br.readLine()) != null) {
    		//忽略空行
    		if(!s.equals("")) {
    			S += s;
    			S += "\n";
    		}
    	}
    	br.close();
    	FlightEntryFactory fef = new FlightEntryFactory();
    	FlightEntry fe1 = fef.getEntry(S);
    	FlightEntry fe2 = fe1;
    	FlightEntry fe3 = fef.getEntry(S);
    	FlightEntry fe4 = null;
    	assertEquals(true,fe1.equals(fe2));
    	assertEquals(true,fe1.equals(fe3));
    	assertEquals(false,fe1.equals(fe4));
    	assertEquals(false,fe1.equals(s));
	}

}

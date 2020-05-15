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
	 * ����equals����
	 * ���Բ��ԣ�
	 * obj��thisָ��ͬһ������obj��this��ָ��ͬһ������
	 * obj��this��ͬ�� obj��this����ͬ
	 * objΪ�գ�obj�ǿ�
	 * obj��this������ͬ��obj��this���Ͳ���ͬ
	 * @throws IOException 
	 */
	@Test
	public void testFlightEntry() throws IOException {
		File f = new File("test/txt/test_3.txt");
    	BufferedReader br = new BufferedReader(new FileReader(f));	
    	String S = "",s = "";
    	while((s = br.readLine()) != null) {
    		//���Կ���
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

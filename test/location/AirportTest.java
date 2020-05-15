package location;

import static org.junit.Assert.*;

import org.junit.Test;

public class AirportTest {

	/**
	 * ������д��equals����
	 * ���Բ��ԣ�
	 * obj��thisָ��ͬһ������obj��this��ָ��ͬһ������
	 * obj��this��ͬ�� obj��this����ͬ
	 * objΪ�գ�obj�ǿ�
	 * obj��this������ͬ��obj��this���Ͳ���ͬ
	 */
	@Test
	public void test() {
		Airport airport = new Airport("����");
		Airport airport1 = airport;
		assertEquals(true,airport.equals(airport1));
		Airport airport2 = new Airport("����");
		assertEquals(false,airport.equals(airport2));
		Airport airport3 = new Airport("����");
		assertEquals(true,airport.equals(airport3));
		airport3 = null;
		assertEquals(false,airport.equals(airport3));
		String s = "001";
		assertEquals(false,airport.equals(s));
	}

}

package location;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ������RailwayStation�е���д��equals�����������򵥵�getter������û���ٲ�����
 * @author Administrator
 *
 */
public class RailwayStationTest {

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
		RailwayStation station = new RailwayStation("����");
		RailwayStation station1 = station;
		assertEquals(true,station.equals(station1));
		RailwayStation station2 = new RailwayStation("����");
		assertEquals(false,station.equals(station2));
		RailwayStation station3 = new RailwayStation("����");
		assertEquals(true,station.equals(station3));
		station3 = null;
		assertEquals(false,station.equals(station3));
		String s = "001";
		assertEquals(false,station.equals(s));
	}

}

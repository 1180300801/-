package planningEntryCollection;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

import location.Airport;
import resource.Flight;

public class FlightCollectionTest {

	/**
	 * ����deleteResources����
	 * ���Բ��ԣ�
	 * �ɻ�����Դ���ϵ��У��ɻ�������Դ�����У��ɻ�Ϊ��
	 * @throws IOException
	 */
	@Test
	public void deleteResourcesTest() throws IOException {
		FlightCollection fc = new FlightCollection("test/txt/testFlight_1.txt");
		Flight flight = new Flight("B6863","A319",140,8.5);
		assertEquals(true,fc.deleteResources(flight));
		assertEquals(false,fc.deleteResources(flight));
		flight = null;
		assertEquals(false,fc.deleteResources(flight));
	}

	/**
	 * ����deleteLocations����
	 * ���Բ��ԣ�
	 * λ���ڿ���λ�ü��ϵ��У�λ�ò��ڿ���λ�ü����У�λ��Ϊ��
	 * @throws IOException
	 */
	@Test
	public void deleteLocationsTest() throws IOException{
		FlightCollection fc = new FlightCollection("test/txt/testFlight_1.txt");
		Airport airport = new Airport("Tianjin");
		assertEquals(true,fc.deleteLocations(airport));
		assertEquals(false,fc.deleteLocations(airport));
		airport = null;
		assertEquals(false,fc.deleteLocations(airport));
	}
}

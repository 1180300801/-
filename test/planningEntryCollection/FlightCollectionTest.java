package planningEntryCollection;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

import location.Airport;
import resource.Flight;

public class FlightCollectionTest {

	/**
	 * 测试deleteResources方法
	 * 测试策略：
	 * 飞机在资源集合当中，飞机不在资源集合中，飞机为空
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
	 * 测试deleteLocations方法
	 * 测试策略：
	 * 位置在可用位置集合当中，位置不在可用位置集合中，位置为空
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

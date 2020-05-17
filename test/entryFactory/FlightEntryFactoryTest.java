package entryFactory;

import static org.junit.Assert.*;

import org.junit.Test;

import location.Airport;
import planningEntry.FlightEntry;
import planningEntry.SingleSortedResourceEntryImpl;
import planningEntry.TwoLocationEntryImpl;
import resource.Flight;
import timeslot.Timeslot;

/**
 * 
 * @author Administrator
 *
 */
public class FlightEntryFactoryTest {

	/**
	 * 测试FlightEntryFactory(S)
	 * 测试策略：
	 * 对于用于构建FlightEntry的字符串S(S是实验提供的文件中一个包含构建一个航班计划项完整信息的字符串，符合特定的语法规则)，有如下等价类划分：
	 * 时间遵循 yyyy-MM-dd HH:mm 的格式；时间不遵循该格式
	 * 航班号由两位大写字母和 2-4 位数字构成；航班号不符合这种格式
	 * 起飞时间中的日期与第一行的日期一致；不一致
	 * 抵达时间中的日期可以与起飞日期一致，也可以晚 1 天；不遵循该规则
	 * 飞机编号第一位为 N 或 B，后面是四位数字；飞机编号第一位不为 N 或 B，后面不是四位数字
	 * 座位数为正整数且范围为[50,600]；座位数为负正整数或范围超过[50,600]
	 * 机龄为数字且范围是[0,30]，可最多 1 位小数或无小数；超出范围，多位小数
	 */
	@Test
	public void testFlightEntryFactory() {
		String test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-01 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		String flightNumber = "AA72";
		Timeslot startAndEndTime = new Timeslot("2020-05-01 03:15","2020-05-01 05:25");
		TwoLocationEntryImpl te = new TwoLocationEntryImpl();
		te.setLocations(new Airport("Tianjin"), new Airport("Sanya"));
		SingleSortedResourceEntryImpl<Flight> se = new SingleSortedResourceEntryImpl<Flight>();
		se.setResource(new Flight("B6863","A319",140,8.5));
		FlightEntry fe = new FlightEntry(flightNumber,startAndEndTime,te,se);
		FlightEntryFactory fef = new FlightEntryFactory();
		assertEquals(fe,fef.getEntry(test));
		//时间不遵循 yyyy-MM-dd HH:mm 的格式
		test = new String("Flight:2020-05,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05 03:15\n" + 
				"ArrivalTime:2020-05 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//航班号不符合由两位大写字母和 2-4 位数字构成格式
		test = new String("Flight:2020-05-01,A7\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-01 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//起飞时间中的日期与第一行的日期不一致
		test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-02 03:15\n" + 
				"ArrivalTime:2020-05-02 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//抵达时间中的日期可以与起飞日期一致，也可以晚 1 天,不遵循该规则
		test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-03 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//飞机编号第一位不为 N 或 B，后面不是四位数字
		test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-01 05:25\n" + 
				"Plane:C56863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//座位数为负正整数或范围超过[50,600]
		test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-01 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:-140\n" + 
				"Age:8.5\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
		//机龄超出范围[0,30]，多位小数
		test = new String("Flight:2020-05-01,AA72\n" + 
				"{\n" + 
				"DepartureAirport:Tianjin\n" + 
				"ArrivalAirport:Sanya\n" + 
				"DepatureTime:2020-05-01 03:15\n" + 
				"ArrivalTime:2020-05-01 05:25\n" + 
				"Plane:B6863\n" + 
				"{\n" + 
				"Type:A319\n" + 
				"Seats:140\n" + 
				"Age:38.56\n" + 
				"}\n" + 
				"}\n");
		assertEquals(null,fef.getEntry(test));
	}
}

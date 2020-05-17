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
	 * ����FlightEntryFactory(S)
	 * ���Բ��ԣ�
	 * �������ڹ���FlightEntry���ַ���S(S��ʵ���ṩ���ļ���һ����������һ������ƻ���������Ϣ���ַ����������ض����﷨����)�������µȼ��໮�֣�
	 * ʱ����ѭ yyyy-MM-dd HH:mm �ĸ�ʽ��ʱ�䲻��ѭ�ø�ʽ
	 * ���������λ��д��ĸ�� 2-4 λ���ֹ��ɣ�����Ų��������ָ�ʽ
	 * ���ʱ���е��������һ�е�����һ�£���һ��
	 * �ִ�ʱ���е����ڿ������������һ�£�Ҳ������ 1 �죻����ѭ�ù���
	 * �ɻ���ŵ�һλΪ N �� B����������λ���֣��ɻ���ŵ�һλ��Ϊ N �� B�����治����λ����
	 * ��λ��Ϊ�������ҷ�ΧΪ[50,600]����λ��Ϊ����������Χ����[50,600]
	 * ����Ϊ�����ҷ�Χ��[0,30]������� 1 λС������С����������Χ����λС��
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
		//ʱ�䲻��ѭ yyyy-MM-dd HH:mm �ĸ�ʽ
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
		//����Ų���������λ��д��ĸ�� 2-4 λ���ֹ��ɸ�ʽ
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
		//���ʱ���е��������һ�е����ڲ�һ��
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
		//�ִ�ʱ���е����ڿ������������һ�£�Ҳ������ 1 ��,����ѭ�ù���
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
		//�ɻ���ŵ�һλ��Ϊ N �� B�����治����λ����
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
		//��λ��Ϊ����������Χ����[50,600]
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
		//���䳬����Χ[0,30]����λС��
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

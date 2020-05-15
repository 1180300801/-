package timeslot;

import static org.junit.Assert.*;
import org.junit.Test;

public class TimeslotTest {

	/**
	 * ����Timeslot�е�checkRep����
	 * ���Բ��ԣ�
	 * ʱ��Է���yyyy-MM-dd HH:mm���﷨����ʱ��Բ�����yyyy-MM-dd HH:mm���﷨����
	 */
	@Test
	public void checkRepTest() {
		Timeslot timeslot = new Timeslot("2020-5-13 21:32","2020-5-13 22:00");
		assertEquals(false,timeslot.checkRep("2020-5-13 ", "2020-5-13 22:00"));
		assertEquals(true,timeslot.checkRep("2020-5-13 21:32","2020-5-13 22:00"));
	}
	
	/**
	 * ����timeSub�������÷���������ֹʱ��������յĲ�ֵ
	 * ���Բ��ԣ�
	 * ʱ��ԵĲ�ֵС��0������0������0
	 */
	@Test
	public void timeSubTest() {
		Timeslot timeslot1 = new Timeslot("2020-5-13 21:32","2020-5-13 22:00");
		assertEquals(0,timeslot1.timeSub());
		Timeslot timeslot2 = new Timeslot("2020-5-12 21:32","2020-5-13 22:00");
		assertEquals(1,timeslot2.timeSub());
		Timeslot timeslot3 = new Timeslot("2020-5-13 21:32","2020-5-12 22:00");
		assertEquals(-1,timeslot3.timeSub());
	}
	
	/**
	 * ����timeSubHourAndMin�������÷���������ֹʱ���ʱ�ֵĲ�ֵ
	 * ���Բ��ԣ�
	 * ʱ��ԵĲ�ֵС��0������0������0
	 */
	@Test
	public void timeSubHourAndMinTest() {
		Timeslot timeslot1 = new Timeslot("2020-5-13 20:32","2020-5-13 22:00");
		assertEquals(88,timeslot1.timeSubHourAndMin());
		Timeslot timeslot2 = new Timeslot("2020-5-12 22:32","2020-5-13 22:00");
		assertEquals(-32,timeslot2.timeSubHourAndMin());
		Timeslot timeslot3 = new Timeslot("2020-5-13 22:00","2020-5-12 22:00");
		assertEquals(0,timeslot3.timeSubHourAndMin());
	}
}

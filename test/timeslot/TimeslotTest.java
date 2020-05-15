package timeslot;

import static org.junit.Assert.*;
import org.junit.Test;

public class TimeslotTest {

	/**
	 * 测试Timeslot中的checkRep方法
	 * 测试策略：
	 * 时间对符合yyyy-MM-dd HH:mm的语法规则，时间对不符合yyyy-MM-dd HH:mm的语法规则
	 */
	@Test
	public void checkRepTest() {
		Timeslot timeslot = new Timeslot("2020-5-13 21:32","2020-5-13 22:00");
		assertEquals(false,timeslot.checkRep("2020-5-13 ", "2020-5-13 22:00"));
		assertEquals(true,timeslot.checkRep("2020-5-13 21:32","2020-5-13 22:00"));
	}
	
	/**
	 * 测试timeSub方法，该方法计算起止时间对年月日的差值
	 * 测试策略：
	 * 时间对的差值小于0，等于0，大于0
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
	 * 测试timeSubHourAndMin方法，该方法计算起止时间对时分的差值
	 * 测试策略：
	 * 时间对的差值小于0，等于0，大于0
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

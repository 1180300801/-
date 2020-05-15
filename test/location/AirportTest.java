package location;

import static org.junit.Assert.*;

import org.junit.Test;

public class AirportTest {

	/**
	 * 测试重写的equals方法
	 * 测试策略：
	 * obj与this指向同一个对象，obj与this不指向同一个对象
	 * obj与this相同， obj与this不相同
	 * obj为空，obj非空
	 * obj与this类型相同，obj与this类型不相同
	 */
	@Test
	public void test() {
		Airport airport = new Airport("昆明");
		Airport airport1 = airport;
		assertEquals(true,airport.equals(airport1));
		Airport airport2 = new Airport("北京");
		assertEquals(false,airport.equals(airport2));
		Airport airport3 = new Airport("昆明");
		assertEquals(true,airport.equals(airport3));
		airport3 = null;
		assertEquals(false,airport.equals(airport3));
		String s = "001";
		assertEquals(false,airport.equals(s));
	}

}

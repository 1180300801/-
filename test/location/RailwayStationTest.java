package location;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 测试了RailwayStation中的重写的equals方法，其它简单的getter方法就没有再测试了
 * @author Administrator
 *
 */
public class RailwayStationTest {

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
		RailwayStation station = new RailwayStation("昆明");
		RailwayStation station1 = station;
		assertEquals(true,station.equals(station1));
		RailwayStation station2 = new RailwayStation("北京");
		assertEquals(false,station.equals(station2));
		RailwayStation station3 = new RailwayStation("昆明");
		assertEquals(true,station.equals(station3));
		station3 = null;
		assertEquals(false,station.equals(station3));
		String s = "001";
		assertEquals(false,station.equals(s));
	}

}

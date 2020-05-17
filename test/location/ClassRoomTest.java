package location;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 测试了ClassRoom中的重写的equals方法，其它简单的getter方法就没有再测试了
 * @author Administrator
 *
 */
public class ClassRoomTest {

	/**
	 * 测试重写的equals方法
	 * 测试策略：
	 * obj与this指向同一个对象，obj与this不指向同一个对象
	 * obj与this相同， obj与this不相同
	 * obj为空，obj非空
	 * obj与this类型相同，obj与this类型不相同
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void test() {
		ClassRoom classroom = new ClassRoom("B12");
		ClassRoom classroom1 = classroom;
		assertEquals(true,classroom.equals(classroom1));
		ClassRoom classroom2 = new ClassRoom("B13");
		assertEquals(false,classroom.equals(classroom2));
		ClassRoom classroom3 = new ClassRoom("B12");
		assertEquals(true,classroom.equals(classroom3));
		classroom3 = null;
		assertEquals(false,classroom.equals(classroom3));
		String s = "001";
		assertEquals(false,classroom.equals(s));
	}

}

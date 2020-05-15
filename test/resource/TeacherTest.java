package resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherTest {

	/**
	 * 测试重写的equals方法
	 * 测试策略：
	 * obj与this指向同一个对象，obj与this不指向同一个对象
	 * obj与this相同， obj与this不相同
	 * obj为空，obj非空
	 * obj与this类型相同，obj与this类型不相同
	 */
	@Test
	public void equalsTest() {
		Teacher teacher = new Teacher("001","王明","男","高级教师");
		Teacher teacher1 = teacher;
		assertEquals(true,teacher.equals(teacher1));
		Teacher teacher2 = new Teacher("002","李明","男","高级教师");
		assertEquals(false,teacher.equals(teacher2));
		Teacher teacher3 = new Teacher("001","王明","男","高级教师");
		assertEquals(true,teacher.equals(teacher3));
		teacher3 = null;
		assertEquals(false,teacher.equals(teacher3));
		String s = "001";
		assertEquals(false,teacher.equals(s));
	}

}

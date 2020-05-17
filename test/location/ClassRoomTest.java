package location;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ������ClassRoom�е���д��equals�����������򵥵�getter������û���ٲ�����
 * @author Administrator
 *
 */
public class ClassRoomTest {

	/**
	 * ������д��equals����
	 * ���Բ��ԣ�
	 * obj��thisָ��ͬһ������obj��this��ָ��ͬһ������
	 * obj��this��ͬ�� obj��this����ͬ
	 * objΪ�գ�obj�ǿ�
	 * obj��this������ͬ��obj��this���Ͳ���ͬ
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

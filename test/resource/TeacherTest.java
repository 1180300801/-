package resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherTest {

	/**
	 * ������д��equals����
	 * ���Բ��ԣ�
	 * obj��thisָ��ͬһ������obj��this��ָ��ͬһ������
	 * obj��this��ͬ�� obj��this����ͬ
	 * objΪ�գ�obj�ǿ�
	 * obj��this������ͬ��obj��this���Ͳ���ͬ
	 */
	@Test
	public void equalsTest() {
		Teacher teacher = new Teacher("001","����","��","�߼���ʦ");
		Teacher teacher1 = teacher;
		assertEquals(true,teacher.equals(teacher1));
		Teacher teacher2 = new Teacher("002","����","��","�߼���ʦ");
		assertEquals(false,teacher.equals(teacher2));
		Teacher teacher3 = new Teacher("001","����","��","�߼���ʦ");
		assertEquals(true,teacher.equals(teacher3));
		teacher3 = null;
		assertEquals(false,teacher.equals(teacher3));
		String s = "001";
		assertEquals(false,teacher.equals(s));
	}

}

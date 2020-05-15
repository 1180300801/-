package entryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateTest {

	/**
	 * ����״̬ģʽ�е�״̬ת��
	 * ���Բ��ԣ�
	 * �������п��ܵ�״̬ת�Ʒ�ʽ
	 */
	@Test
	public void StateMoveTest() {
		State state = StateWaiting.instance;		
		State state1 = StateAllocated.instance;	
		State state2 = StateRunning.instance;	
		State state3 = StateBlocked.instance;
		//WaitingתAllocated��Cancelled
		assertEquals(StateAllocated.instance,state.move("a"));
		assertEquals(StateCancelled.instance,state.move("c"));
		//AllocatedתRunning
		assertEquals(StateRunning.instance,state1.move("r"));
		//RunningתBlocked��Ended
		assertEquals(StateBlocked.instance,state2.move("b"));		
		assertEquals(StateEnded.instance,state2.move("e"));
		//RunningתBlocked��Ended
		assertEquals(StateRunning.instance,state3.move("r"));
	}

}

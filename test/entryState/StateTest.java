package entryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateTest {

	/**
	 * 测试状态模式中的状态转移
	 * 测试策略：
	 * 测试所有可能的状态转移方式
	 */
	@Test
	public void StateMoveTest() {
		State state = StateWaiting.instance;		
		State state1 = StateAllocated.instance;	
		State state2 = StateRunning.instance;	
		State state3 = StateBlocked.instance;
		//Waiting转Allocated和Cancelled
		assertEquals(StateAllocated.instance,state.move("a"));
		assertEquals(StateCancelled.instance,state.move("c"));
		//Allocated转Running
		assertEquals(StateRunning.instance,state1.move("r"));
		//Running转Blocked和Ended
		assertEquals(StateBlocked.instance,state2.move("b"));		
		assertEquals(StateEnded.instance,state2.move("e"));
		//Running转Blocked和Ended
		assertEquals(StateRunning.instance,state3.move("r"));
	}

}

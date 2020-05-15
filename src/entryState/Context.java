package entryState;

public class Context {

	State state;//保存对象的状态
	
	/**
	 * 设置初始状态
	 * @param s 初始状态
	 */
	public Context(State s) {
		state = s;
	}
	
	/**
	 * 接收外部输入，开始转换状态
	 * @param c 外部输入参数，据此转换状态
	 */
	public void move(String c) {
		state = state.move(c);
	}
	
	/**
	 * 
	 * @return 当前状态
	 */
	public State getState() {
		return this.state;
	}
}

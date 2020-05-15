package entryState;

public class Context {

	State state;//��������״̬
	
	/**
	 * ���ó�ʼ״̬
	 * @param s ��ʼ״̬
	 */
	public Context(State s) {
		state = s;
	}
	
	/**
	 * �����ⲿ���룬��ʼת��״̬
	 * @param c �ⲿ����������ݴ�ת��״̬
	 */
	public void move(String c) {
		state = state.move(c);
	}
	
	/**
	 * 
	 * @return ��ǰ״̬
	 */
	public State getState() {
		return this.state;
	}
}

package entryState;

public interface State {

	/**
	 * 根据参数转移状态
	 * @param c 参数 
	 * @return 新状态
	 */
	State move(String c);
	
}

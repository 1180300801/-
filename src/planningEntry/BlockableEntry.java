package planningEntry;

public interface BlockableEntry {

	/**
	 * 任务阻塞时，存放阻塞开始的时间
	 * @param time 阻塞开始的时间
	 */
	boolean block(String time);
}

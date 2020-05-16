package planningEntry;

import java.util.ArrayList;
import java.util.List;

public class BlockableEntryImpl implements BlockableEntry {

	private List<String> time = new ArrayList<String>();//存放暂停时间对
	private int blockNum = 0;
	//AF:一个包含暂停时间对的集合
	//RI:true
	//Safety from rep exposure:所有属性均为私有
	
	/**
	 * 返回阻塞时间集合
	 * @return 阻塞时间集合
	 */
	public List<String> getTime() {
		return time;
	}
		
	@Override
	public boolean block(String time) {
		// TODO Auto-generated method stub
		this.time.add(time);
		blockNum++;
		return true;
	}

	public int getBlockNum() {
		return blockNum;
	}

}

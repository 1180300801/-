package planningEntry;

import java.util.ArrayList;
import java.util.List;

public class BlockableEntryImpl implements BlockableEntry {

	private List<String> time = new ArrayList<String>();//�����ͣʱ���
	private int blockNum = 0;
	//AF:һ��������ͣʱ��Եļ���
	//RI:true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * ��������ʱ�伯��
	 * @return ����ʱ�伯��
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

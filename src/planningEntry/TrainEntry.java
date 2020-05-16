package planningEntry;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import location.Location;
import resource.Carriage;
import resource.Resource;
import timeslot.Timeslot;

public class TrainEntry extends CommonPlanningEntry implements TrainplanningEntry {

	private String trainNumber;//�ƻ�������
	private MultipleLocationEntryImpl mle;//���ö��λ��
	private MultipleSortedResourceEntryImpl<Carriage> msre;//���ö����Դ
	private BlockableEntryImpl be;//��ͣ
	private int blockNum;//��¼block���������ܳ����м�վ����
	//AF:һ��ӵ���г��������ƣ���ֹʱ�䣬��ֹ���м�վ����ʻ�г��ĸ����ƻ�
	//RI:true
	//Safety from rep exposure:�������Ծ�Ϊ˽��
	
	/**
	 * ������
	 * @param trainNumber �г���
	 * @param startAndEndTime ��ֹʱ���
	 * @param mle ���λ��������
	 * @param msre �����Դ������
	 * @param be ������
	 */
	public TrainEntry(String trainNumber,Timeslot startAndEndTime,MultipleLocationEntryImpl mle,MultipleSortedResourceEntryImpl<Carriage> msre,BlockableEntryImpl be) {
		super(startAndEndTime);
		this.trainNumber = trainNumber;
		this.mle = mle;
		this.msre = msre;
		this.be = be;
		blockNum = 0;
		if(this.msre != null)
			setCurrentState("a");
	}
	
	@Override
	public void setLocations(List<? extends Location> locations) {
		// TODO Auto-generated method stub
		mle.setLocations(locations);
	}

	@Override
	public void setResources(List<Carriage> resources) {
		// TODO Auto-generated method stub
		msre.setResources(resources);
	}

	/**
	 * ������Դ
	 */
	public void addResources(Carriage carriage) {
		msre.addResources(carriage);
	}
	
	@Override
	public boolean block(String timeslot) {
		// TODO Auto-generated method stub
		if(blockNum<(mle.getLocations().size()-2)) {
			blockNum++;
			be.block(timeslot);
			return setCurrentState("b");
		}
		else
			JOptionPane.showMessageDialog(null, "�м�վ�����꣬��������;ֹͣ��"+(msre.getResources().size()-2));
		return false;
	}
	
	@Override
	public boolean Cancell() {
		if(msre.getResources() != null) {
			JOptionPane.showMessageDialog(null, "�����ѷ��䣬����ȡ����");
			return false;
		}
		else {
			super.Cancell();
			return true;
		}
	}
	
	/**
	 * 
	 * @return ���λ��������
	 */
	public MultipleLocationEntryImpl getMle() {
		return mle;
	}
	
	/**
	 * 
	 * @return �����Դ������
	 */
	public MultipleSortedResourceEntryImpl<Carriage> getMsre() {
		return msre;
	}
	
	public BlockableEntryImpl getBe() {
		return be;
	}

	/**
	 * 
	 * @return ������
	 */
	public String getTrainNumber() {
		return trainNumber;
	}
	
	@Override
	public List<Resource> getResource(){
		List<Resource> resources = new ArrayList<Resource>();
		for(Carriage carriage:msre.getResources()) {
			resources.add(carriage);
		}
		return resources;
	}
	
	/**
	 * ��ȡ�ƻ������Դ
	 * @return �ƻ������Դ
	 */
	public List<Carriage> getCarriages(){
		return msre.getResources();
	}
	
	@Override
	public String getEntryName() {
		return trainNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		//���Ч��
		if(this == obj)
			return true;
		//��߽�׳�ԣ��ж�obj�Ƿ�Ϊnull�����Ƿ���Teacher���һ������
		if(obj == null|!(obj instanceof TrainEntry))
			return false;
		//����ǰ�����ж�û�еó���������˽�objת��ΪFlight��Ȼ��Ƚ����ǵ�Ψһ��ʶ�Ƿ����
		TrainEntry trainEntry = (TrainEntry)obj;
		if(this.trainNumber.equals(trainEntry.trainNumber)&this.getStartAndEndTime().getStartTime().equals(trainEntry.getStartAndEndTime().getStartTime()))
			return true;
		return false;		
	}
	
	@Override
	public int hashCode() {
		//��String�������Ѿ���д��hashCode����������ֱ�ӵ��þͿ�����
		return this.trainNumber.hashCode();
	}
}

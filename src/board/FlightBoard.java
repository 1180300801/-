package board;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import planningEntry.FlightEntry;
import planningEntryCollection.FlightCollection;
import timeslot.Timeslot;

import javax.swing.JScrollPane;

/**
 * @author Administrator
 *��Ϣ�壬����ʵʱ������λ���Ϲ�ȥ�������ĺͺ���
 *���������ļƻ���
 */
public class FlightBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	//AF:��ʾһ��չʾĳ������������Ϣ�����Ϣ��
    //RI:true
    //Safety from rep exposure:�������Ծ�Ϊ˽��
	/**
	 * Launch the application.
	 */
	public void show(FlightBoard frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlightBoard(String S,FlightCollection fc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, -17, 440, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		   
		JLabel timeLabel = getTimelabel(S);
		timeLabel.setBounds(6, 17, 434, 15);
		panel.add(timeLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u62B5\u8FBE\u822A\u73ED",JLabel.CENTER);
		lblNewLabel_1.setBounds(6, 42, 434, 15);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 47, 430, 98);
		contentPane.add(scrollPane);
		//չʾ�ִ�˵صĺ�����Ϣ
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			getMessage(S,fc),
			new String[] {
				"\u9884\u8BA1\u62B5\u8FBE\u65F6\u95F4", "\u7F16\u53F7", "\u8D77\u70B9-\u7EC8\u70B9", "\u72B6\u6001"
			}
		));
		
		JLabel lblNewLabel_2 = new JLabel("\u51FA\u53D1\u822A\u73ED",JLabel.CENTER);
		lblNewLabel_2.setBounds(0, 147, 434, 15);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 165, 434, 90);
		contentPane.add(scrollPane_1);
		//չʾ�Ӵ˵���ɵĺ�����Ϣ
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			getMessage1(S,fc),
			new String[] {
				"\u9884\u8BA1\u8D77\u98DE\u65F6\u95F4", "\u7F16\u53F7", "\u8D77\u70B9-\u7EC8\u70B9", "\u72B6\u6001"
			}
		));
	}
	
	/**
	 * ʱ��ɱ��label������label����ʾ��������ϵͳʱ��仯
	 * @param S ����
	 * @return JLabel
	 */
	private JLabel getTimelabel(String S) {
		JLabel timeLabel = new JLabel("",JLabel.CENTER);
		Timer time = new Timer(1000,new ActionListener() {  
			@Override  
			public void actionPerformed(ActionEvent arg0) {  
			timeLabel.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"(��ǰʱ��),"+S);			
			}  
		});  
		time.start();
		return timeLabel;
	}
	
	/**
	 * �Ӽƻ���л�ȡ�ִﺽ�����Ϣ
	 * @param S ����
	 * @param fc �ƻ��
	 * @return ��Ϣ������
	 */
	private String[][] getMessage(String S,FlightCollection fc){
		//��Ϣ���ʼ��С�趨Ϊ10�У�һ������������ֵ����������
		int num = 10;
		String[][] s = new String[num][4];
		int i = 0;
		for(FlightEntry fe:fc) {
			if(fe.getTe().getStart().getLocationName().equals(S)) {
				String str = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
				Timeslot timeslot = new Timeslot(str,fe.getStartAndEndTime().getEndTime());
				if(timeslot.timeSub() == 0) {
					if(timeslot.timeSubHourAndMin()>=-60|timeslot.timeSubHourAndMin()<=60) {
						s[i][0] = fe.getStartAndEndTime().getStartTime();
						s[i][1] = fe.getSe().getResource().getNumbering();
						s[i][2] = fe.getTe().getStart().getLocationName()+"-"+fe.getTe().getEnd().getLocationName();
						s[i][3] = fe.getState().toString();
						i++;
						if(i == num) {
							num *= 2;
							String[][] dest = s;
							s = new String[num][4];
							for(int j = 0;j<num/2;j++) {
								s[j][0] = dest[j][0];
								s[j][1] = dest[j][1];
								s[j][2] = dest[j][2];
								s[j][3] = dest[j][3];
							}
						}
					}
				}				
			}
		}
		return s;
	}
	
	/**
	 * �Ӽƻ���л�ȡ�����������Ϣ
	 * @param S ����
	 * @param fc �ƻ��
	 * @return ��Ϣ������
	 */
	private String[][] getMessage1(String S,FlightCollection fc){
		//��Ϣ���ʼ��С�趨Ϊ10�У�һ������������ֵ����������
		int num = 10;
		String[][] s = new String[num][4];
		int i = 0;
		for(FlightEntry fe:fc) {
			if(fe.getTe().getEnd().getLocationName().equals(S)) {
				String str = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
				Timeslot timeslot = new Timeslot(str,fe.getStartAndEndTime().getStartTime());
				if(timeslot.timeSub() == 0) {
					if(timeslot.timeSubHourAndMin()>=-60|timeslot.timeSubHourAndMin()<=60) {
						s[i][0] = fe.getStartAndEndTime().getStartTime();
						s[i][1] = fe.getSe().getResource().getNumbering();
						s[i][2] = fe.getTe().getStart().getLocationName()+"-"+fe.getTe().getEnd().getLocationName();
						s[i][3] = fe.getState().toString();
						i++;
						if(i == 10)
							num *= 2;
					}
				}
			}
		}
		return s;
	}
}

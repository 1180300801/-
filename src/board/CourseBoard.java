package board;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import planningEntry.CourseEntry;
import planningEntryCollection.CourseCollection;
import timeslot.Timeslot;

/**
 * @author Administrator
 *��Ϣ�壬����ʵʱ������λ���Ϲ�ȥ�������ĺͺ���
 *���������ļƻ���
 */
public class CourseBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	//AF:��ʾһ��չʾĳ�����ҿγ̱����Ϣ��
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
	public CourseBoard(String S,CourseCollection fc) {
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
		
		JLabel lblNewLabel_1 = new JLabel("\u6559\u5BA4",JLabel.CENTER);
		lblNewLabel_1.setBounds(6, 42, 434, 15);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 47, 430, 208);
		contentPane.add(scrollPane);
		//չʾĳ���ҿγ̱�
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			getMessage(S,fc),
			new String[] {
				"\u4E0A\u8BFE\u8BA1\u65F6\u95F4", "\u8BFE\u7A0B", "\u6559\u5E08", "\u72B6\u6001"
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
	 * �Ӽƻ���л�ȡ��Ϣ��Ҫչʾ����Ϣ
	 * @param S ����
	 * @param fc �ƻ��
	 * @return ��Ϣ������
	 */
	private String[][] getMessage(String S,CourseCollection fc){
		//��Ϣ���ʼ��С�趨Ϊ10�У�һ������������ֵ����������
		int num = 10;
		String[][] s = new String[num][4];
		int i = 0;
		for(CourseEntry ce:fc) {
			if(ce.getSe().getLocation().getLocationName().equals(S)) {
				String str = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
				Timeslot timeslot = new Timeslot(str,ce.getStartAndEndTime().getEndTime());
				if(timeslot.timeSub() == 0) {
					if(timeslot.timeSubHourAndMin()>=-60|timeslot.timeSubHourAndMin()<=60) {
						s[i][0] = ce.getStartAndEndTime().getStartTime();
						s[i][1] = ce.getSsre().getResource().getName();
						s[i][2] = ce.getSe().getLocation().getLocationName();
						s[i][3] = ce.getState().toString();
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
}

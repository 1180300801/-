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
import javax.swing.JScrollPane;

public class FlightBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String S = "";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightBoard frame = new FlightBoard(S);
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
	public FlightBoard(String S) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		   
		getTimelabel(panel,S);
		
		
		JLabel lblNewLabel_1 = new JLabel("\u62B5\u8FBE\u822A\u73ED",JLabel.CENTER);
		lblNewLabel_1.setBounds(6, 42, 434, 15);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 47, 430, 98);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
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
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u9884\u8BA1\u8D77\u98DE\u65F6\u95F4", "\u7F16\u53F7", "\u8D77\u70B9-\u7EC8\u70B9", "\u72B6\u6001"
			}
		));
	}
	
	private void getTimelabel(JPanel panel,String S) {
		JLabel timeLabel = new JLabel("",JLabel.CENTER);
		timeLabel.setBounds(6, 17, 434, 15);
		panel.add(timeLabel);
		Timer time = new Timer(1000,new ActionListener() {  
			@Override  
			public void actionPerformed(ActionEvent arg0) {  
			timeLabel.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"(当前时间),"+S);			
			}  
		});  
		time.start();
	}
}

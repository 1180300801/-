package planningEntryCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ShowCollection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	//AF:展示计划项集的JFrame
	//RI：true
	//Safety from rep exposure:所有属性均为私有

	/**
	 * Launch the application.
	 */
	public void show(Collection co) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowCollection frame = new ShowCollection(co);
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
	public ShowCollection(Collection co) {
		String S;
		//根据输入的计划项集确定第一列名称
		if(co.getClass().equals(FlightCollection.class))
			S = "航班号";
		else if(co.getClass().equals(TrainCollection.class))
			S = "高铁编号";
		else
			S = "教师名称";
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 519, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			co.getMessage(),
			new String[] {
				S, "               \u8D77\u6B62\u65F6\u95F4", "        \u4F4D\u7F6E", "\u72B6\u6001"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setPreferredWidth(255);
		table.getColumnModel().getColumn(2).setPreferredWidth(132);
		
		JLabel lblNewLabel = new JLabel("\u822A\u73ED\u4FE1\u606F\u8868",JLabel.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(0, 0, 434, 53);
		contentPane.add(lblNewLabel);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

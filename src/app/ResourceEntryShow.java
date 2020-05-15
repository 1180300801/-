package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Administrator
 * 展示使用某个资源的计划项
 */
public class ResourceEntryShow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	//AF:表示一个展示使用某个资源的计划项的页面
    //RI:true
    //Safety from rep exposure:所有属性均为私有

	/**
	 * Launch the application.
	 */
	public void show(ResourceEntryShow frame) {
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
	public ResourceEntryShow(String number,String[][] s) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 437, 138);
		contentPane.add(scrollPane);
		//使用某个资源的计划项信息表
		table = new JTable();
		table.setModel(new DefaultTableModel(
			s,
			new String[] {
				"\u540D\u79F0", "                 \u8D77\u6B62\u65F6\u95F4", "\u8D77\u70B9-\u7EC8\u70B9", "\u72B6\u6001"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(274);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel(number,JLabel.CENTER);
		lblNewLabel.setBounds(39, 25, 331, 15);
		contentPane.add(lblNewLabel);

	}
}
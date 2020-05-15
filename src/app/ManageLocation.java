package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import location.Airport;
import location.ClassRoom;
import location.RailwayStation;
import planningEntryCollection.Collection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Administrator
 * ����λ�õ�ҳ��
 */
public class ManageLocation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	//AF:��ʾһ������λ�õ�ҳ��
    //RI:true
    //Safety from rep exposure:�������Ծ�Ϊ˽��

	/**
	 * Launch the application.
	 */
	public void show(ManageLocation frame) {
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
	public ManageLocation(Collection co,int choice) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5730\u540D");
		lblNewLabel.setBounds(35, 50, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(113, 47, 168, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//��ӿ���λ��
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u4F4D\u7F6E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    if(textField.getText().equals(""))
			    	JOptionPane.showMessageDialog(null, "������ʾ��", "������Ϣ����ȷ��",JOptionPane.ERROR_MESSAGE);
			    else {
			    	if(choice == 1)
			    	    co.addLocations(new Airport(textField.getText()));
			    	else if(choice == 2)
			    		co.addLocations(new RailwayStation(textField.getText()));
			    	else if(choice == 3)
			    		co.addLocations(new ClassRoom(textField.getText()));
			    	JOptionPane.showMessageDialog(null, "��ӳɹ���");
			    }
			}
		});
		btnNewButton.setBounds(34, 107, 93, 23);
		contentPane.add(btnNewButton);
		
		//ɾ������λ��
		JButton btnNewButton_1 = new JButton("\u5220\u9664\u4F4D\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    if(textField.getText().equals(""))
			    	JOptionPane.showMessageDialog(null, "������ʾ��", "������Ϣ����ȷ��",JOptionPane.ERROR_MESSAGE);
			    else {
			    	if(choice == 1)
			    	    co.deleteLocations(new Airport(textField.getText()));
			    	else if(choice == 2)
			    	    co.deleteLocations(new RailwayStation(textField.getText()));
			    	else if(choice == 3)
			    	    co.deleteLocations(new ClassRoom(textField.getText()));
			    	JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			    }
			}
		});
		btnNewButton_1.setBounds(188, 107, 93, 23);
		contentPane.add(btnNewButton_1);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}

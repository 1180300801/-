package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import board.CourseBoard;
import location.ClassRoom;
import location.Location;
import planningEntry.CourseEntry;
import planningEntry.PlanningEntry;
import planningEntry.SingleLocationEntryImpl;
import planningEntry.SingleSortedResourceEntryImpl;
import planningEntryAPIs.CheckLocationConflict;
import planningEntryAPIs.CheckResourceExclusiveConflict;
import planningEntryAPIs.FindPreEntryPerResource;
import planningEntryAPIs.LocationConflictAPI;
import planningEntryCollection.CourseCollection;
import planningEntryCollection.ShowCollection;
import resource.Teacher;
import timeslot.Timeslot;

/**
 * @author Administrator
 * CourseCalendarApp，管理课程
 */
public class CourseCalendarApp {
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private static CourseCollection fc;
	private JTextField textField_1;
	private LocationConflictAPI checkLocationConflict = new CheckLocationConflict();
	//AF:表示管理课程app的主页面
    //RI:true
    //Safety from rep exposure:所有属性均为私有

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		setMessage("src/txt/ClassSchedule.txt");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseCalendarApp window = new CourseCalendarApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CourseCalendarApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 474, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(-6, -17, 348, 47);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(335, -18, 123, 201);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, 8, 348, 175);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		lblNewLabel.setBounds(10, 28, 54, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		lblNewLabel_1.setBounds(10, 58, 54, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		lblNewLabel_2.setBounds(10, 88, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u4E0A\u8BFE\u6559\u5BA4");
		lblNewLabel_3.setBounds(10, 118, 54, 15);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(74, 25, 88, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 115, 264, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u8BA1\u5212\u9879\u96C6");
		lblNewLabel_5.setBounds(10, 148, 54, 15);
		panel.add(lblNewLabel_5);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ClassSchedule.txt", "ClassSchedule_1.txt"}));
		comboBox.setBounds(74, 145, 264, 21);
		panel.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021"}));
		comboBox_1.setBounds(74, 55, 54, 21);
		panel.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_2.setBounds(138, 55, 39, 21);
		panel.add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_3.setBounds(187, 55, 39, 21);
		panel.add(comboBox_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_4.setBounds(247, 55, 39, 21);
		panel.add(comboBox_4);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>();
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_5.setBounds(296, 55, 42, 21);
		panel.add(comboBox_5);
		
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021"}));
		comboBox_6.setBounds(74, 85, 54, 21);
		panel.add(comboBox_6);
		
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_7.setBounds(138, 85, 39, 21);
		panel.add(comboBox_7);
		
		JComboBox<String> comboBox_8 = new JComboBox<String>();
		comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_8.setBounds(187, 85, 39, 21);
		panel.add(comboBox_8);
		
		JComboBox<String> comboBox_9 = new JComboBox<String>();
		comboBox_9.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_9.setBounds(247, 85, 39, 21);
		panel.add(comboBox_9);
		
		JComboBox<String> comboBox_10 = new JComboBox<String>();
		comboBox_10.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_10.setBounds(296, 85, 42, 21);
		panel.add(comboBox_10);
		
		JLabel lblNewLabel_6 = new JLabel("\u6559\u5E08\u7F16\u53F7");
		lblNewLabel_6.setBounds(181, 28, 54, 15);
		panel.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(245, 25, 82, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//展示所有计划项
		JButton btnNewButton = new JButton("\u6240\u6709\u8BFE\u7A0B");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ShowCollection flights = new ShowCollection(fc);
				flights.show();
			}
		});
		btnNewButton.setBounds(0, 17, 116, 23);
		panel_1.add(btnNewButton);
		
		//展示某教室在过去1小时和未来1小时的计划项
		JButton btnNewButton_1 = new JButton("\u5F53\u524D\u6559\u5BA4\u8BFE\u8868");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ClassRoom classRoom = new ClassRoom(textField_2.getText());
			    CourseBoard board = new CourseBoard(classRoom.getLocationName(),fc);
				board.show();
			}
		});
		btnNewButton_1.setBounds(116, 17, 116, 23);
		panel_1.add(btnNewButton_1);
		
		//展示使用某资源的所有计划项
		JButton btnNewButton_2 = new JButton("\u5F53\u524D\u6559\u5E08\u8BFE\u7A0B");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField_1.getText();
				 
				int n = 10;
				String[][] s = new String[n][4];
				int i = 0;
				for(CourseEntry ce:fc) {
					if(ce.getSsre().getResource().getIDNumber().equals(number)) {
						s[i][0] = ce.getClassName();
						s[i][1] = ce.getStartAndEndTime().getStartTime()+" to "+ce.getStartAndEndTime().getEndTime();;
						s[i][2] = ce.getSe().getLocation().getLocationName();
						s[i][3] = ce.getState().toString();
						i++;
						if(i == n) {
							n *= 2;
							String[][] dest = s;
							s = new String[n][4];
							for(int j = 0;j<n/2;j++) {
								s[j][0] = dest[j][0];
								s[j][1] = dest[j][1];
								s[j][2] = dest[j][2];
								s[j][3] = dest[j][3];
							}
						}
					}
				}
				ResourceEntryShow rfes = new ResourceEntryShow(number,s);
				rfes.show(rfes);
			}
		});
		btnNewButton_2.setBounds(232, 17, 116, 23);
		panel_1.add(btnNewButton_2);
		
		//查看输入课程的状态
		JButton btnNewButton_3 = new JButton("\u5F53\u524D\u8BFE\u7A0B\u72B6\u6001");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				 for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		JOptionPane.showMessageDialog(null, courseEntry.getState());
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该航班不存在！");
			}
		});
		btnNewButton_3.setBounds(6, 17, 116, 25);
		panel_2.add(btnNewButton_3);
		
		//新增课程，资源项只需要输入编号（唯一标识），若该资源不在可用资源中，请添加后再试
		JButton btnNewButton_4 = new JButton("\u65B0\u589E\u8BFE\u7A0B");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String number = textField_1.getText();
				ClassRoom classRoom = new ClassRoom(textField_2.getText());
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				String endTime = comboBox_6.getSelectedItem().toString()+"-"+comboBox_7.getSelectedItem().toString()+"-"+comboBox_8.getSelectedItem().toString()+" "+comboBox_9.getSelectedItem().toString()+":"+comboBox_10.getSelectedItem().toString();
			    
				int flag = 0;				
				Teacher teacher = null;
				Set<Teacher> teachers = fc.getResources();
				xunhuan:
				for(Teacher teacher1:teachers) {
					if(teacher1.getIDNumber().equals(number)) {
						teacher = teacher1;
						break xunhuan;
					}									
				}
				SingleSortedResourceEntryImpl<Teacher> se = new SingleSortedResourceEntryImpl<Teacher>();
				if(teacher != null)
				    se.setResource(teacher);
				else {
					JOptionPane.showMessageDialog(null, "此教师不存在，请添加后再试！");
					flag = 1;
				}					
				
				Timeslot timeslot = new Timeslot(startTime,endTime);
			    if(timeslot.timeSub()>1) {
			    	JOptionPane.showMessageDialog(null, "时间差过大！");
			    	flag = 1;
			    }

			    
			    SingleLocationEntryImpl sle = new SingleLocationEntryImpl();
			    sle.setLocations(classRoom);
			    Set<Location> classRooms = fc.getLocations();
			    if(!classRooms.contains(classRoom)) {
			    	JOptionPane.showMessageDialog(null, "可用位置不存在，请添加后再试！");
			    	flag = 1;
			    }
			    
			    if(flag == 0)
			        fc.addEntry(new CourseEntry(course,timeslot,sle,se));
			}
		});
		btnNewButton_4.setBounds(6, 42, 116, 25);
		panel_2.add(btnNewButton_4);
		
		//为某计划项分配资源
		JButton btnNewButton_5 = new JButton("\u5206\u914D\u8D44\u6E90");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String number = textField_1.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				Teacher tc = null;
				Set<Teacher> teachers = fc.getResources();
				xunhuan:
				for(Teacher teacher:teachers) {
					if(teacher.getIDNumber().equals(number)) {
						tc = teacher;
						break xunhuan;
					}									
				}
				if(tc == null)
					JOptionPane.showMessageDialog(null, "此教师不存在，请添加后再试！");
				
				for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		if(courseEntry.getState().equals("Waiting")) {
			    			courseEntry.setResource(tc);
			    			JOptionPane.showMessageDialog(null, "成功！");
			    			flag = 1;
			    		}			    			
			    	}
			    }
				if(flag == 0)
					JOptionPane.showMessageDialog(null, "已分配资源！");
			}
		});
		btnNewButton_5.setBounds(6, 67, 116, 25);
		panel_2.add(btnNewButton_5);
		
		//启动某一计划项
		JButton btnNewButton_6 = new JButton("\u542F\u52A8\u5F53\u524D\u8BFE\u7A0B");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(courseEntry.Start())
			    		    JOptionPane.showMessageDialog(null, "启动成功，当前状态："+courseEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该课程不存在！");
			}
		});
		btnNewButton_6.setBounds(6, 92, 116, 25);
		panel_2.add(btnNewButton_6);
		
		//结束某一计划项
		JButton btnNewButton_7 = new JButton("\u7ED3\u675F\u5F53\u524D\u8BFE\u7A0B");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				 
				int flag = 0;
				for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(courseEntry.End())
			    		    JOptionPane.showMessageDialog(null, "结束课程成功，当前状态："+courseEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该课程不存在！");
			}
		});
		btnNewButton_7.setBounds(6, 117, 116, 25);
		panel_2.add(btnNewButton_7);
		
		//改变计划项集合
		JButton btnNewButton_8 = new JButton("\u66F4\u6539\u8BFE\u8868");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileChoose = comboBox.getSelectedItem().toString();
				
				String str = "src/txt/"+fileChoose;
				try {
					setMessage(str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_8.setBounds(6, 167, 116, 25);
		panel_2.add(btnNewButton_8);
		
		//取消某一计划项
		JButton btnNewButton_9 = new JButton("\u53D6\u6D88\u5F53\u524D\u8BFE\u7A0B");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(courseEntry.Cancell())
			    		    JOptionPane.showMessageDialog(null, "结束课程成功，当前状态："+courseEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该课程不存在！");
			}
		});
		btnNewButton_9.setBounds(6, 142, 116, 25);
		panel_2.add(btnNewButton_9);
		
		//管理资源
		JButton btnNewButton_10 = new JButton("\u7BA1\u7406\u8D44\u6E90");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageCourse manageCourse = new ManageCourse(fc);
				manageCourse.show(manageCourse);				
			}
		});
		btnNewButton_10.setBounds(222, 193, 103, 23);
		frame.getContentPane().add(btnNewButton_10);
		
		//管理位置
		JButton btnNewButton_11 = new JButton("\u7BA1\u7406\u4F4D\u7F6E");
		btnNewButton_11.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ManageLocation addLocation = new ManageLocation(fc,1);
				addLocation.show();
			}
		});
		btnNewButton_11.setBounds(335, 193, 117, 23);
		frame.getContentPane().add(btnNewButton_11);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 222, 458, 80);
		frame.getContentPane().add(scrollPane);
		
		//备注
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.RED);
		scrollPane.setViewportView(textArea);
		textArea.setText(" \u6CE8\uFF1A1.\u201C\u6240\u6709\u8BFE\u7A0B\u201D\uFF1A\u67E5\u770B\u5F53\u524D\u8BA1\u5212\u96C6\u4E2D\u7684\u6240\u6709\u8BFE\u7A0B\u3002\r\n2.\u201C\u5F53\u6559\u5E08\u8BFE\u7A0B\u201D\uFF1A\u53EA\u9700\u5728\u4E0A\u8BFE\u6559\u5BA4\u5904\u8F93\u5165\u4F4D\u7F6E\uFF0C\u5C31\u53EF\u67E5\u770B\u8BE5\u6559\u5BA4\u7684\u8BFE\u7A0B\u8868\u3002\r\n3.\u201C\u5F53\u524D\u6559\u5E08\u8BFE\u7A0B\u201D\uFF1A\u8F93\u5165\u5BF9\u5E94\u7684\u6559\u5E08\u7F16\u53F7\uFF0C\u5373\u53EF\u67E5\u770B\u8BE5\u6559\u5E08\u5F53\u524D\u662F\u5426\u5728\u4E0A\u8BFE\u3002\u5DF2\u6709\u76849\u4E2A\u6559\u5E08\u7684\u7F16\u53F7\u4E3A001-009\u3002\r\n4.\u201C\u5F53\u524D\u8BFE\u7A0B\u72B6\u6001\u201D\uFF1A\u8F93\u5165\u8BFE\u7A0B\u540D\u79F0\u548C\u5F00\u59CB\u65F6\u95F4\u5373\u53EF\u67E5\u770B\u8BE5\u8BFE\u7A0B\u5F53\u524D\u72B6\u6001\u3002\r\n5.\u201C\u65B0\u589E\u8BFE\u7A0B\u201D\uFF1A\u9664\u8BA1\u5212\u9879\u96C6\u4E0D\u9700\u8981\u8F93\u5165\uFF0C\u5176\u5B83\u90FD\u5F97\u8F93\u5165\u3002\r\n6.\u201C\u5206\u914D\u8D44\u6E90\u201D\uFF1A\u8F93\u5165\u8BFE\u7A0B\u540D\u79F0\uFF0C\u6559\u5E08\u7F16\u53F7\uFF0C\u5F00\u59CB\u65F6\u95F4\u5373\u53EF\u4E3A\u8BE5\u8BFE\u7A0B\u5206\u914D\u5BF9\u5E94\u7F16\u53F7\u5230\u6559\u5E08\u3002\r\n7.\u201C\u542F\u52A8\u5F53\u524D\u8BFE\u7A0B\u201D\uFF1A\u8F93\u5165\u8BFE\u7A0B\u540D\u79F0\u548C\u5F00\u59CB\u65F6\u95F4\uFF0C\u70B9\u51FB\u5373\u53EF\u542F\u52A8\u3002\r\n8.\u201C\u7ED3\u675F\u5F53\u524D\u822A\u73ED\u201D\uFF1A\u7C7B\u6BD47\u3002\r\n9.\u201C\u67E5\u770B\u51B2\u7A81\u201D\uFF1A\r\n10.\u201C\u66F4\u6539\u8BA1\u5212\u9879\u96C6\u201D\uFF1A\u5728\u5DE6\u4FA7\u9009\u62E9\u4E00\u4E2A\u8BA1\u5212\u9879\u96C6\uFF08\u6587\u4EF6\uFF09\uFF0C\u70B9\u51FB\u5373\u53EF\u3002\r\n11.\u201C\u68C0\u67E5\u77DB\u76FE\u201D\uFF1A\u67E5\u770B\u662F\u5426\u5B58\u5728\u8D44\u6E90\u62A2\u5360\u77DB\u76FE\u3002\r\n12.\u201C\u524D\u5E8F\u8BA1\u5212\u9879\u201D\uFF1A\u8F93\u5165\u8BFE\u7A0B\u540D\u79F0\uFF0C\u6559\u5E08\u7F16\u53F7\uFF0C\u5F00\u59CB\u65F6\u95F4\uFF0C\u4EE5\u6B64\u641C\u7D22\u8BA1\u5212\u9879\u96C6\u5408\u4E2D\u4E0E\u8F93\u5165\u8BFE\u7A0B\u4F7F\u7528\u540C\u4E00\u8D44\u6E90\u4E14\u65F6\u95F4\u5728\u524D\u9762\u7684\u8BA1\u5212\u9879\u3002\r\n13.\u201C\u7BA1\u7406\u8D44\u6E90\u201D\uFF1A\u53EF\u589E\u52A0\uFF08\u5220\u9664\u3001\u4FEE\u6539\uFF09\u5F53\u524D\u8BA1\u5212\u9879\u96C6\u4E2D\u7684\u8D44\u6E90\u3002\r\n14.\u201C\u7BA1\u7406\u4F4D\u7F6E\u201D\uFF1A\u540C12.");
		
		//检查矛盾
		JButton btnNewButton_12 = new JButton("\u68C0\u67E5\u77DB\u76FE");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<PlanningEntry> pe = new ArrayList<PlanningEntry>();
				for(CourseEntry ce:fc.getCourseEntry()) {
					pe.add(ce);
				}
				//strategy模式
				checkLocationConflict.checkLocationConflict(pe);
				
				CheckResourceExclusiveConflict crec = new CheckResourceExclusiveConflict();
				crec.checkResourceExclusiveConflict(pe);
			}
		});
		btnNewButton_12.setBounds(4, 193, 93, 23);
		frame.getContentPane().add(btnNewButton_12);
		
		//获取前序计划
		JButton btnNewButton_13 = new JButton("\u524D\u5E8F\u8BA1\u5212\u9879");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				int flag = 0;
				for(CourseEntry courseEntry:fc) {
			    	if(courseEntry.getClassName().equals(course)&&courseEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		FindPreEntryPerResource fpepr = new FindPreEntryPerResource();
			    		int size = fc.getCourseEntry().size();
			    		List<PlanningEntry> pe = new ArrayList<PlanningEntry>();
			    		for(int i = 0;i<size;i++) {
			    			pe.add(fc.getCourseEntry().get(i));
			    		}
			    		CourseEntry ce = (CourseEntry)fpepr.findPreEntryPerResource(textField_1.getText(),courseEntry,pe);
			    		if(ce != null)
			    		    JOptionPane.showMessageDialog(null, "课程："+ce.getClassName()+"\n起止时间："+ce.getStartAndEndTime().getStartTime()+"-"+ce.getStartAndEndTime().getEndTime()+"\n教室："+ce.getSe().getLocation().getLocationName()+"\n教师编号："+ce.getSsre().getResource().getIDNumber()+"\n状态："+ce.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "不存在前序计划！");			    			
			    	}
			    }
				if(flag == 0)
					JOptionPane.showMessageDialog(null, "输入的计划项不存在！");	
			}
		});
		btnNewButton_13.setBounds(118, 193, 93, 23);
		frame.getContentPane().add(btnNewButton_13);
	}
	
	//读取给定文件，获取计划项集合
	private static void setMessage(String fileName) throws IOException {
		fc = new CourseCollection(fileName);
		JOptionPane.showMessageDialog(null, "读取文件"+fileName+"成功！");
	}
}

/**
 * 
 * @author Administrator
 * 管理资源页面
 */
@SuppressWarnings("serial")
class ManageCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	//AF:表示管理航班资源的页面
    //RI:true
    //Safety from rep exposure:所有属性均为私有

	/**
	 * Launch the application.
	 */
	public void show(ManageCourse frame) {
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
	public ManageCourse(CourseCollection fc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F66\u53A2\u7F16\u53F7");
		lblNewLabel.setBounds(35, 40, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" \u7C7B  \u578B ");
		lblNewLabel_1.setBounds(35, 80, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u51FA\u5382\u5E74\u4EFD");
		lblNewLabel_2.setBounds(35, 166, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5B9A \u5458 \u6570");
		lblNewLabel_3.setBounds(35, 124, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(116, 37, 199, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 77, 199, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 121, 199, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(116, 163, 199, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		//添加资源
		JButton btnNewButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = "(Teacher:)(\\\\d{3})\\n\\\\{\\n(Name:)([\\u4e00-\\u9fa5]+)\\n(Sex:)([\\u4e00-\\u9fa5])\\n(Title:)([\\u4e00-\\u9fa5]+)";
				Pattern pattern = Pattern.compile(str);
				String p = "Teacher:"+textField.getText()+"Name:"+textField_1.getText()+"Sex:"+textField_2.getText()+"Title:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					Teacher teacher = new Teacher(textField.getText(),textField_1.getText(),textField_2.getText(),textField_2.getText());
				    fc.addResources(teacher);
				    JOptionPane.showMessageDialog(null, "添加成功！");
				}
				else
					JOptionPane.showMessageDialog(null, "错误提示框", "输入信息不正确！",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton.setBounds(311, 214, 93, 23);
		contentPane.add(btnNewButton);
		
		//修改资源
		JButton btnNewButton_1 = new JButton("\u786E\u8BA4\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "(Teacher:)(\\\\d{3})\\n\\\\{\\n(Name:)([\\u4e00-\\u9fa5]+)\\n(Sex:)([\\u4e00-\\u9fa5])\\n(Title:)([\\u4e00-\\u9fa5]+)";
				Pattern pattern = Pattern.compile(str);
				String p = "Teacher:"+textField.getText()+"Name:"+textField_1.getText()+"Sex:"+textField_2.getText()+"Title:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					String idNumber = textField.getText();
					Teacher teacher = null;
					Set<Teacher> teachers = fc.getResources();
					xunhuan:
					for(Teacher f:teachers) {
						if(f.getIDNumber().equals(idNumber)) {
							teacher = f;
							break xunhuan;
						}									
					}
					if(teacher == null)
						JOptionPane.showMessageDialog(null, "该教师不存在，请添加后再试！");
					fc.deleteResources(teacher);
					
					Teacher tc = new Teacher(textField.getText(),textField_1.getText(),textField_2.getText(),textField_2.getText());
				    fc.addResources(tc);
				    JOptionPane.showMessageDialog(null, "修改成功！");
				}
				else
					JOptionPane.showMessageDialog(null, "错误提示框", "输入信息不正确！",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(10, 214, 93, 23);
		contentPane.add(btnNewButton_1);
		
		//删除资源
		JButton btnNewButton_2 = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "(Teacher:)(\\\\d{3})\\n\\\\{\\n(Name:)([\\u4e00-\\u9fa5]+)\\n(Sex:)([\\u4e00-\\u9fa5])\\n(Title:)([\\u4e00-\\u9fa5]+)";
				Pattern pattern = Pattern.compile(str);
				String p = "Teacher:"+textField.getText()+"Name:"+textField_1.getText()+"Sex:"+textField_2.getText()+"Title:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					Teacher tc = new Teacher(textField.getText(),textField_1.getText(),textField_2.getText(),textField_2.getText());
				    fc.deleteResources(tc);
				    JOptionPane.showMessageDialog(null, "删除成功！");
				}
				else
					JOptionPane.showMessageDialog(null, "错误提示框", "输入信息不正确！",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_2.setBounds(156, 214, 93, 23);
		contentPane.add(btnNewButton_2);
		
	}

}

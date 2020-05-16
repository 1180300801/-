package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import board.TrainBoard;
import location.Location;
import location.RailwayStation;
import planningEntry.BlockableEntryImpl;
import planningEntry.MultipleLocationEntryImpl;
import planningEntry.MultipleSortedResourceEntryImpl;
import planningEntry.PlanningEntry;
import planningEntry.TrainEntry;
import planningEntryAPIs.PlanningEntryAPIs;
import planningEntryCollection.ShowCollection;
import planningEntryCollection.TrainCollection;
import resource.Carriage;
import resource.Resource;
import timeslot.Timeslot;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Administrator
 * TrainScheduleApp，管理高铁计划
 */
public class TrainScheduleApp {

	private JFrame frmTrainscheduleapp;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private static TrainCollection fc;
	private JTextField textField_1;
	private JTextField textField_4;
	//AF:表示管理高铁计划app的主页面
    //RI:true
    //Safety from rep exposure:所有属性均为私有

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		setMessage("src/txt/TrainSchedule_3.txt");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainScheduleApp window = new TrainScheduleApp();
					window.frmTrainscheduleapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrainScheduleApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrainscheduleapp = new JFrame();
		frmTrainscheduleapp.setTitle("TrainScheduleApp");
		frmTrainscheduleapp.setBounds(100, 100, 474, 362);
		frmTrainscheduleapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrainscheduleapp.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(-6, -17, 348, 47);
		frmTrainscheduleapp.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(340, -18, 126, 227);
		frmTrainscheduleapp.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, 8, 348, 201);
		frmTrainscheduleapp.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  \u5217\u8F66\u53F7");
		lblNewLabel.setBounds(10, 23, 54, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u51FA\u53D1\u65F6\u95F4");
		lblNewLabel_1.setBounds(10, 48, 54, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u62B5\u8FBE\u65F6\u95F4");
		lblNewLabel_2.setBounds(10, 73, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8D77 \u59CB \u7AD9");
		lblNewLabel_3.setBounds(10, 98, 54, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u7EC8 \u70B9 \u7AD9");
		lblNewLabel_4.setBounds(10, 148, 54, 15);
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(74, 20, 88, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 95, 264, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(74, 145, 264, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u8BA1\u5212\u9879\u96C6");
		lblNewLabel_5.setBounds(10, 172, 54, 15);
		panel.add(lblNewLabel_5);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TrainSchedule_1.txt", "TrainSchedule_2.txt", "TrainSchedule_3.txt", "TrainSchedule_4.txt", "TrainSchedule_5.txt"}));
		comboBox.setBounds(74, 170, 264, 21);
		panel.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021"}));
		comboBox_1.setBounds(74, 45, 54, 21);
		panel.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_2.setBounds(138, 45, 39, 21);
		panel.add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_3.setBounds(187, 45, 39, 21);
		panel.add(comboBox_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_4.setBounds(247, 45, 39, 21);
		panel.add(comboBox_4);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>();
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_5.setBounds(296, 45, 42, 21);
		panel.add(comboBox_5);
		
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021"}));
		comboBox_6.setBounds(74, 70, 54, 21);
		panel.add(comboBox_6);
		
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_7.setBounds(138, 70, 39, 21);
		panel.add(comboBox_7);
		
		JComboBox<String> comboBox_8 = new JComboBox<String>();
		comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_8.setBounds(187, 70, 39, 21);
		panel.add(comboBox_8);
		
		JComboBox<String> comboBox_9 = new JComboBox<String>();
		comboBox_9.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_9.setBounds(247, 70, 39, 21);
		panel.add(comboBox_9);
		
		JComboBox<String> comboBox_10 = new JComboBox<String>();
		comboBox_10.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_10.setBounds(296, 70, 42, 21);
		panel.add(comboBox_10);
		
		JLabel lblNewLabel_6 = new JLabel("\u8F66\u53A2\u7F16\u53F7");
		lblNewLabel_6.setBounds(181, 23, 54, 15);
		panel.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(245, 20, 82, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u4E2D\u95F4\u7AD9");
		lblNewLabel_7.setBounds(10, 126, 54, 15);
		panel.add(lblNewLabel_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(74, 120, 264, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		//展示所有计划项
		JButton btnNewButton = new JButton("\u6240\u6709\u5217\u8F66\u8BA1\u5212");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ShowCollection trains = new ShowCollection(fc);
				trains.show();
			}
		});
		btnNewButton.setBounds(0, 17, 116, 23);
		panel_1.add(btnNewButton);
		
		//展示某高铁站在过去1小时和未来1小时的计划项
		JButton btnNewButton_1 = new JButton("\u5F53\u524D\u4F4D\u7F6E\u5217\u8F66");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				RailwayStation startStation = new RailwayStation(textField_2.getText());
			    TrainBoard board = new TrainBoard(startStation.getLocationName(),fc);
				board.show();
			}
		});
		btnNewButton_1.setBounds(116, 17, 116, 23);
		panel_1.add(btnNewButton_1);
		
		//展示使用某资源的所有计划项
		JButton btnNewButton_2 = new JButton("\u5F53\u524D\u8D44\u6E90\u5217\u8F66");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField_1.getText();
				 
				int n = 10;
				String[][] s = new String[n][4];
				int i = 0;
				for(TrainEntry fe:fc) {
					for(Carriage carriage:fe.getCarriages()) {
						if(carriage.getNumbering().equals(number)) {
							s[i][0] = fe.getEntryName();
							s[i][1] = fe.getStartAndEndTime().getStartTime()+" to "+fe.getStartAndEndTime().getEndTime();;
							s[i][2] = fe.getMle().getLocations().get(0).getLocationName()+"-"+fe.getMle().getLocations().get(fe.getMle().getLocations().size()-1).getLocationName();
							s[i][3] = fe.getState().toString();
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
				}
				ResourceEntryShow rfes = new ResourceEntryShow(number,s);
				rfes.show(rfes);
			}
		});
		btnNewButton_2.setBounds(232, 17, 116, 23);
		panel_1.add(btnNewButton_2);
		
		//查看输入高铁的状态
		JButton btnNewButton_3 = new JButton("\u5F53\u524D\u5217\u8F66\u72B6\u6001");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				 for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		JOptionPane.showMessageDialog(null, trainEntry.getState());
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该航班不存在！");
			}
		});
		btnNewButton_3.setBounds(0, 17, 116, 25);
		panel_2.add(btnNewButton_3);
		
		//新增高铁计划，资源项只需要输入编号（唯一标识），若该资源不在可用资源中，请添加后再试
		JButton btnNewButton_4 = new JButton("\u65B0\u589E\u5217\u8F66\u8BA1\u5212");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String number = textField_1.getText();
				String[] numbers = number.split(",");
				List<RailwayStation> locations = new ArrayList<RailwayStation>();
				locations.add(new RailwayStation(textField_2.getText()));
				String inter = textField_3.getText();
				String[] inters = inter.split(",");
				for(int in = 0;in<inters.length;in++) {
					locations.add(new RailwayStation(inters[in]));
				}
				locations.add(new RailwayStation(textField_3.getText()));
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				String endTime = comboBox_6.getSelectedItem().toString()+"-"+comboBox_7.getSelectedItem().toString()+"-"+comboBox_8.getSelectedItem().toString()+" "+comboBox_9.getSelectedItem().toString()+":"+comboBox_10.getSelectedItem().toString();
			    
				int flag = 0;				
				List<Carriage> fl = new ArrayList<Carriage>();
				Set<Carriage> carriages = fc.getResources();
				xunhuan:
				for(Carriage carriage:carriages) {
					for(int in = 0;in<numbers.length;in++) {
						if(carriage.getNumbering().equals(numbers[in])) {
							fl.add(carriage);
							break xunhuan;
						}	
					}								
				}
				MultipleSortedResourceEntryImpl<Carriage> msre = new MultipleSortedResourceEntryImpl<Carriage>();
				if(fl.size() != numbers.length|fl.size() == 0)
				    msre.setResources(fl);
				else {
					JOptionPane.showMessageDialog(null, "可用资源中不存在该车厢，请添加车厢后再试！");
					flag = 1;
				}					
				
				Timeslot timeslot = new Timeslot(startTime,endTime);
			    if(timeslot.timeSub()>1) {
			    	JOptionPane.showMessageDialog(null, "可用资源中不存在该车厢，请添加车厢后再试！");
			    	flag = 1;
			    }

			    
			    MultipleLocationEntryImpl mle = new MultipleLocationEntryImpl();
			    mle.setLocations(locations);
			    Set<Location> stations = fc.getLocations();
			    for(RailwayStation rs:locations) {
			    	if(!stations.contains(rs)) {
			    		JOptionPane.showMessageDialog(null, "可用位置中不存在"+rs.getLocationName()+"，请添加后再试！");
				    	flag = 1;
			    	}			    		
			    }		    
			    
			    BlockableEntryImpl be = new BlockableEntryImpl();
			    
			    if(flag == 0)
			        fc.addEntry(new TrainEntry(flightNumber,timeslot,mle,msre,be));
			}
		});
		btnNewButton_4.setBounds(0, 42, 116, 25);
		panel_2.add(btnNewButton_4);
		
		//为某计划项分配资源
		JButton btnNewButton_5 = new JButton("\u5206\u914D/\u6DFB\u52A0\u8D44\u6E90");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String number = textField_1.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				Carriage fl = null;
				Set<Carriage> carriages = fc.getResources();
				xunhuan:
				for(Carriage carriage:carriages) {
					if(carriage.getNumbering().equals(number)) {
						fl = carriage;
						break xunhuan;
					}									
				}
				if(fl == null)
					JOptionPane.showMessageDialog(null, "可用资源中不存在该车厢，请添加车厢后再试！");
				
				else {
					for(TrainEntry trainEntry:fc) {
				    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
				    		if(trainEntry.getState().equals("Waiting")) {
				    			trainEntry.addResources(fl);
				    			JOptionPane.showMessageDialog(null, "成功！");
				    			flag = 1;
				    		}			    			
				    	}
				    }
				}
				if(flag == 0)
					JOptionPane.showMessageDialog(null, "已分配资源！");
			}
		});
		btnNewButton_5.setBounds(0, 67, 116, 25);
		panel_2.add(btnNewButton_5);
		
		//启动某一计划项
		JButton btnNewButton_6 = new JButton("\u542F\u52A8\u5F53\u524D\u5217\u8F66");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(trainEntry.Start()) {
			    			 JOptionPane.showMessageDialog(null, "启动成功，当前状态："+trainEntry.getState());
			    		}
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该列车不存在！");
			}
		});
		btnNewButton_6.setBounds(0, 92, 116, 25);
		panel_2.add(btnNewButton_6);
		
		//结束某一计划项
		JButton btnNewButton_7 = new JButton("\u7EC8\u6B62\u5F53\u524D\u5217\u8F66");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				 
				int flag = 0;
				for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(trainEntry.End())
			    		    JOptionPane.showMessageDialog(null, "结束成功，当前状态："+trainEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该列车不存在！");
			}
		});
		btnNewButton_7.setBounds(0, 142, 116, 25);
		panel_2.add(btnNewButton_7);
		
		//改变计划项集合
		JButton btnNewButton_8 = new JButton("\u66F4\u6539\u8BA1\u5212\u9879\u96C6");
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
		btnNewButton_8.setBounds(0, 192, 116, 25);
		panel_2.add(btnNewButton_8);
		
		//取消某一计划项
		JButton btnNewButton_9 = new JButton("\u53D6\u6D88\u5F53\u524D\u5217\u8F66");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(trainEntry.Cancell())
			    		    JOptionPane.showMessageDialog(null, "取消成功，当前状态："+trainEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该列车不存在！");
			}
		});
		btnNewButton_9.setBounds(0, 167, 116, 25);
		panel_2.add(btnNewButton_9);
		
		//停车
		JButton btnNewButton_14 = new JButton("\u4E2D\u9014\u505C\u8F66");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String trainNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				
				int flag = 0;
				for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(trainNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		if(trainEntry.block(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())))
			    		    JOptionPane.showMessageDialog(null, "中停成功，当前状态："+trainEntry.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "错误提示框", "当前状态不支持该操作！",JOptionPane.ERROR_MESSAGE);
			    	}
			    }
			    if(flag == 0)
			    	JOptionPane.showMessageDialog(null, "该列车不存在！");
			}
		});
		btnNewButton_14.setBounds(0, 117, 116, 25);
		panel_2.add(btnNewButton_14);
		
		//管理资源
		JButton btnNewButton_10 = new JButton("\u7BA1\u7406\u8D44\u6E90");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageResource addFlight = new ManageResource(fc);
			    addFlight.show(addFlight);				
			}
		});
		btnNewButton_10.setBounds(184, 213, 92, 23);
		frmTrainscheduleapp.getContentPane().add(btnNewButton_10);
		
		//管理位置
		JButton btnNewButton_11 = new JButton("\u7BA1\u7406\u4F4D\u7F6E");
		btnNewButton_11.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ManageLocation addLocation = new ManageLocation(fc,2);
				addLocation.show();
			}
		});
		btnNewButton_11.setBounds(276, 213, 92, 23);
		frmTrainscheduleapp.getContentPane().add(btnNewButton_11);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 241, 458, 86);
		frmTrainscheduleapp.getContentPane().add(scrollPane);
		
		//备注
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.RED);
		textArea.setText("\u7279\u522B\u6CE8\u610F\uFF1A\u8F93\u5165\u4E2D\u95F4\u7AD9\u65F6\uFF0C\u4E24\u4E2A\u4E2D\u95F4\u7AD9\u4E4B\u95F4\u4EE5\u9017\u53F7\u9694\u5F00\uFF1B\u8F93\u5165\u591A\u4E2A\u8F66\u53A2\u7F16\u53F7\u65F6\uFF0C\u4E5F\u4EE5\u9017\u53F7\u9694\u5F00\u3002\r\n\u6CE8\uFF1A1.\u201C\u6240\u6709\u5217\u8F66\u8BA1\u5212\u201D\uFF1A\u67E5\u770B\u5F53\u524D\u8BA1\u5212\u96C6\u4E2D\u7684\u6240\u6709\u5217\u8F66\u8BA1\u5212\u3002\r\n2.\u201C\u5F53\u524D\u4F4D\u7F6E\u5217\u8F66\u201D\uFF1A\u53EA\u9700\u5728\u8D77\u59CB\u7AD9\u5904\u8F93\u5165\u4F4D\u7F6E\uFF0C\u5C31\u53EF\u67E5\u770B\u8BE5\u4F4D\u7F6E\u7684\u5217\u8F66\u8868\u3002\r\n3.\u201C\u5F53\u524D\u8D44\u6E90\u5217\u8F66\u201D\uFF1A\u8F93\u5165\u5BF9\u5E94\u7684\u8F66\u53A2\u7F16\u53F7\uFF0C\u5373\u53EF\u67E5\u770B\u8BE5\u8F66\u53A2\u7684\u4F7F\u7528\u60C5\u51B5\u3002\r\n4.\u201C\u5F53\u524D\u5217\u8F66\u72B6\u6001\u201D\uFF1A\u8F93\u5165\u5217\u8F66\u53F7\u548C\u51FA\u53D1\u65F6\u95F4\u5373\u53EF\u67E5\u770B\u8BE5\u5217\u8F66\u5F53\u524D\u72B6\u6001\u3002\r\n5.\u201C\u65B0\u589E\u5217\u8F66\u201D\uFF1A\u9664\u8BA1\u5212\u9879\u96C6\u4E0D\u9700\u8981\u8F93\u5165\uFF0C\u5176\u5B83\u90FD\u5F97\u8F93\u5165\u3002\r\n6.\u201C\u5206\u914D/\u6DFB\u52A0\u8D44\u6E90\u201D\uFF1A\u8F93\u5165\u5217\u8F66\u53F7\uFF0C\u8F66\u53A2\u7F16\u53F7\uFF0C\u51FA\u53D1\u65F6\u95F4\u5373\u53EF\u4E3A\u8BE5\u8D9F\u5217\u8F66\u5206\u914D/\u6DFB\u52A0\u5BF9\u5E94\u7F16\u53F7\u7684\u8F66\u53A2\u3002\r\n7.\u201C\u542F\u52A8\u5F53\u524D\u5217\u8F66\u201D\uFF1A\u8F93\u5165\u822A\u73ED\u53F7\u548C\u8D77\u98DE\u65F6\u95F4\uFF0C\u70B9\u51FB\u5373\u53EF\u542F\u52A8\u3002\r\n8.\"\u4E2D\u9014\u505C\u8F66\u201C\uFF1A\u8F93\u5165\u5217\u8F66\u7F16\u53F7\u548C\u51FA\u53D1\u65F6\u95F4\uFF0C\u70B9\u51FB\u6309\u94AE\uFF0C\u82E5\u8BE5\u5217\u8F66\u8FD8\u6709\u4E2D\u95F4\u7AD9\uFF0C\u5373\u53EF\u505C\u8F66\u3002\r\n9.\u201C\u7ED3\u675F\u5F53\u524D\u5217\u8F66\u201D\uFF1A\u7C7B\u6BD47\u3002\r\n10.\u201C\u67E5\u770B\u51B2\u7A81\u201D\uFF1A\r\n11.\u201C\u66F4\u6539\u8BA1\u5212\u9879\u96C6\u201D\uFF1A\u5728\u5DE6\u4FA7\u9009\u62E9\u4E00\u4E2A\u8BA1\u5212\u9879\u96C6\uFF08\u6587\u4EF6\uFF09\uFF0C\u70B9\u51FB\u5373\u53EF\u3002\r\n12.\u201C\u68C0\u67E5\u77DB\u76FE\u201D\uFF1A\u67E5\u770B\u662F\u5426\u5B58\u5728\u8D44\u6E90\u62A2\u5360\u77DB\u76FE\u3002\r\n13.\u201C\u524D\u5E8F\u8BA1\u5212\u9879\u201D\uFF1A\u8F93\u5165\u5217\u8F66\u53F7\uFF0C\u8F66\u53A2\u7F16\u53F7\uFF0C\u51FA\u53D1\u65F6\u95F4\uFF0C\u4EE5\u6B64\u641C\u7D22\u8BA1\u5212\u9879\u96C6\u5408\u4E2D\u4E0E\u8F93\u5165\u5217\u8F66\u4F7F\u7528\u540C\u4E00\u8D44\u6E90\u4E14\u65F6\u95F4\u5728\u524D\u9762\u7684\u8BA1\u5212\u9879\u3002\r\n14.\u201C\u7BA1\u7406\u8D44\u6E90\u201D\uFF1A\u53EF\u589E\u52A0\uFF08\u5220\u9664\u3001\u4FEE\u6539\uFF09\u5F53\u524D\u8BA1\u5212\u9879\u96C6\u4E2D\u7684\u8D44\u6E90\u3002\r\n15.\u201C\u7BA1\u7406\u4F4D\u7F6E\u201D\uFF1A\u540C12.\r\n16.\u201C\u5168\u90E8\u7AD9\u70B9\u201D\uFF1A\u8F93\u5165\u5217\u8F66\u53F7\u548C\u51FA\u53D1\u65F6\u95F4\uFF0C\u70B9\u51FB\u6309\u94AE\u5373\u53EF\u67E5\u770B\u8BE5\u8D9F\u5217\u8F66\u9014\u7ECF\u7684\u4E2D\u95F4\u7AD9\u3002");
		scrollPane.setViewportView(textArea);
		
		//检查矛盾
		JButton btnNewButton_12 = new JButton("\u68C0\u67E5\u77DB\u76FE");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<PlanningEntry> pe = new ArrayList<PlanningEntry>();
				for(TrainEntry ce:fc.getTrainEntry()) {
					pe.add(ce);
				}
				PlanningEntryAPIs pea = new PlanningEntryAPIs();
				pea.checkResourceExclusiveConflict(pe);
			}
		});
		btnNewButton_12.setBounds(0, 213, 92, 23);
		frmTrainscheduleapp.getContentPane().add(btnNewButton_12);
		
		//获取前序计划
		JButton btnNewButton_13 = new JButton("\u524D\u5E8F\u8BA1\u5212\u9879");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flightNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				int flag = 0;
				for(TrainEntry trainEntry:fc) {
			    	if(trainEntry.getTrainNumber().equals(flightNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
			    		flag = 1;
			    		PlanningEntryAPIs pea = new PlanningEntryAPIs();
			    		int size = fc.getTrainEntry().size();
			    		List<PlanningEntry> pe = new ArrayList<PlanningEntry>();
			    		for(int i = 0;i<size;i++) {
			    			pe.add(fc.getTrainEntry().get(i));
			    		}
			    		Set<Carriage> st = fc.getResources();
			    		Resource re = null;
			    		for(Carriage carriage:st) {
			    			if(carriage.getNumbering().equals(textField_1.getText()))
			    				re = carriage;
			    		}
			    		TrainEntry te = (TrainEntry)pea.findPreEntryPerResource(re,trainEntry,pe);
			    		if(te != null)
			    			JOptionPane.showMessageDialog(null, "列车号："+te.getTrainNumber()+"\n起止时间："+te.getStartAndEndTime().getStartTime()+"-"+te.getStartAndEndTime().getEndTime()+"\n起点-终点："+te.getMle().getLocations().get(0).getLocationName()+"-"+te.getMle().getLocations().get(te.getMle().getLocations().size()-1).getLocationName()+"\n车厢1编号："+te.getMsre().toString()+"\n状态："+te.getState());
			    		else
			    			JOptionPane.showMessageDialog(null, "不存在前序计划！");		
			    	}
			    }
				if(flag == 0)
					JOptionPane.showMessageDialog(null, "输入的计划项不存在！");	
			}
		});
		btnNewButton_13.setBounds(92, 213, 92, 23);
		frmTrainscheduleapp.getContentPane().add(btnNewButton_13);
		
		//查看某趟列车经过的全部站点
		JButton btnNewButton_15 = new JButton("\u5168\u90E8\u7AD9\u70B9");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String trainNumber = textField.getText();
				String startTime = comboBox_1.getSelectedItem().toString()+"-"+comboBox_2.getSelectedItem().toString()+"-"+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString();
				for(TrainEntry trainEntry:fc) {
					if(trainEntry.getTrainNumber().equals(trainNumber)&&trainEntry.getStartAndEndTime().getStartTime().equals(startTime)) {
						@SuppressWarnings("unchecked")
						List<RailwayStation> lo = (List<RailwayStation>) trainEntry.getMle().getLocations();
						String out = "";
						int size = lo.size();
						for(int ou = 0;ou<size;ou++) {
							if(ou<size-1)
								out = out+lo.get(ou).getLocationName()+"->";
							else
							    out += lo.get(ou).getLocationName();
						}														
						JOptionPane.showMessageDialog(null, "全部站点"+out);
					}			    	
				}
			}
		});
		btnNewButton_15.setBounds(366, 213, 92, 23);
		frmTrainscheduleapp.getContentPane().add(btnNewButton_15);
	}
	
	//读取给定文件，获取计划项集合
	private static void setMessage(String fileName) throws IOException {
		fc = new TrainCollection(fileName);
		JOptionPane.showMessageDialog(null, "读取文件"+fileName+"成功！");
	}
}

/**
 * 
 * @author Administrator
 * 管理资源页面
 */
@SuppressWarnings("serial")
class ManageResource extends JFrame {

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
	public void show(ManageResource addFlight) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addFlight.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageResource(TrainCollection fc) {
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
				String str = "(Carriage):([A-Z]+[0-9]+)(Type):([A-Za-z0-9]+)(PersonnelNumber:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))(FactoryYear:)(20[12][0-9])";
				Pattern pattern = Pattern.compile(str);
				String p = "Carriage:"+textField.getText()+"Type:"+textField_1.getText()+"PersonnelNumber:"+textField_2.getText()+"FactoryYear:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					Carriage carriage = new Carriage(textField.getText(),textField_1.getText(),Integer.parseInt(textField_2.getText()),textField_2.getText());
				    fc.addResources(carriage);
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
				String str = "(Carriage):([A-Z]+[0-9]+)(Type):([A-Za-z0-9]+)(PersonnelNumber:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))(FactoryYear:)(20[12][0-9])";
				Pattern pattern = Pattern.compile(str);
				String p = "Carriage:"+textField.getText()+"Type:"+textField_1.getText()+"PersonnelNumber:"+textField_2.getText()+"FactoryYear:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					String carriage = textField.getText();
					Carriage cg = null;
					Set<Carriage> carriages = fc.getResources();
					xunhuan:
					for(Carriage carriage1:carriages) {
						if(carriage1.getNumbering().equals(carriage)) {
							cg = carriage1;
							break xunhuan;
						}									
					}
					if(cg == null)
						JOptionPane.showMessageDialog(null, "可用资源中不存在该飞机，请添加飞机后再试！");
					fc.deleteResources(cg);
					
					Carriage Cg = new Carriage(textField.getText(),textField_1.getText(),Integer.parseInt(textField_2.getText()),textField_2.getText());
				    fc.addResources(Cg);
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
				String str = "(Carriage):([A-Z]+[0-9]+)(Type):([A-Za-z0-9]+)(PersonnelNumber:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))(FactoryYear:)(20[12][0-9])";
				Pattern pattern = Pattern.compile(str);
				String p = "Carriage:"+textField.getText()+"Type:"+textField_1.getText()+"PersonnelNumber:"+textField_2.getText()+"FactoryYear:"+textField_2.getText();
				Matcher mc = pattern.matcher(p);
				if(mc.find()) {
					Carriage carriage = new Carriage(textField.getText(),textField_1.getText(),Integer.parseInt(textField_2.getText()),textField_2.getText());
				    fc.deleteResources(carriage);
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Teachers extends UserInterface {

	String userName = null;
	String priviliges = null;
	Database db = new Database();
	
	/**
	 * 
	 * @param username
	 * @param priv
	 */
	public void teacherPage(String username, String priv) {
		userName = username;
		priviliges = priv;
		// background image set up
		ImageIcon icon = new ImageIcon("src\\images\\admin.jpg");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		// admin page main frame set up
		JFrame frame = new JFrame();
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setResizable(false);

		// get container and set to invisible
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);

		JPanel main = new JPanel();

		super.profile(main, frame, username, priv);
		JButton logOut = new JButton("Log Out");
		logOut.setBounds(1780, 60, 100, 30);
		frame.add(logOut);

		frame.add(main);
		frame.add(navigation(main, frame));
		frame.setLayout(null);
		frame.setSize(icon.getIconWidth(), icon.getIconHeight());
		frame.setVisible(true);

		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginPage();
			}
		});
	}

	/**
	 * 
	 * @param main
	 * @param fr
	 * @return
	 */
	public JPanel navigation(JPanel main, JFrame fr) {
		JPanel nav = new JPanel();
		// nav layout set up
		nav.setLayout(new GridLayout(5, 1));
		nav.setBounds(0, 146, 343, 400);
		nav.setOpaque(false);

		// nav button added
		JButton profile = new JButton("- Profile -");
		navAttribute(profile);
		JButton accounts = new JButton("- Students Grades -");
		navAttribute(accounts);
		JButton classes = new JButton("- Degree Classes -");
		navAttribute(classes);

		// action listener
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJSwing(main);
				try {
					profile(main, fr, userName, priviliges);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		accounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJSwing(main);
				try {
					grade(main, fr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJSwing(main);
				try {
					degree(main, fr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		// add buttons to panel
		nav.add(profile);
		nav.add(accounts);
		nav.add(classes);

		return nav;
	}

	/**
	 * 
	 * @param main
	 * @param frame
	 * @throws Exception
	 */
	public void grade(JPanel main, JFrame frame) throws Exception {
		String role = "Student_Grades";
		JTable table = db.displayTable(role);

		main.setLayout(new GridLayout());
    
		JButton addGrade = new JButton("Add Grade");
		JButton updateGrade = new JButton("Update Grade");
		JButton meanGrade = new JButton("Calculate Mean Grade");

		addGrades(addGrade, table);
		updateGrades(updateGrade, table);
		meanGrade(meanGrade, table);
		main.setBounds(343, 146, 1577, 788);
		main.setVisible(true);
		main.add(addGrade);
		main.add(updateGrade);
		main.add(meanGrade);
	}
	public void degree(JPanel main, JFrame frame) throws Exception {

		JTable table = db.displayTable("Student_Class_Link");
		JScrollPane jsp= new JScrollPane(table);
		jsp.setSize(new Dimension(1577, 588));

		
		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(jsp,BorderLayout.CENTER);
	}
	/**
	 * 
	 * @param bt
	 * @param table
	 */
	public void meanGrade(JButton bt, JTable table) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame meanGr = new JFrame("Select Student");
				meanGr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				meanGr.setLayout(null);
				meanGr.setVisible(true);
				try {
					JPanel meanGrP = new JPanel();
					meanGrP.setLayout(null);
					meanGrP.setBounds(0, 0, 300, 220);
					meanGr.setResizable(false);

					JLabel stuId = new JLabel("Student:");
					stuId.setBounds(30, 50, 130, 20);
					JComboBox stuIdBox = db.getPBox("Student");
					stuIdBox.setBounds(170, 50, 130, 20);

					JLabel per = new JLabel("Period of study:");
					per.setBounds(30, 110, 130, 20);
					JComboBox perBox = db.getPBox("Period_of_Study");
					perBox.setBounds(170, 110, 130, 20);

					meanGrP.add(per);
					meanGrP.add(perBox);

					meanGrP.add(stuId);
					meanGrP.add(stuIdBox);

					JButton okbtn = new JButton("Calculate");
					okbtn.setBounds(30, 140, 80, 20);
					JButton cancelbtn = new JButton("Cancel");
					cancelbtn.setBounds(220, 140, 80, 20);
					meanGrP.add(okbtn);
					meanGrP.add(cancelbtn);
					meanGr.setLocation(900, 500);
					meanGr.setSize(350, 220);
					meanGr.setVisible(true);
					meanGr.add(meanGrP);
					okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							
							Integer stu = (Integer) stuIdBox.getSelectedItem();
							int per = Integer.parseInt((String) perBox.getSelectedItem());

							meanGr.dispose();
							JFrame showMead = new JFrame("Mean grade");
							showMead.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							showMead.setLayout(null);
							showMead.setVisible(true);
							JPanel showMeadP = new JPanel();
							showMeadP.setLayout(null);
							showMeadP.setBounds(0, 0, 600, 700);

							JTable meanGrade = db.meanGradeTable(stu, per);
							float test = db.getAverageGrade(meanGrade);
							JScrollPane jsp = new JScrollPane(meanGrade);
							jsp.setSize(new Dimension(600, 500));
							JLabel mean = new JLabel(
									"Weighted mean grade for this period is : " + test + "");
							mean.setBounds(30, 520, 500, 20);
							JButton register = new JButton("Register a student");
							register.setBounds(30, 550, 540, 20);
							try {
								
								registerStudent(register,stu);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							showMeadP.add(jsp);
							showMeadP.add(mean);
							showMeadP.add(register);

							showMead.setResizable(false);

							showMead.setLocation(900, 500);
							showMead.setSize(600, 700);
							showMead.setVisible(true);
							showMead.add(showMeadP);
						}
					});
					cancelbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							meanGr.dispose();
						}
					});
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
	}
	private void registerStudent(JButton remmovebt,int degId) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				

					try {
					JFrame regStudent=new JFrame("Register student");
					regStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					regStudent.setLayout(null);
					regStudent.setVisible(true);
	
					JPanel regStudentP=new JPanel();
					regStudentP.setLayout(null);
					regStudentP.setBounds(0,0,500,220);
					regStudent.setResizable(false);

					JLabel sDate = new JLabel("Start date(YYYY/MM/DD):");
	
			        String[] listData = new String[]{"2018", "2019", "2020", "2021","2022","2023","2024","2025"};
			        final JComboBox<String> startYear = new JComboBox<String>(listData);
			        startYear.setBounds(250, 20, 60, 20);
			        String[] listData2= new String[]{"01", "02", "03", "04","05", "06", "07","08", "09", "10","11", "12"};
			        final JComboBox<String> startMonth = new JComboBox<String>(listData2);
			        startMonth.setBounds(310, 20, 60, 20);
			        String[] listData3 = new String[]{"01", "02", "03", "04","05", "06", "07","08", "09", "10","11", "12"+
			        		"13", "14", "15", "16","17", "18", "19","20", "21", "22","23", "24", "25","26", "27", "28","29", "30", "31"};
			        final JComboBox<String> startDay = new JComboBox<String>(listData3);
			        startDay.setBounds(370, 20, 60, 20);	
					
					JLabel eDate = new JLabel("End date:");
			        String[] listData4 = new String[]{"2018", "2019", "2020", "2021","2022","2023","2024","2025"};
			        final JComboBox<String> endYear = new JComboBox<String>(listData4);
			        endYear.setBounds(250, 50, 60, 20);
			        String[] listData5= new String[]{"01", "02", "03", "04","05", "06", "07","08", "09", "10","11", "12"};
			        final JComboBox<String> endMonth = new JComboBox<String>(listData5);
			        endMonth.setBounds(310, 50, 60, 20);
			        String[] listData6 = new String[]{"01", "02", "03", "04","05", "06", "07","08", "09", "10","11", "12"+
			        		"13", "14", "15", "16","17", "18", "19","20", "21", "22","23", "24", "25","26", "27", "28","29", "30", "31"};
			        final JComboBox<String> endDay = new JComboBox<String>(listData6);
			        endDay.setBounds(370, 50, 60, 20);
	
	
					sDate.setBounds(30, 20, 220, 20);
					eDate.setBounds(30, 50, 220, 20);
					
					
					JLabel label = new JLabel("Label:");
					label.setBounds(30, 80, 220, 20);
	
					String[] listData7 = new String[]{"A", "B", "C", "D","E", "F", "G","H", "I", "J","K", "L"};
			        final JComboBox<String> labelBox = new JComboBox<String>(listData7);
			        labelBox.setBounds(250, 80, 60, 20);
					
					JLabel level = new JLabel("Level of Study:");
					level.setBounds(30, 110, 220, 20);
			        
			       				
					JComboBox levelBox = db.getPBox("Level_of_Study");
	
					levelBox.setBounds(250, 110, 60, 20);
					
					JLabel degree = new JLabel("Degree class (for graduation):");
					degree.setBounds(30, 140, 220, 20);
					JComboBox degreeClass = db.getPBox("Degree_Class");
					degreeClass.setBounds(250, 140, 60, 20);
				
					regStudentP.add(sDate);
					regStudentP.add(degreeClass);
					regStudentP.add(degree);

					regStudentP.add(startYear);
					regStudentP.add(startMonth);
					regStudentP.add(startDay);
					regStudentP.add(endYear);
					regStudentP.add(endMonth);
					regStudentP.add(endDay);
					regStudentP.add(eDate);
					regStudentP.add(labelBox);
					regStudentP.add(label);
					
					regStudentP.add(levelBox);
					regStudentP.add(level);
					
																								 
				    JButton okbtn = new JButton("Progress");
				    okbtn.setBounds(60, 170, 90, 20);
				    JButton repeatbtn = new JButton("Repeat");
				    repeatbtn.setBounds(160, 170, 90, 20);
				    JButton graduebtn = new JButton("Graduate");
				    graduebtn.setBounds(260, 170, 90, 20);
				    JButton failbtn = new JButton("Fail");
				    failbtn.setBounds(360, 170, 90, 20);
				    
				    regStudentP.add(okbtn);
				    regStudentP.add(repeatbtn);
				    regStudentP.add(graduebtn);
				    regStudentP.add(failbtn);

				    regStudent.setLocation(900,500);
				    regStudent.setSize(500,240);
				    regStudent.setVisible(true);
				    regStudent.add(regStudentP);
				    okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
	
							
							String startDate = (String) startYear.getSelectedItem()+"-"+(String) startMonth.getSelectedItem()+"-"+startDay.getSelectedItem();
							String endDate = (String) endYear.getSelectedItem()+"-"+(String) endMonth.getSelectedItem()+"-"+endDay.getSelectedItem();
							char label = ((String) labelBox.getSelectedItem()).charAt(0);
							String level = (String) levelBox.getSelectedItem();
							System.out.print(startDate);
							db.registerStudent(startDate, endDate,label, level,degId, "pass");
							System.out.println("Success");
							regStudent.dispose();
								
								
						}
					});
				    repeatbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String startDate = (String) startYear.getSelectedItem()+"-"+(String) startMonth.getSelectedItem()+"-"+startDay.getSelectedItem();
							String endDate = (String) endYear.getSelectedItem()+"-"+(String) endMonth.getSelectedItem()+"-"+endDay.getSelectedItem();
							char label = ((String) labelBox.getSelectedItem()).charAt(0);
							String level = (String) levelBox.getSelectedItem();
							db.registerStudent(startDate, endDate,label, level,degId,"repeat");
							regStudent.dispose();
						}
					});
				    graduebtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String classID = (String)degreeClass.getSelectedItem();
							db.graduate(degId,classID);
							regStudent.dispose();
						}
					});
				    failbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
//							String startDate = (String) startYear.getSelectedItem()+"-"+(String) startMonth.getSelectedItem()+"-"+startDay.getSelectedItem();
//							String endDate = (String) endYear.getSelectedItem()+"-"+(String) endMonth.getSelectedItem()+"-"+endDay.getSelectedItem();
//							char label = ((String) labelBox.getSelectedItem()).charAt(0);
//							String level = (String) levelBox.getSelectedItem();
//							db.registerStudent(startDate, endDate,label, level,degId,"fail");
//							regStudent.dispose();
							regStudent.dispose();
						}
					});
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			
		});
	
	}
	/**
	 * 
	 * @param bt
	 * @param table
	 */
	public void addGrades(JButton bt, JTable table) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addGr = new JFrame("Add grade");
				addGr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addGr.setLayout(null);
				addGr.setVisible(true);
				try {
					JPanel addGrP = new JPanel();
					addGrP.setLayout(null);
					addGrP.setBounds(0, 0, 300, 220);
					addGr.setResizable(false);

					JLabel leadeeName = new JLabel("Module name:");
					leadeeName.setBounds(30, 20, 130, 20);
					JComboBox allMod = db.getPBox("Module");
					allMod.setBounds(170, 20, 130, 20);

					JLabel stuId = new JLabel("Student:");
					stuId.setBounds(30, 50, 130, 20);
					JComboBox stuIdBox = db.getPBox("Student");
					stuIdBox.setBounds(170, 50, 130, 20);

					JLabel grade = new JLabel("Initial grade:");
					grade.setBounds(30, 80, 130, 20);
					JTextField grade1 = new JTextField();
					grade1.setBounds(170, 80, 130, 20);

					JLabel per = new JLabel("Period of study:");
					per.setBounds(30, 110, 130, 20);
					JComboBox perBox = db.getPBox("Period_of_Study");
					perBox.setBounds(170, 110, 130, 20);

					addGrP.add(per);
					addGrP.add(perBox);

					addGrP.add(leadeeName);
					addGrP.add(allMod);
					addGrP.add(stuId);
					addGrP.add(stuIdBox);
					addGrP.add(grade);
					addGrP.add(grade1);

					JButton okbtn = new JButton("Add");
					okbtn.setBounds(30, 140, 80, 20);
					JButton cancelbtn = new JButton("Cancel");
					cancelbtn.setBounds(220, 140, 80, 20);
					addGrP.add(okbtn);
					addGrP.add(cancelbtn);
					addGr.setLocation(900, 500);
					addGr.setSize(350, 220);
					addGr.setVisible(true);
					addGr.add(addGrP);
					okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							String mod = (String) allMod.getSelectedItem();
							Integer stu = (Integer) stuIdBox.getSelectedItem();
							int grade = Integer.parseInt(grade1.getText());
							int per = Integer.parseInt((String) perBox.getSelectedItem());
							db.insertGrade(mod, stu, per, grade);
							System.out.println("Success");
							addGr.dispose();

						}
					});
					cancelbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							addGr.dispose();
						}
					});
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});

	}

	/**
	 * Update the grades of a student
	 * @param bt	The button to be pressed
	 * @param table The table of grades
	 */
	public void updateGrades(JButton bt, JTable table) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Create and initialise frame
				JFrame upGr = new JFrame("Update grade");
				upGr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				upGr.setLayout(null);
				upGr.setVisible(true);
				upGr.setLocation(900, 500);
				upGr.setSize(350, 350);

				try {
					//Create and initialise panel
					JPanel upGrP = new JPanel();
					upGrP.setLayout(null);
					upGrP.setBounds(0, 0, 300, 400);
					upGr.setResizable(false);
					
					//Create label and combo box for modules
					JLabel moduleName = new JLabel("Module name:");
					moduleName.setBounds(30, 20, 130, 20);
					JComboBox<?> allMod = db.getPBox("Module");
					allMod.setBounds(170, 20, 130, 20);
					
					//Create label and combo box for students
					JLabel stuId = new JLabel("Student:");
					stuId.setBounds(30, 50, 130, 20);
					JComboBox<?> stuIdBox = db.getPBox("Student");
					stuIdBox.setBounds(170, 50, 130, 20);
					
					//Create label and combo box for type of grade (re-sit or repeat)
					JLabel typeLabel = new JLabel("Type:");
					typeLabel.setBounds(30, 80, 130, 20);
					JComboBox<String> typeBox = new JComboBox<String>();
					typeBox.addItem("Resit");
					typeBox.addItem("Repeat");
					typeBox.setBounds(170, 80, 130, 20);
					
					//Create label and text field for the grade value
					JLabel grade = new JLabel("Grade:");
					grade.setBounds(30, 110, 130, 20);
					JTextField gradeText = new JTextField();
					gradeText.setBounds(170, 110, 130, 20);
					
					//Create label and combo box for the period of study
					JLabel per = new JLabel("Period of study:");
					per.setBounds(30, 140, 130, 20);
					JComboBox<?> perBox = db.getPBox("Period_of_Study");
					perBox.setBounds(170, 140, 130, 20);
					
					//Create and initialise buttons for updating and cancelling
					JButton okbtn = new JButton("Update");
					okbtn.setBounds(30, 170, 80, 20);
					JButton cancelbtn = new JButton("Cancel");
					cancelbtn.setBounds(220, 170, 80, 20);
					
					//Add elements to the panel
					upGrP.add(per);
					upGrP.add(perBox);
					upGrP.add(moduleName);
					upGrP.add(allMod);
					upGrP.add(stuId);
					upGrP.add(stuIdBox);
					upGrP.add(typeLabel);
					upGrP.add(typeBox);
					upGrP.add(grade);
					upGrP.add(gradeText);
					upGrP.add(okbtn);
					upGrP.add(cancelbtn);
					
					//Add panel to frame
					upGr.add(upGrP);
					
					//Set frame to visible
					upGr.setVisible(true);
					
					//Action listener for the okay button
					okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//Get data from data fields
							float grade = Float.parseFloat(gradeText.getText());
							Integer regID = (Integer) stuIdBox.getSelectedItem();
							String mod = (String) allMod.getSelectedItem();
							String type = (String) allMod.getSelectedItem();
							
							//Update the grade in the database
							db.updateGrade(regID, mod, type, grade);
							
							//Dispose the frame
							upGr.dispose();
						}
					});
					
					//Action listener for the cancel button
					cancelbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//Dispose the frame
							upGr.dispose();
						}
					});
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

			}
		});
	}
}

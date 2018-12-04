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
									"Weighted mean grade for this period is : " + test + ". This student got a: ");
							mean.setBounds(30, 520, 500, 20);

							showMeadP.add(jsp);
							showMeadP.add(mean);

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
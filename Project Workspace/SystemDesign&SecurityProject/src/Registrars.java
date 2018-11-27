
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class Registrars extends UserInterface{
	Statement stmt = null;
	Connection con = null;  // a Connection object
	String userName = null;
	String priviliges = null;
	public void registarPage(String username,String priv) {
		userName = username;
		priviliges = priv;
		//background image set up
		ImageIcon icon=new ImageIcon("src\\images\\admin.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		//admin page main frame set up
		JFrame frame=new JFrame();
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));	
		
		//get container and set to invisible
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		JPanel main =new JPanel();
		
		super.profile(main,frame,username,priv);
		JButton logOut = new JButton("Log Out");
		logOut.setBounds(1780,60,100,30);
		frame.add(logOut);
		
		frame.add(main);
		frame.add(navigation(main,frame));
		frame.setLayout(null);
		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
		
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new LoginPage();
			}
		});
	}
	public JPanel navigation(JPanel main,JFrame fr) {
		JPanel nav =new JPanel();
		//nav layout set up
		nav.setLayout(new GridLayout(5,1));
		nav.setBounds(0,146, 343, 400);
		nav.setOpaque(false);
		
		//nav button added
		JButton profile=new JButton("- Profile -");
		navAttribute(profile);
		JButton accounts=new JButton("- Students -");
		navAttribute(accounts);

		
		//action listener
		profile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				profile(main,fr,userName, priviliges);
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
				students(main,fr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
		});



		//add buttons to panel
		nav.add(profile);
		nav.add(accounts);

		return nav;
	}

	public void students(JPanel main,JFrame frame) throws Exception {
		ResultSet studentsRs;
		//find drivers
		stmt = super.findDriver();
		String sql ="select * from Students";
		studentsRs = stmt.executeQuery(sql);
		if(!(studentsRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) studentsRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));
		}
		do{
		     rows.addElement(getNextRow(studentsRs,rsmd));
		}while(studentsRs.next());
		
		JTable table = new JTable(rows,columnHeads);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		studentsRs.close();		
		stmt.close();
		
		JButton addStudents= new JButton("Add student");
		JButton removeStudent= new JButton("Remove student");
		JButton regStudent= new JButton("Register student");

		addStudents.setBounds(0,738, 200, 50);
		removeStudent.setBounds(250,738, 200, 50);
		regStudent.setBounds(500,738, 200, 50);
		registerStudent(regStudent);

		removeItem(removeStudent,table, "Students");
		addStudent(addStudents);

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addStudents);
		main.add(removeStudent);
		main.add(regStudent);

		main.add(jsp);
		
	}
	private void addStudent(JButton addStudents) throws Exception {
		addStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet stuRs;
			
				//new frame for adding department
				JFrame addStudent=new JFrame("Add Student");
				addStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addStudent.setLayout(null);
				addStudent.setVisible(true);
				
				JPanel addStudentP=new JPanel();
				addStudentP.setLayout(null);
				addStudentP.setBounds(0,0,410,340);
				
			    JLabel title = new JLabel("Tiele:");
			    title.setBounds(30, 20, 130, 20);
			    JTextField title1 = new JTextField();
			    title1.setBounds(170, 20, 190, 20);
			    
			    JLabel surname = new JLabel("Surname:");
			    surname.setBounds(30, 50, 130, 20);
			    JTextField surname1 = new JTextField();
			    surname1.setBounds(170, 50, 190, 20);
			    
			    JLabel forename = new JLabel("Forename:");
			    forename.setBounds(30, 80, 130, 20);
			    JTextField forename1 = new JTextField();
			    forename1.setBounds(170, 80, 190, 20);
			    
			    JLabel degree = new JLabel("Degree");
			    degree.setBounds(30, 110, 130, 20);
			    //combo box for selecting level of study
				JComboBox PrivilegesBox=new JComboBox();
				PrivilegesBox.setBounds(170, 110, 190, 20);
			    
				
				try {
				//find drivers
				stmt = findDriver();
				String sql ="select `Full name` from Degrees";
				
				stuRs = stmt.executeQuery(sql);
				
				//String first = depRs.getString("Full name");
					ResultSetMetaData rsmd= (ResultSetMetaData) stuRs.getMetaData();
				if(!(stuRs.next()))
				{
				   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					PrivilegesBox.addItem(stuRs.getString("Full name"));
						while(stuRs.next()) {
						PrivilegesBox.addItem(stuRs.getString("Full name"));
					}
				}
				stuRs.close();
				stmt.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				JLabel registrationNum = new JLabel("Registration number:");
				registrationNum.setBounds(30, 140, 130, 20);
			    JTextField registrationNum1 = new JTextField();
			    registrationNum1.setBounds(170, 140, 190, 20);
				
				JLabel email = new JLabel("Email address:");
				email.setBounds(30, 170, 130, 20);
			    JTextField email1 = new JTextField();
			    email1.setBounds(170, 170, 190, 20);
			    
				JLabel address = new JLabel("Address:");
				address.setBounds(30, 200, 130, 20);
			    JTextField address1 = new JTextField();
			    address1.setBounds(170, 200, 190, 20);
			    
				JLabel personalTutor = new JLabel("Personal tutor:");
				personalTutor.setBounds(30, 230, 130, 20);
			    JTextField personalTutor1 = new JTextField();
			    personalTutor1.setBounds(170, 230, 190, 20);
			   
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 260, 130, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(230, 260, 130, 20);

			    addStudentP.add(title);
			    addStudentP.add(title1);
			    addStudentP.add(surname);
			    addStudentP.add(surname1);
			    addStudentP.add(forename);
			    addStudentP.add(forename1);
			    addStudentP.add(degree);
			    addStudentP.add(PrivilegesBox);
			    addStudentP.add(registrationNum);
			    addStudentP.add(registrationNum1);
			    addStudentP.add(email);
			    addStudentP.add(email1);
			    addStudentP.add(address);
			    addStudentP.add(address1);
			    addStudentP.add(personalTutor);
			    addStudentP.add(personalTutor1);
			    
			    addStudentP.add(okbtn);
			    addStudentP.add(cancelbtn);
			    
			    addStudent.setLocation(900,500);
			    addStudent.setSize(410,340);
			    addStudent.setVisible(true);
			    addStudent.add(addStudentP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String title = title1.getText();
							    String sName = surname1.getText();
							    String fName = forename1.getText();
								String regNum = registrationNum1.getText();
							    String email = email1.getText();
							    String add = address1.getText();
							    String perTutor = personalTutor1.getText();
							    
							    String deg = (String) PrivilegesBox.getSelectedItem();

							    stmt = findDriver();
								String insertDepartments="INSERT INTO `Students` VALUES ('"+title+"','"+sName+"','"+fName+"','"+deg+"','"+regNum+"','"+email+"','"+add+"','"+perTutor+"');";
								stmt.executeUpdate(insertDepartments);
								System.out.println("Success");
								stmt.close();
								addStudent.dispose();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addStudent.dispose();
					}
				});
		    }
		});
	}
	private void registerStudent(JButton remmovebt) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				JFrame regStudent=new JFrame("Register student");
//				regStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				regStudent.setLayout(null);
//				regStudent.setVisible(true);
//				
//				JPanel regStudentP=new JPanel();
//				regStudentP.setLayout(null);
//				regStudentP.setBounds(0,0,300,220);
//				
//				JLabel label = new JLabel("Label:");
//				label.setBounds(30, 20, 130, 20);
//				JTextField label1 = new JTextField();
//				label.setBounds(140, 20, 100, 20);
//				
//				regStudentP.add(label);
//				regStudentP.add(label1);
//
//				regStudent.setLocation(900,500);
//				regStudent.setSize(300,240);
//			    regStudent.setVisible(true);
//			    regStudent.add(regStudentP);
				//new frame for adding department
				JFrame regStudent=new JFrame("Register student");
				regStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				regStudent.setLayout(null);
				regStudent.setVisible(true);
				
				JPanel regStudentP=new JPanel();
				regStudentP.setLayout(null);
				regStudentP.setBounds(0,0,300,220);
				
			    JLabel label = new JLabel("Label:");
			    label.setBounds(30, 20, 100, 20);
			    JTextField label1 = new JTextField();
			    label1.setBounds(140, 20, 100, 20);
			    JLabel sDate = new JLabel("Start date:");
			    sDate.setBounds(30, 50, 100, 20);
			    JTextField sDate1 = new JTextField();
			    sDate1.setBounds(140, 50, 100, 20);
			    
			    JLabel eDate = new JLabel("End date:");
			    eDate.setBounds(30, 80, 100, 20);
			    JTextField eDate1 = new JTextField();
			    eDate1.setBounds(140, 80, 100, 20);
			    
			    JLabel level = new JLabel("Level of study:");
			    level.setBounds(30, 110, 100, 20);
			    //combo box for selecting level of study
				JComboBox PrivilegesBox=new JComboBox();
				PrivilegesBox.addItem("1");
				PrivilegesBox.addItem("2");
				PrivilegesBox.addItem("3");
				PrivilegesBox.addItem("P");
				PrivilegesBox.setBounds(140, 110, 100, 20);
			    

				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 140, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 140, 80, 20);

			    addDegreeP.add(deeName);
			    addDegreeP.add(deeName1);
			    addDegreeP.add(abbCode);
			    addDegreeP.add(abbCode1);
			    addDegreeP.add(level);
			    addDegreeP.add(leadDep);
			    addDegreeP.add(leadDep1);

			    addDegreeP.add(PrivilegesBox);

			    addDegreeP.add(okbtn);
			    addDegreeP.add(cancelbtn);
			    addDegree.setLocation(900,500);
			    addDegree.setSize(290,220);
			    addDegree.setVisible(true);
			    addDegree.add(addDegreeP);
				
				
			}
		});
	
	}
	private void removeItem(JButton remmovebt, JTable table,String compName) {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected == -1)
					JOptionPane.showMessageDialog(null, "No degree selected, select an degree.", "Error",JOptionPane.WARNING_MESSAGE);  

				else {	
				String getname= table.getValueAt(selected, 4).toString();
				try {
				stmt = findDriver();
				
				System.out.print(getname);	
				String removeName;
				
				
					removeName= "DELETE FROM "+compName+" WHERE `Registration number` = '"+getname+"'"; 
					System.out.print(removeName);
				
				
				stmt.executeUpdate(removeName);
				stmt.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
			});
		
	}
	//navigation attribute set up
	public void navAttribute(JButton bt) {
		bt.setForeground(Color.WHITE);
		bt.setFont(new Font("Dialog", Font.PLAIN, 16));
		bt.setPreferredSize(new Dimension(120,50));
		bt.setContentAreaFilled(false);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		//profile.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\pb.png"));			
	}
	
	public void updateJSwing(JPanel main) {
		main.removeAll();
		main.repaint();
		main.updateUI();
	}
	
	private Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow=new Vector();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}

}

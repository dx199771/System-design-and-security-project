
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class Registrars extends UserInterface{

	String userName = null;
	String priviliges = null;
	Database db = new Database();

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
		frame.setResizable(false);

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
		String role ="Student";
		JTable table = db.displayTable(role);

		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		
		JButton addStudents= new JButton("Add student");
		JButton removeStudent= new JButton("Remove student");
		JButton optional= new JButton("Add/Drop optional module(s)");

		addStudents.setBounds(0,738, 200, 50);
		removeStudent.setBounds(250,738, 200, 50);
		optional.setBounds(500,738, 200, 50);

		String itemName = "Student";

		addStudent(addStudents,main,frame);
		optionalModule(optional,table,main,frame);
		removeItem(removeStudent,table,itemName,main,frame);
		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addStudents);
		main.add(removeStudent);
		main.add(optional);

		main.add(jsp);
		
	}
	public void optionalModule(JButton button,JTable table,JPanel main,JFrame fr) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected != -1) {
					int degId =  Integer.parseInt((String) table.getValueAt(selected,0));

					try {	
				JFrame OptionalModule=new JFrame("Optional Module");
				OptionalModule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				OptionalModule.setLayout(null);
				OptionalModule.setVisible(true);
				
				JPanel OptionalModuleP=new JPanel();
				
				OptionalModule.setResizable(false);

				

				
				JLabel addOp = new JLabel("Add a optional module:");
				addOp.setBounds(30, 60, 130, 20);

				JComboBox allMod = db.getPBox("Module");
				allMod.setBounds(200, 60, 130, 20);

				JButton add = new JButton("Add");
				add.setBounds(350, 60, 120, 20);

				
				
				
				JLabel dropOp = new JLabel("Drop a optional module:");
				dropOp.setBounds(30, 90, 140, 20);

				JComboBox allMod1 = db.getPBox("Module");
				allMod1.setBounds(200, 90, 130, 20);

				JButton drop = new JButton("Drop");
				drop.setBounds(350, 90, 120, 20);
				

				OptionalModuleP.add(add);
				OptionalModuleP.add(dropOp);
				OptionalModuleP.add(allMod1);
				OptionalModuleP.add(drop);
				
				OptionalModuleP.add(addOp);
				OptionalModuleP.add(allMod);

				OptionalModuleP.setLayout(null);
				OptionalModuleP.setBounds(0,0,510,200);
			    
				OptionalModule.setLocation(900,500);
				OptionalModule.setSize(510,200);
				OptionalModule.setVisible(true);
				OptionalModule.add(OptionalModuleP);
				add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String mod = (String) allMod.getSelectedItem();
						db.addOptional(degId,mod);
						String mess = "You have added one optional module"+", current credits: "+ db.getCurrentCredit(degId);
						JOptionPane.showMessageDialog(null, mess, "No data",JOptionPane.INFORMATION_MESSAGE);

					}
				});
				drop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String mod = (String) allMod1.getSelectedItem();
						db.dropOptional(degId,mod);
						String mess = "You have dropped one optional module"+", current credits: "+ db.getCurrentCredit(degId);

						JOptionPane.showMessageDialog(null, mess, "No data",JOptionPane.INFORMATION_MESSAGE);

					}
				});

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				else
					JOptionPane.showMessageDialog(null, "You must select a student!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
	}
	private void addStudent(JButton addStudents,JPanel main,JFrame fr) throws Exception {
		addStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				//new frame for adding department
				JFrame addStudent=new JFrame("Add Student");
				addStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addStudent.setLayout(null);
				addStudent.setVisible(true);
				
				JPanel addStudentP=new JPanel();
				addStudentP.setLayout(null);
				addStudentP.setBounds(0,0,410,340);
				
			    JLabel title = new JLabel("Tiele:");
			    title.setBounds(30, 20, 130, 20);
			    addStudent.setResizable(false);

			    JComboBox titleBox=new JComboBox();
			    titleBox.addItem("Mr.");
				titleBox.addItem("Mrs.");
				titleBox.addItem("Miss.");
				titleBox.addItem("Ms.");
				titleBox.addItem("Mx.");
				titleBox.addItem("Sir.");
				titleBox.addItem("Dr.");
			    titleBox.setBounds(170, 20, 190, 20);

				
			    JLabel surname = new JLabel("Surname:");
			    surname.setBounds(30, 50, 130, 20);
			    JTextField surname1 = new JTextField();
			    surname1.setBounds(170, 50, 190, 20);
			    
			    JLabel forename = new JLabel("Forename:");
			    forename.setBounds(30, 80, 130, 20);
			    JTextField forename1 = new JTextField();
			    forename1.setBounds(170, 80, 190, 20);
			    
			    JLabel email = new JLabel("Email");
			    email.setBounds(30, 110, 130, 20);
			    JTextField email1 = new JTextField();
			    email1.setBounds(170, 110, 190, 20);
			    
			    JLabel tutor = new JLabel("Tutor");
			    tutor.setBounds(30, 140, 130, 20);
			    String sql ="Tutor";
			    
			    JComboBox tutorBox = db.getPBox(sql);
				
			    tutorBox.setBounds(170, 140, 190, 20);    
			    
			    JLabel accout = new JLabel("Set accout");
			    accout.setBounds(30, 170, 130, 20);
			    JComboBox accout1 = db.getPBox("Login_Details");
			    accout1.setBounds(170, 170, 190, 20);		    				

			    JLabel degree = new JLabel("Degree");
			    degree.setBounds(30, 200, 130, 20);				
			    JComboBox degreeBox = db.getPBox("Degree");
				degreeBox.setBounds(170, 200, 190, 20);
		   
				
				
				
				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 260, 130, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(230, 260, 130, 20);

			    addStudentP.add(title);
			    addStudentP.add(titleBox);
			    addStudentP.add(surname);
			    addStudentP.add(surname1);
			    addStudentP.add(forename);
			    addStudentP.add(forename1);
			    addStudentP.add(degree);
			    addStudentP.add(degreeBox);
			    addStudentP.add(accout);
			    addStudentP.add(accout1);

			    addStudentP.add(email);
			    addStudentP.add(email1);

			    addStudentP.add(tutor);
			    addStudentP.add(tutorBox);
			    
			    addStudentP.add(okbtn);
			    addStudentP.add(cancelbtn);
			    
			    addStudent.setLocation(900,500);
			    addStudent.setSize(410,340);
			    addStudent.setVisible(true);
			    addStudent.add(addStudentP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

								
							    String tit = (String) titleBox.getSelectedItem();
								String sName = surname1.getText();
							    String fName = forename1.getText();
							    String emai = email1.getText();
							    String tuto = (String) tutorBox.getSelectedItem();
							    int accout = Integer.parseInt((String) accout1.getSelectedItem());

							    String dee = (String)degreeBox.getSelectedItem();

							    db.insertStudent(tit,sName,fName,emai,tuto,accout,dee);
								System.out.println("Success");
								addStudent.dispose();
								try {
									students(main,fr);
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
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	


	
}

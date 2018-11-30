
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class Teachers extends UserInterface{

	String userName = null;
	String priviliges = null;
	Database db = new Database();

	public void teacherPage(String username,String priv) {
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
		JButton accounts=new JButton("- Students Grades -");
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
				grade(main,fr);
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

	public void grade(JPanel main,JFrame frame) throws Exception {
		String role ="Student_Grades";
		JTable table = db.displayTable(role);

		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		
		JButton addGrade= new JButton("Add grades");
		JButton updateGrade= new JButton("Update grades");
		JButton meanGrade= new JButton("Mean grade (Select a student to show its grade)");

		addGrade.setBounds(0,738, 200, 50);
		updateGrade.setBounds(250,738, 200, 50);
		meanGrade.setBounds(500,738, 400, 50);
		addGrades(addGrade,table);
		updateGrades(updateGrade,table);
		meanGrade(meanGrade,table);
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addGrade);
		main.add(updateGrade);
		main.add(meanGrade);

		main.add(jsp);
		
	}
	public void meanGrade(JButton bt,JTable table) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected != -1) {
					int regId =  Integer.parseInt((String) table.getValueAt(selected,1));
					int perId =  Integer.parseInt((String) table.getValueAt(selected,2));

					JFrame showMead=new JFrame("Mean grade");
					showMead.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					showMead.setLayout(null);
					showMead.setVisible(true);
					JPanel showMeadP=new JPanel();
					showMead.setLayout(null);
					showMead.setBounds(0,0,300,220);
					
					JTable meanGrade = db.meanGradeTable(regId,perId);
					
					
					
					
					
					showMead.setLocation(900,500);
					showMead.setSize(350,220);
					showMead.setVisible(true);
					showMead.add(showMeadP);
				}
				else
					JOptionPane.showMessageDialog(null, "You must select a grade!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
		});	
	}
	public void addGrades(JButton bt,JTable table) {
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addGr=new JFrame("Add grade");
				addGr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addGr.setLayout(null);
				addGr.setVisible(true);
				try {
				JPanel addGrP=new JPanel();
				addGrP.setLayout(null);
				addGrP.setBounds(0,0,300,220);
				
				
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
				addGr.setLocation(900,500);
				addGr.setSize(350,220);
				addGr.setVisible(true);
				addGr.add(addGrP);
				  okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							String mod = (String) allMod.getSelectedItem();
							Integer stu =  (Integer)stuIdBox.getSelectedItem();
							int grade =Integer.parseInt(grade1.getText());
							int per = Integer.parseInt((String) perBox.getSelectedItem());
							db.insertGrade(mod,stu,per,grade);
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
	public void updateGrades(JButton bt,JTable table) {
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selected = table.getSelectedRow();
				if(selected != -1) {
				int regId =  Integer.parseInt((String) table.getValueAt(selected,1));
				int modId =  Integer.parseInt((String) table.getValueAt(selected,0));

				JFrame upGr=new JFrame("Update grade");
				upGr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				upGr.setLayout(null);
				upGr.setVisible(true);
				JPanel upGrP=new JPanel();
				upGrP.setLayout(null);
				upGrP.setBounds(0,0,300,150);
				
				
			    JLabel gradeT = new JLabel("Grade Type:");
			    gradeT.setBounds(30, 20, 130, 20);
				JComboBox gradeType = new JComboBox();
				gradeType.addItem("Resit Grade");
				gradeType.addItem("Repeat Grade");
				gradeType.setBounds(170, 20, 130, 20);
				
				
			    JLabel grade = new JLabel("Grade:");
			    grade.setBounds(30, 50, 130, 20);
			    JTextField grade1 = new JTextField();
			    grade1.setBounds(170, 50, 130, 20);
				
				

			    
			    upGrP.add(gradeT);
			    upGrP.add(gradeType);
			    upGrP.add(grade);
			    upGrP.add(grade1);
				
				
				JButton okbtn = new JButton("Update");
				okbtn.setBounds(30, 80, 80, 20);
				JButton cancelbtn = new JButton("Cancel");
				cancelbtn.setBounds(220, 80, 80, 20);
				upGrP.add(okbtn);
				upGrP.add(cancelbtn);
				upGr.setLocation(900,500);
				upGr.setSize(350,150);
				upGr.setVisible(true);
				upGr.add(upGrP);
				  okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							String type = (String) gradeType.getSelectedItem();
							int grade = Integer.parseInt((String)grade1.getText());
							db.updateGrade(type, grade,regId,modId);
							System.out.println("Success");
							upGr.dispose();
										
						}
					});
					cancelbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							upGr.dispose();
						}
					});
			}
				else
					JOptionPane.showMessageDialog(null, "You must select a grade!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
	}

	

	
}

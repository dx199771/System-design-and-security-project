
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.table.JTableHeader;


public class Administrators extends UserInterface{
	Statement stmt =null;
	Connection con = null;  // a Connection object
	String userName = null;
	String priviliges = null;
	public void adminPage(String username,String priv) {
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
		JButton accounts=new JButton("- Accounts -");
		navAttribute(accounts);
		JButton departments=new JButton("- Departments -");
		navAttribute(departments);
		JButton degree=new JButton("- Degrees -");
		navAttribute(degree);
		JButton modules=new JButton("- Modules -");
		navAttribute(modules);
		
		//action listener
		profile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				profile(main,fr, userName, priviliges);
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
				accounts(main,fr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		});
		departments.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				departments(main,fr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
		degree.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				degrees(main,fr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
		modules.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				modules(main,fr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
		//add buttons to panel
		nav.add(profile);
		nav.add(accounts);
		nav.add(departments);
		nav.add(degree);
		nav.add(modules);
		return nav;
	}
	
	
	
	//add account method
	private void addAccount(JButton addAccounts) {
		addAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding account
				JFrame addAccount=new JFrame("Add account");
				addAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addAccount.setLayout(null);
				addAccount.setVisible(true);
				
				JPanel addAccountP=new JPanel();
				addAccountP.setLayout(null);
				addAccountP.setBounds(0,0,300,220);
				
			    JLabel accName = new JLabel("Account name:");
			    accName.setBounds(30, 40, 100, 20);
			    JTextField accName1 = new JTextField();
			    accName1.setBounds(140, 40, 100, 20);
			    JLabel password = new JLabel("Password:");
			    password.setBounds(30, 70, 100, 20);
			    JTextField password1 = new JTextField();
			    password1.setBounds(140, 70, 100, 20);
			    JLabel Privileges = new JLabel("Privileges:");
			    Privileges.setBounds(30, 100, 100, 20);
			    
			    //combo box for selecting privileges
				JComboBox PrivilegesBox=new JComboBox();
				PrivilegesBox.addItem("Administrators");
				PrivilegesBox.addItem("Registrars");
				PrivilegesBox.addItem("Teachers");
				PrivilegesBox.addItem("Students");
				PrivilegesBox.setBounds(140, 100, 100, 20);

				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 140, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 140, 80, 20);

			    addAccountP.add(accName);
			    addAccountP.add(accName1);
			    addAccountP.add(password);
			    addAccountP.add(password1);
			    addAccountP.add(Privileges);
			    addAccountP.add(PrivilegesBox);
			    addAccountP.add(okbtn);
			    addAccountP.add(cancelbtn);
			    addAccount.setLocation(900,500);
			    addAccount.setSize(290,220);
			    addAccount.setVisible(true);
				addAccount.add(addAccountP);
				
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String accName = accName1.getText();
							    String password = password1.getText();
							    String privil = (String) PrivilegesBox.getSelectedItem();
								
							    stmt = findDriver();
								String insertAccounts="INSERT INTO `Accounts` VALUES ('"+accName+"','"+password+"','"+privil+"')";
								System.out.print(privil);
								stmt.executeUpdate(insertAccounts);
								System.out.println("Success");
								stmt.close();
								addAccount.dispose();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addAccount.dispose();
					}
				});
		    }
		});
	}
	//add account method
	private void addDepartment(JButton addDepartments) {
		addDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding department
				JFrame addDepartment=new JFrame("Add department");
				addDepartment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addDepartment.setLayout(null);
				addDepartment.setVisible(true);
				
				JPanel addDepartmentP=new JPanel();
				addDepartmentP.setLayout(null);
				addDepartmentP.setBounds(0,0,300,220);
				
			    JLabel depName = new JLabel("Full name:");
			    depName.setBounds(30, 40, 100, 20);
			    JTextField depName1 = new JTextField();
			    depName1.setBounds(140, 40, 100, 20);
			    JLabel abbCode = new JLabel("Abbreviated code:");
			    abbCode.setBounds(30, 70, 100, 20);
			    JTextField abbCode1 = new JTextField();
			    abbCode1.setBounds(140, 70, 100, 20);

			    
			    

				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 140, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 140, 80, 20);

			    addDepartmentP.add(depName);
			    addDepartmentP.add(depName1);
			    addDepartmentP.add(abbCode);
			    addDepartmentP.add(abbCode1);
			    addDepartmentP.add(okbtn);
			    addDepartmentP.add(cancelbtn);
			    addDepartment.setLocation(900,500);
			    addDepartment.setSize(290,220);
			    addDepartment.setVisible(true);
			    addDepartment.add(addDepartmentP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String depName = depName1.getText();
							    String abbCode = abbCode1.getText();
								
							    stmt = findDriver();
								String insertDepartments="INSERT INTO `Departments` VALUES ('"+depName+"','"+abbCode+"','test');";
								stmt.executeUpdate(insertDepartments);
								System.out.println("Success");
								stmt.close();
								addDepartment.dispose();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addDepartment.dispose();
					}
				});
		    }
		});
	}
	private void addDegree(JButton addDegree) {
		addDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding department
				JFrame addDegree=new JFrame("Add degree");
				addDegree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addDegree.setLayout(null);
				addDegree.setVisible(true);
				
				JPanel addDegreeP=new JPanel();
				addDegreeP.setLayout(null);
				addDegreeP.setBounds(0,0,300,220);
				
			    JLabel deeName = new JLabel("Full name:");
			    deeName.setBounds(30, 20, 100, 20);
			    JTextField deeName1 = new JTextField();
			    deeName1.setBounds(140, 20, 100, 20);
			    JLabel abbCode = new JLabel("Code:");
			    abbCode.setBounds(30, 50, 100, 20);
			    JTextField abbCode1 = new JTextField();
			    abbCode1.setBounds(140, 50, 100, 20);
			    
			    JLabel leadDep = new JLabel("Lead department:");
			    leadDep.setBounds(30, 80, 100, 20);
			    JTextField leadDep1 = new JTextField();
			    leadDep1.setBounds(140, 80, 100, 20);
			    
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
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String deeName = deeName1.getText();
							    String abbCode = abbCode1.getText();
							    String leadDep = leadDep1.getText();
							    String level = (String) PrivilegesBox.getSelectedItem();

							    stmt = findDriver();
								String insertDepartments="INSERT INTO `Degrees` VALUES ('"+deeName+"','"+abbCode+"','"+leadDep+"','"+level+"'+'test');";
								stmt.executeUpdate(insertDepartments);
								System.out.println("Success");
								stmt.close();
								addDegree.dispose();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addDegree.dispose();
					}
				});
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
				String getname= table.getValueAt(selected, 0).toString();
				try {
				stmt = findDriver();
				
				System.out.print(getname);	
				String removeName;
				
				if(compName == "Accounts") {
					removeName= "DELETE FROM "+compName+" WHERE `Username` = '"+getname+"'"; 
					System.out.print(removeName);
				
				}else {
					removeName= "DELETE FROM "+compName+" WHERE `Full name` = '"+getname+"'"; 
				System.out.print(removeName);

				}
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
	private void linkDegree(JButton remmovebt) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet depRs;
				//new frame for linking degree
				JFrame linkDegree=new JFrame("Link degree");
				linkDegree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				linkDegree.setLayout(null);
				linkDegree.setVisible(true);
				
				JPanel linkDegreeP=new JPanel();
				linkDegreeP.setLayout(null);
				linkDegreeP.setBounds(0,0,300,220);
				
			    JLabel depName = new JLabel("Department name:");
			    depName.setBounds(30, 20, 130, 20);

			   
			    //combo box for selecting level of study
				JComboBox PrivilegesBox=new JComboBox();
				PrivilegesBox.setBounds(170, 20, 130, 20);
			    
		
				
				try {
					//find drivers
				    stmt = findDriver();
					String sql ="select `Full name` from Departments";
					depRs = stmt.executeQuery(sql);
					//String first = depRs.getString("Full name");

					ResultSetMetaData rsmd= (ResultSetMetaData) depRs.getMetaData();
					if(!(depRs.next()))
					{
					   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						PrivilegesBox.addItem(depRs.getString("Full name"));

						while(depRs.next()) {
							PrivilegesBox.addItem(depRs.getString("Full name"));
						}
					}
					depRs.close();

					stmt.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}		


				
				
				
			    JButton okbtn = new JButton("Link");
			    okbtn.setBounds(30, 140, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(220, 140, 80, 20);

			    linkDegreeP.add(depName);

			    linkDegreeP.add(PrivilegesBox);

			    linkDegreeP.add(okbtn);
			    linkDegreeP.add(cancelbtn);
			    linkDegree.setLocation(900,500);
			    linkDegree.setSize(350,220);
			    linkDegree.setVisible(true);
			    linkDegree.add(linkDegreeP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {

							    String level = (String) PrivilegesBox.getSelectedItem();

							    stmt = findDriver();
								String insertDepartments="INSERT INTO `Degrees` VALUES ('"+deeName+"','"+abbCode+"','"+level+"','test');";
								stmt.executeUpdate(insertDepartments);
								System.out.println("Success");
								stmt.close();
								linkDegree.dispose();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						linkDegree.dispose();
					}
				});
			}
			});
		
	}
	public void accounts(JPanel main,JFrame frame) throws Exception {
		ResultSet accountsRs;
		
		
		//find drivers
		stmt = super.findDriver();

		String sql ="select * from Accounts";
		accountsRs = stmt.executeQuery(sql);
		if(!(accountsRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) accountsRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));
		}
		do{
		     rows.addElement(getNextRow(accountsRs,rsmd));
		}while(accountsRs.next());
			

		
		

		
		JTable table = new JTable(rows,columnHeads);
		JScrollPane jsp= new JScrollPane(table);
	  
		jsp.setSize(new Dimension(1577, 588));
		accountsRs.close();		
		stmt.close();
	    //
	    
		JButton removeAccounts= new JButton("Remove account");
		JButton addAccounts= new JButton("Add account");
		addAccounts.setBounds(0,738, 200, 50);
		removeAccounts.setBounds(250,738, 200, 50);
		//event listner
		String itemName = "Accounts";
		addAccount(addAccounts);
		removeItem(removeAccounts,table, itemName);

		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addAccounts);
		main.add(removeAccounts);
		main.add(jsp,BorderLayout.CENTER);
		
		
	}
	public void departments(JPanel main,JFrame frame) throws Exception {
		ResultSet departmentsRs;
		
		
		//find drivers
		stmt = super.findDriver();
		String sql ="select * from Departments";
		departmentsRs = stmt.executeQuery(sql);
		if(!(departmentsRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) departmentsRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));//
		}
		do{
		     rows.addElement(getNextRow(departmentsRs,rsmd));//
		}while(departmentsRs.next());
			

		
		
		JTable table = new JTable(rows,columnHeads);	
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		
		departmentsRs.close();		
		stmt.close();


		JButton removeDepartments= new JButton("Remove department");
		JButton addDepartmentsB= new JButton("Add department");
		addDepartmentsB.setBounds(0,738, 200, 50);
		removeDepartments.setBounds(250,738, 200, 50);
		String itemName = "Departments";

		addDepartment(addDepartmentsB);
		removeItem(removeDepartments,table, itemName);


		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDepartmentsB);
		main.add(removeDepartments);
		main.add(jsp);
		
		
	}
	public void degrees(JPanel main,JFrame frame) throws Exception {
		ResultSet degreesRs;
		
		
		//find drivers
		stmt = super.findDriver();
		String sql ="select * from Degrees";
		degreesRs = stmt.executeQuery(sql);
		if(!(degreesRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) degreesRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));//
		}
		do{
		     rows.addElement(getNextRow(degreesRs,rsmd));//
		}while(degreesRs.next());
			

		
		
		JTable table = new JTable(rows,columnHeads);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		degreesRs.close();		
		stmt.close();

	    
		JButton removeDegrees= new JButton("Remove degree");
		JButton addDegrees= new JButton("Add degree");
		JButton linkDegrees= new JButton("Link degree");

		addDegrees.setBounds(0,738, 200, 50);
		removeDegrees.setBounds(250,738, 200, 50);
		linkDegrees.setBounds(500,738, 200, 50);
		
		
		linkDegree(linkDegrees);
		addDegree(addDegrees);
		String itemName = "Degrees";
		removeItem(removeDegrees,table, itemName);

		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDegrees);
		main.add(removeDegrees);
		main.add(linkDegrees);
		main.add(jsp);
		
		
	}
	public void modules(JPanel main,JFrame frame) throws Exception {
		ResultSet modulesRs;
		
		
		//find drivers
		stmt = super.findDriver();
		String sql ="select * from Modules";
		modulesRs = stmt.executeQuery(sql);
		if(!(modulesRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) modulesRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));//
		}
		do{
		     rows.addElement(getNextRow(modulesRs,rsmd));//
		}while(modulesRs.next());
			

		
		
		JTable table = new JTable(rows,columnHeads);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		modulesRs.close();		
		stmt.close();

	    
		JButton removeDegrees= new JButton("Remove degree");
		JButton addDegrees= new JButton("Add degree");
		JButton linkDegrees= new JButton("Link degree");

		addDegrees.setBounds(0,738, 200, 50);
		removeDegrees.setBounds(250,738, 200, 50);
		linkDegrees.setBounds(500,738, 200, 50);
		
		
		linkDegree(linkDegrees);
		addDegree(addDegrees);
		String itemName = "Degrees";
		removeItem(removeDegrees,table, itemName);

		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDegrees);
		main.add(removeDegrees);
		main.add(linkDegrees);
		main.add(jsp);
		
		
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
	
	//update UI
	public void updateJSwing(JPanel main) {
		main.removeAll();
		main.repaint();
		main.updateUI();
	}
	
	//get next row in database table
	private Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow=new Vector();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
	
}

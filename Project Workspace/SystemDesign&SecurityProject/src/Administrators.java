
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class Administrators extends UserInterface{

	String userName = null;
	String priviliges = null;
	Database db = new Database();
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
				PrivilegesBox.addItem("Administrator");
				PrivilegesBox.addItem("Registrar");
				PrivilegesBox.addItem("Teacher");
				PrivilegesBox.addItem("Student");
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
								
								db.insertAccount(accName,password,privil);
								System.out.println("Success added one user");
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
								
								db.insertDepa(depName, abbCode);
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
				addDegreeP.setBounds(0,0,290,250);
				
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
			    

			    
			    JLabel level = new JLabel("Years:");
			    level.setBounds(30, 110, 100, 20);
			    //combo box for selecting level of study
				JComboBox PrivilegesBox=new JComboBox();
				PrivilegesBox.addItem("1");
				PrivilegesBox.addItem("2");
				PrivilegesBox.addItem("3");
				PrivilegesBox.addItem("4");
				PrivilegesBox.addItem("P");
				PrivilegesBox.setBounds(140, 110, 100, 20);
			    
			    JLabel industry = new JLabel("Year in industry:");
			    industry.setBounds(30, 140, 100, 20);
			    JCheckBox industry1 = new JCheckBox();
			    industry1.setBounds(140, 140, 100, 20);
				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 170, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 170, 80, 20);

			    addDegreeP.add(deeName);
			    addDegreeP.add(deeName1);
			    addDegreeP.add(abbCode);
			    addDegreeP.add(abbCode1);
			    addDegreeP.add(level);
			    addDegreeP.add(leadDep);
			    addDegreeP.add(leadDep1);

			    addDegreeP.add(PrivilegesBox);
			    addDegreeP.add(industry);
			    addDegreeP.add(industry1);

			    addDegreeP.add(okbtn);
			    addDegreeP.add(cancelbtn);
			    addDegree.setLocation(900,500);
			    addDegree.setSize(290,250);
			    addDegree.setVisible(true);
			    addDegree.add(addDegreeP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String deeName = deeName1.getText();
							    String abbCode = abbCode1.getText();
							    String level = (String) PrivilegesBox.getSelectedItem();
							    Boolean selected =industry1.isSelected();

								db.insertDee(deeName,abbCode,level,selected);
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
				int getNumber= Integer.parseInt( (String) table.getValueAt(selected, 0));
				db.removeItem(compName, getNumber);
				}
			}
		});
	}
	private void linkDegree(JButton remmovebt,JTable table) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox depBox = null;

				int selected = table.getSelectedRow();
				try {
					depBox = db.getPBox("department");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				if(selected != -1) {
				//new frame for linking degree
				JFrame linkDegree=new JFrame("Link degree");
				linkDegree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				linkDegree.setLayout(null);
				linkDegree.setVisible(true);
				
				JPanel linkDegreeP=new JPanel();
				linkDegreeP.setLayout(null);
				linkDegreeP.setBounds(0,0,300,220);
				
			    JLabel depName = new JLabel("Lead department name:");
			    depName.setBounds(30, 20, 130, 20);
			    JLabel leadepName = new JLabel("Department name:");
			    leadepName.setBounds(30, 50, 130, 20);
			    depBox.setBounds(170, 50, 130, 20);
			    //combo box for selecting level of study
			    linkDegreeP.add(depBox);
			    linkDegreeP.add(leadepName);

				JComboBox PrivilegesBox;
				try {
					final String lead = (String)depBox.getSelectedItem();
					PrivilegesBox = db.getPBox("department");
					PrivilegesBox.setBounds(170, 20, 130, 20);
					linkDegreeP.add(PrivilegesBox);
					int degId =  Integer.parseInt((String) table.getValueAt(selected,0));
					String yearS = (String) table.getValueAt(selected,3);


				    JButton okbtn = new JButton("Link");
				    okbtn.setBounds(30, 140, 80, 20);
				    JButton cancelbtn = new JButton("Cancel");
				    cancelbtn.setBounds(220, 140, 80, 20);

				    linkDegreeP.add(depName);


				    linkDegreeP.add(okbtn);
				    linkDegreeP.add(cancelbtn);
				    linkDegree.setLocation(900,500);
				    linkDegree.setSize(350,220);
				    linkDegree.setVisible(true);
				    linkDegree.add(linkDegreeP);
					okbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {


								    String level = (String) PrivilegesBox.getSelectedItem();
								    try {
										db.linkDee(degId, level,lead);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									System.out.println("Success");
									linkDegree.dispose();
								
								
						}
					});
					cancelbtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							linkDegree.dispose();
						}
					});
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				}
				else
					JOptionPane.showMessageDialog(null, "You must select a deegre!", "No data",JOptionPane.INFORMATION_MESSAGE);

			}
			});
		
	}
	private void linkMod(JButton remmovebt,JTable table) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox deeBox = null;
				int selected = table.getSelectedRow();
				int degId =  Integer.parseInt((String) table.getValueAt(selected,0));

				try {
					deeBox = db.getPBox("degree");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(selected != -1) {
				JFrame linkMod=new JFrame("Link module");
				linkMod.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				linkMod.setLayout(null);
				linkMod.setVisible(true);
				JPanel linkModP=new JPanel();
				linkModP.setLayout(null);
				linkModP.setBounds(0,0,350,220);
				
				JLabel deeName = new JLabel("Degree name:");
				deeName.setBounds(30, 30, 130, 20);	

				deeBox.setBounds(170, 30, 130, 20);
				
				JLabel credits = new JLabel("Credits:");
				credits.setBounds(30, 60, 130, 20);
				JTextField credits1 =new JTextField();
				credits1.setBounds(170, 60, 130, 20);

				JLabel term = new JLabel("Term:");
				term.setBounds(30, 90, 130, 20);
				JComboBox term1=new JComboBox();
				term1.addItem("Autumn");
				term1.addItem("Spring");
				term1.addItem("Year");
				term1.setBounds(170, 90, 130, 20);
				String name = (String)deeBox.getSelectedItem();

				JLabel core = new JLabel("Core:");
				core.setBounds(30, 120, 130, 20);
				JCheckBox core1 = new JCheckBox();
				core1.setBounds(170, 120, 130, 20);
				
				JButton okbtn = new JButton("Link");
			    okbtn.setBounds(30, 150, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(220, 150, 80, 20);

			    linkModP.add(deeName);
			    linkModP.add(deeBox);
			    linkModP.add(credits);
			    linkModP.add(credits1);
			    linkModP.add(term);
			    linkModP.add(term1);
			    linkModP.add(core);
			    linkModP.add(core1);
			    linkModP.add(okbtn);
			    linkModP.add(cancelbtn);
			    linkMod.setLocation(900,500);
			    linkMod.setSize(350,220);
			    linkMod.setVisible(true);
			    linkMod.add(linkModP);
			    okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String cre = credits1.getText();
						String term = (String) term1.getSelectedItem();
						boolean core = core1.isSelected();
						try {
							db.linkMod(degId,name, cre, term,core);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Success");
						linkMod.dispose();			
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						linkMod.dispose();
					}
				});
				}
				else
					JOptionPane.showMessageDialog(null, "You must select a module!", "No data",JOptionPane.INFORMATION_MESSAGE);

			}
		});
	}
	public void accounts(JPanel main,JFrame frame) throws Exception {

		String role ="accounts";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);
		jsp.setSize(new Dimension(1577, 588));
	    
		JButton removeAccounts= new JButton("Remove account");
		JButton addAccounts= new JButton("Add account");
		addAccounts.setBounds(0,738, 200, 50);
		removeAccounts.setBounds(250,738, 200, 50);
		//event listener
		String itemName = "accounts";
		addAccount(addAccounts);
		removeItem(removeAccounts,table, itemName);

		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addAccounts);
		main.add(removeAccounts);
		main.add(jsp,BorderLayout.CENTER);
		
	}
	public void departments(JPanel main,JFrame frame) throws Exception {
		
		String role ="department";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		
		JButton removeDepartments= new JButton("Remove department");
		JButton addDepartmentsB= new JButton("Add department");
		addDepartmentsB.setBounds(0,738, 200, 50);
		removeDepartments.setBounds(250,738, 200, 50);
		String itemName = "department";

		addDepartment(addDepartmentsB);
		removeItem(removeDepartments,table, itemName);

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDepartmentsB);
		main.add(removeDepartments);
		main.add(jsp);
		
		
	}
	public void degrees(JPanel main,JFrame frame) throws Exception {
		
		String role ="degree";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
   
		JButton removeDegrees= new JButton("Remove degree");
		JButton addDegrees= new JButton("Add degree");
		JButton linkDegrees= new JButton("Link degree");

		addDegrees.setBounds(0,738, 200, 50);
		removeDegrees.setBounds(250,738, 200, 50);
		linkDegrees.setBounds(500,738, 200, 50);
		
		linkDegree(linkDegrees,table);
		addDegree(addDegrees);
		String itemName = "degree";
		removeItem(removeDegrees,table, itemName);

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDegrees);
		main.add(removeDegrees);
		main.add(linkDegrees);
		main.add(jsp);
			
	}
	public void modules(JPanel main,JFrame frame) throws Exception {		
		
		String role ="module";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
	 
		JButton removeMod= new JButton("Remove module");
		JButton addMod= new JButton("Add module");
		JButton linkMod= new JButton("Link module");

		addMod.setBounds(0,738, 200, 50);
		removeMod.setBounds(250,738, 200, 50);
		linkMod.setBounds(500,738, 200, 50);
		
		linkMod(linkMod,table);
		addDegree(addMod);
		String itemName = "module";
		removeItem(removeMod,table, itemName);
		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addMod);
		main.add(removeMod);
		main.add(linkMod);
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
	

	
}

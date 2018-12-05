
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
		frame.setResizable(false);

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
	private void addAccount(JButton addAccounts,JPanel main,JFrame fr) {
		addAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding account
				JFrame addAccount=new JFrame("Add account");
				addAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addAccount.setLayout(null);
				addAccount.setVisible(true);
				addAccount.setResizable(false);

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
							    String password = SecurityHandler.hashPassword(password1.getText());
							    String privil = (String) PrivilegesBox.getSelectedItem();
								int privilInt = 0;
								if(privil=="Administrator")
									privilInt= 1;
								else if(privil=="Registrar")
									privilInt= 2;
								else if(privil=="Teacher")
									privilInt= 3;
								else if(privil=="Student")
									privilInt= 4;
								db.insertAccount(accName,password,privilInt);
								addAccount.dispose();
								accounts(main,fr);
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
	private void addDepartment(JButton addDepartments,JPanel main,JFrame fr) {
		addDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding department
				JFrame addDepartment=new JFrame("Add department");
				addDepartment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addDepartment.setLayout(null);
				addDepartment.setVisible(true);
				addDepartment.setResizable(false);

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
								departments(main,fr);

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
	private void addDegree(JButton addDegree,JPanel main,JFrame fr) {
		addDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding department
				JFrame addDegree=new JFrame("Add degree");
				addDegree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			    try {
			    JLabel enDep = new JLabel("Entry:");
			    enDep.setBounds(30, 80, 100, 20);
			    JComboBox entry = new JComboBox();
			    entry.addItem("U");
			    entry.addItem("P");
			    entry.setBounds(140, 80, 100, 20);
			    
			    addDegree.setResizable(false);

			    
			    JLabel level = new JLabel("Level:");
			    level.setBounds(30, 110, 100, 20);
			    //combo box for selecting level of study
				
				JComboBox studyId = db.getPBox("Level_of_Study");
				

				studyId.setBounds(140, 110, 100, 20);
			    

				
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 170, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 170, 80, 20);

			    addDegreeP.add(deeName);
			    addDegreeP.add(deeName1);
			    addDegreeP.add(abbCode);
			    addDegreeP.add(abbCode1);
			    addDegreeP.add(level);
			    addDegreeP.add(enDep);
			    addDegreeP.add(entry);

			    addDegreeP.add(studyId);


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
							    char entry1 = ((String) entry.getSelectedItem()).charAt(0);

							    String level = (String) studyId.getSelectedItem();

								db.insertDee(deeName,abbCode,entry1,level);
								addDegree.dispose();
								degrees(main,fr);
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
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		    }
		});
	}
	private void addModu(JButton addButton,JPanel main,JFrame fr) {
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new frame for adding department
				JFrame addModule=new JFrame("Add module");
				addModule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addModule.setLayout(null);
				addModule.setVisible(true);
				
				JPanel addModuleP=new JPanel();
				addModuleP.setLayout(null);
				addModuleP.setBounds(0,0,290,250);
				
			    JLabel depName = new JLabel("Full name:");
			    depName.setBounds(30, 40, 100, 20);
			    JTextField depName1 = new JTextField();
			    depName1.setBounds(140, 40, 100, 20);
			    JLabel abbCode = new JLabel("Abbreviated code:");
			    abbCode.setBounds(30, 70, 100, 20);
			    JTextField abbCode1 = new JTextField();
			    abbCode1.setBounds(140, 70, 100, 20);
			    addModule.setResizable(false);

			    
			    try {
			    JLabel abbCre = new JLabel("Credits:");
			    abbCre.setBounds(30, 100, 100, 20);
			     
				
				JComboBox abbCre1 = db.getPBox("Credits");
				abbCre1.setBounds(140, 100, 100, 20);
			    
			    JLabel time = new JLabel("Time:");
			    time.setBounds(30, 130, 100, 20);
				JComboBox time1 = db.getPBox("Teaching_Time");
				time1.setBounds(140, 130, 100, 20);
			    
			    
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 160, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(160, 160, 80, 20);

			    addModuleP.add(depName);
			    addModuleP.add(depName1);
			    addModuleP.add(abbCode);
			    addModuleP.add(abbCode1);
			    addModuleP.add(abbCre);
			    addModuleP.add(abbCre1);
			    addModuleP.add(time);
			    addModuleP.add(time1);
			    
			    addModuleP.add(okbtn);
			    addModuleP.add(cancelbtn);
			    addModule.setLocation(900,500);
			    addModule.setSize(290,250);
			    addModule.setVisible(true);
			    addModule.add(addModuleP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								String modName = depName1.getText();
							    String abbCode = abbCode1.getText();
							    int cre = Integer.parseInt((String)abbCre1.getSelectedItem());
							    String time = (String)time1.getSelectedItem();

								db.insertModule(modName, abbCode,cre,time);
								addModule.dispose();
								modules(main,fr);

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}							
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addModule.dispose();
					}
				});
				}catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		    }
		});
	}
	private void linkDegree(JButton remmovebt,JTable table,JPanel main,JFrame fr) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selected = table.getSelectedRow();


				if(selected != -1) {
					try {
				int degId =  Integer.parseInt((String) table.getValueAt(selected,0));

				//new frame for linking degree
				JFrame linkDegree=new JFrame("Link degree");
				linkDegree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				linkDegree.setLayout(null);
				linkDegree.setVisible(true);
				
				JPanel linkDegreeP=new JPanel();
				linkDegreeP.setLayout(null);
				linkDegreeP.setBounds(0,0,300,220);
				linkDegree.setResizable(false);


			    
			    
			    JLabel leadepName = new JLabel("Department name:");
			    leadepName.setBounds(30, 20, 130, 20);
				JComboBox depBox = db.getPBox("Department");
			    depBox.setBounds(170, 20, 130, 20);
			    
			    JLabel depName = new JLabel("Lead department?:");
			    depName.setBounds(30, 50, 130, 20);
			    JCheckBox lead = new JCheckBox();
			    lead.setBounds(170, 50, 130, 20);
			    //combo box for selecting level of study

				JButton okbtn = new JButton("Link");
				okbtn.setBounds(30, 140, 80, 20);
				JButton cancelbtn = new JButton("Cancel");
				cancelbtn.setBounds(220, 140, 80, 20);

				linkDegreeP.add(depName);
			    linkDegreeP.add(depBox);
			    linkDegreeP.add(leadepName);
			    linkDegreeP.add(lead);
				linkDegreeP.add(okbtn);
				linkDegreeP.add(cancelbtn);
				linkDegree.setLocation(900,500);
				linkDegree.setSize(350,220);
				linkDegree.setVisible(true);
				linkDegree.add(linkDegreeP);
				okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						boolean lead1 = lead.isSelected();
						String level = (String) depBox.getSelectedItem();
						try {
							db.linkDee(degId, level,lead1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Success");
						linkDegree.dispose();
						try {
							degrees(main,fr);
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
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				}
				
				else
					JOptionPane.showMessageDialog(null, "You must select a degree!", "No data",JOptionPane.INFORMATION_MESSAGE);

			}
			});
		
	}
	private void linkMod(JButton remmovebt,JTable table,JPanel main,JFrame fr) throws Exception {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();


				if(selected != -1) {
					int modId =  Integer.parseInt((String) table.getValueAt(selected,0));

				try {
				//new frame for linking degree
				JFrame linkMod=new JFrame("Link module");
				linkMod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				linkMod.setLayout(null);
				linkMod.setVisible(true);
				
				JPanel linkModP=new JPanel();
				linkModP.setLayout(null);
				linkModP.setBounds(0,0,300,220);
				
				linkMod.setResizable(false);

			    
			    
			    JLabel leadeeName = new JLabel("Degree name:");
			    leadeeName.setBounds(30, 20, 130, 20);
				JComboBox deeBox = db.getPBox("Degree");
				deeBox.setBounds(170, 20, 130, 20);
			    
			    JLabel deeName = new JLabel("Core:");
			    deeName.setBounds(30, 50, 130, 20);
			    JCheckBox core = new JCheckBox();
			    core.setBounds(170, 50, 130, 20);
			    //combo box for selecting level of study

				JButton okbtn = new JButton("Link");
				okbtn.setBounds(30, 140, 80, 20);
				JButton cancelbtn = new JButton("Cancel");
				cancelbtn.setBounds(220, 140, 80, 20);

				linkModP.add(deeName);
				linkModP.add(deeBox);
			    linkModP.add(leadeeName);
			    linkModP.add(core);
			    linkModP.add(okbtn);
				linkModP.add(cancelbtn);
				linkMod.setLocation(900,500);
				linkMod.setSize(350,220);
				linkMod.setVisible(true);
				linkMod.add(linkModP);
			    okbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String level = (String) deeBox.getSelectedItem();
						boolean core1 = core.isSelected();
						try {
							db.linkMod(modId, level,core1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Success");
						linkMod.dispose();
						try {
							modules(main,fr);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

									
					}
				});
				cancelbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						linkMod.dispose();
					}
				});
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				}
				else
					JOptionPane.showMessageDialog(null, "You must select a module!", "No data",JOptionPane.INFORMATION_MESSAGE);

			}
		});
	}
	public void accounts(JPanel main,JFrame frame) throws Exception {

		String role ="Login_Details";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);
		jsp.setSize(new Dimension(1577, 588));
	    
		JButton removeAccounts= new JButton("Remove account");
		JButton addAccounts= new JButton("Add account");
		addAccounts.setBounds(0,738, 200, 50);
		removeAccounts.setBounds(250,738, 200, 50);
		//event listener
		String itemName = "Login_Details";
		addAccount(addAccounts,main,frame);
		removeItem(removeAccounts,table, itemName,main,frame);

		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addAccounts);
		main.add(removeAccounts);
		main.add(jsp,BorderLayout.CENTER);
		
	}
	public void departments(JPanel main,JFrame frame) throws Exception {
		
		String role ="Department";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
		
		JButton removeDepartments= new JButton("Remove department");
		JButton addDepartmentsB= new JButton("Add department");
		addDepartmentsB.setBounds(0,738, 200, 50);
		removeDepartments.setBounds(250,738, 200, 50);
		String itemName = "Department";

		addDepartment(addDepartmentsB,main,frame);
		removeItem(removeDepartments,table, itemName,main,frame);

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDepartmentsB);
		main.add(removeDepartments);
		main.add(jsp);
		
		
	}
	public void degrees(JPanel main,JFrame frame) throws Exception {
		
		String role ="Degree";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
   
		JButton removeDegrees= new JButton("Remove degree");
		JButton addDegrees= new JButton("Add degree");
		JButton linkDegrees= new JButton("Link degree");

		addDegrees.setBounds(0,738, 200, 50);
		removeDegrees.setBounds(250,738, 200, 50);
		linkDegrees.setBounds(500,738, 200, 50);
		
		linkDegree(linkDegrees,table,main,frame);
		addDegree(addDegrees,main,frame);
		String itemName = "Degree";
		removeItem(removeDegrees,table, itemName,main,frame);

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addDegrees);
		main.add(removeDegrees);
		main.add(linkDegrees);
		main.add(jsp);
			
	}
	public void modules(JPanel main,JFrame frame) throws Exception {		
		
		String role ="Module";
		JTable table = db.displayTable(role);
		JScrollPane jsp= new JScrollPane(table);

		jsp.setSize(new Dimension(1577, 588));
	 
		JButton removeMod= new JButton("Remove module");
		JButton addMod= new JButton("Add module");
		JButton linkMod= new JButton("Link module");

		addMod.setBounds(0,738, 200, 50);
		removeMod.setBounds(250,738, 200, 50);
		linkMod.setBounds(500,738, 200, 50);
		
		linkMod(linkMod,table,main,frame);
		addModu(addMod,main,frame);
		String itemName = "Module";
		removeItem(removeMod,table, itemName,main,frame);
		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addMod);
		main.add(removeMod);
		main.add(linkMod);
		main.add(jsp);	
	}

	

	

	
}

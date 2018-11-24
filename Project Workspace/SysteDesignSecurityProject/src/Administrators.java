
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class Administrators {
	Statement stmt = null;
	Connection con = null;  // a Connection object
	FindDrivers fd = new FindDrivers();
	public void adminPage() {
		
		//admin page main frame set up
		JFrame frame=new JFrame();
		
		//background image set up
		ImageIcon icon=new ImageIcon("src\\images\\admin.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));	
		
		//get container and set to invisible
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		//
		JPanel main =new JPanel();
		//profile(main,frame);
		
		navigation(frame,main);
		
		
		
		frame.add(main);
		frame.add(navigation(frame,main));

	}
	
	public JPanel navigation(JFrame fr, JPanel main) {
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
				main.removeAll();
				main.repaint();
				main.updateUI();
				profile(main,fr);

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
				//departments(main,fr);
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
				//degree(main,fr);
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
				//modules(main,fr);
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
	
	
	//profile page
	public void profile(JPanel main,JFrame frame) {
		//get name and privileges from database;
		String username = "";
		String privileges = "";

		JLabel welcome =new JLabel("Hi, "+username); 
		welcome.setFont(new java.awt.Font("Dialog", 1, 60));
		welcome.setBounds(20, 50, 700, 500);
		JLabel welcome2 =new JLabel("Welcome to information system"); 
		welcome2.setFont(new java.awt.Font("Dialog", 1, 40));
		welcome2.setBounds(20, 110, 700, 500);
		JLabel welcome3 =new JLabel("Your pribileges is "+privileges); 
		welcome3.setFont(new java.awt.Font("Dialog", 1, 40));
		welcome3.setBounds(20, 110, 700, 500);
		
		main.setLayout(null);
		main.setBounds(343,146, 1577, 788);
		main.setOpaque(false);
		main.add(welcome);
		main.add(welcome2);
	}
	//add account method
	public void addAccount(JButton addAccounts) {
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
								fd.findDrivers();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							String accName = accName1.getText();
						    String password = password1.getText();
						    String privil = (String) PrivilegesBox.getSelectedItem();
							String insertAccounts="INSERT INTO `Accounts` VALUES ("+accName+","+password+","+privil+");";
							System.out.print(privil);
							try {
								stmt.executeUpdate(insertAccounts);
								System.out.println("Success");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								stmt.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							addAccount.dispose();
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
	public void removeAccount() {
		
	}
	
	public void accounts(JPanel main,JFrame frame) throws Exception {
		ResultSet accountsRs;
		
		
		//find drivers
		fd.findDrivers();
		String sql ="select * from Accounts";
		accountsRs = stmt.executeQuery(sql);
		if(!(accountsRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "1", "1",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) accountsRs.getMetaData();
		Vector rows = new Vector();
		Vector columnHeads=new Vector();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));//
		}
		do{
		     rows.addElement(getNextRow(accountsRs,rsmd));//
		}while(accountsRs.next());
		
		JButton remove = new JButton("remove");
		columnHeads.addElement("remove");
				

		
		
			JTable table = new JTable(rows,columnHeads);	
			table.setSize(new Dimension(1577, 788));// 
			accountsRs.close();		
	    stmt.close();
		con.close();
	    //
	    
	    
	    

		JButton addAccounts= new JButton("Add account");
		addAccounts.setBounds(0,738, 200, 50);
		addAccount(addAccounts);
		
		
		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addAccounts);
		main.add(table);
		
		
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
	
	public static void main(String[] args) 
	{
		Administrators admin = new Administrators();
		admin.adminPage();
	}	
	
	
}

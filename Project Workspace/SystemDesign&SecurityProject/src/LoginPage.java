import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
 
public class  LoginPage{
	Statement stmt = null;
	Connection con = null;  // a Connection object
	FindDrivers getcon = new FindDrivers();
	String username = null;
	String Privileges = null;
	public LoginPage(){
	
		ImageIcon icon=new ImageIcon("src\\images\\t1.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		JFrame frame=new JFrame();
		
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);
 			
				
		JPanel panel=new JPanel();
		
        panel.setLayout(null);
        
        
        JLabel usernameIcon = new JLabel(new ImageIcon("src\\images\\username.jpg"));
        usernameIcon.setBounds(40,20,150,40);

		JTextField userNameTF=new JTextField(10);
		userNameTF.setBounds(80,20,200,40);
		//userNameTF.setPlaceholder = @"User name";
        JLabel passwordIcon = new JLabel(new ImageIcon("src\\images\\password.jpg"));
        passwordIcon.setBounds(40,70,150,40);

        JPasswordField passwordTF=new JPasswordField(10);
		passwordTF.setBounds(80,70,200,40);

        
		JButton jb=new JButton("Log In");
		jb.setBackground(new Color(51,153,255));
		jb.setBounds(40, 140, 240, 40);
		jb.setForeground(Color.WHITE);
		jb.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.setBounds(1354,450,300,700);
		
		panel.add(userNameTF);
		panel.add(passwordTF);
		panel.add(jb);
		panel.add(usernameIcon);
		panel.add(passwordIcon);

		panel.setOpaque(false);
		
		

		frame.add(panel);
		
		frame.setLayout(null);

		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
 
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = null;
					username = userNameTF.getText();
					String password = passwordTF.getText(); 
					//User authentication
					findDriver();
					String rsString = "select `Privileges` from `Accounts` where `Username`='"+userNameTF.getText()+"' and `Password`='"+passwordTF.getText()+"'";
					rs = stmt.executeQuery(rsString);

					if(rs.next()) {
						if(rs.getString("Privileges").equals("Administrators")) {
							Privileges = "Administrators";
							Administrators admin = new Administrators();
							admin.adminPage(getUserName(),getPriviliges());
							frame.dispose();
							}
							else if(rs.getString("Privileges").equals("Registrars")){
							Privileges = "Registrars";
						    Registrars regis = new Registrars();
						    regis.registarPage(getUserName(),getPriviliges());
							frame.dispose();
							}
							else if(rs.getString("Privileges").equals("Teachers")){
							Privileges = "Teachers";
							//Teachers teacher = new Teachers(getUserName(),getPriviliges());
							//teacher.teacherPage();
							frame.dispose();
							}
							else if(rs.getString("Privileges").equals("Students")){
							Privileges = "Students";
							//Students student = new Students(getUserName(),getPriviliges());
							//student.studentPage();
							}
							
						}
					else
						JOptionPane.showMessageDialog(null, "Invalid username or password", "Login failed",JOptionPane.WARNING_MESSAGE);  
					con.close();
					stmt.close();
					}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
			});


	}
	public String getUserName() {
		return username;
	}
	public String getPriviliges() {
		return Privileges;
	}
	//find Driver
	public void findDriver(){
		try {
			  con = DriverManager.getConnection(
			  		"jdbc:mysql://stusql.dcs.shef.ac.uk/team031", "team031", "4934b78c"); 
			  
			  stmt = con.createStatement();
			  	
			}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new LoginPage();
	}
}

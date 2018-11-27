import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public abstract class UserInterface {
	Statement stmt = null;
	Connection con = null;  // a Connection object
	
	public void profile(JPanel main,JFrame frame,String username,String privileges) {
		//get name and privileges from database;



		JLabel welcome =new JLabel("Hi, "+username); 
		welcome.setFont(new java.awt.Font("Dialog", 1, 60));
		welcome.setBounds(20, 50, 700, 500);
		JLabel welcome2 =new JLabel("Welcome to information system"); 
		welcome2.setFont(new java.awt.Font("Dialog", 1, 40));
		welcome2.setBounds(20, 110, 700, 500);
		JLabel welcome3 =new JLabel("Your privileges is "+privileges); 
		welcome3.setFont(new java.awt.Font("Dialog", 1, 40));
		welcome3.setBounds(20, 170, 700, 500);
		
		main.setLayout(null);
		main.setBounds(343,146, 1577, 788);
		main.setOpaque(false);
		main.add(welcome);
		main.add(welcome2);
		main.add(welcome3);

	}

	
	//find Driver
	public Statement findDriver(){
		try {
			  con = DriverManager.getConnection(
			  		"jdbc:mysql://stusql.dcs.shef.ac.uk/team031", "team031", "4934b78c"); 
			  
			  stmt = con.createStatement();
			  	
			}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
		return stmt;
	}
	
}
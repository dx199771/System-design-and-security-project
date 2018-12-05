import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	Database db = new Database();

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
	public void removeItem(JButton remmovebt, JTable table,String compName,JPanel main,JFrame fr) {
		remmovebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected == -1)
					JOptionPane.showMessageDialog(null, "No item selected, select an degree.", "Error",JOptionPane.WARNING_MESSAGE);  

				else {	
				int getNumber= Integer.parseInt( (String) table.getValueAt(selected, 0));
				db.removeItem(compName, getNumber);
				}
			}
		});
	}
}
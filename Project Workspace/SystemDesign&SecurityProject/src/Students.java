
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class Students extends UserInterface{
	Statement stmt = null;
	Connection con = null;  // a Connection object
	FindDrivers fd = new FindDrivers();
	public void studentPage() {
		
		//background image set up
		ImageIcon icon=new ImageIcon("src\\images\\admin.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		//student page main frame set up
		JFrame frame=new JFrame();
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));	
		
		//get container and set to invisible
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		JPanel main =new JPanel();
		
		super.profile(main,frame);
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
				profile(main,fr);
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
		findDriver();
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
		con.close();
		stmt.close();
		
		JButton addStudents= new JButton("Add student");
		JButton removeStudent= new JButton("Remove student");
		addStudents.setBounds(0,738, 200, 50);
		removeStudent.setBounds(250,738, 200, 50);
		

		main.setBounds(343,146, 1577, 788);
		main.setVisible(true); 
		main.add(addStudents);
		main.add(removeStudent);
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

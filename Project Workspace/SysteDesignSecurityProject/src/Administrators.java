import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTable;

public class Administrators{
	public void adminPage() {
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Desktop\\admin.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		JFrame frame=new JFrame();
		
		//获取窗口的第二层，将label放入
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		//获取frame的顶层容器,并设置为透明
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		accounts(frame);
		
		JPanel nav =new JPanel();
		nav.setLayout(new GridLayout(5,1));
		nav.setBounds(0,146, 343, 788);
		nav.setOpaque(false);
		
		
		JButton profile=new JButton("profile");
		profile.setPreferredSize(new Dimension(80,30));
		profile.setContentAreaFilled(false);
		profile.setBorderPainted(false);
		//profile.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\pb.png"));		
		profile.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JButton accounts=new JButton("accounts");
		JButton departments=new JButton("departments");
		JButton degree=new JButton("degree");
		JButton modules=new JButton("modules");
		
		
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				profile(frame);
			}
			});
		accounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accounts(frame);
				}
			});
		departments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
			});
		degree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
			});
		modules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
			});

		
		nav.add(profile);
		nav.add(accounts);
		nav.add(departments);
		nav.add(degree);
		nav.add(modules);

		
		frame.add(nav);
		frame.setLayout(null);

		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
 
		
	}
	

	public void profile(JFrame frame) {
		JPanel main =new JPanel();
		main.setLayout(new GridLayout(3,1));
		main.setBounds(343,146, 1577, 788);
		main.setOpaque(false);

	
		JLabel welcome =new JLabel("Hi, Xu Dong"); 
		welcome.setFont(new java.awt.Font("Dialog", 1, 60));
		
		JLabel welcome2 =new JLabel("Welcome to information system"); 
		welcome2.setFont(new java.awt.Font("Dialog", 1, 40));

		
		main.add(welcome);
		main.add(welcome2);
		frame.add(main);

	}
	public void departments(JFrame frame) {
		JPanel accounts = new JPanel(); 
		  Object[][] playerInfo = {
				  { "王鹏", new Integer(91), new Integer(100), new Integer(191), new Boolean(true) }, 
				  { "朱学莲", new Integer(82), new Integer(69), new Integer(151), new Boolean(true) }, 
				  { "梅婷", new Integer(47), new Integer(57), new Integer(104), new Boolean(false) }, 
				  { "赵龙", new Integer(61), new Integer(57), new Integer(118), new Boolean(false) }, 
				  { "李兵", new Integer(90), new Integer(87), new Integer(177), new Boolean(true) }, }; 
		  String[] Names = { "姓名", "语文", "数学", "总分", "及格" }; 
		  JTable table = new JTable(playerInfo, Names); 
		  table.setPreferredScrollableViewportSize(new Dimension(550, 100)); 
		  JScrollPane scrollPane = new JScrollPane(table); 
		  accounts.setVisible(true); 

	}
	public void accounts(JFrame frame) {

		JPanel accounts = new JPanel(); 
		accounts.setBounds(343,146, 1577, 788);

		Object[][] playerInfo = {
				{ "王鹏", new Integer(91), new Integer(100), new Integer(191), new Boolean(true) }, 
				{ "朱学莲", new Integer(82), new Integer(69), new Integer(151), new Boolean(true) }, 
				{ "梅婷", new Integer(47), new Integer(57), new Integer(104), new Boolean(false) }, 
				{ "赵龙", new Integer(61), new Integer(57), new Integer(118), new Boolean(false) }, 
				{ "李兵", new Integer(90), new Integer(87), new Integer(177), new Boolean(true) }, }; 
		String[] Names = { "姓名", "语文", "数学", "总分", "及格" }; 
		JTable table = new JTable(playerInfo, Names); 
		table.setPreferredScrollableViewportSize(new Dimension(550, 100)); 
		JScrollPane scrollPane = new JScrollPane(table); 
	    accounts.setVisible(true); 
	    accounts.add(table);
		frame.add(accounts);
				
	}

}
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Administrators{
	FindDrivers fd = new FindDrivers();

	public void adminPage() {
		ImageIcon icon=new ImageIcon("src\\images\\admin.jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		JFrame frame=new JFrame();
		
		//获取窗口的第二层，将label放入
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		//获取frame的顶层容器,并设置为透明
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		JPanel main =new JPanel();
		profile(main,frame);
		frame.add(main);

		  
		JPanel nav =new JPanel();
		nav.setLayout(new GridLayout(5,1));
		nav.setBounds(0,146, 343, 400);
		nav.setOpaque(false);
		
		
		JButton profile=new JButton("· Profile ·");
		navAttribute(profile);
		JButton accounts=new JButton("· Accounts ·");
		navAttribute(accounts);
		JButton departments=new JButton("· Departments ·");
		navAttribute(departments);
		JButton degree=new JButton("· Degrees ·");
		navAttribute(degree);
		JButton modules=new JButton("· Modules ·");
		navAttribute(modules);
		
		
		profile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				main.removeAll();
				main.repaint();
				main.updateUI();
				profile(main,frame);

		}
		});
		accounts.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			try {
				accounts(main,frame);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		});
		departments.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			departments(main,frame);
		}
		});
		degree.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			degree(main,frame);
		}
		});
		modules.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateJSwing(main);
			modules(main,frame);
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
	public void updateJSwing(JPanel main) {
		main.removeAll();
		main.repaint();
		main.updateUI();
	}
	public void navAttribute(JButton profile) {
		profile.setForeground(Color.WHITE);
		profile.setFont(new Font("Dialog", Font.PLAIN, 16));
		profile.setPreferredSize(new Dimension(120,50));
		profile.setContentAreaFilled(false);
		profile.setBorderPainted(false);
		profile.setFocusPainted(false);
		//profile.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\pb.png"));			
	}
	public void profile(JPanel main,JFrame frame) {
		main.setLayout(null);
		main.setBounds(343,146, 1577, 788);
		main.setOpaque(false);

	
		JLabel welcome =new JLabel("Hi, Xu Dong"); 
		welcome.setFont(new java.awt.Font("Dialog", 1, 60));
		welcome.setBounds(20, 50, 700, 500);
		JLabel welcome2 =new JLabel("Welcome to information system"); 
		welcome2.setFont(new java.awt.Font("Dialog", 1, 40));
		welcome2.setBounds(20, 110, 700, 500);


		main.add(welcome);
		main.add(welcome2);
	}
	public void accounts(JPanel main,JFrame frame) throws Exception {
		main.setBounds(343,146, 1577, 788);
		
		
		DefaultTableModel tableModel = new DefaultTableModel(4,4);	
		JTable table = new JTable(tableModel);
		
		JButton addAccounts= new JButton("Add account");
		addAccounts.setBounds(0,738, 200, 50);
		System.out.print(fd.getRow("Accounts"));
		
		main.add(table);
		main.add(addAccounts);
		
		addAccounts.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
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
		    JButton okbtn = new JButton("Confirm");
		    okbtn.setBounds(30, 120, 80, 20);
		    JButton cancelbtn = new JButton("Cancel");
		    cancelbtn.setBounds(160, 120, 80, 20);

		    addAccountP.add(accName);
		    addAccountP.add(accName1);
		    addAccountP.add(password);
		    addAccountP.add(password1);
		    addAccountP.add(okbtn);
		    addAccountP.add(cancelbtn);
		    addAccount.setLocation(900,500);
		    addAccount.setSize(300,220);
		    addAccount.setVisible(true);
			addAccount.add(addAccountP);

		    }
		});
	}
	public void departments(JPanel main,JFrame frame) {
		main.setBounds(343,146, 1577, 788);
		Object[][] playerInfo = {
				{ "王鹏", new Integer(91), new Integer(100), new Integer(191), new Boolean(true) }, 
				{ "朱学莲", new Integer(82), new Integer(69), new Integer(151), new Boolean(true) }, 
				{ "梅", new Integer(47), new Integer(57), new Integer(104), new Boolean(false) }, 
				{ "赵龙", new Integer(61), new Integer(57), new Integer(118), new Boolean(false) }, 
				{ "李兵", new Integer(90), new Integer(87), new Integer(177), new Boolean(true) }, }; 
		String[] Names = { "姓", "语文", "数学", "总分", "及格" }; 
		JTable table = new JTable(playerInfo, Names); 
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 100)); 
		table.setBounds(0,0,1577,738);

		JScrollPane scrollPane = new JScrollPane(table); 
		main.setVisible(true); 
		  
		JButton addDepartment= new JButton("Add department");
		addDepartment.setBounds(0,738, 200, 50);

		main.add(addDepartment);
		main.add(table);
		
		addDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame addDepartment=new JFrame("Add department");
				addDepartment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addDepartment.setLayout(null);
				addDepartment.setVisible(true);
				JPanel addDepartmentP=new JPanel();
				addDepartmentP.setLayout(null);
				addDepartmentP.setBounds(0,0,300,220);
				
			    JLabel deName = new JLabel("Department name:");
			    deName.setBounds(30, 40, 120, 20);
			    JTextField deName1 = new JTextField();
			    deName1.setBounds(140, 40, 120, 20);

			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 120, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(180, 120, 80, 20);

			    addDepartmentP.add(deName);
			    addDepartmentP.add(deName1);
			    addDepartmentP.add(okbtn);
			    addDepartmentP.add(cancelbtn);
			    addDepartment.setLocation(900,500);
			    addDepartment.setSize(300,220);
			    addDepartment.setVisible(true);
			    addDepartment.add(addDepartmentP);

			    }
			});
	}

	public void degree(JPanel main,JFrame frame) {
		main.setBounds(343,146, 1577, 788);
		Object[][] playerInfo = {
				{ "王鹏", new Integer(91), new Integer(100), new Integer(191), new Boolean(true) }, 
				{ "朱学莲", new Integer(82), new Integer(69), new Integer(151), new Boolean(true) }, 
				{ "梅", new Integer(47), new Integer(57), new Integer(104), new Boolean(false) }, 
				{ "赵龙", new Integer(61), new Integer(57), new Integer(118), new Boolean(false) }, 
				{ "李兵", new Integer(90), new Integer(87), new Integer(177), new Boolean(true) }, }; 
		String[] Names = { "姓", "语文", "数学", "总分", "及格" }; 
		JTable table = new JTable(playerInfo, Names); 
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 100)); 
		table.setBounds(0,0,1577,738);

		JScrollPane scrollPane = new JScrollPane(table); 
		main.setVisible(true); 
		  
		JButton addDegree= new JButton("Add degree");
		addDegree.setBounds(0,738, 200, 50);

		main.add(addDegree);
		main.add(table);
		addDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame addDegree=new JFrame("Add degree");
				addDegree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addDegree.setLayout(null);
				addDegree.setVisible(true);
				JPanel addDegreeP=new JPanel();
				addDegreeP.setLayout(null);
				addDegreeP.setBounds(0,0,300,220);
				
			    JLabel degName = new JLabel("Degree name:");
			    degName.setBounds(30, 40, 120, 20);
			    JTextField degName1 = new JTextField();
			    degName1.setBounds(140, 40, 120, 20);

			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 120, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(180, 120, 80, 20);

			    addDegreeP.add(degName);
			    addDegreeP.add(degName1);
			    addDegreeP.add(okbtn);
			    addDegreeP.add(cancelbtn);
			    addDegree.setLocation(900,500);
			    addDegree.setSize(300,220);
			    addDegree.setVisible(true);
			    addDegree.add(addDegreeP);

			    }
			});
	}
	public void modules(JPanel main,JFrame frame) {
		main.setBounds(343,146, 1577, 788);
		Object[][] playerInfo = {
				{ "王鹏", new Integer(91), new Integer(100), new Integer(191), new Boolean(true) }, 
				{ "朱学莲", new Integer(82), new Integer(69), new Integer(151), new Boolean(true) }, 
				{ "梅", new Integer(47), new Integer(57), new Integer(104), new Boolean(false) }, 
				{ "赵龙", new Integer(61), new Integer(57), new Integer(118), new Boolean(false) }, 
				{ "李兵", new Integer(90), new Integer(87), new Integer(177), new Boolean(true) }, }; 
		String[] Names = { "姓", "语文", "数学", "总分", "及格" }; 
		JTable table = new JTable(playerInfo, Names); 
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 100)); 
		table.setBounds(0,0,1577,738);

		JScrollPane scrollPane = new JScrollPane(table); 
		main.setVisible(true); 
		  
		JButton addModules= new JButton("Add modules");
		addModules.setBounds(0,738, 200, 50);

		main.add(addModules);
		main.add(table);
		addModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame addModules=new JFrame("Add module");
				addModules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addModules.setLayout(null);
				addModules.setVisible(true);
				JPanel addModulesP=new JPanel();
				addModulesP.setLayout(null);
				addModulesP.setBounds(0,0,300,220);
				
			    JLabel molName = new JLabel("Module name:");
			    molName.setBounds(30, 40, 120, 20);
			    JTextField molName1 = new JTextField();
			    molName1.setBounds(140, 40, 120, 20);

			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 120, 80, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(180, 120, 80, 20);

			    addModulesP.add(molName);
			    addModulesP.add(molName1);
			    addModulesP.add(okbtn);
			    addModulesP.add(cancelbtn);
			    addModules.setLocation(900,500);
			    addModules.setSize(300,220);
			    addModules.setVisible(true);
			    addModules.add(addModulesP);

			    }
			});
	}
}
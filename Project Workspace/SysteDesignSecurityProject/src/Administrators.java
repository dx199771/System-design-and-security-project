import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
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

		JPanel nav =new JPanel();
		nav.setLayout(new GridLayout(5,1));
		nav.setBounds(0,146, 343, 788);
		nav.setOpaque(false);
		
		
		JButton profile=new JButton("profile");
		JButton accounts=new JButton("accounts");
		JButton departments=new JButton("departments");
		JButton degree=new JButton("degree");
		JButton modules=new JButton("modules");
		
		nav.add(profile);
		nav.add(accounts);
		nav.add(departments);
		nav.add(degree);
		nav.add(modules);

		
		frame.add(main);
		frame.add(nav);
		frame.setLayout(null);

		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
 
		
	}   
}
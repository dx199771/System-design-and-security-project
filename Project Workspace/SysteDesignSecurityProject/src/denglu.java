import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class  denglu
{
 
	public denglu(){
		//加载图片
		ImageIcon icon=new ImageIcon("src\\images\\t1.jpg");
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);
		
		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		JFrame frame=new JFrame();
		
		//获取窗口的第二层，将label放入
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
		//获取frame的顶层容器,并设置为透明
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
		jb.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.setBounds(1354,450,300,700);
		
		panel.add(userNameTF);
		panel.add(passwordTF);
		panel.add(jb);
		panel.add(usernameIcon);
		panel.add(passwordIcon);

		//必须设置为透明的。否则看不到图片
		panel.setOpaque(false);
		
		

		frame.add(panel);
		
		frame.setLayout(null);

		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
 
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = "3";
				String pass = "3";
						
				if(user.equals(userNameTF.getText()) && pass.equals(passwordTF.getText())) {
					Administrators admin = new Administrators();
					admin.adminPage();
					frame.dispose();
			
				}
			}
			});

	}
	public static void main(String[] args) 
	{
		new denglu();
	}
}

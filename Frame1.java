import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import java.sql.*;
public class Frame1 {

	private JFrame frame;
	private JTextField loginpw;
	private JTextField username;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run() 
			{
				try 
				{
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;

	/**
	 * Create the application.
	 */
	
	public Frame1() 
	{
		
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		username = new JTextField();
		username.setBounds(176, 58, 126, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(34, 61, 87, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(34, 101, 87, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 101, 126, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
				String query="select * from userInfo where Username=? and Password=?";
//				String name=username.getText();
//				String pw=passwordField.getText();
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, username.getText());
				pst.setString(2, passwordField.getText());
				
				ResultSet rs=pst.executeQuery();
				int ct=0;
				while(rs.next())
				{
					ct++;
					
				}
				if(ct==1)
				{
					JOptionPane.showMessageDialog(null, "Username and Password is correct");
					frame.dispose();
					HotelDetails hD = new HotelDetails(); //calls the next class from obj
					hD.setVisible(true);
				}
				else if(ct>1)
				{
					JOptionPane.showMessageDialog(null,"Duplicate details"); //Checks if two have the same details.
				}
				else
					JOptionPane.showMessageDialog(null, "Username and Password is incorrect");
				
				rs.close();
				pst.close();
			}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
			
		});
		btnLogin.setBounds(176, 149, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton newReg = new JButton("Click here to register\r\n");
		newReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				NewRegistration nR = new NewRegistration();
				nR.setVisible(true);
			}
		});
		newReg.setBounds(113, 202, 206, 25);
		frame.getContentPane().add(newReg);
	}
}

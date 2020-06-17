import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
public class NewRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewRegistration frame = new NewRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public NewRegistration() 
	{
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 35, 77, 16);
		contentPane.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(41, 64, 77, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(41, 89, 77, 16);
		contentPane.add(lblAddress);
		
		JLabel lblEmailId = new JLabel("Emaild");
		lblEmailId.setBounds(41, 118, 77, 16);
		contentPane.add(lblEmailId);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(41, 147, 77, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(41, 178, 77, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(161, 32, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 61, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 89, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(161, 115, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(161, 147, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(161, 178, 116, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					String query="insert into userInfo" + "(Name, Date_of_Birth, Address, EmailId, Username, Password) values" + "(?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					
					pst.execute();
					//ResultSet rs = pst.executeQuery();
					
					JOptionPane.showMessageDialog(null,"Data Saved into the database.");
					pst.close();
					//rs.close();
				}
				catch(Exception e1)
				{
					System.out.println("*********");
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnRegister.setBounds(161, 228, 97, 25);
		contentPane.add(btnRegister);
	}

}

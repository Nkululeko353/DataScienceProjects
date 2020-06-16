package com.java.employeesystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class UserLogin {

	static int uempidtopass;
	
	JFrame frameLoginPage;
	private JPasswordField passwordField;
	private JTextField textFieldEmpid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin window = new UserLogin();
					window.frameLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserLogin() {
		initialize();
	}

	private void initialize() {
		frameLoginPage = new JFrame();
		frameLoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage(UserLogin.class.getResource("/com/java/employeesystem/power_icon.png")));
		frameLoginPage.setResizable(false);
		frameLoginPage.getContentPane().setBackground(Color.WHITE);
		frameLoginPage.setBackground(Color.WHITE);
		frameLoginPage.setTitle("Log In to continue");
		frameLoginPage.setBounds(100, 100, 924, 634);
		frameLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLoginPage.getContentPane().setLayout(null);

		
		JLabel lblLogInTo = new JLabel("Log In to continue");
		lblLogInTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogInTo.setFont(new Font("Raleway", Font.PLAIN, 18));
		lblLogInTo.setBounds(10, 69, 847, 67);
		frameLoginPage.getContentPane().add(lblLogInTo);
		
		JLabel lblUsername = new JLabel("EmpID");
		lblUsername.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 184, 847, 23);
		frameLoginPage.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(10, 252, 847, 39);
		frameLoginPage.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter Password ");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(148, 302, 565, 23);
		frameLoginPage.getContentPane().add(passwordField);
		
		textFieldEmpid = new JTextField();
		textFieldEmpid.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmpid.setToolTipText("Enter EmpID");
		textFieldEmpid.setBounds(148, 218, 565, 23);
		frameLoginPage.getContentPane().add(textFieldEmpid);
		textFieldEmpid.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setForeground(new Color(255, 255, 255));
		btnLogIn.setFont(new Font("Raleway", Font.PLAIN, 13));
		btnLogIn.setBackground(new Color(100, 149, 237));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s_empid=textFieldEmpid.getText();
				int emp_id=0;
				if( ! s_empid.equals(""))
				{
					s_empid=textFieldEmpid.getText();
					emp_id=Integer.valueOf(s_empid);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please Enter EmpID");
				}

				uempidtopass=emp_id;
					
				String password=new String(passwordField.getPassword());	
				 if (password.equals("")) {
					JOptionPane.showMessageDialog(frameLoginPage, "Password cannot be blank");
					
				}
				
				else{
				
				
				try{  
								
					//			String password=passwordField.getPassword().toString();
//					char[] password=passwordField.getPassword();
//					String password=new String(passwordField.getPassword().toString());
					
					Class.forName("com.mysql.jdbc.Driver");  
					Connection connection;
//					if (AdminConfigure.hostname!=null && AdminConfigure.portno!=null && AdminConfigure.dbname!=null && AdminConfigure.dbuname!=null && AdminConfigure.dbpassword!=null ) {
//						connection=DriverManager.getConnection(  
//								"jdbc:mysql://"+AdminConfigure.hostname+":"+AdminConfigure.portno+"/"+AdminConfigure.dbname+"",""+AdminConfigure.dbuname+"",""+AdminConfigure.dbpassword+"");  
//					}
//					
//					else{
					
					connection=DriverManager.getConnection(  
							"jdbc:mysql://"+DBValues.dbhost+":"+DBValues.dbport+"/"+DBValues.dbname+"",""+DBValues.dbuname+"",""+DBValues.dbpass+"");  

					//}
					String qu2= "select * from login_master where empid='"+emp_id+"'&& password='"+password+"' ";
					
					Statement statement= connection.createStatement();
					ResultSet row= statement.executeQuery(qu2);
//					if(row.getString("confirmed")=="0"){
//						System.out.println("user not verified");
//					}
//					
//					else
					if (row.first()) {
						JOptionPane.showMessageDialog(frameLoginPage, " Logged In successfully!");
						
						EmployeeSection landingPage = new EmployeeSection();
						landingPage.setVisible(true);
						frameLoginPage.dispose();
						
					}
					else{
						JOptionPane.showMessageDialog(frameLoginPage, " EmpID/Password is Wrong or User is not verified by Admin. Contact Admin");
						
						
					}
						
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}  

				
				
			}
			}});
		btnLogIn.setBounds(225, 336, 401, 47);
		frameLoginPage.getContentPane().add(btnLogIn);
		
		JLabel lblOr = new JLabel("or");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(363, 425, 115, 14);
		frameLoginPage.getContentPane().add(lblOr);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.setForeground(new Color(0, 0, 0));
		btnSignup.setBackground(new Color(0, 250, 154));
		btnSignup.setFont(new Font("Raleway", Font.PLAIN, 12));
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignUpPage page = new SignUpPage();
				page.frameSignUp.setVisible(true);
				frameLoginPage.dispose();
				
				
			}
		});
		btnSignup.setBounds(290, 465, 271, 39);
		frameLoginPage.getContentPane().add(btnSignup);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setBackground(Color.LIGHT_GRAY);
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Raleway", Font.PLAIN, 20));
		lblUserLogin.setBounds(297, 27, 264, 47);
		frameLoginPage.getContentPane().add(lblUserLogin);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartPage page = new StartPage();
				page.frmWelcome.setVisible(true);
				frameLoginPage.dispose();
			}
		});
		btnGoBack.setBounds(10, 10, 153, 23);
		frameLoginPage.getContentPane().add(btnGoBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UserLogin.class.getResource("/com/java/employeesystem/bg_gray.png")));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(0, 0, 918, 617);
		frameLoginPage.getContentPane().add(label);
	}
}

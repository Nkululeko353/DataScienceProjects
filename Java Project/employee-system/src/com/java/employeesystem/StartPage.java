package com.java.employeesystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

//import java.awt.SystemColor;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class StartPage {

	JFrame frmWelcome;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage window = new StartPage();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public StartPage() {
		
		initialize();
	}

	private void initialize() {
		
		
		
		
		frmWelcome = new JFrame();
		frmWelcome.setResizable(false);
		frmWelcome.setIconImage(Toolkit.getDefaultToolkit().getImage(StartPage.class.getResource("/com/java/employeesystem/power_icon.png")));
		frmWelcome.setTitle("Welcome !");
		frmWelcome.getContentPane().setBackground(new Color(255, 255, 255));
		frmWelcome.getContentPane().setForeground(Color.WHITE);
		frmWelcome.setBounds(100, 100, 1026, 750);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		JLabel lblEmployeeManagementSystem = new JLabel("Employee Management System");
		lblEmployeeManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeManagementSystem.setFont(new Font("Cookie", Font.PLAIN, 36));
		lblEmployeeManagementSystem.setBounds(10, 48, 1000, 55);
		frmWelcome.getContentPane().add(lblEmployeeManagementSystem);
		
		JLabel lblAJdbcswingApplication = new JLabel("A JDBC-Swing Application");
		lblAJdbcswingApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblAJdbcswingApplication.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAJdbcswingApplication.setBounds(10, 102, 990, 24);
		frmWelcome.getContentPane().add(lblAJdbcswingApplication);
		
		JLabel label = new JLabel("________________________________________________");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(20, 89, 1000, 14);
		frmWelcome.getContentPane().add(label);
		
		JLabel lblMadeBy = new JLabel("Made by");
		lblMadeBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblMadeBy.setBounds(10, 515, 1000, 14);
		frmWelcome.getContentPane().add(lblMadeBy);
		
		JLabel lblGauravSachdeva = new JLabel("Nkululeko Freedom Mqadi");
		lblGauravSachdeva.setHorizontalAlignment(SwingConstants.CENTER);
		lblGauravSachdeva.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		lblGauravSachdeva.setBounds(10, 543, 1000, 32);
		frmWelcome.getContentPane().add(lblGauravSachdeva);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Raleway", Font.PLAIN, 13));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin page = new UserLogin();
				//page.frame
				page.frameLoginPage.setVisible(true);
				frmWelcome.dispose();
				
			}
		});
		btnLogIn.setBackground(UIManager.getColor("Button.background"));
		btnLogIn.setBounds(45, 208, 296, 48);
		frmWelcome.getContentPane().add(btnLogIn);
		
		JLabel lblOr = new JLabel("or");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(45, 267, 296, 14);
		frmWelcome.getContentPane().add(lblOr);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Raleway", Font.PLAIN, 12));
		btnSignUp.setForeground(Color.BLACK);
		btnSignUp.setBackground(UIManager.getColor("Button.background"));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage page = new SignUpPage();
				//page.frame
				page.frameSignUp.setVisible(true);
				frmWelcome.dispose();
				
			}
		});
		btnSignUp.setBounds(45, 292, 296, 38);
		frmWelcome.getContentPane().add(btnSignUp);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setBounds(45, 165, 296, 32);
		frmWelcome.getContentPane().add(lblUserLogin);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setBounds(692, 171, 268, 21);
		frmWelcome.getContentPane().add(lblAdminLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin adminLogin = new AdminLogin();
				adminLogin.setVisible(true);
				frmWelcome.dispose();
			}
		});
		btnLogin.setFont(new Font("Raleway", Font.PLAIN, 13));
		btnLogin.setBounds(692, 208, 268, 48);
		frmWelcome.getContentPane().add(btnLogin);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(StartPage.class.getResource("/com/java/employeesystem/back_3.jpg")));
		label_1.setBounds(0, 0, 1044, 743);
		frmWelcome.getContentPane().add(label_1);
	}
}

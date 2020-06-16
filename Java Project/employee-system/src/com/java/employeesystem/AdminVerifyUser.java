package com.java.employeesystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminVerifyUser {

	JList<String> list;
	 JFrame frmVerifyUsers;
	 JTextField textField;

	 int empid2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminVerifyUser window = new AdminVerifyUser();
					window.frmVerifyUsers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminVerifyUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerifyUsers = new JFrame();
		frmVerifyUsers.setResizable(false);
		frmVerifyUsers.setTitle("Verify Users");
		frmVerifyUsers.setIconImage(Toolkit.getDefaultToolkit().getImage(AdminVerifyUser.class.getResource("/com/java/employeesystem/power_icon.png")));
		frmVerifyUsers.setBounds(100, 100, 1261, 677);
		frmVerifyUsers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVerifyUsers.getContentPane().setLayout(null);
		
		JButton btnVerifyUsers = new JButton("Verify Users");
		btnVerifyUsers.setForeground(Color.WHITE);
		btnVerifyUsers.setBackground(Color.WHITE);
		btnVerifyUsers.setEnabled(false);
		btnVerifyUsers.setBounds(10, 11, 1235, 50);
		frmVerifyUsers.getContentPane().add(btnVerifyUsers);
		
		JLabel lblListOfNon = new JLabel("List of Non Verified Users");
		lblListOfNon.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfNon.setBounds(698, 72, 547, 39);
		frmVerifyUsers.getContentPane().add(lblListOfNon);
		
		list = new JList<String>();
		list.setBounds(708, 165, 537, 473);
		frmVerifyUsers.getContentPane().add(list);
		
		JLabel lblVerifyUsers = new JLabel("Verify Users");
		lblVerifyUsers.setBackground(Color.YELLOW);
		lblVerifyUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerifyUsers.setBounds(10, 84, 678, 27);
		frmVerifyUsers.getContentPane().add(lblVerifyUsers);
		
		textField = new JTextField();
		textField.setBounds(10, 145, 678, 50);
		frmVerifyUsers.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ss
				empid2=Integer.parseInt(textField.getText());
			
				//	String billAmt3=label_
							//label_6.setText(row2.getString("billDate"));
				int empid=Integer.parseInt(textField.getText());
							
					if (textField.equals("")) {
						JOptionPane.showMessageDialog(frmVerifyUsers, "Please enter Emp ID");
					}

					else if (textField.getText().contains(" ")) {
						JOptionPane.showMessageDialog(frmVerifyUsers, "Emp ID cannot have spaces.");
					}

					else{
						
						try{
						Class.forName("com.mysql.jdbc.Driver");  
						Connection connection=DriverManager
								.getConnection(  
										"jdbc:mysql://"+DBValues.dbhost+":"+DBValues.dbport+"/"+DBValues.dbname+"",""+DBValues.dbuname+"",""+DBValues.dbpass+"");  
						
//						String billdate=row2.getString("billDate");
				
	//
						//System.out.println(billdate);
//						String qu34= "select * from billdetails where acno='"+acno23+"'";
//						Statement statement24= connection.createStatement();
////						
//						ResultSet row23= statement24.executeQuery(qu34);
//						if (row23.first()) {
//							label_4.setText(row23.getString("custname"));
//							
//						}
//						row23.close();
//						//JUST TO GET NAME
						
//						String qu3= "select * from billdetails where acno='"+acno23+"'  && paid='0'";
						String qu3= "update login_master set role='employee' where empid='"+empid+"' ";
						Statement statement2= connection.createStatement();
//						int row2= statement2.executeUpdate(qu3);
						int row2= statement2.executeUpdate(qu3);

					
						int response = JOptionPane.showConfirmDialog(null, " Verified!!! "
								+ "Do you want to continue?", "Verified",
						        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						    if (response == JOptionPane.NO_OPTION) {
						     AdminSection page = new AdminSection();
						     page.setVisible(true);
						     frmVerifyUsers.dispose();
						    	
						    } else if (response == JOptionPane.YES_OPTION) {
						    	
						    	frmVerifyUsers.dispose();
						    	
						    	AdminVerifyUser n= new AdminVerifyUser();
						    	n.frmVerifyUsers.setVisible(true);
						     
						    } else if (response == JOptionPane.CLOSED_OPTION) {
						     
						    }
						  
						
						
						
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}  
						
				
					}
				//dd
				
			}
		});
		btnVerify.setBounds(243, 242, 200, 50);
		frmVerifyUsers.getContentPane().add(btnVerify);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminSection adminLanding = new AdminSection();
				adminLanding.setVisible(true);
				frmVerifyUsers.dispose();
				
			}
		});
		btnGoBack.setBounds(10, 505, 200, 133);
		frmVerifyUsers.getContentPane().add(btnGoBack);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//START
							
					try{
//						data_upb=
						
				//	String username = textField.getText().toString();
					Class.forName("com.mysql.jdbc.Driver");  
					Connection connection=DriverManager
							.getConnection(  
									"jdbc:mysql://"+DBValues.dbhost+":"+DBValues.dbport+"/"+DBValues.dbname+"",""+DBValues.dbuname+"",""+DBValues.dbpass+"");  
					
					String qu3= "select * from login_master where role=''";
					Statement statement2= connection.createStatement();
//					int row2= statement2.executeUpdate(qu3);
					ResultSet row2= statement2.executeQuery(qu3);
					

					if (row2.next()) {
						
						DefaultListModel<String> model = new DefaultListModel<String>();
						
//						String ss=row2.first();
						 int empidno=row2.getInt("empid");
						 
					//	 String acnoo=row2.getString("acno"); 
						 String data2=" EmpID: "+empidno;
					        model.addElement(data2); //add each item to the model

						 while (row2.next()) //go through each row that your query returns
						    {
							 int empidno2=row2.getInt("empid");

						        String data22=" EmpID: "+empidno2;
							          
						        model.addElement(data22); //add each item to the model
						    }
						 
						 list.setModel(model);
//						 resultSet.close
						
						JOptionPane.showMessageDialog(frmVerifyUsers, " You have non verified users");
						
					}
					else{
						JOptionPane.showMessageDialog(frmVerifyUsers, " No Entries");
					
//						frmAddNewCustomer.dispose();	
					}	
					
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					
					e2.printStackTrace();
				}  
					
				}
				
				//STOP
				
				
			
		});
		btnRefresh.setBounds(708, 113, 537, 39);
		frmVerifyUsers.getContentPane().add(btnRefresh);
		
		JLabel lblEnterUserIDTo = new JLabel("Enter UserID to be verified");
		lblEnterUserIDTo.setForeground(Color.RED);
		lblEnterUserIDTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUserIDTo.setBounds(10, 120, 678, 14);
		frmVerifyUsers.getContentPane().add(lblEnterUserIDTo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminVerifyUser.class.getResource("/com/java/employeesystem/bg_gray.png")));
		label.setBounds(-21, 0, 1336, 689);
		frmVerifyUsers.getContentPane().add(label);
	}

}

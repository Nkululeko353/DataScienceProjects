package com.java.employeesystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class SendComments extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static SendComments frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SendComments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SendComments() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSendComments = new JLabel("Enter Your Feedback Here.....");
		lblSendComments.setForeground(Color.DARK_GRAY);
		lblSendComments.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblcomplianceid = new JLabel("ComplianceID:");
		
		JLabel lblempid = new JLabel("EmpID:");
		
		JLabel lblcomments = new JLabel("Comments:");
		
		JLabel lblcreateDate = new JLabel("CreateDate:");
		
		JLabel lbldepartment_id = new JLabel("DepartmentID:");
		
		JButton btnSendComments = new JButton("Send Comments");
		btnSendComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String comments=textArea.getText();
				boolean performSave=true;
				String screatedate=textField_2.getText();
				DateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date createdate =null;
				 if(!screatedate.equals(""))
				    {
				    	screatedate=textField_2.getText();
				    	createdate=Date.valueOf(screatedate);
				    	try {
							createdate=(Date) sdf.parse(screatedate);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	performSave=true;
				    }
				 else
				    {
				    	JOptionPane.showMessageDialog(null,"Please Enter Create Date");
						performSave=false;
				    }
				 String s_complianceid=textField.getText();
					int compliance_id=0;
						if( ! s_complianceid.equals(""))
						{
							s_complianceid=textField.getText();
							compliance_id=Integer.valueOf(s_complianceid);
							performSave=true;
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Please Enter Compliance ID");
							performSave=false;
						}
						 String s_empid=textField_1.getText();
							int emp_id=0;
								if( ! s_empid.equals(""))
								{
									s_empid=textField_1.getText();
									emp_id=Integer.valueOf(s_empid);
									performSave=true;
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Please Enter Emp ID");
									performSave=false;
								}
								 String s_departmentid=textField_3.getText();
									int department_id=0;
										if( ! s_departmentid.equals(""))
										{
											s_departmentid=textField_3.getText();
											department_id=Integer.valueOf(s_departmentid);
											performSave=true;
										}
										else
										{
											JOptionPane.showMessageDialog(null,"Please Enter Department ID");
											performSave=false;
										}
										if(textArea.getText().isEmpty())
										{
											JOptionPane.showMessageDialog(null,"Please Enter Comments");
											performSave=false;
										}
				if(performSave)	
				{
				int status=StatusReportDao.save(compliance_id, emp_id, comments, createdate, department_id);
				if(status>0){
					JOptionPane.showMessageDialog(SendComments.this,"Comment sent successfully!");
					textField.setText("");textField_1.setText("");textField_2.setText("");
					textField_3.setText("");
					textArea.setText("");
				}else{
					JOptionPane.showMessageDialog(SendComments.this,"Sorry, Unable to send comment!");
				}
				}
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		
		textArea = new JTextArea();
		textArea.setRows(3);
		textArea.setColumns(20);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeSection.main(new String[]{});
				frame.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(144)
							.addComponent(lblSendComments))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblcomplianceid)
								.addComponent(lblempid, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcomments, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcreateDate, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbldepartment_id, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
									.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
					.addContainerGap(124, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(128)
					.addComponent(btnSendComments, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBack)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblcreateDate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblSendComments)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblcomplianceid)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblempid)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcomments)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblcreateDate)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbldepartment_id)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnSendComments, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBack))
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		}
	}

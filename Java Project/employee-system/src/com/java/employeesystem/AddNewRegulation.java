package com.java.employeesystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.LayoutStyle.ComponentPlacement;
public class AddNewRegulation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AddNewRegulation frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddNewRegulation();
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
	public AddNewRegulation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddRegulation = new JLabel("Add Regulation");
		lblAddRegulation.setForeground(Color.DARK_GRAY);
		lblAddRegulation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		JLabel lbldepartment_id = new JLabel("Department ID:");
		
		JLabel lblrlType = new JLabel("Regulation Type");
		
		JLabel lbldetails = new JLabel("Details:");
		
		JLabel lblcreateDate = new JLabel("Create Date:");
		
		textField = new JTextField();
		textField.setColumns(10);
	
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setRows(3);
		textArea.setColumns(20);
		
		JButton btnAddRegulation = new JButton("Add Regulation");
		btnAddRegulation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
	        	String rlType=textField_1.getText();
				String details=textArea.getText();
				
				boolean performSave=true;
				String screatedate=textField_3.getText();
				DateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			    Date createdate =null;
			    if(!screatedate.equals(""))
			    {
			    	screatedate=textField_3.getText();
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
			    	JOptionPane.showMessageDialog(null,"Please Enter Date");
					performSave=false;
			    }
				String s_departmentid=textField.getText();
				int department_id=0;
					if( ! s_departmentid.equals(""))
					{
						s_departmentid=textField.getText();
						department_id=Integer.valueOf(s_departmentid);
						performSave=true;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Please Enter Department ID");
						performSave=false;
					}
				
				    if(textField_1.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter RLType");
						performSave=false;
					}
					if(textArea.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter Details");
						performSave=false;
					}
					
					 if(performSave)
					{
				      
				      int status=ComplianceDao.save(department_id,rlType,details,createdate);
				
				if(status>0){
					JOptionPane.showMessageDialog(AddNewRegulation.this,"Regulation added successfully!");
					textField.setText("");textField_1.setText("");
					textField_3.setText("");textArea.setText("");
				}else{
					JOptionPane.showMessageDialog(AddNewRegulation.this,"Sorry, Unable to add Regulation!");
				}
			}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminSection.main(new String[]{});
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
							.addComponent(lblAddRegulation))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldepartment_id, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblrlType, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbldetails, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcreateDate, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
					.addContainerGap(124, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(128)
					.addComponent(btnAddRegulation, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblAddRegulation)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbldepartment_id)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblrlType)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbldetails)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblcreateDate)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnAddRegulation, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBack))
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		}
	}


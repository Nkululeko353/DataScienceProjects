package com.java.employeesystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.sql.Date;
public class AddNewEmployee extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AddNewEmployee frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddNewEmployee();
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
	public AddNewEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddEmployee= new JLabel("Add Employee");
		lblAddEmployee.setForeground(Color.DARK_GRAY);
		lblAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lbldepartment_id = new JLabel("DepartmentID:");
		
		JLabel lblfirstname = new JLabel("FirstName");
		
		JLabel lbllastname = new JLabel("LastName:");
		
		JLabel lblemail = new JLabel("Email:");
		
		JLabel lbldob = new JLabel("DOB:");
		
		
		
		textField = new JTextField();
		textField.setColumns(10);
	
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname=textField_1.getText();
				String lastname=textField_2.getText();
				String email=textField_3.getText();
				//String sdob=textField_4.getText();
				boolean performSave=true;
				String sdob=textField_4.getText();
				DateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date dob =null;
				
				 if(!sdob.equals(""))
				    {
				    	sdob=textField_4.getText();
				    	dob=Date.valueOf(sdob);
				    	try {
							dob=(Date) sdf.parse(sdob);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	performSave=true;
				    }
				 else
				    {
				    	JOptionPane.showMessageDialog(null,"Please Enter Date of Birth");
						performSave=false;
				    }
				 String s_departmentid=textField.getText();
				 int department_id=0;
				 if( ! s_departmentid.equals(""))
					{
						s_departmentid=textField.getText();
						//department_id=Integer.valueOf(s_departmentid);
						department_id=Integer.parseInt(s_departmentid);
						performSave=true;
					}
				 else
					{
						JOptionPane.showMessageDialog(null,"Please Enter Department ID");
						performSave=false;
					}
				 if(textField_1.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter FirstName");
						performSave=false;
					}
					if(textField_2.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter LastName");
						performSave=false;
					}
					if(textField_3.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter Email");
						performSave=false;
					}
					DateFormat dateFormat=new SimpleDateFormat("yyyy");
					java.util.Date date=new java.util.Date();
					int thisYear=Integer.parseInt(dateFormat.format(date));
					int yearDob=Integer.parseInt(sdob.substring(0,4));
					int age=thisYear-yearDob;
					
					if(age<24)
					{
						JOptionPane.showMessageDialog(AddNewEmployee.this,"Sorry, You are under age!Someone who is older than 24 is allowed");
						performSave=false;
					}
					if(performSave)
					{	
				int status=EmployeeDao.save(department_id,firstname,lastname,email,dob);
				
				if(status>0){
					JOptionPane.showMessageDialog(AddNewEmployee.this,"Employee added successfully!");
					textField.setText("");textField_1.setText("");textField_2.setText("");
					textField_3.setText("");textField_4.setText("");
				}else{
					JOptionPane.showMessageDialog(AddNewEmployee.this,"Sorry, Unable to add Employee!");
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
							.addComponent(lblAddEmployee))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldepartment_id, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblfirstname, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllastname, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblemail, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbldob, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
					.addContainerGap(124, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(128)
					.addComponent(btnAddEmployee, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBack)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbldob, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAddEmployee)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbldepartment_id)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblfirstname)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbllastname)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblemail)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbldob)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddEmployee, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}


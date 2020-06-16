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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class AdminSection extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AdminSection frame;
	private JPanel contentPane;
	JScrollPane sp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminSection();
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
	public AdminSection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		JLabel lblAdminSection = new JLabel("Admin Section");
		lblAdminSection.setForeground(Color.DARK_GRAY);
		lblAdminSection.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnAddRegulation = new JButton("Add Regulations");
		btnAddRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewRegulation.main(new String[]{});
				frame.dispose();
			}
		});
		
		JButton btnViewRegulation = new JButton("View Regulations");
		btnViewRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewRegulations.main(new String[]{});
			}
		});
		
		JButton btnEditRegulation = new JButton("Update Regulations");
		btnEditRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRegulation.main(new String[]{});
			}
		});
		
		JButton btnAddDepartment = new JButton("Add Departments");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewDepartment.main(new String[]{});
			}
		});
		
		JButton btnViewDepartment = new JButton("View Departments");
		btnViewDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDepartments.main(new String[]{});
			}
		});
		
		JButton btnEditDepartment = new JButton("Update Departments");
		btnEditDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDepartment.main(new String[]{});
			}
		});
		
		JButton btnAddEmployee = new JButton("Add Employees");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewEmployee.main(new String[]{});
			}
		});
		
		JButton btnViewEmployee = new JButton("View Employees");
		btnViewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEmployees.main(new String[]{});
			}
		});
		
		JButton btnEditEmployee = new JButton("Edit Employees");
		btnEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditEmployee.main(new String[]{});
			}
		});
		
		JButton btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteEmployee.main(new String[]{});
			}
		});
		
		JButton btnViewComments = new JButton("View Comments");
		btnViewComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewComments.main(new String[]{});
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin.main(new String[]{});
				frame.dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(161)
							.addComponent(lblAdminSection))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(149)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddRegulation, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnViewRegulation, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditRegulation, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddDepartment, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnViewDepartment, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditDepartment, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddEmployee, GroupLayout.PREFERRED_SIZE,  155,GroupLayout.PREFERRED_SIZE)
								.addComponent(btnViewEmployee, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditEmployee, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteEmployee, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnViewComments, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAdminSection)
					.addGap(29)
					.addComponent(btnAddRegulation, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnViewRegulation, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditRegulation, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAddDepartment, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnViewDepartment, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditDepartment, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAddEmployee, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnViewEmployee, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditEmployee, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDeleteEmployee, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnViewComments, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}

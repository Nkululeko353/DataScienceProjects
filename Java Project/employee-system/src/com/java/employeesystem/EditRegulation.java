package com.java.employeesystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class EditRegulation extends JFrame {
	static EditRegulation frame;
	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea;
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
					frame = new EditRegulation();
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
	public EditRegulation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lblrlType = new JLabel("RLType:");
		
		
		JLabel lbldetails = new JLabel("Details:");
		
		JLabel lblcreateDate = new JLabel("Paid:");
		
		JLabel lbldepartment_id = new JLabel("DepartmentID:");
		
		
		JButton btnEditRegulation = new JButton("Update Regulation");
		btnEditRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rlType=textField.getText();
				String details=textArea.getText();
				String screateDate=textField_2.getText();
				String format="dd/MM/yyyy";
				SimpleDateFormat sdf= new SimpleDateFormat(format);
				Date createdate = Date.valueOf(screateDate);
				try {
					createdate = (Date) sdf.parse(screateDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int department_id=Integer.parseInt(textField_3.getText());
				int complianceid=Integer.parseInt(textField_4.getText());
				Compliance c=new Compliance(complianceid,department_id,rlType,details,createdate);
				
				int status=ComplianceDao.update(c);
				
				if(status>0){
					JOptionPane.showMessageDialog(EditRegulation.this,"Regulation updated successfully!");
					AdminSection.main(new String[]{});
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(EditRegulation.this,"Sorry, Unable to edit Regulation!");
				}
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textArea = new JTextArea();
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSection.main(new String[]{});
				frame.dispose();
			}
		});
		
JLabel lblcomplianceid = new JLabel("ComplianceID:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JButton btnLoadRecord = new JButton("Load Record");
		btnLoadRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String scomplianceid=textField_4.getText();
				if(scomplianceid==null||scomplianceid.trim().equals("")){
					JOptionPane.showMessageDialog(EditRegulation.this,"Please enter ComplianceID first!");
				}else{
				int complianceid=Integer.parseInt(scomplianceid);
				Compliance c=ComplianceDao.getComplianceByid(complianceid);
				textField.setText(c.getRlType());
				textArea.setText(c.getDetails());
				textField_2.setText(String.valueOf(c.getCreateDate()));
				textField_3.setText(String.valueOf(c.getDepartment_id()));
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblrlType)
						.addComponent(lbldetails , GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcreateDate, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbldepartment_id, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblcomplianceid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
									.addComponent(btnLoadRecord)))
					                .addGap(16)
					.addContainerGap())
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lbldepartment_id)
						.addContainerGap(356, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblcreateDate, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
						.addContainerGap(383, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(128)
						.addComponent(btnEditRegulation, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnBack)
						.addContainerGap(78, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lbldetails, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(364, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(27)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(46, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblcomplianceid)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLoadRecord))
						.addGap(16)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblrlType)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbldetails)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblcreateDate)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbldepartment_id)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGap(7)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnEditRegulation, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBack))
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		}
	}
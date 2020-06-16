package com.java.employeesystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewDepartments extends JFrame {
	static ViewDepartments frame;
	public ViewDepartments() {
		//Code to view data in JTable
		List<Department> list=DepartmentDao.view();
		int size=list.size();
		
		String data[][]=new String[size][2];
		int row=0;
		for(Department d:list){
			data[row][0]=String.valueOf(d.getDepartment_id());
			data[row][1]=d.getDepartment_nm();
			row++;
		}
		String columnNames[]={"DepartmentID","DepartmentName"};
		
		JTable jt=new JTable(data,columnNames);
		JScrollPane sp=new JScrollPane(jt);
		add(sp);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewDepartments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
